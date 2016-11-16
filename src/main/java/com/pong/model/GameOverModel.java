package com.pong.model;

/**
 * @author LBEVAN
 */
public class GameOverModel implements Model {

    private final int playerScore;
    private final int computerScore;

    public GameOverModel() {
        playerScore = 0;
        computerScore = 0;
    }

    public GameOverModel(int playerScore, int computerScore) {
        this.playerScore = playerScore;
        this.computerScore = computerScore;
    }

    public String deriveWinnerText() {
        if(playerScore > computerScore) {
            return "You've won! Good job.";
        } else {
            return "The Computer won! Better luck next time.";
        }
    }
}
