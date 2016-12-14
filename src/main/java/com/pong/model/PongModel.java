package com.pong.model;

import com.pong.gui.frame.PongFrame;
import com.pong.model.entity.Ball;
import com.pong.model.entity.Computer;
import com.pong.model.entity.Player;
import com.pong.model.listener.BallListener;
import com.pong.model.modifier.Modifier;
import com.pong.model.modifier.ModifierSpawner;
import com.pong.model.wrapper.GameOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * PongModel represents the model of the game.
 * It houses the entities active in the game.
 *
 * @author LBEVAN
 */
public class PongModel implements Model, BallListener {

    private final GameOptions gameOptions;

    private Player player;
    private Ball ball;
    private Computer computer;

    private ModifierSpawner modifierSpawner = new ModifierSpawner();
    private List<Modifier> activeModifiers;

    private int playerScore = 0;
    private int computerScore = 0;

    private int maxScore = 10;

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
        ball.addListener(this);
    }

    /**
     * Update the entities.
     */
    public void update() {
        checkGameOverScenario();

        ball.update();
        player.update();
        computer.update();

        modifierSpawner.spawn(this);

        cleanup();
    }

    /**
     * Check if the Player or the Computer has won (i.e. hit the maxScore).
     */
    public boolean checkGameOverScenario() {
        return playerScore >= maxScore || computerScore >= maxScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playerScored() {
        playerScore++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void computerScored() {
        computerScore++;
    }

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
     * Add a modifier to the game world.
     *
     * @param modifier
     */
    public void addActiveModifier(Modifier modifier) {
        activeModifiers.add(modifier);
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
}
