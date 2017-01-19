package com.pong.model.input;

import com.pong.model.PongModel;
import com.pong.model.entity.player.PlayerId;
import com.pong.model.eventhandler.ModifierEventHandler;
import com.pong.model.modifier.AbstractModifier;
import com.pong.model.modifier.ModifierType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author LBEVAN
 */
public class GlobalInputManager {

    // region data
    private final InputMap inputMap;
    private final ActionMap actionMap;

    private final PongModel pongModel;
    // endregion

    // region init
    /**
     * Constructor.
     */
    public GlobalInputManager(InputMap inputMap, ActionMap actionMap, PongModel pongModel) {
        this.inputMap = inputMap;
        this.actionMap = actionMap;
        this.pongModel = pongModel;

        createInputBindings();
    }
    // endregion

    public void createInputBindings() {
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "ONE");
        actionMap.put("ONE", new ModifierAction(ModifierType.INCREASE_HEIGHT, pongModel.getPlayerById(PlayerId.ONE)));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, false), "TWO");
        actionMap.put("TWO", new ModifierAction(ModifierType.INCREASE_SPEED, pongModel.getPlayerById(PlayerId.ONE)));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, false), "THREE");
        actionMap.put("THREE", new OffensiveModifierAction(new ModifierAction(ModifierType.INCREASE_BALL_SPEED, pongModel.getBall()), pongModel.getPlayerById(PlayerId.ONE)));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0, false), "FOUR");
        actionMap.put("FOUR", new OffensiveModifierAction(new ModifierAction(ModifierType.DECREASE_SPEED, pongModel.getPlayerById(PlayerId.TWO)), pongModel.getPlayerById(PlayerId.ONE)));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0, false), "FIVE");
        actionMap.put("FIVE", new OffensiveModifierAction(new ModifierAction(ModifierType.DECREASE_HEIGHT, pongModel.getPlayerById(PlayerId.TWO)), pongModel.getPlayerById(PlayerId.ONE)));
    }

    /**
     * ModifierAction defines an action on the Player entity so that when the player presses
     * a modifier button (e.g. 1, 2, 3....) it will consume the specified modifier;
     */
    private class ModifierAction extends AbstractAction {

        public ModifierType modifierType;
        private ModifierEventHandler modifierEventHandler;

        /**
         * Constructor.
         *
         * @param modifierType
         */
        public ModifierAction(ModifierType modifierType, ModifierEventHandler modifierEventHandler) {
            this.modifierType = modifierType;
            this.modifierEventHandler = modifierEventHandler;
        }

        /**
         * {@inheritDoc}
         */
        public void actionPerformed(ActionEvent e) {
            AbstractModifier modifier = modifierType.create();
            modifierEventHandler.onConsumeModifier(modifier);
        }
    }

    private class ModifierActionDecorator extends AbstractAction {

        protected ModifierAction modifierAction;

        public ModifierActionDecorator(ModifierAction modifierAction) {
            this.modifierAction = modifierAction;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            this.modifierAction.actionPerformed(e);
        }
    }

    private class OffensiveModifierAction extends ModifierActionDecorator {

        private ModifierEventHandler modifierEventHandler;

        public OffensiveModifierAction(ModifierAction modifierAction, ModifierEventHandler modifierEventHandler) {
            super(modifierAction);
            this.modifierEventHandler = modifierEventHandler;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            this.modifierEventHandler.onUseOffensiveModifier(modifierAction.modifierType);
            this.modifierAction.actionPerformed(e);
        }
    }

}
