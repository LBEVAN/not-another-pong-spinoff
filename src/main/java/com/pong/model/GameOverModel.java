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
    private final int playerScore;
    private final int computerScore;

    private final Leaderboard leaderboard;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param playerScore
     * @param computerScore
     */
    public GameOverModel(int playerScore, int computerScore) {
        this.playerScore = playerScore;
        this.computerScore = computerScore;

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
        if(playerScore > computerScore) {
            return "You've won! Good job.";
        } else {
            return "The Computer won! Better luck next time.";
        }
    }
    // endregion

    // region getters & setters
    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public List<LeaderboardEntry> getHighScores() {
        return leaderboard.getHighScores();
    }
    // endregion
}
