package com.pong.model.environment;

import com.pong.model.eventhandler.EnvironmentBallEventHandler;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * The EnvironmentBallSpawner handles the spawning of the environment ball into the game.
 *
 * @author LBEVAN
 */
public class EnvironmentBallSpawner {

    private final int minTimeBetweenSpawns = 15;
    private long lastDestructionTime;

    /**
     * Spawn and return a new environmental ball with its specific properties.
     *
     * @param currentEnvironment
     * @return environmentBall
     */
    public EnvironmentBall spawn(final Environment currentEnvironment, EnvironmentBallEventHandler eventHandler) {
        final EnvironmentBallType environmentBallType = getRandomEnvironmentBallType(currentEnvironment);
        return new EnvironmentBall(environmentBallType, eventHandler);
    }

    /**
     * Determine whether a new environmentBall should be spawned.
     *
     * @return shouldSpawn
     */
    public boolean shouldSpawn() {
        // spawn a ball time since last spawn is bigger than the minimum time and random chance has hit
        return hasTimeSinceLastDestructionExceededMinTimeBetweenSpawns() && ThreadLocalRandom.current().nextBoolean();
    }

    /**
     * Set the new last destruction time
     *
    * @param lastDestructionTime
    */
    public void setLastDestructionTime(long lastDestructionTime) {
        this.lastDestructionTime = lastDestructionTime;
    }

    private boolean hasTimeSinceLastDestructionExceededMinTimeBetweenSpawns() {
        long elapsedTime = System.nanoTime() - lastDestructionTime;
        return elapsedTime / 1000000000.0 > minTimeBetweenSpawns;
    }

    /**
     * Get a random environmentalBallType that does not have the current environment associated to it.
     *
     * @param currentEnvironment
     * @return environmentBallType
     */
    private EnvironmentBallType getRandomEnvironmentBallType(final Environment currentEnvironment) {
        // get valid types (types that do not have the current environment)
        Stream<EnvironmentBallType> stream = Stream.of(EnvironmentBallType.values());
        EnvironmentBallType[] validTypes = stream.filter(e -> e.getEnvironment() != currentEnvironment).toArray(value -> new EnvironmentBallType[value]);

        // choose a random one of the types
        int randInt = ThreadLocalRandom.current().nextInt(0, validTypes.length);
        EnvironmentBallType environmentBallType = validTypes[randInt];

        return environmentBallType;
    }
}
