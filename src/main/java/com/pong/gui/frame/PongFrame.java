package com.pong.gui.frame;

import com.pong.GameStateManager;
import com.pong.factory.MvcFactory;
import com.pong.gui.view.GameStateView;
import com.pong.state.GameState;
import com.pong.system.ResourceManager;

import javax.swing.*;

/**
 * PongFrame is the main window of the application.
 * It is responsible for initialising itself and the starting view.
 *
 * @author LBEVAN
 */
public class PongFrame extends JFrame {

    public static final String VERSION = "1.0";
    public static final String TITLE = "Not Another Pong Spin-off";
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = SCREEN_WIDTH / 16 * 9;

    /**
     * Constructor.
     */
    public PongFrame() {
        // Load the application resources
        ResourceManager.getInstance().loadResources();

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setTitle(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // bind the application view
        GameStateView gameStateView = new GameStateView(SCREEN_WIDTH, SCREEN_HEIGHT);
        GameStateManager.getInstance().setGameStateView(gameStateView);

        GameStateManager.getInstance().changeState(GameState.MENU, MvcFactory.createMainMenu());
        setContentPane(gameStateView);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
