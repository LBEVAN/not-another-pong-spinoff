package com.pong.model.listener;

/**
 * The BallListener interfaces defines events that need to be handled for the Ball entity.
 *
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
