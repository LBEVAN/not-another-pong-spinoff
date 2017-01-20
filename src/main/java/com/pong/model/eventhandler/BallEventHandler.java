package com.pong.model.eventhandler;

import com.pong.model.entity.player.PlayerId;

/**
 * Ball events interface. Defines ball events that need to be handled, called back from within the Ball class.
 *
 * @author LBEVAN
 */
public interface BallEventHandler {

    /**
     * Event - a player has scored.
     *
     * @param playerId
     */
    void onPlayerScored(PlayerId playerId);

    /**
     * Event - the ball has collided with the environmental ball.
     */
    void onEnvironmentalBallCollision();
}
