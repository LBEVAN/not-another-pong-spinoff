package com.pong.model.entity;

import com.pong.model.modifier.Modifier;
import com.pong.model.modifier.ModifierSystem;
import com.pong.model.modifier.ModifierType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;


/**
 * Base entity class that defines default properties, behaviour
 * and interactions that each entity must have in the game.
 *
 * @author LBEVAN
 */
public abstract class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int baseSpeed = 0;
    protected int modifiedSpeed = 0;

    protected ModifierSystem modifierSystem;
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

        this.modifierSystem = new ModifierSystem();
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
    public int getBaseSpeed() {
        return baseSpeed;
    }

    /**
     * Set the baseSpeed of the entity.
     *
     * @param baseSpeed
     */
    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    /**
     * Retrieve the modified speed of the entity.
     *
     * return modifiedSpeed
     */
    public int getModifiedSpeed() {
        return modifiedSpeed;
    }

    /**
     * Set the modifiedSpeed of the entity.
     *
     * @param modifiedSpeed
     */
    public void setModifiedSpeed(int modifiedSpeed) {
        this.modifiedSpeed = modifiedSpeed;
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
     * Add a modifier to the entity.
     *
     * @param modifier
     */
    public void addModifier(final Modifier modifier) {
        modifierSystem.addModifier(modifier);
    }

    /**
     * Get the modifiers on this entity.
     */
    public Collection<Modifier> getModifiers() {
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
    public abstract int getSpeed();

    /**
     * Retrieve the image associated to the entity.
     *
     * @return bufferedImage
     */
    public abstract BufferedImage getImage();
}
