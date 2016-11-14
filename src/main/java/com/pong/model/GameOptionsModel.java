package com.pong.model;

import com.pong.ai.difficulty.DifficultyType;
import com.pong.model.wrapper.GameOptions;

/**
 * GameOptionsModel represents the model for the GameOptionsView.
 *
 * @author LBEVAN
 */
public class GameOptionsModel implements Model {

    private DifficultyType selectedDifficulty;

    /**
     * Constructor.
     */
    public GameOptionsModel() {
        // init the game difficulty to Medium
        selectedDifficulty = DifficultyType.MEDIUM;
    }

    /**
     * Create the game options using the preferences
     * selected on the game options screen.
     *
     * @return gameOptions
     */
    public GameOptions createGameOptions() {
        return new GameOptions(selectedDifficulty.getDifficulty());
    }

    /**
     * Retrieve the available set of difficulties to choose from.
     *
     * @return difficulties
     */
    public DifficultyType[] getDifficulties() {
        return DifficultyType.values();
    }

    /**
     * Retrieve the currently selected difficulty.
     *
     * @return selectedDifficulty
     */
    public DifficultyType getSelectedDifficulty() {
        return selectedDifficulty;
    }

    /**
     * Set the current selected difficulty.
     *
     * @param selectedDifficulty
     */
    public void setSelectedDifficulty(DifficultyType selectedDifficulty) {
        this.selectedDifficulty = selectedDifficulty;
    }
}
