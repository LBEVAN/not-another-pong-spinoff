package com.pong.model;

import com.pong.model.leaderboard.Leaderboard;
import com.pong.model.leaderboard.LeaderboardEntry;

import java.util.List;

/**
 * LeaderboardModel represents the model for the leaderboard screen.
 * 
 * @author LBEVAN
 */
public class LeaderboardModel implements Model {

    // region data
    private Leaderboard leaderboard;
    // endregion

    // region init
    /**
     * Constructor.
     */
    public LeaderboardModel() {
        this.leaderboard = new Leaderboard();
    }
    // endregion

    // region public API
    /**
     * Retrieve the high scores from the leaderboard.
     *
     * @return List<LeaderboardEntry>
     */
    public List<LeaderboardEntry> getHighScores() {
        return leaderboard.getHighScores();
    }

    /**
     * Clear the leaderboard (remove all high scores).
     */
    public void clearLeaderboard() {
        leaderboard.clear();
    }
    // endregion
}
