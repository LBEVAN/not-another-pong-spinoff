package com.pong.model.entity;

import com.pong.controller.input.Direction;
import com.pong.gui.frame.PongFrame;
import com.pong.model.PongModel;
import com.pong.state.AIStateMachine;
import com.pong.state.ComputerState;
import com.pong.state.StateMachine;
import com.pong.system.ResourceManager;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * The Computer class represents the computer entity in the game.
 *
 * @author LBEVAN
 */
public class Computer extends Entity {

    private final PongModel pongModel;

    public final StateMachine<Computer, ComputerState> stateMachine = new AIStateMachine<Computer, ComputerState>(this);

    private final int normalMoveSpeed;
    private final int idleMoveSpeed = 1;
    private final double sightRange;
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
    public Computer(int x, int y, int width, int height, final PongModel pongModel) {
        super(x, y, width, height);

        this.pongModel = pongModel;

        stateMachine.setInitialState(ComputerState.IDLE_MOVE);

        normalMoveSpeed = (int) pongModel.getGameOptions().getDifficulty().getSpeed();
        sightRange = pongModel.getGameOptions().getDifficulty().getSightRange();
    }

    /**
     * Move and track the ball.
     */
    public void moveAndTrackBall() {
        setBaseSpeed(normalMoveSpeed);
        int ballY = pongModel.getBall().getY();

        // check if within top bounds
        // and the ball y is less than the paddle y
        if (y >= 20 && ballY < y) {
            // move up
            y -= getSpeed();
        }

        // check if within bottom bounds
        // and the ball y is more than the paddle y
        if (y <= PongFrame.SCREEN_HEIGHT - 60 && ballY > y) {
            // move down
            y += getSpeed();
        }
    }

    /**
     * Idle move action - randomly move
     */
    public void idleMove() {
        setBaseSpeed(idleMoveSpeed);
        if(idleMoveTime == 0) {
            idleMoveTime = System.currentTimeMillis();
            idleMoveDirection = Direction.values()[new Random().nextInt(Direction.values().length)];
        }

        if(System.currentTimeMillis() / 1000 - idleMoveTime / 1000 > 3) {
            // more than 3 seconds
            idleMoveTime = 0;
        }

        if(y >= 20 && idleMoveDirection == Direction.UP) {
            y -= getSpeed();
        } else if(y <= PongFrame.SCREEN_HEIGHT - 60 && idleMoveDirection == Direction.DOWN) {
            y += getSpeed();
        }
    }

    /**
    * Check if the ball is in sight range.
            *
            * @return isBallInSightRange
    */
    public boolean isBallInSightRange() {
        if(pongModel.getBall().getX() >= PongFrame.SCREEN_WIDTH - (PongFrame.SCREEN_WIDTH * sightRange)) {
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
        modifierSystem.update(this);
    }

    /**
     * {@inheritDoc}
     */
    public int getSpeed() {
        return baseSpeed + modifiedSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        return ResourceManager.getInstance().getGraphic("Paddle");
    }
}
