package com.pong.model.environment;

import com.pong.gui.frame.PongFrame;
import com.pong.model.entity.Entity;

import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The EnvironmentBall class represents a EnvironmentBall entity in the game.
 * Each game tick it moves and checks collisions with the arena. If hit it will be destroyed and initiate
 * an event to change the game environment.
 *
 * @author LBEVAN
 */
public class EnvironmentBall extends Entity {

    private final EnvironmentBallType environmentBallType;

    private double deltaX = -1;
    private double deltaY = -1;

    /**
     * Constructor.
     *
     * @param environmentBallType the type of the ball
     */
    public EnvironmentBall(EnvironmentBallType environmentBallType) {
        super(
            randomXYPosition(PongFrame.SCREEN_WIDTH - 40 + 1),
            randomXYPosition(PongFrame.SCREEN_HEIGHT - 40 + 1),
            25,
            25
        );
        this.environmentBallType = environmentBallType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        checkCollisionWithArena();
        move();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        return 0.6;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        return null;
    }

    /**
     * Check if the Ball collides with the arena bounds.
     * If so inverse the x or y delta (direction).
     */
    private void checkCollisionWithArena() {
        // check if hit bottom or top (y-axis)
        if(y < 0 || y > PongFrame.SCREEN_HEIGHT -height) {
            deltaY *= -1;
        }
    }

    /**
     * Retrieve the environmentBallType
     *
     * @return environmentBallType
     */
    public EnvironmentBallType getEnvironmentBallType() {
        return environmentBallType;
    }

    /**
     * Produce a random x/y co-ordinate in the arena, taking into account in-operable space.
     *
     * @param bound
     * @return random co-ordinate
     */
    private static double randomXYPosition(final int bound) {
        return ThreadLocalRandom.current().nextDouble(30, bound);
    }

    /**
     * Move the ball, called every tick, by the delta (speed).
     */
    private void move() {
        x += deltaX * getSpeed();
        y += deltaY * getSpeed();
    }
}
