package com.pong.controller;

import com.pong.factory.MvcFactory;
import com.pong.factory.MvcWrapper;
import com.pong.gamestate.GameState;
import com.pong.gamestate.GameStateManager;
import com.pong.gui.view.GameOptionsView;
import com.pong.gui.view.HowToPlayView;
import com.pong.gui.view.LeaderboardView;
import com.pong.gui.view.MainMenuView;
import com.pong.model.GameOptionsModel;
import com.pong.model.LeaderboardModel;
import com.pong.model.MainMenuModel;
import com.pong.model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MainMenuController controls input on the MainMenuView. It also controls
 * the animation timer and delegates those tasks to the model and view.
 *
 * @author LBEVAN
 */
public class MainMenuController implements Controller {

    // region data
    private final MainMenuModel model;
    private final MainMenuView view;

    private Timer animationTimer;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param model
     * @param view
     */
    public MainMenuController(final MainMenuModel model, final MainMenuView view) {
        this.model = model;
        this.view = view;

        this.animationTimer = new Timer(1000/60, new AnimatorLoop());
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
        animationTimer.start();
    }
    // endregion

    // region private API
    /**
     * Create and set the action listeners to the view.
     */
    private void initActionListeners() {
        view.getExitButton().addActionListener((e) -> System.exit(0));
        view.getPlayButton().addActionListener((e) -> playButtonAction());
        view.getLeaderboardButton().addActionListener((e) -> leaderboardButtonAction());
        view.getHowToPlayButton().addActionListener((e) -> howToPlayButtonAction());
    }

    /**
     * Perform the play button action - switch to the game.
     */
    private void playButtonAction() {
        animationTimer.stop();

        MvcWrapper<GameOptionsModel, GameOptionsView, GameOptionsController> mvc = MvcFactory.createGameOptions();
        GameStateManager.getInstance().changeState(GameState.GAME_OPTIONS, mvc);
    }

    /**
     * Perform the leaderboard button action - switch to the leaderboard view
     */
    private void leaderboardButtonAction() {
        MvcWrapper<LeaderboardModel, LeaderboardView, LeaderboardController> mvc = MvcFactory.createLeaderboard();
        GameStateManager.getInstance().changeState(GameState.LEADERBOARD, mvc);
    }

    /**
     * Perform the howToPlay button action - switch to the How To Play view.
     */
    private void howToPlayButtonAction() {
        MvcWrapper<Model, HowToPlayView, HowToPlayController> mvc = MvcFactory.createHowToPlay();
        GameStateManager.getInstance().changeState(GameState.HOW_TO_PLAY, mvc);
    }

    /**
     * The animator loop. This will be called from the timer every tick.
     */
    private final class AnimatorLoop implements ActionListener {

        /**
         * {@inheritDoc}
         */
        public void actionPerformed(ActionEvent e) {
            model.update();
            view.repaint();
        }
    }
    // endregion
}