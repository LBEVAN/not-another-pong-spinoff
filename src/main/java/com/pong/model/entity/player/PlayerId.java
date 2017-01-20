package com.pong.model.entity.player;

/**
 * Defines the playerId's valid in the game.
 *
 * @author LBEVAN
 */
public enum PlayerId {
    ONE, TWO;

    public static PlayerId getOther(PlayerId playerId) {
        return playerId == ONE ? TWO : ONE;
    }
}
