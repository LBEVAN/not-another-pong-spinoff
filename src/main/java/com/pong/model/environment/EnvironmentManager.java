package com.pong.model.environment;

import com.pong.model.eventhandler.EnvironmentBallEventHandler;
import com.pong.system.Constants;
import com.pong.system.sound.Sound;
import com.pong.system.sound.SoundCommand;
import com.pong.system.sound.SoundManager;

/**
 * The EnvironmentManager manages all of the data, processing and events of the game environment.
 *
 * @author LBEVAN
 */
public class EnvironmentManager implements EnvironmentBallEventHandler {

    private Environment environment;
    private EnvironmentBall environmentBall;
    private EnvironmentBallSpawner environmentBallSpawner = new EnvironmentBallSpawner();

    /**
     * Constructor.
     *
     * @param environment first environment to set
     */
    public EnvironmentManager(Environment environment) {
        this.environment = environment;
    }

    /**
     * Update the environment per tick.
     */
    public void update() {
        // if ball exists, update, else try and spawn
        if(environmentBall != null) {
            // update existing ball
            environmentBall.update();
        } else if(environmentBallSpawner.shouldSpawn()) {
            // spawn
            environmentBall = environmentBallSpawner.spawn(environment, this);
        }
    }

    /**
     * Handle the event when the environmental ball has collided with the game ball.
     */
    public void onEnvironmentalBallCollision() {
        // retrieve details on the current ball and switch environment
        final Environment newEnvironment = environmentBall.getEnvironmentBallType().getEnvironment();
        environment = newEnvironment;

        // play appropriate environment switch sound - done this way instead of annotation as there is no constant at compile time!
        SoundManager.getInstance().playSoundOverMusic(environmentBall.getEnvironmentBallType().getSoundKey());

        // destroy the current ball and update destruction time
        environmentBall = null;
        environmentBallSpawner.setLastDestructionTime(System.nanoTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Sound(soundKey = Constants.BALL_DEATH_SOUND, soundCommand = SoundCommand.PLAY_SOUND)
    public void onEnvironmentBallDestruction() {
        // destroy the current ball
        environmentBall = null;

        // update the last destruction time
        environmentBallSpawner.setLastDestructionTime(System.nanoTime());
    }

    /**
     * Retrieve the current active environment.
     *
     * @return environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Set the new environment.
     *
     * @param environment
     */
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Retrieve the current active environmentBall, if any.
     *
     * @return environmentBall
     */
    public EnvironmentBall getEnvironmentBall() {
        return environmentBall;
    }
}
