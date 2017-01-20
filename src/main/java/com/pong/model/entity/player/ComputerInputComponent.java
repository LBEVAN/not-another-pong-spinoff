package com.pong.model.entity.player;

import com.pong.controller.input.Direction;
import com.pong.gui.frame.PongFrame;
import com.pong.model.PongModel;
import com.pong.model.entity.Player;
import com.pong.model.entity.component.InputComponent;
import com.pong.model.modifier.ModifierType;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

    private int modifierUseCooldown = 10;
    private long timeSinceLastModifierUse = 0;
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

            if(canUseModifier()) {
                useRandomModifier();
            }
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

    /**
     * See if the computer can use a modifier.
     * It is valid to use a modifier when the timeSinceLastModifierUse is over the modifierUseCooldown
     * and a random 2% chance is calculated.
     *
     * @return canUseModifier
     */
    public boolean canUseModifier() {
        boolean isOverModifierUseCooldown = (System.nanoTime() - timeSinceLastModifierUse) / 1000000000.0 > modifierUseCooldown;

        if(isOverModifierUseCooldown) {
            return Math.random() > 0.99;
        } else {
            return false;
        }
    }

    /**
     * Use a random modifier.
     */
    public void useRandomModifier() {
        ModifierType[] modifierTypes = ModifierType.values();
        int num = ThreadLocalRandom.current().nextInt(0, modifierTypes.length);
        ModifierType modifierType = modifierTypes[num];

        modifierType.generateAction(pongModel, PlayerId.TWO).execute();
        timeSinceLastModifierUse = System.nanoTime();
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
