package com.pong;

import com.pong.gui.view.GameStateView;
import com.pong.state.GameState;

import java.util.Stack;

/**
 * The GameStateManager manages all view states in the game (e.g. Menu, Game).
 *
 * @author LBEVAN
 */
public class GameStateManager {

    private static GameStateManager instance;

    private GameStateView gameStateView;
    private Stack<GameState> gameStateStack = new Stack<GameState>();

    /**
     * Private constructor to stop external creation.
     */
    private GameStateManager() {}

    /**
     * Retrieve the instance of the Menu Manager.
     *
     * @return menuManager
     */
    public static GameStateManager getInstance() {
        if(instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    /**
     * Set the gameStateView.
     *
     * @param gameStateView
     */
    public void setGameStateView(GameStateView gameStateView) {
        this.gameStateView = gameStateView;
    }

    /**
     * Change the current active state,
     * pushing the previous state down in the stack.
     *
     * @param gameState
     */
    public void changeState(GameState gameState) {
        gameStateStack.add(gameState);
        gameState.getView().init(gameState.getModel());
        gameState.getController().init(gameState.getModel(), gameState.getView());

        gameStateView.changeView(gameState.getView());
    }

    /**
     * Return to the previous state
     */
    public void returnToPreviousState() {
        gameStateStack.pop();
        changeState(gameStateStack.peek());
    }
}