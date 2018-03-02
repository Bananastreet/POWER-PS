package com.powerps;


final class Synthesizer {

	public static void init() {
		NOISE = new int[32768];
		for (int i = 0; i < 32768; i++)
			if (Math.random() > 0.5D)
				NOISE[i] = 1;
			else
				NOISE[i] = -1;

		SINE = new int[32768];
		for (int j = 0; j < 32768; j++)
			SINE[j] = (int) (Math.sin((double) j / 5215.1903000000002D) * 16384D);

		samples = new int[0x35d54];
	}

	public int[] synthesize(int sampleCount, int duration) {
		for (int k = 0; k < sampleCount; k++)
			samples[k] = 0;

		if (duration < 10)
			return samples;
		double d = (double) sampleCount / ((double) duration + 0.0D);
		pitch.resetValues();
		volume.resetValues();
		int pitchMultiplierStep = 0;
		int pitchModifierBaseStep = 0;
		int pitchModifierPhase = 0;
		if (pitchModifier != null) {
			pitchModifier.resetValues();
			pitchModifierAmplitude.resetValues();
			pitchMultiplierStep = (int) (((double) (pitchModifier.end - pitchModifier.start) * 32.768000000000001D) / d);
			pitchModifierBaseStep = (int) (((double) pitchModifier.start * 32.768000000000001D) / d);
		}
		int volumeMultiplierStep = 0;
		int volumeMultiplierBaseStep = 0;
		int volumeMultiplierPhase = 0;
		if (volumeMultiplier != null) {
			volumeMultiplier.resetValues();
			volumeMultiplierAmplitude.resetValues();
			volumeMultiplierStep = (int) (((double) (volumeMultiplier.end - volumeMultiplier.start) * 32.768000000000001D) / d);
			volumeMultiplierBaseStep = (int) (((double) volumeMultiplier.start * 32.768000000000001D) / d);
		}
		for (int j2 = 0; j2 < 5; j2++)
			if (oscillatorVolume[j2] != 0) {
				phases[j2] = 0;
				delays[j2] = (int) ((double) anIntArray108[j2] * d);
				volumeSteps[j2] = (oscillatorVolume[j2] << 14) / 100;
				pitchSteps[j2] = (int) (((double) (pitch.end - pitch.start) * 32.768000000000001D
						* Math.pow(1.0057929410678534D, anIntArray107[j2])) / d);
				pitchBaseSteps[j2] = (int) (((double) pitch.start * 32.768000000000001D) / d);
			}

		for (int sample = 0; sample < sampleCount; sample++) {
			int pitchChange = pitch.step(sampleCount);
			int volumeChange = volume.step(sampleCount);
			if (pitchModifier != null) {
				int j5 = pitchModifier.step(sampleCount);
				int j6 = pitchModifierAmplitude.step(sampleCount);
				pitchChange += evaluateWave(j6, pitchModifierPhase, pitchModifier.form) >> 1;
				pitchModifierPhase += (j5 * pitchMultiplierStep >> 16) + pitchModifierBaseStep;
			}
			if (volumeMultiplier != null) {
				int k5 = volumeMultiplier.step(sampleCount);
				int k6 = volumeMultiplierAmplitude.step(sampleCount);
				volumeChange = volumeChange * ((evaluateWave(k6, volumeMultiplierPhase, volumeMultiplier.form) >> 1) + 32768) >> 15;
				volumeMultiplierPhase += (k5 * volumeMultiplierStep >> 16) + volumeMultiplierBaseStep;
			}
			for (int l5 = 0; l5 < 5; l5++) {
				if (oscillatorVolume[l5] == 0)
					continue;
				int l6 = sample + delays[l5];
				if (l6 < sampleCount) {
					samples[l6] += evaluateWave(volumeChange * volumeSteps[l5] >> 15, phases[l5],
							pitch.form);
					phases[l5] += (pitchChange * pitchSteps[l5] >> 16) + pitchBaseSteps[l5];
				}
			}

		}

		if (release != null) {
			release.resetValues();
			attack.resetValues();
			int counter = 0;
			boolean muted = true;
			for (int i7 = 0; i7 < sampleCount; i7++) {
				int on = release.step(sampleCount);
				int off = attack.step(sampleCount);
				int threshold;
				if (muted)
					threshold = release.start + ((release.end - release.start) * on >> 8);
				else
					threshold = release.start + ((release.end - release.start) * off >> 8);
				if ((counter += 256) >= threshold) {
					counter = 0;
					muted = !muted;
				}
				if (muted)
					samples[i7] = 0;
			}

		}
		if (delayTime > 0 && delayDecay > 0) {
			int delay = (int) ((double) delayTime * d);
			for (int l4 = delay; l4 < sampleCount; l4++)
				samples[l4] += (samples[l4 - delay] * delayDecay) / 100;

		}
		if (filter.pairs[0] > 0 || filter.pairs[1] > 0) {
			filterEnvelope.resetValues();
			int change = filterEnvelope.step(sampleCount + 1);
			int forwardOrder = filter.compute(0, (float) change / 65536F);
			int backOrder = filter.compute(1, (float) change / 65536F);
			if (sampleCount >= forwardOrder + backOrder) {
				int j7 = 0;
				int delay = backOrder;
				if (delay > sampleCount - forwardOrder)
					delay = sampleCount - forwardOrder;
				for (; j7 < delay; j7++) {
					int sample = (int) ((long) samples[j7 + forwardOrder] * (long) Filter.forwardMultiplier >> 16);
					for (int k8 = 0; k8 < forwardOrder; k8++)
						sample += (int) ((long) samples[(j7 + forwardOrder) - 1 - k8]
								* (long) Filter.coefficients[0][k8] >> 16);

					for (int j9 = 0; j9 < j7; j9++)
						sample -= (int) ((long) samples[j7 - 1 - j9]
								* (long) Filter.coefficients[1][j9] >> 16);

					samples[j7] = sample;
					change = filterEnvelope.step(sampleCount + 1);
				}

				char c = '\200';
				delay = c;
				do {
					if (delay > sampleCount - forwardOrder)
						delay = sampleCount - forwardOrder;
					for (; j7 < delay; j7++) {
						int l8 = (int) ((long) samples[j7 + forwardOrder] * (long) Filter.forwardMultiplier >> 16);
						for (int k9 = 0; k9 < forwardOrder; k9++)
							l8 += (int) ((long) samples[(j7 + forwardOrder) - 1 - k9]
									* (long) Filter.coefficients[0][k9] >> 16);

						for (int i10 = 0; i10 < backOrder; i10++)
							l8 -= (int) ((long) samples[j7 - 1 - i10]
									* (long) Filter.coefficients[1][i10] >> 16);

						samples[j7] = l8;
						change = filterEnvelope.step(sampleCount + 1);
					}

					if (j7 >= sampleCount - forwardOrder)
						break;
					forwardOrder = filter.compute(0, (float) change / 65536F);
					backOrder = filter.compute(1, (float) change / 65536F);
					delay += c;
				} while (true);
				for (; j7 < sampleCount; j7++) {
					int sample = 0;
					for (int l9 = (j7 + forwardOrder) - sampleCount; l9 < forwardOrder; l9++)
						sample += (int) ((long) samples[(j7 + forwardOrder) - 1 - l9]
								* (long) Filter.coefficients[0][l9] >> 16);

					for (int j10 = 0; j10 < backOrder; j10++)
						sample -= (int) ((long) samples[j7 - 1 - j10]
								* (long) Filter.coefficients[1][j10] >> 16);

					samples[j7] = sample;
				}

			}
		}
		for (int sample = 0; sample < sampleCount; sample++) {
			if (samples[sample] < -32768)
				samples[sample] = -32768;
			if (samples[sample] > 32767)
				samples[sample] = 32767;
		}

		return samples;
	}

