package com.pong.model.entity;

import com.pong.gui.frame.PongFrame;
import com.pong.model.PongModel;
import com.pong.model.entity.player.PlayerId;
import com.pong.model.environment.EnvironmentBall;
import com.pong.model.eventhandler.BallEventHandler;
import com.pong.system.Constants;
import com.pong.system.resource.ResourceManager;
import com.pong.system.sound.Sound;
import com.pong.system.sound.SoundCommand;

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
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        move();

        checkCollisionWithPlayer(PlayerId.ONE);
        checkCollisionWithPlayer(PlayerId.TWO);
        checkCollisionWithArena();
        checkCollisionWithEnvironmentalBall();
        checkScoreZone();

        modifierSystem.update(this);
    }

    // region private API
    /**
     * Move the ball, called every tick, by the delta (speed).
     */
    private void move() {
        x += deltaX * getSpeed();
        y += deltaY * getSpeed();
    }

    /**
     * Check if the Ball collides with a Player.
     * If so inverse the x delta (direction).
     */
    private void checkCollisionWithPlayer(PlayerId playerId) {
        final Player player = pongModel.getPlayerById(playerId);
        if(getBounds().intersects(player.getBounds())) {

            // WIP
//            final double yTop = pongModel.getPlayer1().getY();
//            final double yMiddle = yTop + (pongModel.getPlayer1().getHeight() / 2);
//            final double yBottom = yTop + pongModel.getPlayer1().getHeight();
//
//            double intersectPoint = yMiddle - y;
//            System.out.println("Intersect Point: " + intersectPoint);
            // WIP

            deltaX *= -1;
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
            onPlayerScored(PlayerId.TWO);
            resetPosition();
        }

        if(x >= PongFrame.SCREEN_WIDTH + width) {
            // computer score zone, notify handler that the player has scored
            onPlayerScored(PlayerId.ONE);
            resetPosition();
        }
    }

    /**
     * Delegate event method for when a player scores.
     * This is used to play a sound when fired.
     *
     * @param playerId
     */
    @Sound(soundKey = Constants.BALL_DEATH_SOUND, soundCommand = SoundCommand.PLAY_SOUND)
    private void onPlayerScored(PlayerId playerId) {
        ballEventHandler.onPlayerScored(playerId);
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
    // endregion

    // region getters & setters
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
        return (normalMoveSpeed + modifiedSpeed) * pongModel.getEnvironment().getSpeedModifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        return ResourceManager.getInstance().getGraphic("Ball");
    }
    // endregion
}
