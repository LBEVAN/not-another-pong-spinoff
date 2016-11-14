package com.pong.model.wrapper;

import com.pong.ai.difficulty.Difficulty;

/**
 * @author LBEVAN
 */
public class GameOptions {

    private Difficulty difficulty;

    /**
     * Constructor.
     *
     * @param difficulty
     */
    public GameOptions(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

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
}
