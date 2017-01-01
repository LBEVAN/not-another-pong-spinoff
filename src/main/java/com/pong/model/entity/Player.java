package com.pong.model.entity;

import com.pong.controller.input.Direction;
import com.pong.gui.frame.PongFrame;
import com.pong.system.resource.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * The Player class represents the player entity in the game.
 * It primarily handles the input mechanisms.
 *
 * @author LBEVAN
 */
public class Player extends Entity {

    private double deltaY = 0;
    private Direction direction;

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

        modifierSystem.update(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        return ResourceManager.getInstance().getGraphic("Paddle");
    }

    /**
     * Action to move the player entity in a direction at the specified speed.
     *
     * @param deltaY
     * @param direction
     */
    public void move(double deltaY, Direction direction) {
        this.deltaY = deltaY;
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    public double getSpeed() {
        if(baseSpeed == 0) {
            // base speed is zero, meaning there is no user input; return 0 to ensure no movement
            return 0;
        } else if(baseSpeed < 0) {
            // base speed is negative, so minus from base speed
            return baseSpeed - modifiedSpeed;
        } else {
            // base speed is positive, so add to base speed
            return baseSpeed + modifiedSpeed;
        }
    }
}
