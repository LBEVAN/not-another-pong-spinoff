package com.pong.ai.difficulty;

/**
 * Implementation for Difficulty - Medium.
 *
 * @author LBEVAN
 */
public class MediumDifficulty implements Difficulty {

    private final double sightRange = 0.75;
    private final double speed = 4;
    private final int pointValue = 3;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPointValue() {
        return pointValue;
    }

    @Override
    public String toString() {
        return "Medium";
    }
}
