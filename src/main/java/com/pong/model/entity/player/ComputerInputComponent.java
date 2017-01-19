package com.pong.model.entity.player;

import com.pong.controller.input.Direction;
import com.pong.gui.frame.PongFrame;
import com.pong.model.PongModel;
import com.pong.model.entity.Player;
import com.pong.model.entity.component.InputComponent;

import java.util.Random;

/**
 * @author LBEVAN
 */
public class ComputerInputComponent implements InputComponent<Player> {

    // region data
    private final PongModel pongModel;

    private final double normalMoveSpeed;
    private final double idleMoveSpeed = 1;
    private final double sightRange;

    private Direction idleMoveDirection;
    private long idleMoveTime = 0;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param pongModel
     */
    public ComputerInputComponent(PongModel pongModel) {
        this.pongModel = pongModel;
        this.normalMoveSpeed = pongModel.getGameOptions().getDifficulty().getSpeed();
        this.sightRange = pongModel.getGameOptions().getDifficulty().getSightRange();
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Player entity) {
        if(isBallInSightRange()) {
            moveAndTrackBall(entity);
        } else {
            idleMove(entity);
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
     * Move and track the ball.
     */
    public void moveAndTrackBall(Player player) {
        player.setBaseSpeed(normalMoveSpeed);
        double playerY = player.getY();
        double ballY = pongModel.getBall().getY();

        // check if within top bounds
        // and the ball y is less than the paddle y
        if (playerY >= 20 && ballY < playerY) {
            // move up
            playerY -= calculateSpeed(player);
        }

        // check if within bottom bounds
        // and the ball y is more than the paddle y
        if (playerY <= PongFrame.SCREEN_HEIGHT - 60 && ballY > playerY) {
            // move down
            playerY += calculateSpeed(player);
        }

        player.setY(playerY);
    }

    /**
     * Idle move action - randomly move
     */
    public void idleMove(Player player) {
        player.setBaseSpeed(idleMoveSpeed);
        double playerY = player.getY();

        if(idleMoveTime == 0) {
            idleMoveTime = System.currentTimeMillis();
            idleMoveDirection = Direction.values()[new Random().nextInt(Direction.values().length)];
        }

        if(System.currentTimeMillis() / 1000 - idleMoveTime / 1000 > 3) {
            // more than 3 seconds
            idleMoveTime = 0;
        }

        if(playerY >= 20 && idleMoveDirection == Direction.UP) {
            playerY -= calculateSpeed(player);
        } else if(playerY <= PongFrame.SCREEN_HEIGHT - 60 && idleMoveDirection == Direction.DOWN) {
            playerY += calculateSpeed(player);
        }

        player.setY(playerY);
    }
    // endregion

    // region privateAPI
    /**
     * Calculate the speed for the given entity.
     *
     * @param player
     * @return speed
     */
    private double calculateSpeed(Player player) {
        return player.getBaseSpeed() + player.getModifiedSpeed();
    }
    // endregion
}
