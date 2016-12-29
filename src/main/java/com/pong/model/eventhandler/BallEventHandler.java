package com.pong.model.eventhandler;

/**
 * Ball events interface. Defines ball events that need to be handled, called back from within the Ball class.
 *
 * @author LBEVAN
 */
public interface BallEventHandler {

    /**
     * Event - the player has scored.
     */
    void onPlayerScored();

    /**
     * Event - the computer has scored.
     */
    void onComputerScored();

    /**
     * Event - the ball has collided with the environmental ball.
     */
    void onEnvironmentalBallCollision();
}
