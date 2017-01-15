package com.pong.model.leaderboard;

import java.io.Serializable;

/**
 * The LeaderboardEntry class models a entry in a leaderboard (high score),
 * comprising of the player name and score achieved.
 * 
 * @author LBEVAN
 */
public class LeaderboardEntry implements Serializable, Comparable<LeaderboardEntry> {

    // region data
    private String playerName;
    private Integer score;
    // endregion

    // region init
    /**
     * Constructor
     *
     * @param playerName
     * @param score
     */
    public LeaderboardEntry(String playerName, Integer score) {
        this.playerName = playerName;
        this.score = score;
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final LeaderboardEntry entryToCompare) {
        if(score > entryToCompare.getScore()) {
            return -1;
        } else if(score < entryToCompare.getScore()) {
            return 1;
        } else {
            // scores are the same so match on name
            int result = playerName.compareTo(entryToCompare.getPlayerName());
            if(result > 0) {
                return 1;
            } else if(result < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the playerName.
     *
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Retrieve the score.
     *
     * @return score
     */
    public Integer getScore() {
        return score;
    }
    // endregion
}
