package com.pong.ai.difficulty;

/**
 * The DifficultyType enum defines a set of difficulty types in the game.
 * These are primarily used in the game setup, where the user can choose AI difficulty.
 *
 * @author LBEVAN
 */
public enum DifficultyType {

    EASY(new EasyDifficulty()),
    MEDIUM(new MediumDifficulty()),
    HARD(new HardDifficulty());

    private Difficulty difficulty;

    /**
     * Enum constructor.
     *
     * @param difficulty
     */
    DifficultyType(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Retrieve the difficulty associated to the difficulty type.
     *
     * @return difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }
}
