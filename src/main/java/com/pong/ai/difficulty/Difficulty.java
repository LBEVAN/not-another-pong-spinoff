package com.pong.ai.difficulty;

/**
 * Interface to define properties to query against AI difficulties.
 *
 * @author LBEVAN
 */
public interface Difficulty {

    /**
     * Retrieve the sight range for this difficulty.
     *
     * @return sightRange
     */
    double getSightRange();

    /**
     * Retrieve the speed for this difficulty.
     *
     * @return speed
     */
    double getSpeed();
}
