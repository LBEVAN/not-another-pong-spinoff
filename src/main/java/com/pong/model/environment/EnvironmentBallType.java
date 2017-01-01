package com.pong.model.environment;

import com.pong.system.Constants;
import com.pong.system.resource.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * Enum that defines types of environment ball types available in the game.
 * Each type provides specific properties needed for the ball.
 *
 * @author LBEVAN
 */
public enum EnvironmentBallType {

    SPACE(Environment.SPACE, ResourceManager.getInstance().getGraphic(Constants.SPACE_ENVIRONMENT_ICON), Constants.SPACE_ENVIRONMENT_SWITCH_SOUND),
    ICE(Environment.ICE, ResourceManager.getInstance().getGraphic(Constants.ICE_ENVIRONMENT_ICON), Constants.ICE_ENVIRONMENT_SWITCH_SOUND),
    DESERT(Environment.DESERT, ResourceManager.getInstance().getGraphic(Constants.DESERT_ENVIRONMENT_ICON), Constants.DESERT_ENVIRONMENT_SWITCH_SOUND);

    private final Environment environment;
    private final BufferedImage image;
    private final String soundKey;

    /**
     * Constructor.
     *
     * @param environment
     * @param image
     * @param soundKey
     */
    EnvironmentBallType(Environment environment, BufferedImage image, String soundKey) {
        this.environment = environment;
        this.image = image;
        this.soundKey = soundKey;
    }

    /**
     * Retrieve the environment associated to the ball type.
     *
     * @return environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Retrieve the image for the environment.
     *
     * @return image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Retrieve the soundKey for the environment.
     *
     * @return soundKey
     */
    public String getSoundKey() {
        return soundKey;
    }
}
