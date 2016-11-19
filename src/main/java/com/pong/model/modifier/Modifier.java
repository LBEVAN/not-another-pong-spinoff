package com.pong.model.modifier;

import com.pong.model.entity.Entity;

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
     * Check whether the modification has been applied.
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
}
