package com.pong.controller;

import com.pong.GameStateManager;
import com.pong.gui.view.MainMenuView;
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
public class MainMenuController implements Controller<MainMenuModel, MainMenuView>{

    private MainMenuView mainMenuView;
    private MainMenuModel mainMenuModel;

    private Timer animationTimer;

    /**
     * Constructor.
     */
    public MainMenuController() {
        this.animationTimer = new Timer(1000/60, new AnimatorLoop());
    }

    /**
     * Create and set the action listeners to the view.
     */
    private void initActionListeners() {
        // exit button
        mainMenuView.getExitButton().addActionListener((e) -> System.exit(0));
        mainMenuView.getPlayButton().addActionListener((e) -> playButtonAction());
    }

    /**
     * Perform the play button action - switch to the game.
     */
    private void playButtonAction() {
        animationTimer.stop();
        GameStateManager.getInstance().changeState(GameState.GAME_OPTIONS);
    }

    /**
     * {@inheritDoc}
     */
    public void init(MainMenuModel model, MainMenuView view) {
        this.mainMenuModel = model;
        this.mainMenuView = view;

        initActionListeners();

        animationTimer.start();
    }

    /**
     * The animator loop. This will be called from the timer every tick.
     */
    private final class AnimatorLoop implements ActionListener {

        /**
         * {@inheritDoc}
         */
        public void actionPerformed(ActionEvent e) {
            mainMenuModel.update();
            mainMenuView.repaint();
        }
    }
}
