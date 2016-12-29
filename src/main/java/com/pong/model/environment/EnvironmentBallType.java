package com.pong.model.environment;

import java.awt.*;

/**
 * Enum that defines types of environment ball types available in the game.
 * Each type provides specific properties needed for the ball.
 *
 * @author LBEVAN
 */
public enum EnvironmentBallType {

    SPACE(Color.BLACK, Environment.SPACE),
    ICE(Color.CYAN, Environment.ICE),
    DESERT(Color.YELLOW, Environment.DESERT);

    private final Color colour;
    private final Environment environment;

    /**
     * Constructor.
     *
     * @param colour
     * @param environment
     */
    EnvironmentBallType(Color colour, Environment environment) {
        this.colour = colour;
        this.environment = environment;
    }

    /**
     * Retrieve the colour associated to the ball type.
     *
     * @return colour
     */
    public Color getColour() {
        return colour;
    }

    /**
     * Retrieve the environment associated to the ball type.
     *
     * @return environment
     */
    public Environment getEnvironment() {
        return environment;
    }
}
