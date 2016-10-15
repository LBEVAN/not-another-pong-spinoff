package com.pong.entity;

import java.awt.*;

/**
 * Paddle class represents a paddle entity in the game.
 *
 * @author LBEVAN
 */
public class Paddle implements Entity {

    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Paddle constructor.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics graphics) {
        graphics.drawRect(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    public void update() {

    }

    /**
     * {@inheritDoc}
     */
    public int getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public int getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    public int getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    public int getHeight() {
        return height;
    }
}
