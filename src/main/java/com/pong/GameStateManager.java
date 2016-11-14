package com.pong;

import com.pong.factory.MVCWrapper;
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
    private Stack<GameState> gameStateStack = new Stack<>();
    private MVCWrapper mvc;

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
        mvc = gameState.getFactory().create();
        gameStateStack.add(gameState);
        gameStateView.changeView(mvc.getView());
    }

    /**
     * Return to the previous state
     */
    public void returnToPreviousState() {
        gameStateStack.pop();
        GameState gameState = gameStateStack.peek();
        mvc = gameState.getFactory().create();
        gameStateView.changeView(mvc.getView());
    }

    /**
     * Retrieve the current game state stack.
     *
     * @return gameStateStack
     */
    public Stack<GameState> getGameStateStack() {
        return gameStateStack;
    }

    /**
     * Retrieve the current active MVC components.
     *
     * @return mvc
     */
    public MVCWrapper getCurrentMVC() {
        return mvc;
    }
}
