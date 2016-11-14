package com.pong.controller;

import com.pong.GameStateManager;
import com.pong.ai.difficulty.DifficultyType;
import com.pong.gui.view.GameOptionsView;
import com.pong.model.GameOptionsModel;
import com.pong.model.PongModel;
import com.pong.state.GameState;

import java.awt.event.ItemEvent;

/**
 * The GameOptionsController controls the input for the GameOptionsView.
 *
 * @author LBEVAN
 */
public class GameOptionsController implements Controller<GameOptionsModel, GameOptionsView> {

    private GameOptionsModel model;
    private GameOptionsView view;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(GameOptionsModel model, GameOptionsView view) {
        this.model = model;
        this.view = view;

        initActionListeners();
    }

    /**
     * Create and set the action listeners to the view.
     */
    private void initActionListeners() {
        view.getStartButton().addActionListener(e -> startGameAction());

        view.getBackButton().addActionListener(e -> GameStateManager.getInstance().returnToPreviousState());

        view.getDifficultyComboBox().addItemListener(e -> difficultySelectedAction(e));
    }

    /**
     * Handle the action of starting the game.
     * Change the state and set the game options.
     */
    private void startGameAction() {
        GameStateManager.getInstance().changeState(GameState.GAME);
        ((PongModel)GameStateManager.getInstance().getCurrentMVC().getModel()).setGameOptions(model.createGameOptions());
    }

    /**
     * Handle the event when a difficulty is selected.
     * Only act on 'SELECTED' change events. Avoid deselection.
     *
     * @param event
     */
    private void difficultySelectedAction(ItemEvent event) {
        // only do this is the item event is selected (avoid deselect)
        if(event.getStateChange() == ItemEvent.SELECTED) {
            model.setSelectedDifficulty((DifficultyType) event.getItem());
        }
    }
}
