package com.pong.model.entity;

import com.pong.controller.input.Direction;
import com.pong.gui.frame.PongFrame;
import com.pong.model.eventhandler.InputEventHandler;
import com.pong.model.input.PlayerInput;
import com.pong.model.modifier.AbstractModifier;
import com.pong.model.modifier.ModifierType;
import com.pong.system.resource.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * The Player class represents the player entity in the game.
 * It primarily handles the input mechanisms.
 *
 * @author LBEVAN
 */
public class Player extends Entity implements InputEventHandler {

    // region data
    private PlayerInput playerInput;

    private double deltaY = 0;
    private Direction direction;
    // endregion

    // region init
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
    // endregion

    // region public API
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
    public void move(double deltaY, Direction direction) {
        this.deltaY = deltaY;
        this.direction = direction;
    }
    // endregion

    // region events
    /**
     * {@inheritDoc}
     */
    public void onMoveAction(int deltaY, Direction direction) {
        // set the base speed to the deltaY (i.e. moving or not moving)
        setBaseSpeed(deltaY);
        // get the move speed from the player itself as it needs to calculate base and modified speed
        move(getSpeed(), direction);
    }

    /**
     * {@inheritDoc}
     */
    public void onConsumeModifierAction(AbstractModifier modifier) {
        addModifier(modifier);
    }
    // endregion

    // region getters & setters
    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        return ResourceManager.getInstance().getGraphic("Paddle");
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

    /**
     * Set the PlayerInput component.
     *
     * @param playerInput
     */
    public void setPlayerInput(PlayerInput playerInput) {
        this.playerInput = playerInput;
    }

    /**
     * Get the number of charges remaining for a given modifier type.
     *
     * @return numCharges
     */
    public int getNumChargesForModifierType(ModifierType modifierType) {
        return modifierSystem.getNumChargesForModifierType(modifierType);
    }

    public boolean isMaxNumChargesForModifierType(ModifierType modifierType) {
        return modifierSystem.isMaxNumChargesForModifierType(modifierType);
    }
    // endregion
}
