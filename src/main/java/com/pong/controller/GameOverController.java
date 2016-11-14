package com.pong.controller;

import com.pong.GameStateManager;
import com.pong.gui.view.GameOverView;
import com.pong.model.GameOverModel;
import com.pong.state.GameState;

/**
 * The GameOverController controls the input actions for the game over screen.
 *
 * @author LBEVAN
 */
public class GameOverController implements Controller<GameOverModel, GameOverView> {

    private GameOverModel model;
    private GameOverView view;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(GameOverModel model, GameOverView view) {
        this.model = model;
        this.view = view;

        initActionListeners();
    }

    /**
     * Create and set the action listeners to the view.
     */
    private void initActionListeners() {
        view.getPlayAgainButton().addActionListener(e -> GameStateManager.getInstance().changeState(GameState.GAME_OPTIONS));

        view.getExitButton().addActionListener(e -> GameStateManager.getInstance().changeState(GameState.MENU));
    }
}
