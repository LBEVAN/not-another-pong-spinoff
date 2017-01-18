package com.pong.model;

import com.pong.gui.frame.PongFrame;
import com.pong.model.entity.Ball;
import com.pong.model.entity.Computer;
import com.pong.model.entity.Player;
import com.pong.model.environment.Environment;
import com.pong.model.environment.EnvironmentBall;
import com.pong.model.environment.EnvironmentManager;
import com.pong.model.eventhandler.BallEventHandler;
import com.pong.model.modifier.Modifier;
import com.pong.model.modifier.ModifierSpawner;
import com.pong.model.wrapper.GameOptions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * PongModel represents the model of the game.
 * It houses the entities active in the game.
 *
 * @author LBEVAN
 */
public class PongModel implements Model, BallEventHandler {

    // region data
    private final GameOptions gameOptions;

    private Player player;
    private Ball ball;
    private Computer computer;

    private List<Modifier> activeModifiers;

    private int playerScore = 0;
    private int computerScore = 0;

    private int maxScore = 1;

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
        activeModifiers = new ArrayList<>();

        // create the player
        this.player = new Player(10, PongFrame.SCREEN_HEIGHT / 2, 15, 75);

        // create the computer
        this.computer = new Computer(PongFrame.SCREEN_WIDTH - 30, PongFrame.SCREEN_HEIGHT / 2, 15, 75, this);

        // create the ball
        this.ball = new Ball(PongFrame.SCREEN_WIDTH / 2, PongFrame.SCREEN_HEIGHT / 2, 25, 25, this);
        ball.setBallEventHandler(this);

        this.environmentManager = new EnvironmentManager(Environment.SPACE);
    }
    // endregion

    // region public API
    /**
     * Update the entities.
     */
    public void update() {
        checkGameOverScenario();

        ball.update();
        player.update();
        computer.update();

        environmentManager.update();

        cleanup();
    }

    /**
     * Check if the Player or the Computer has won (i.e. hit the maxScore).
     */
    public boolean checkGameOverScenario() {
        return playerScore >= maxScore || computerScore >= maxScore;
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
     * Add a modifier to the game world.
     *
     * @param modifier
     */
    public void addActiveModifier(Modifier modifier) {
        activeModifiers.add(modifier);
    }
    // endregion

    // region events
    /**
     * {@inheritDoc}
     */
    @Override
    public void onPlayerScored() {
        playerScore += gameOptions.getDifficulty().getPointValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onComputerScored() {
        computerScore += gameOptions.getDifficulty().getPointValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnvironmentalBallCollision() {
        environmentManager.onEnvironmentalBallCollision();
    }
    // endregion

    // region private API
    /**
     * Delegate method to perform cleanup actions every tick (e.g. removing inactive modifiers);
     */
    private void cleanup() {
        Iterator<Modifier> iter = activeModifiers.iterator();
        while(iter.hasNext()) {
            Modifier modifier = iter.next();
            if(!modifier.isActive()) {
                iter.remove();
            }
        }
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
     * Retrieve the Player.
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Retrieve the Computer.
     *
     * @return computer
     */
    public Computer getComputer() {
        return computer;
    }

    /**
     * Retrieve the player score.
     *
     * @return playerScore
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Retrieve the computer score.
     *
     * @return computerScore
     */
    public int getComputerScore() {
        return computerScore;
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
     * Retrieve the list of active modifiers in the game world.
     *
     * @return activeModifiers
     */
    public List<Modifier> getActiveModifiers() {
        return activeModifiers;
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
    // endregion
}
