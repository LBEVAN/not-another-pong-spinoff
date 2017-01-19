package com.pong.model.scoring;

import com.pong.model.entity.player.PlayerId;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LBEVAN
 */
public class ScoreManager {

    // region data
    private Map<PlayerId, Integer> scores = new HashMap<>();
    // endregion

    // region init
    /**
     * Constructor.
     */
    public ScoreManager() {
        for(PlayerId playerId : PlayerId.values()) {
            scores.put(playerId, 0);
        }
    }
    // endregion

    // region public API
    /**
     * Increase the score for the player by the specified amount.
     *
     * @param playerId
     * @param scoreToAdd
     */
    public void increaseScore(PlayerId playerId, Integer scoreToAdd) {
        scores.put(playerId, scores.get(playerId) + scoreToAdd);
    }

    /**
     * Retrieve the score for a given player.
     *
     * @param playerId
     * @return score
     */
    public Integer getScore(PlayerId playerId) {
        return scores.get(playerId);
    }

    /**
     * Retrieve the scores for all players.
     *
     * @return scores
     */
    public Map<PlayerId, Integer> getScores() {
        return scores;
    }
    // endregion
}
