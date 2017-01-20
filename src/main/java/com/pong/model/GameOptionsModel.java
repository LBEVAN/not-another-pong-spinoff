package com.pong.model;

import com.pong.Pong;
import com.pong.ai.difficulty.DifficultyType;
import com.pong.model.wrapper.GameOptions;
import com.pong.system.Constants;

import java.awt.*;
import java.util.prefs.Preferences;

/**
 * GameOptionsModel represents the model for the GameOptionsView.
 *
 * @author LBEVAN
 */
public class GameOptionsModel implements Model {

    // region data
    private DifficultyType selectedDifficulty;
    private String playerName;
    private Color playerColor;
    // endregion

    // region init
    /**
     * Constructor.
     */
    public GameOptionsModel() {
        // init the game difficulty to Medium
        selectedDifficulty = DifficultyType.MEDIUM;
        playerName = getStoredPlayerName();
        playerColor = Color.WHITE;
    }
    // endregion

    // region public API
    /**
     * Create the game options using the preferences
     * selected on the game options screen.
     *
     * @return gameOptions
     */
    public GameOptions createGameOptions() {
        return new GameOptions(selectedDifficulty.getDifficulty(), playerName, playerColor);
    }

    /**
     * Save the entered player name into the preferences.
     */
    public void savePlayerName() {
        Preferences preferences = Preferences.userNodeForPackage(Pong.class);
        preferences.put(Constants.KEY_PLAYERNAME, playerName);
    }
    // endregion

    // region private API
    /**
     * Retrieve the stored player name from the preferences.
     * Return empty string if no name is found.
     *
     * @return playerName
     */
    private String getStoredPlayerName() {
        Preferences preferences = Preferences.userNodeForPackage(Pong.class);
        return preferences.get(Constants.KEY_PLAYERNAME, "");
    }
    // endregion

    // region getters & setters
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

    /**
     * Retrieve the player name.
     *
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Set the player name.
     *
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }
    // endregion
}
