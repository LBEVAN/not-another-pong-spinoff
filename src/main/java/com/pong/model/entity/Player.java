package com.pong.model.entity;

import com.pong.controller.input.Direction;
import com.pong.gui.frame.PongFrame;
import com.pong.model.modifier.HeightModifier;
import com.pong.model.modifier.ModifierSystem;

/**
 * The Player class represents the player entity in the game.
 * It primarily handles the input mechanisms.
 *
 * @author LBEVAN
 */
public class Player extends Entity {

    private int deltaY = 0;
    private Direction direction;
    private ModifierSystem modifierSystem;

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

        modifierSystem = new ModifierSystem();
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
