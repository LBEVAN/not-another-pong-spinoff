package com.pong.model;

import com.pong.model.leaderboard.Leaderboard;
import com.pong.model.leaderboard.LeaderboardEntry;

import java.util.List;

/**
 * GameOverModel represents the model for the GameOverView.
 *
 * @author LBEVAN
 */
public class GameOverModel implements Model {

    // region data
    private final int p1Score;
    private final String p1Name;
    private final int p2Score;
    private final String p2Name;

    private final Leaderboard leaderboard;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param p1Score
     * @param p2Name
     * @param p2Score
     * @param p2Name
     */
    public GameOverModel(int p1Score, String p1Name, int p2Score, String p2Name) {
        this.p1Score = p1Score;
        this.p2Score = p2Score;
        this.p1Name = p1Name;
        this.p2Name = p2Name;

        this.leaderboard = new Leaderboard();
    }
    // endregion

    // region public API
    /**
     * Derive the winner text from the game scores.
     *
     * @return winnerText
     */
    public String deriveWinnerText() {
        if(p1Score > p2Score) {
            return "You've won! Good job.";
        } else {
            return "The Computer won! Better luck next time.";
        }
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the p1 score.
     *
     * @return p1Score
     */
    public int getP1Score() {
        return p1Score;
    }

    /**
     * Retrieve the p2 score.
     *
     * @return p2Score
     */
    public int getP2Score() {
        return p2Score;
    }

    /**
     * Retrieve the p1 name.
     *
     * @return p1Name
     */
    public String getP1Name() {
        return p1Name;
    }

    /**
     * Retrieve the p2 name.
     *
     * @return p2Name
     */
    public String getP2Name() {
        return p2Name;
    }

    /**
     * Retrieve the game high scores.
     *
     * @return highscores
     */
    public List<LeaderboardEntry> getHighScores() {
        return leaderboard.getHighScores();
    }
    // endregion
}
