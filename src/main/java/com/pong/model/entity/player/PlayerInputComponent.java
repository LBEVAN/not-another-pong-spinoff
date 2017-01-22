package com.pong.model.entity.player;

import com.pong.controller.input.Direction;
import com.pong.gui.frame.PongFrame;
import com.pong.model.entity.component.InputComponent;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.pong.controller.input.Direction.DOWN;

/**
 * @author LBEVAN
 */
public class PlayerInputComponent implements InputComponent<Player> {

    // region data
    private final InputMap inputMap;
    private final ActionMap actionMap;

    private int deltaY = 0;
    private Direction direction;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param inputMap
     * @param actionMap
     */
    public PlayerInputComponent(InputMap inputMap, ActionMap actionMap) {
        this.inputMap = inputMap;
        this.actionMap = actionMap;

        createInputBindings();
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Player entity) {
        entity.setBaseSpeed(deltaY);

        if(isWithinBounds(entity)) {
            move(entity);
        }
    }
    // endregion

    // region private API
    /**
     * Calculate the speed for the given entity.
     *
     * @param player
     * @return speed
     */
    private double calculateSpeed(Player player) {
        final double baseSpeed = player.getBaseSpeed();
        final double modifiedSpeed = player.getModifiedSpeed();

        if(baseSpeed == 0) {
            // base speed is zero, meaning there is no user input; return 0 to ensure no movement
            return 0;
        } else if(baseSpeed < 0) {
            // base speed is negative, so minus from base speed
            return baseSpeed - modifiedSpeed;
        } else {
            // base speed is positive, so add to base speed
            return baseSpeed + modifiedSpeed;
        }
    }

    /**
     * Check whether the entity is within the bounds of the game world.
     *
     * @param entity
     * @return isWithinBounds
     */
    private boolean isWithinBounds(Player entity) {
        final double x = entity.getX();
        final double y = entity.getY();
        final int height = entity.getHeight();

        return (direction == DOWN && y <= PongFrame.SCREEN_HEIGHT - height)
                || (direction == Direction.UP && y >= 0);
    }

    /**
     * Move the entity.
     *
     * @param entity
     */
    private void move(Player entity) {
        entity.setY(entity.getY() + calculateSpeed(entity) + deltaY);;
    }

    /**
     * MoveAction event. Set the delta and direction in which to move.
     *
     * @param deltaY
     * @param direction
     */
    private void onMoveAction(int deltaY, Direction direction) {
        this.deltaY = deltaY;
        this.direction = direction;
    }

    /**
     * MoveAction defines a action on the Player entity
     * in order to move it in a direction at a specified speed.
     */
    private class MoveAction extends AbstractAction {

        private int deltaY;
        private Direction direction;

        /**
         * Constructor.
         *
         * @param deltaY
         * @param direction
         */
        public MoveAction(int deltaY, Direction direction) {
            this.deltaY = deltaY;
            this.direction = direction;
        }

        /**
         * {@inheritDoc}
         */
        public void actionPerformed(ActionEvent e) {
            onMoveAction(deltaY, direction);
        }
    }

    /**
     * Create the relevant input bindings for the player.
     */
    public void createInputBindings() {
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DOWN");
        actionMap.put("DOWN", new MoveAction(3, Direction.DOWN));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "RELEASED_DOWN");
        actionMap.put("RELEASED_DOWN", new MoveAction(0, Direction.DOWN));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UP");
        actionMap.put("UP", new MoveAction(-3, Direction.UP));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "RELEASED_UP");
        actionMap.put("RELEASED_UP", new MoveAction(0, Direction.UP));
    }
    // endregion
}
