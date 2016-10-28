package com.pong.model.entity;

import com.pong.controller.input.Direction;
import com.pong.gui.frame.PongFrame;

/**
 * The Player class represents the player entity in the game.
 * It primarily handles the input mechanisms.
 *
 * @author LBEVAN
 */
public class Player extends Entity {

    public int deltaY = 0;
    public Direction direction;

    /**
     * Player constructor.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        // if within bounds move player
        if(direction == Direction.DOWN && y <= PongFrame.SCREEN_HEIGHT - height) {
            y += deltaY;
        } else if(direction == Direction.UP && y >= 0) {
            y += deltaY;
        }
    }

    /**
     * Action to move the player entity in a direction at the specified speed.
     *
     * @param deltaY
     * @param direction
     */
    public void move(int deltaY, Direction direction) {
        this.deltaY = deltaY;
        this.direction = direction;
    }
}
