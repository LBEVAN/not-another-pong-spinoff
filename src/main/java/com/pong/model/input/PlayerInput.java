package com.pong.model.input;

import com.pong.controller.input.Direction;
import com.pong.model.eventhandler.InputEventHandler;
import com.pong.model.modifier.AbstractModifier;
import com.pong.model.modifier.IncreaseHeightModifier;
import com.pong.model.modifier.ModifierType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author LBEVAN
 */
public class PlayerInput {

    // region data
    private final InputMap inputMap;
    private final ActionMap actionMap;

    private InputEventHandler inputEventHandler;
    // endregion

    // region init
    /**
     * Constructor.
     */
    public PlayerInput(InputMap inputMap, ActionMap actionMap, InputEventHandler inputEventHandler) {
        this.inputMap = inputMap;
        this.actionMap = actionMap;
        this.inputEventHandler = inputEventHandler;

        createInputBindings();
    }
    // endregion

    // region public API
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

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "ONE");
        actionMap.put("ONE", new ConsumeModifierAction(ModifierType.INCREASE_HEIGHT));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, false), "TWO");
        actionMap.put("TWO", new ConsumeModifierAction(ModifierType.INCREASE_SPEED));
    }
    // endregion

    // region private API
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
            inputEventHandler.onMoveAction(deltaY, direction);
        }
    }

    /**
     * ConsumeModifierAction defines an action on the Player entity so that when the player presses
     * a modifier button (e.g. 1, 2, 3....) it will consume the specified modifier;
     */
    private class ConsumeModifierAction extends AbstractAction {

        private ModifierType modifierType;

        /**
         * Constructor.
         *
         * @param modifierType
         */
        public ConsumeModifierAction(ModifierType modifierType) {
            this.modifierType = modifierType;
        }

        /**
         * {@inheritDoc}
         */
        public void actionPerformed(ActionEvent e) {
            inputEventHandler.onConsumeModifierAction(modifierType.create());
        }
    }
    // endregion
}
