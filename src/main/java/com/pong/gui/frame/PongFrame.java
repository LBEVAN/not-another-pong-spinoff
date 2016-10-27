package com.pong.gui.frame;

import com.pong.controller.MainMenuController;
import com.pong.controller.PongController;
import com.pong.model.MainMenuModel;
import com.pong.model.PongModel;
import com.pong.gui.view.MainMenuView;
import com.pong.gui.view.PongView;
import com.pong.gui.view.Views;

import javax.swing.*;

/**
 * PongFrame is the main window of the application.
 * It is responsible for initialising itself and the starting view.
 *
 * @author LBEVAN
 */
public class PongFrame extends JFrame {

    public static final String VERSION = "v1.0";
    public static final String TITLE = "Not Another Pong Spin-off";
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 400;

    private JPanel panelHolder;

    /**
     * Constructor.
     */
    public PongFrame() {
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setTitle(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Switch the view of the application - one of the views from {@link Views}
     *
     * @param view
     */
    public void switchView(Views view) {
        switch (view) {
            case MAIN_MENU:
                // switch to main menu
                MainMenuModel mainMenuModel = new MainMenuModel();
                MainMenuView mainMenuView = new MainMenuView(mainMenuModel, PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT);

                setContentPane(mainMenuView);
                revalidate();
                pack();
                setLocationRelativeTo(null);

                MainMenuController mainMenuController = new MainMenuController(mainMenuView, this, mainMenuModel);
                break;

            case GAME:
                // switch to game and start timer
                PongModel pongModel = new PongModel();
                PongView pongView = new PongView(PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT, pongModel);

                setContentPane(pongView);
                revalidate();
                pack();
                setLocationRelativeTo(null);

                PongController pongController = new PongController(pongModel, this, pongView);
                pongController.start();
                break;
        }
    }
}
