package com.pong.model.entity;

import com.pong.gui.frame.PongFrame;
import com.pong.listener.BallListener;
import com.pong.model.PongModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The Ball class represents a Ball entity in the game.
 * Each game tick it moves and checks collisions with other entities.
 *
 * @author LBEVAN
 */
public class Ball extends Entity {

    private final PongModel pongModel;

    private int deltaX = 4;
    private int deltaY = 4;

    private List<BallListener> listeners = new ArrayList<BallListener>();

    /**
     * Constructor.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param pongModel
     */
    public Ball(int x, int y, int width, int height, final PongModel pongModel) {
        super(x, y, width, height);
        this.pongModel = pongModel;
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        move();

        checkCollisionWithPlayer();
        checkCollisionWithComputer();
        checkCollisionWithArena();
        checkScoreZone();
    }

    /**
     * Move the ball, called every tick, by the delta (speed).
     */
    private void move() {
        x += deltaX;
        y += deltaY;
    }

    /**
     * Check if the Ball collides with the Player.
     * If so inverse the x delta (direction).
     */
    private void checkCollisionWithPlayer() {
        if(doesIntersect(pongModel.getPlayer())) {
            deltaX = -deltaX;
        }
    }

    /**
     * Check if the Ball collides with the Computer.
     * If so inverse the x delta (direction).
     */
    private void checkCollisionWithComputer() {
        if(doesIntersect(pongModel.getComputer())) {
            deltaX = -deltaX;
        }
    }

    /**
     * Check if the Ball collides with the arena bounds.
     * If so inverse the x or y delta (direction).
     */
    private void checkCollisionWithArena() {
        // check if hit bottom or top (y-axis)
        if(y < 0) {
            deltaY = Math.abs(deltaY);
        } else if(y > PongFrame.SCREEN_HEIGHT -height) {
            deltaY = -deltaY;
        }
    }

    /**
     * Check if the Ball is in a score zone.
     * If so, notify listeners of the score event.
     */
    private void checkScoreZone() {
        if(x <= 0 - width) {
            // player score zone, notify listeners that the computer has scored
            for (BallListener listener : listeners) {
                listener.computerScored();
            }
            resetPosition();
        }

        if(x >= PongFrame.SCREEN_WIDTH + width) {
            // computer score zone, notify listeners that the player has scored
            for (BallListener listener : listeners) {
                listener.playerScored();
            }
            resetPosition();
        }
    }

    /**
     * Check if this entity intersects another entity.
     *
     * @param entity
     * @return boolean
     */
    private boolean doesIntersect(final Entity entity) {
        if (entity.getWidth() <= 0 || entity.getHeight() <= 0) {
            return false;
        }
        return (entity.getX() + entity.getWidth() > this.getX() &&
                entity.getY() + entity.getHeight() > this.getY() &&
                entity.getX() < this.getX() + getWidth() &&
                entity.getY() < this.getY() + getHeight());
    }

    /**
     * Reset the ball position back to the middle of the arena.
     */
    private void resetPosition() {
        x = PongFrame.SCREEN_WIDTH / 2 - width / 2;
        y = PongFrame.SCREEN_HEIGHT / 2 - height / 2;
    }

    /**
     * {@inheritDoc}
     */
    public void addListener(BallListener listener) {
        listeners.add(listener);
    }
}
