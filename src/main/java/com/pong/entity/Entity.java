package com.pong.entity;

import java.awt.*;

/**
 * Entity interface enforces a basic set of
 * behaviours each entity must have in the game.
 *
 * @author LBEVAN
 */
public interface Entity {

    /**
     * Retrieve the x coordinate of the entity.
     *
     * @return x
     */
    int getX();

    /**
     * Retrieve the y coordinate of the entity.
     *
     * @return y
     */
    int getY();

    /**
     * Retrieve the width of the entity.
     *
     * @return width
     */
    int getWidth();

    /**
     * Retrieve the height of the entity.
     *
     * @return height
     */
    int getHeight();

    /**
     * Render the entity.
     *
     * @param graphics
     */
    void render(Graphics graphics);

    /**
     * Update the entity (e.g. move).
     */
    void update();
}
