package com.pong.model.entity;

import com.pong.ai.difficulty.Difficulty;
import com.pong.controller.input.Direction;
import com.pong.gui.frame.PongFrame;
import com.pong.model.PongModel;
import com.pong.state.AIStateMachine;
import com.pong.state.ComputerState;
import com.pong.state.StateMachine;

import java.util.Random;

/**
 * The Computer class represents the computer entity in the game.
 *
 * @author LBEVAN
 */
public class Computer extends Entity {

    private final PongModel pongModel;
    private final Difficulty difficulty;

    public final StateMachine<Computer, ComputerState> stateMachine = new AIStateMachine<Computer, ComputerState>(this);

    private final int idleMoveSpeed = 1;
    private Direction idleMoveDirection;
    private long idleMoveTime = 0;

    /**
     * Player constructor.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param pongModel
     */
    public Computer(int x, int y, int width, int height, final PongModel pongModel, final Difficulty difficulty) {
        super(x, y, width, height);

        this.pongModel = pongModel;
        this.difficulty = difficulty;

        stateMachine.setInitialState(ComputerState.IDLE_MOVE);
    }

    /**
     * Move and track the ball.
     */
    public void moveAndTrackBall() {
        int ballY = pongModel.getBall().getY();

        // check if within top bounds
        // and the ball y is less than the paddle y
        if (y >= 20 && ballY < y) {
            // move up
            y -= difficulty.getSpeed();
        }

        // check if within bottom bounds
        // and the ball y is more than the paddle y
        if (y <= PongFrame.SCREEN_HEIGHT - 60 && ballY > y) {
            // move down
            y += difficulty.getSpeed();
        }
    }

    /**
     * Idle move action - randomly move
     */
    public void idleMove() {
        if(idleMoveTime == 0) {
            idleMoveTime = System.currentTimeMillis();
            idleMoveDirection = Direction.values()[new Random().nextInt(Direction.values().length)];
        }

        if(System.currentTimeMillis() / 1000 - idleMoveTime / 1000 > 3) {
            // more than 3 seconds
            idleMoveTime = 0;
        }

        if(idleMoveDirection == Direction.UP) {
            y -= idleMoveSpeed;
        } else if(idleMoveDirection == Direction.DOWN) {
            y += idleMoveSpeed;
        }
    }

    /**
     * Check if the ball is in sight range.
     *
     * @return isBallInSightRange
     */
    public boolean isBallInSightRange() {
        if(pongModel.getBall().getX() >= PongFrame.SCREEN_WIDTH - (PongFrame.SCREEN_WIDTH * difficulty.getSightRange())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        stateMachine.update();
    }
}
