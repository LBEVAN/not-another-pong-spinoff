package com.pong.model.entity;

import com.pong.model.modifier.AbstractModifier;
import com.pong.model.modifier.Modifier;
import com.pong.model.modifier.ModifierSystem;
import com.pong.model.modifier.ModifierType;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Collection;


/**
 * Base entity class that defines default properties, behaviour
 * and interactions that each entity must have in the game.
 *
 * @author LBEVAN
 */
public abstract class Entity {

    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected double baseSpeed = 0;
    protected double modifiedSpeed = 0;

    protected ModifierSystem modifierSystem;
    /**
     * Entity constructor.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    protected Entity(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.modifierSystem = new ModifierSystem();
    }

    /**
     * Retrieve the x coordinate of the entity.
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Retrieve the y coordinate of the entity.
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Set the y coordinate of the entity.
     *
     * @param y
     */
    public void setY(double y) {
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
     * Set the height of the entity.
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Retrieve the baseSpeed of the entity.
     *
     * @return baseSpeed
     */
    public double getBaseSpeed() {
        return baseSpeed;
    }

    /**
     * Set the baseSpeed of the entity.
     *
     * @param baseSpeed
     */
    public void setBaseSpeed(double baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    /**
     * Retrieve the modified speed of the entity.
     *
     * return modifiedSpeed
     */
    public double getModifiedSpeed() {
        return modifiedSpeed;
    }

    /**
     * Set the modifiedSpeed of the entity.
     *
     * @param modifiedSpeed
     */
    public void setModifiedSpeed(double modifiedSpeed) {
        this.modifiedSpeed = modifiedSpeed;
    }

    /**
     * Get the bounds of the entity in the form of a {@link Rectangle2D}
     *
     * @return bounds
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, width, height).getBounds2D();
    }

    /**
     * Add a modifier to the entity.
     *
     * @param modifier
     */
    public void addModifier(final AbstractModifier modifier) {
        modifierSystem.addModifier(modifier);
    }

    /**
     * Get the modifiers on this entity.
     */
    public Collection<AbstractModifier> getModifiers() {
        return modifierSystem.getModifiers();
    }

    /**
     * Update the entity (e.g. move).
     */
    public abstract void update();

    /**
     * Retrieve the speed of the entity.
     *
     * @return speed
     */
    public abstract double getSpeed();

    /**
     * Retrieve the image associated to the entity.
     *
     * @return bufferedImage
     */
    public abstract BufferedImage getImage();
}
