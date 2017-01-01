package com.pong.model.environment;

import com.pong.gui.frame.PongFrame;
import com.pong.model.entity.Entity;
import com.pong.model.eventhandler.EnvironmentBallEventHandler;

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

    private double deltaX;
    private double deltaY;

    private EnvironmentBallEventHandler eventHandler;

    /**
     * Constructor.
     *
     * @param environmentBallType the type of the ball
     * @param eventHandler
     */
    public EnvironmentBall(EnvironmentBallType environmentBallType, EnvironmentBallEventHandler eventHandler) {
        super(PongFrame.SCREEN_WIDTH / 2, randomYPosition(30, PongFrame.SCREEN_HEIGHT - 40 + 1), 25, 25);

        this.environmentBallType = environmentBallType;
        this.eventHandler = eventHandler;

        deltaX = ThreadLocalRandom.current().nextBoolean() ? -1 : 1;
        deltaY = ThreadLocalRandom.current().nextBoolean() ? -1 : 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        checkCollisionWithArena();
        checkDestructionZone();

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
        return environmentBallType.getImage();
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
     * Check if the Ball is in a destruction zone
     * If so, notify the event handler.
     */
    private void checkDestructionZone() {
        if(x <= 0 - width) {
            eventHandler.onEnvironmentBallDestruction();
        }

        if(x >= PongFrame.SCREEN_WIDTH + width) {
            eventHandler.onEnvironmentBallDestruction();
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
     * Set the event handler.
     *
     * @param eventHandler
     */
    public void setEventHandler(EnvironmentBallEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    /**
     * Produce a random y co-ordinate in the area, taking into account the in-operable space
     * at the top and bottom of the arena.
     *
     * @return y co-ordinate
     */
    private static double randomYPosition(final int origin, final int bound) {
        return ThreadLocalRandom.current().nextDouble(origin, bound);
    }

    /**
     * Move the ball, called every tick, by the delta (speed).
     */
    private void move() {
        x += deltaX * getSpeed();
        y += deltaY * getSpeed();
    }
}
