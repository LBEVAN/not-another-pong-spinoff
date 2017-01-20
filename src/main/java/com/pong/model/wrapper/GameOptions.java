package com.pong.model.wrapper;

import com.pong.ai.difficulty.Difficulty;

/**
 * @author LBEVAN
 */
public class GameOptions {

    // region data
    private Difficulty difficulty;
    private String playerName;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param difficulty
     */
    public GameOptions(Difficulty difficulty, String playerName) {
        this.difficulty = difficulty;
        this.playerName = playerName;
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the difficulty.
     *
     * @return difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Set the difficulty.
     *
     * @param difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Retrieve the player name;
     *
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }
    // endregion
}
