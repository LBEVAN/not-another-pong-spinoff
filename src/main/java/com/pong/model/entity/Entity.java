package com.pong.model.entity;

import javax.swing.*;
import java.awt.*;

/**
 * Base entity class that defines default properties, behaviour
 * and interactions that each entity must have in the game.
 *
 * @author LBEVAN
 */
public abstract class Entity extends JComponent {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    /**
     * Entity constructor.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    protected Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Retrieve the x coordinate of the entity.
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieve the y coordinate of the entity.
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y coordinate of the entity.
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retrieve the width of the entity.
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retrieve the height of the entity.
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the bounds of the entity in the form of a {@link Rectangle}
     *
     * @return bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Update the entity (e.g. move).
     */
    public abstract void update();
}