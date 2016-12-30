package com.pong.model.eventhandler;

/**
 * EnvironmentBall events interface. Defines ball events that need to be handled,
 * called back from within the EnvironmentBall class.
 *
 * @author LBEVAN
 */
public interface EnvironmentBallEventHandler {

    /**
     * Event - the ball has entered a destruction zone
     */
    void onEnvironmentBallDestruction();
}
