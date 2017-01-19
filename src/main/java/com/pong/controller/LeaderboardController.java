package com.pong.controller;

import com.pong.gamestate.GameStateManager;
import com.pong.factory.MvcFactory;
import com.pong.factory.MvcWrapper;
import com.pong.gui.view.LeaderboardView;
import com.pong.gui.view.MainMenuView;
import com.pong.model.LeaderboardModel;
import com.pong.model.MainMenuModel;
import com.pong.gamestate.GameState;

/**
 * The LeaderboardController controls the input actions for the leaderboard screen.
 *
 * @author LBEVAN
 */
public class LeaderboardController implements Controller {

    // region data
    private LeaderboardModel model;
    private LeaderboardView view;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param model
     * @param view
     */
    public LeaderboardController(LeaderboardModel model, LeaderboardView view) {
        this.model = model;
        this.view = view;
    }
    // endregion

    // region public API
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
    public void start() {

    }
    // endregion

    // region private API
    /**
     * Create and set the action listeners to the view.
     */
    private void initActionListeners() {
        view.getClearButton().addActionListener((e) -> {
            model.clearLeaderboard();
            view.updateHighScoresDisplay();
        });

        view.getBackButton().addActionListener((e) -> {
            MvcWrapper<MainMenuModel, MainMenuView, MainMenuController> mvc = MvcFactory.createMainMenu();
            GameStateManager.getInstance().changeState(GameState.MENU, mvc);
        });
    }
    // endregion
}
