package com.pong.listener;

/**
 * @author LBEVAN
 */
public interface BallListener {

    /**
     * Event - the player has scored.
     */
    void playerScored();

    /**
     * Event - the computer has scored.
     */
    void computerScored();
}
