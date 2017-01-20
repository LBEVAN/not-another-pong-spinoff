package com.pong.model.wrapper;

import com.pong.ai.difficulty.Difficulty;

import java.awt.*;

/**
 * @author LBEVAN
 */
public class GameOptions {

    // region data
    private Difficulty difficulty;
    private String playerName;
    private Color playerColour;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param difficulty
     */
    public GameOptions(Difficulty difficulty, String playerName, Color playerColour) {
        this.difficulty = difficulty;
        this.playerName = playerName;
        this.playerColour = playerColour;
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
     * Retrieve the player name;
     *
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Retrieve the player colour;
     *
     * @return playerColour
     */
    public Color getPlayerColour() {
        return playerColour;
    }
    // endregion
}
