package com.powerps;

final class Envelope {

    public static int anInt546;
    int start;
    int end;
    int form;
    private int segments;
    private int durations[];
    private int peaks[];
    private int threshold;
    private int segmentIndex;
    private int step;
    private int amplitude;
    private int ticks;
    public Envelope() {
    }

    public void decode(Stream stream) {
        form = stream.readUnsignedByte();
        start = stream.readDWord();
        end = stream.readDWord();
        decodeSegments(stream);
    }

    public void decodeSegments(Stream stream) {
        segments = stream.readUnsignedByte();
        durations = new int[segments];
        peaks = new int[segments];
        for (int i = 0; i < segments; i++) {
            durations[i] = stream.readUnsignedWord();
            peaks[i] = stream.readUnsignedWord();
        }

    }

    void resetValues() {
        threshold = 0;
        segmentIndex = 0;
        step = 0;
        amplitude = 0;
        ticks = 0;
    }

    int step(int period) {
        if (ticks >= threshold) {
            amplitude = peaks[segmentIndex++] << 15;
            if (segmentIndex >= segments)
                segmentIndex = segments - 1;
            threshold = (int) (((double) durations[segmentIndex] / 65536D) * (double) period);
            if (threshold > ticks)
                step = ((peaks[segmentIndex] << 15) - amplitude) / (threshold - ticks);
        }
        amplitude += step;
        ticks++;
        return amplitude - step >> 15;
    }
}
