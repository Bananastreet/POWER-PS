package com.powerps;

import java.awt.*;

final class RSFrame extends java.awt.Frame {

    private final RSApplet rsApplet;
    public Toolkit toolkit;
    public Dimension screenSize;
    public int screenWidth;
    public int screenHeight;

    public RSFrame(RSApplet rsapplet, int width, int height, boolean undecorative, boolean resizable) {
        toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        setFocusTraversalKeysEnabled(false);
        screenHeight = (int) screenSize.getHeight();
        rsApplet = rsapplet;
        setTitle("Power-Ps");
        setUndecorated(undecorative);
        setResizable(resizable);
        setVisible(true);
        Insets insets = getInsets();
        setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
        setLocation((screenWidth - width) / 2, (screenHeight - height) / 2);
        requestFocus();
        toFront();
    }

    public RSFrame(RSApplet rsapplet, int width, int height) {
        this(rsapplet, width, height, false, false);
    }

    public int getFrameWidth() {
        Insets insets = getInsets();
        return getWidth() - (insets.left + insets.right);
    }

    public int getFrameHeight() {
        Insets insets = getInsets();
        return getHeight() - (insets.top + insets.bottom);
    }

    public Graphics getGraphics() {
        Graphics g = super.getGraphics();
        Insets insets = getInsets();
        g.translate(insets.left, insets.top);
        return g;
    }

    public void update(Graphics g) {
        rsApplet.update(g);
    }

    public void paint(Graphics g) {
        rsApplet.paint(g);
    }
}
