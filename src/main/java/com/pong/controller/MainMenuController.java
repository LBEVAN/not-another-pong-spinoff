package com.pong.controller;

import com.pong.gui.frame.PongFrame;
import com.pong.model.MainMenuModel;
import com.pong.gui.view.MainMenuView;
import com.pong.gui.view.Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MainMenuController handles the input for the main menu.
 *
 * @author LBEVAN
 */
public class MainMenuController {

    private MainMenuView mainMenuView;
    private PongFrame pongFrame;
    private MainMenuModel mainMenuModel;

    private Timer animationTimer;

    public MainMenuController(MainMenuView mainMenuView, PongFrame pongFrame, MainMenuModel mainMenuModel) {
        this.mainMenuView = mainMenuView;
        this.pongFrame = pongFrame;
        this.mainMenuModel = mainMenuModel;

        this.animationTimer = new Timer(1000/60, new AnimatorLoop());
        animationTimer.start();

        initActionListeners();
    }

    /**
     * Create and set the action listeners to the view.
     */
    private void initActionListeners() {
        // exit button
        mainMenuView.getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitButtonAction();
            }
        });

        // play button
        mainMenuView.getPlayButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               playButtonAction();
            }
        });
    }

    /**
     * Perform the exit button action - exit the application.
     */
    private void exitButtonAction() {
        System.exit(0);
    }

    /**
     * Perform the play button action - switch to the game.
     */
    private void playButtonAction() {
        animationTimer.stop();
        pongFrame.switchView(Views.GAME);
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
