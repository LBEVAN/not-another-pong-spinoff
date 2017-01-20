package com.pong.model.input;

import com.pong.controller.PongController;
import com.pong.model.PongModel;
import com.pong.model.entity.player.PlayerId;
import com.pong.model.modifier.ModifierType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * GlobalInputManager handles all of the generic input for the game,
 * such as user input for modifiers and special shortcuts.
 *
 * @author LBEVAN
 */
public class GlobalInputManager {

    // region data
    private final InputMap inputMap;
    private final ActionMap actionMap;

    private final PongModel pongModel;
    private final PongController pongController;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param inputMap
     * @param actionMap
     * @param pongModel
     * @param pongController
     */
    public GlobalInputManager(InputMap inputMap, ActionMap actionMap, PongModel pongModel, PongController pongController) {
        this.inputMap = inputMap;
        this.actionMap = actionMap;
        this.pongModel = pongModel;
        this.pongController = pongController;

        createInputBindings();
    }
    // endregion

    // region private API
    /**
     * Setup the input bindings.
     */
    private void createInputBindings() {
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "ESC");
        actionMap.put("ESC", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pongController.pauseOrResume();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "ONE");
        actionMap.put("ONE", new ModifierActionWrapper(ModifierType.INCREASE_HEIGHT, pongModel, PlayerId.ONE));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, false), "TWO");
        actionMap.put("TWO", new ModifierActionWrapper(ModifierType.INCREASE_SPEED, pongModel, PlayerId.ONE));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, false), "THREE");
        actionMap.put("THREE", new ModifierActionWrapper(ModifierType.INCREASE_BALL_SPEED, pongModel, PlayerId.ONE));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0, false), "FOUR");
        actionMap.put("FOUR", new ModifierActionWrapper(ModifierType.DECREASE_SPEED, pongModel, PlayerId.ONE));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0, false), "FIVE");
        actionMap.put("FIVE", new ModifierActionWrapper(ModifierType.DECREASE_HEIGHT, pongModel, PlayerId.ONE));
    }

    /**
     * Wrapper AbstractAction class for executing a modifier action.
     */
    private class ModifierActionWrapper extends AbstractAction {

        private ModifierType modifierType;
        private PongModel pongModel;
        private PlayerId playerId;

        /**
         * Constructor.
         *
         * @param modifierType
         * @param pongModel
         * @param playerId
         */
        public ModifierActionWrapper(ModifierType modifierType, PongModel pongModel, PlayerId playerId) {
            this.modifierType = modifierType;
            this.pongModel = pongModel;
            this.playerId = playerId;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            modifierType.generateAction(pongModel, playerId).execute();
        }
    }
    // endregion
}
