package com.pong.model.entity;

import com.pong.gui.frame.PongFrame;

/**
 * The Player class represents the player entity in the game.
 * It primarily handles the input mechanisms.
 *
 * @author LBEVAN
 */
public class Player extends Entity {

    private int speed = 10;

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
     * Move the Player up.
     */
    public void moveUp() {
        if(y >= 0) {
            y -= speed;
        }
    }

    /**
     * Move the Player down.
     */
    public void moveDown() {
        if(y <= PongFrame.SCREEN_HEIGHT - height) {
            y += speed;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        
    }
}
