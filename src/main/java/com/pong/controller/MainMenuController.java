package com.pong.controller;

import com.pong.GameStateManager;
import com.pong.factory.MvcFactory;
import com.pong.factory.MvcWrapper;
import com.pong.gui.view.GameOptionsView;
import com.pong.gui.view.MainMenuView;
import com.pong.model.GameOptionsModel;
import com.pong.model.MainMenuModel;
import com.pong.state.GameState;

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

    private final MainMenuModel model;
    private final MainMenuView view;

    private Timer animationTimer;

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

    /**
     * Create and set the action listeners to the view.
     */
    private void initActionListeners() {
        view.getExitButton().addActionListener((e) -> System.exit(0));
        view.getPlayButton().addActionListener((e) -> playButtonAction());
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
}
