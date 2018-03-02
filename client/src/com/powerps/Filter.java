package com.powerps;


/**
 * An implementation of a reconfigurable filter that calculates
 * coefficients from pole magnitude/phases and a serial
 * configuration of cascading second-order iir filters.
 */
final class Filter {

	final int pairs[] = new int[2];
	private final int phases[][][] = new int[2][2][4];
	private final int magnitudes[][][] = new int[2][2][4];
	private final int unity[] = new int[2];
	private static final float minimisedCoefficients[][] = new float[2][8];
	static final int coefficients[][] = new int[2][8];
	private static float forwardMinimisedCoefficientMultiplier;
	static int forwardMultiplier;
	

	public Filter() {
	}
	
	/**
	 * Perform precise linear interpolation on the magnitude (where "precise" means that the result is guaranteed to be
	 * the first magnitude ({@code magnitudes[direction][1][pair]}) when the step is {@code 1}).
	 * 
	 * @param direction The direction, where {@code 0} is feedforward, and {@code 1} is feedback.
	 * @param pair The pair.
	 * @param step The step (the interpolation parameter).
	 * @return The interpolated magnitude.
	 */
	private float interpolateMagnitude(int direction, int pair, float step) {
		float magnitude = (float) magnitudes[direction][0][pair]
				+ step * (float) (magnitudes[direction][1][pair] - magnitudes[direction][0][pair]);
		magnitude *= 0.001525879F;
		return 1.0F - (float) Math.pow(10D, -magnitude / 20F);
	}

	private float normalise(float exponent) {
		float f1 = 32.7032F * (float) Math.pow(2D, exponent);
		return (f1 * 3.141593F) / 11025F;
	}

	/**
	 * Perform linear interpolation on the phase
	 * 
	 * @param direction The direction, where {@code 0} is feedforward, and {@code 1} is feedback.
	 * @param pair The pair.
	 * @param step The step (the interpolation parameter).
	 * @return The interpolated phase.
	 */
	private float interpolatePhase(float direction, int pair, int step) {
		float phase = (float) phases[step][0][pair]
				+ direction * (float) (phases[step][1][pair] - phases[step][0][pair]);
		phase *= 0.0001220703F;
		return normalise(phase);
	}

	public int compute(int direction, float step) {
		if (direction == 0) {
			float unity = (float) this.unity[0] + (float) (this.unity[1] - this.unity[0]) * step;
			unity *= 0.003051758F;
			forwardMinimisedCoefficientMultiplier = (float) Math.pow(0.10000000000000001D, unity / 20F);
			forwardMultiplier = (int) (forwardMinimisedCoefficientMultiplier * 65536F);
		}
		if (pairs[direction] == 0)
			return 0;
		float initialMagnitude = interpolateMagnitude(direction, 0, step);
		minimisedCoefficients[direction][0] = -2F * initialMagnitude * (float) Math.cos(interpolatePhase(step, 0, direction));
		minimisedCoefficients[direction][1] = initialMagnitude * initialMagnitude;
		for (int pair = 1; pair < pairs[direction]; pair++) {
			float magnitude = interpolateMagnitude(direction, pair, step);
			float f4 = -2F * magnitude * (float) Math.cos(interpolatePhase(step, pair, direction));
			float f5 = magnitude * magnitude;
			minimisedCoefficients[direction][pair * 2 + 1] = minimisedCoefficients[direction][pair * 2 - 1] * f5;
			minimisedCoefficients[direction][pair * 2] = minimisedCoefficients[direction][pair * 2 - 1] * f4
					+ minimisedCoefficients[direction][pair * 2 - 2] * f5;
			for (int j1 = pair * 2 - 1; j1 >= 2; j1--)
				minimisedCoefficients[direction][j1] += minimisedCoefficients[direction][j1 - 1] * f4 + minimisedCoefficients[direction][j1 - 2] * f5;

			minimisedCoefficients[direction][1] += minimisedCoefficients[direction][0] * f4 + f5;
			minimisedCoefficients[direction][0] += f4;
		}

		if (direction == 0) {
			for (int l = 0; l < pairs[0] * 2; l++)
				minimisedCoefficients[0][l] *= forwardMinimisedCoefficientMultiplier;

		}
		for (int i1 = 0; i1 < pairs[direction] * 2; i1++)
			coefficients[direction][i1] = (int) (minimisedCoefficients[direction][i1] * 65536F);

		return pairs[direction] * 2;
	}

	public void decode(Stream stream, Envelope soundEnveleope) {
		int i = stream.readUnsignedByte();
		pairs[0] = i >> 4;
		pairs[1] = i & 0xf;
		if (i != 0) {
			unity[0] = stream.readUnsignedWord();
			unity[1] = stream.readUnsignedWord();
			int j = stream.readUnsignedByte();
			for (int k = 0; k < 2; k++) {
				for (int l = 0; l < pairs[k]; l++) {
					phases[k][0][l] = stream.readUnsignedWord();
					magnitudes[k][0][l] = stream.readUnsignedWord();
				}

			}

			for (int i1 = 0; i1 < 2; i1++) {
				for (int j1 = 0; j1 < pairs[i1]; j1++)
					if ((j & 1 << i1 * 4 << j1) != 0) {
						phases[i1][1][j1] = stream.readUnsignedWord();
						magnitudes[i1][1][j1] = stream.readUnsignedWord();
					} else {
						phases[i1][1][j1] = phases[i1][0][j1];
						magnitudes[i1][1][j1] = magnitudes[i1][0][j1];
					}

			}

			if (j != 0 || unity[1] != unity[0])
				soundEnveleope.decodeSegments(stream);
		} else {
			unity[0] = unity[1] = 0;
		}
	}

}
