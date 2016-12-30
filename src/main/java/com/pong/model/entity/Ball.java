package com.pong.model.entity;

import com.pong.gui.frame.PongFrame;
import com.pong.model.PongModel;
import com.pong.model.environment.EnvironmentBall;
import com.pong.model.eventhandler.BallEventHandler;
import com.pong.model.modifier.Modifier;
import com.pong.system.ResourceManager;
import com.pong.system.Sound;
import com.pong.system.SoundCommand;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * The Ball class represents a Ball entity in the game.
 * Each game tick it moves and checks collisions with other entities.
 *
 * @author LBEVAN
 */
public class Ball extends Entity {

    private final PongModel pongModel;

    private Entity owner;

    private double deltaX = -2;
    private double deltaY = -2;
    private double normalMoveSpeed = 3;

    private BallEventHandler ballEventHandler;

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

        // assign first owner to the computer (for now)
        this.owner = pongModel.getComputer();
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        move();

        checkCollisionWithPlayer();
        checkCollisionWithComputer();
        checkCollisionWithArena();
        checkCollisionWithModifier();
        checkCollisionWithEnvironmentalBall();
        checkScoreZone();

        modifierSystem.update(this);
    }

    /**
     * Move the ball, called every tick, by the delta (speed).
     */
    private void move() {
        x += deltaX * getSpeed();
        y += deltaY * getSpeed();
    }

    /**
     * Check if the Ball collides with the Player.
     * If so inverse the x delta (direction).
     */
    private void checkCollisionWithPlayer() {
        if(getBounds().intersects(pongModel.getPlayer().getBounds())) {
            owner = pongModel.getPlayer();

            // WIP
            final double yTop = pongModel.getPlayer().getY();
            final double yMiddle = yTop + (pongModel.getPlayer().getHeight() / 2);
            final double yBottom = yTop + pongModel.getPlayer().getHeight();

            double intersectPoint = yMiddle - y;
            System.out.println("Intersect Point: " + intersectPoint);
            // WIP

            // only reverse the delta is we are going towards the paddle.
            // this is here to fix a bug whereby the collision is detected multiple times
            // and the entity gets trapped in the paddle.
            if(deltaX < 0) {
                deltaX *= -1;
            }
        }
    }

    /**
     * Check if the Ball collides with the Computer.
     * If so inverse the x delta (direction).
     */
    private void checkCollisionWithComputer() {
        if(getBounds().intersects(pongModel.getComputer().getBounds())) {
            owner = pongModel.getComputer();
            // only reverse the delta is we are going towards the paddle.
            // this is here to fix a bug whereby the collision is detected multiple times
            // and the entity gets trapped in the paddle.
            if(deltaX > 0) {
                deltaX *= -1;
            }
        }
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
     * Check if the Ball is in a score zone.
     * If so, notify listeners of the score event.
     */
    private void checkScoreZone() {
        if(x <= 0 - width) {
            // player score zone, notify handler that the computer has scored
            onComputerScored();
            resetPosition();
        }

        if(x >= PongFrame.SCREEN_WIDTH + width) {
            // computer score zone, notify handler that the player has scored
            onPlayerScored();
            resetPosition();
        }
    }

    @Sound(soundKey = "BallDeath", soundCommand = SoundCommand.PLAY_SOUND)
    private void onComputerScored() {
        ballEventHandler.onComputerScored();
    }

    @Sound(soundKey = "BallDeath", soundCommand = SoundCommand.PLAY_SOUND)
    private void onPlayerScored() {
        ballEventHandler.onPlayerScored();
    }

    /**
     * Check if the ball has intersected a modifier.
     * If so add it to the owner entity.
     */
    private void checkCollisionWithModifier() {
        for(Modifier modifier: pongModel.getActiveModifiers()) {
            if(getBounds().intersects(modifier.getBounds())) {
                modifier.onHit();
                owner.addModifier(modifier);
            }
        }
    }

    /**
     * Check if the ball has intersected the environment ball.
     * If so callback to the model to handle the event.
     */
    private void checkCollisionWithEnvironmentalBall() {
        final EnvironmentBall environmentBall = pongModel.getEnvironmentBall();
        if(environmentBall!= null && getBounds().intersects(environmentBall.getBounds())) {
            ballEventHandler.onEnvironmentalBallCollision();
        }
    }

    /**
     * Reset the ball position back to the middle of the arena.
     */
    private void resetPosition() {
        x = PongFrame.SCREEN_WIDTH / 2 - width / 2;
        y = PongFrame.SCREEN_HEIGHT / 2 - height / 2;
        deltaY = new Random().nextBoolean() ? -2 : 2;
    }


    /**
     * Set the ball event handler.
     *
     * @param ballEventHandler
     */
    public void setBallEventHandler(BallEventHandler ballEventHandler) {
        this.ballEventHandler = ballEventHandler;
    }

    /**
     * {@inheritDoc}
     */
    public double getSpeed() {
        // the ball speed if affected by the environment (e.g. ice = quicker, desert = slower)
        return normalMoveSpeed * pongModel.getEnvironment().getSpeedModifier();
    }

    @Override
    public BufferedImage getImage() {
        // todo: change
        return ResourceManager.getInstance().getGraphic("Ball");
    }
}
