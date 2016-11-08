package com.pong.controller;

import com.pong.GameStateManager;
import com.pong.gui.view.GameOptionsView;
import com.pong.model.GameOptionsModel;
import com.pong.state.GameState;

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
    public void init(GameOptionsModel model, GameOptionsView view) {
        this.model = model;
        this.view = view;

        initActionListeners();
    }

    /**
     * Create and set the action listeners to the view.
     */
    private void initActionListeners() {
        view.getStartButton().addActionListener((e) -> GameStateManager.getInstance().changeState(GameState.GAME));
        view.getBackButton().addActionListener((e) -> GameStateManager.getInstance().returnToPreviousState());
    }
}
