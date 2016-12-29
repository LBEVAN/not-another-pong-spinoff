package com.pong.model.environment;

import com.pong.system.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * Enum that defines types of environments available in the game.
 * Each environment provides specific game properties that act effect gameplay.
 *
 * @author LBEVAN
 */
public enum Environment {

    SPACE(1, ResourceManager.getInstance().getGraphic("Space")),
    ICE(1.4, ResourceManager.getInstance().getGraphic("Ice")),
    DESERT(0.8, ResourceManager.getInstance().getGraphic("Desert"));

    private final double speedModifier;
    private final BufferedImage image;

    /**
     * Constructor.
     */
    Environment(double speedModifier, BufferedImage image) {
        this.speedModifier = speedModifier;
        this.image = image;
    }

    /**
     * Retrieve the speedModifier for the environment.
     *
     * @return
     */
    public double getSpeedModifier() {
        return speedModifier;
    }

    /**
     * Retrieve the image for the environment.
     *
     * @return image
     */
    public BufferedImage getImage() {
        return image;
    }
}