	private int evaluateWave(int i, int k, int l) {
		if (l == 1)
			if ((k & 0x7fff) < 16384)
				return i;
			else
				return -i;
		if (l == 2)
			return SINE[k & 0x7fff] * i >> 14;
		if (l == 3)
			return ((k & 0x7fff) * i >> 14) - i;
		if (l == 4)
			return NOISE[k / 2607 & 0x7fff] * i;
		else
			return 0;
	}

	public void decode(Stream stream) {
		pitch = new Envelope();
		pitch.decode(stream);
		volume = new Envelope();
		volume.decode(stream);
		int option = stream.readUnsignedByte();
		if (option != 0) {
			stream.currentOffset--;
			pitchModifier = new Envelope();
			pitchModifier.decode(stream);
			pitchModifierAmplitude = new Envelope();
			pitchModifierAmplitude.decode(stream);
		}
		option = stream.readUnsignedByte();
		if (option != 0) {
			stream.currentOffset--;
			volumeMultiplier = new Envelope();
			volumeMultiplier.decode(stream);
			volumeMultiplierAmplitude = new Envelope();
			volumeMultiplierAmplitude.decode(stream);
		}
		option = stream.readUnsignedByte();
		if (option != 0) {
			stream.currentOffset--;
			release = new Envelope();
			release.decode(stream);
			attack = new Envelope();
			attack.decode(stream);
		}
		int j = 0;
		do {
			if (j >= 10)
				break;
			int k = stream.readUSmart();
			if (k == 0)
				break;
			oscillatorVolume[j] = k;
			anIntArray107[j] = stream.readSmart();
			anIntArray108[j] = stream.readUSmart();
			j++;
		} while (true);
		delayTime = stream.readUSmart();
		delayDecay = stream.readUSmart();
		duration = stream.readUnsignedWord();
		offset = stream.readUnsignedWord();
		filter = new Filter();
		filterEnvelope = new Envelope();
		filter.decode(stream, filterEnvelope);
	}

	public Synthesizer() {
		delayDecay = 100;
		duration = 500;
	}

	private Envelope pitch;
	private Envelope volume;
	private Envelope pitchModifier;
	private Envelope pitchModifierAmplitude;
	private Envelope volumeMultiplier;
	private Envelope volumeMultiplierAmplitude;
	private Envelope release;
	private Envelope attack;
	private final int oscillatorVolume[] = new int[5];
	private final int anIntArray107[] = new int[5];
	private final int anIntArray108[] = new int[5];
	private int delayTime;
	private int delayDecay;
	private Filter filter;
	private Envelope filterEnvelope;
	int duration;
	int offset;
	private static int samples[];
	private static int NOISE[];
	private static int SINE[];
	private static final int phases[] = new int[5];
	private static final int delays[] = new int[5];
	private static final int volumeSteps[] = new int[5];
	private static final int pitchSteps[] = new int[5];
	private static final int pitchBaseSteps[] = new int[5];

}
