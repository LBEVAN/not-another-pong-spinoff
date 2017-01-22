package com.pong.model;

import com.pong.gui.frame.PongFrame;
import com.pong.model.entity.Ball;
import com.pong.model.entity.player.Player;
import com.pong.model.entity.player.PlayerId;
import com.pong.model.environment.Environment;
import com.pong.model.environment.EnvironmentBall;
import com.pong.model.environment.EnvironmentManager;
import com.pong.model.eventhandler.BallEventHandler;
import com.pong.model.scoring.ScoreManager;
import com.pong.model.wrapper.GameOptions;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * PongModel represents the model of the game.
 * It houses the entities active in the game.
 *
 * @author LBEVAN
 */
public class PongModel implements Model, BallEventHandler {

    // region data
    private final GameOptions gameOptions;

    private final ScoreManager scoreManager;

    private Player player1, player2;
    private Ball ball;

    private EnvironmentManager environmentManager;

    private long startGameTime;
    private final double gameDuration = 90;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param gameOptions
     */
    public PongModel(final GameOptions gameOptions) {
        this.gameOptions = gameOptions;

        // create player1
        this.player1 = new Player(10, PongFrame.SCREEN_HEIGHT / 2, 15, 75, gameOptions.getPlayerName(), gameOptions.getPlayerColour());

        // create the player2
        this.player2 = new Player(PongFrame.SCREEN_WIDTH - 30, PongFrame.SCREEN_HEIGHT / 2, 15, 75, "Computer", Color.WHITE);

        // create the ball
        this.ball = new Ball(PongFrame.SCREEN_WIDTH / 2, PongFrame.SCREEN_HEIGHT / 2, 25, 25, this);
        ball.setBallEventHandler(this);

        this.environmentManager = new EnvironmentManager(Environment.SPACE);
        this.scoreManager = new ScoreManager();
    }
    // endregion

    // region public API
    /**
     * Update the entities.
     */
    public void update() {
        ball.update();
        player1.update();
        player2.update();

        environmentManager.update();
    }

    /**
     * Check if the game is over.
     * The game is considered over when the elapsed time is greater than the game duration.
     *
     * @return isGameOver
     */
    public boolean isGameOver() {
        long elapsedTime = System.nanoTime() - startGameTime;
        return elapsedTime / 1000000000.0 > gameDuration;
    }

    /**
     * Retrieve the remaining game time.
     *
     * @return timeRemaining
     */
    public double getTimeRemaining() {
        long elapsedTime = System.nanoTime() - startGameTime;
        return new BigDecimal(gameDuration - (elapsedTime / 1000000000.0)).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Retrieve the player by its id.
     * PlayerId.ONE = player1.
     * PlayerId.TWO = player2.
     *
     * @param playerId
     * @return player
     */
    public Player getPlayerById(PlayerId playerId) {
        Player player = null;

        switch (playerId) {
            case ONE: player = player1; break;
            case TWO: player = player2; break;
        }

        return player;
    }
    // endregion

    // region events
    /**
     * {@inheritDoc}
     */
    @Override
    public void onPlayerScored(PlayerId playerId) {
        scoreManager.increaseScore(playerId, gameOptions.getDifficulty().getPointValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnvironmentalBallCollision() {
        environmentManager.onEnvironmentalBallCollision();
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the Ball.
     *
     * @return ball
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * Retrieve the gameOptions.
     *
     * @return gameOptions
     */
    public GameOptions getGameOptions() {
        return gameOptions;
    }

    /**
     * Retrieve the current active environment.
     *
     * @return environment
     */
    public Environment getEnvironment() {
        return environmentManager.getEnvironment();
    }

    /**
     * Retrieve the environmentBall. Delegate to environment manager.
     *
     * @return environmentBall
     */
    public EnvironmentBall getEnvironmentBall() {
        return environmentManager.getEnvironmentBall();
    }

    /**
     * Set the startGameTime.
     *
     * @param startGameTime
     */
    public void setStartGameTime(long startGameTime) {
        this.startGameTime = startGameTime;
    }

    /**
     * Retrieve the scoreManager.
     *
     * @return
     */
    public ScoreManager getScoreManager() {
        return scoreManager;
    }
    // endregion
}
