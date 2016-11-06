package com.pong.ai.difficulty;

/**
 * Implementation for Difficulty - Easy.
 *
 * @author LBEVAN
 */
public class EasyDifficulty implements Difficulty {

    private final double sightRange = 0.6;
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
        return "Easy";
    }
}
