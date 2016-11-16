package com.pong.controller;

import com.pong.GameStateManager;
import com.pong.factory.MvcFactory;
import com.pong.factory.MvcWrapper;
import com.pong.gui.view.GameOptionsView;
import com.pong.gui.view.GameOverView;
import com.pong.gui.view.MainMenuView;
import com.pong.model.GameOptionsModel;
import com.pong.model.GameOverModel;
import com.pong.model.MainMenuModel;
import com.pong.state.GameState;

/**
 * The GameOverController controls the input actions for the game over screen.
 *
 * @author LBEVAN
 */
public class GameOverController implements Controller {

    private final GameOverModel model;
    private final GameOverView view;

    /**
     * Constructor.
     *
     * @param model
     * @param view
     */
    public GameOverController(final GameOverModel model, final GameOverView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        initActionListeners();
        initViewLabels();
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
        view.getPlayAgainButton().addActionListener(e -> {
            MvcWrapper<GameOptionsModel, GameOptionsView, GameOptionsController> mvc = MvcFactory.createGameOptions();
            GameStateManager.getInstance().changeState(GameState.GAME_OPTIONS, mvc);
        });

        view.getExitButton().addActionListener(e -> {
            MvcWrapper<MainMenuModel, MainMenuView, MainMenuController> mvc = MvcFactory.createMainMenu();
            GameStateManager.getInstance().changeState(GameState.MENU, mvc);
        });
    }

    /**
     * Set data to the dependent labels in the view.
     */
    private void initViewLabels() {
        view.setWinnerLabelText(model.deriveWinnerText());
    }
}
