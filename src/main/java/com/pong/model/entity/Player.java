package com.pong.model.entity;

import com.pong.model.entity.component.InputComponent;
import com.pong.system.resource.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * The Player class represents the player entity in the game.
 * It primarily handles the input mechanisms.
 *
 * @author LBEVAN
 */
public class Player extends Entity {

    // region data
    private InputComponent<Player> inputComponent;
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
        inputComponent.update(this);
        modifierSystem.update(this);
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

    public InputComponent<Player> getInputComponent() {
        return inputComponent;
    }

    public void setInputComponent(InputComponent<Player> inputComponent) {
        this.inputComponent = inputComponent;
    }
    // endregion
}
