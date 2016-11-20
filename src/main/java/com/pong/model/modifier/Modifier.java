package com.pong.model.modifier;

import com.pong.model.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Modifier defines behaviour all modifiers must have.
 * Modifiers can also be known as Power Ups or Power Downs.
 * Modifiers act on entities in the game such as a Player or a Ball, modifying its properties (e.g. height).
 *
 * @author LBEVAN
 */
public interface Modifier<E extends Entity> {

    /**
     * Apply the modification to the specified entity.
     *
     * @param entity
     */
    void apply(final E entity);

    /**
     * Check whether the modification has been applied to an entity.
     *
     * @return hasApplied
     */
    boolean hasApplied();

    /**
     * Check whether the modification has expired.
     *
     * @return hasExpired
     */
    boolean hasExpired();

    /**
     * Remove the modification on the specified entity.
     *
     * @param entity
     */
    void remove(final E entity);

    /**
     * Retrieve the image associated to the modifier.
     *
     * @return bufferedImage
     */
    BufferedImage getImage();

    /**
     * Retrieve the modifier value.
     *
     * @return value
     */
    int getValue();

    /**
     * Retrieve the x co-ordinate of the modifier in the game  world.
     *
     * @return x
     */
    int getX();

    /**
     * Retrieve the y co-ordinate of the modifier in the game world.
     *
     * @return y
     */
    int getY();

    /**
     * Retrieve the width of the modifier.
     *
     * @return width
     */
    int getWidth();

    /**
     * Retrieve the height of the modifier.
     *
     * @return height
     */
    int getHeight();

    /**
     * Perform actions on the modifier when it is collided with.
     */
    void onHit();

    /**
     * Retrieve whether the modifier is active in the game world.
     *
     * @return isActive
     */
    boolean isActive();

    /**
     * Retrieve the bounds for the modifier in the form of a Rectangle.
     *
     * @return bounds
     */
    default Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
