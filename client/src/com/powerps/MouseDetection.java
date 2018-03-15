package com.powerps;

final class MouseDetection implements Runnable {

    public final Object syncObject = new Object();
    public final int coordsY[] = new int[500];
    public final int coordsX[] = new int[500];
    public boolean running;
    public int coordsIndex;
    private Client clientInstance;
    public MouseDetection(Client client1) {
        running = true;
        clientInstance = client1;
    }

    public void run() {
        while (running) {
            synchronized (syncObject) {
                if (coordsIndex < 500) {
                    coordsX[coordsIndex] = clientInstance.mouseX;
                    coordsY[coordsIndex] = clientInstance.mouseY;
                    coordsIndex++;
                }
            }
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
    }
}
