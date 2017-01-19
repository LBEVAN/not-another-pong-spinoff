package com.pong.controller;

import com.pong.gamestate.GameStateManager;
import com.pong.ai.difficulty.DifficultyType;
import com.pong.factory.MvcFactory;
import com.pong.factory.MvcWrapper;
import com.pong.gui.view.GameOptionsView;
import com.pong.gui.view.PongView;
import com.pong.model.GameOptionsModel;
import com.pong.model.PongModel;
import com.pong.gamestate.GameState;

import java.awt.event.ItemEvent;

/**
 * The GameOptionsController controls the input for the GameOptionsView.
 *
 * @author LBEVAN
 */
public class GameOptionsController implements Controller {

    private final GameOptionsModel model;
    private final GameOptionsView view;

    /**
     * Constructor.
     *
     * @param model
     * @param view
     */
    public GameOptionsController(final GameOptionsModel model, final GameOptionsView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        initActionListeners();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {}

    /**
     * Create and set the action listeners to the view.
     */
    private void initActionListeners() {
        view.getStartButton().addActionListener(e -> startGameAction());

        view.getBackButton().addActionListener(e -> {
            GameStateManager.getInstance().returnToPreviousState();
            GameStateManager.getInstance().getCurrentMvc().getController().start();
        });

        view.getDifficultyComboBox().addItemListener(e -> difficultySelectedAction(e));
    }

    /**
     * Handle the action of starting the game.
     * Change the gamestate and set the game options.
     */
    private void startGameAction() {
        MvcWrapper<PongModel, PongView, PongController> mvc = MvcFactory.createPong(model.createGameOptions());

        GameStateManager.getInstance().changeState(GameState.GAME, mvc);
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
