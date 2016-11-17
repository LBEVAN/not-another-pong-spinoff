package com.pong.model;

/**
 * GameOverModel represents the model for the GameOverView.
 *
 * @author LBEVAN
 */
public class GameOverModel implements Model {

    private final int playerScore;
    private final int computerScore;

    /**
     * Constructor.
     *
     * @param playerScore
     * @param computerScore
     */
    public GameOverModel(int playerScore, int computerScore) {
        this.playerScore = playerScore;
        this.computerScore = computerScore;
    }

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

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }
}
