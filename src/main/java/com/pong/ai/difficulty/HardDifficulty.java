package com.pong.ai.difficulty;

/**
 * Implementation for Difficulty - Hard.
 *
 * @author LBEVAN
 */
public class HardDifficulty implements Difficulty {

    private final double sightRange = 0.9;
    private final double speed = 4;

    /**
     * {@inheritDoc}
     */
    public double getSightRange() {
        return sightRange;
    }

    /**
     * {@inheritDoc}
     */
    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Hard";
    }

}
