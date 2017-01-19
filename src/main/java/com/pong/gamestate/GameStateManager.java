package com.pong.gamestate;

import com.pong.factory.MvcWrapper;
import com.pong.gui.view.GameStateView;

import java.util.Stack;

/**
 * The GameStateManager manages all view states in the game (e.g. Menu, Game).
 *
 * @author LBEVAN
 */
public class GameStateManager {

    private static GameStateManager instance;

    private GameStateView gameStateView;
    private Stack<CombinedGameStateMvc> gameStateStack = new Stack<>();

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
     * Set the gameStateView. This should be done only once - from the initial class (i.e. PongFrame).
     *
     * @param gameStateView
     */
    public void setGameStateView(GameStateView gameStateView) {
        this.gameStateView = gameStateView;
    }

    /**
     * Change the current game gamestate to the new specified gamestate.
     *
     * @param gameState
     * @param mvc
     */
    public void changeState(final GameState gameState, final MvcWrapper mvc) {
        CombinedGameStateMvc combinedGameStateMvc = new CombinedGameStateMvc(gameState, mvc);
        gameStateStack.add(combinedGameStateMvc);
        gameStateView.changeView(mvc.getView());
    }

    /**
     * Return to the previous gamestate.
     * Note: this will not recreate the mvc components!
     */
    public void returnToPreviousState() {
        gameStateStack.pop();
        CombinedGameStateMvc combinedGameStateMvc = gameStateStack.peek();
        gameStateView.changeView(combinedGameStateMvc.getMvc().getView());
    }

    /**
     * Retrieve the size of the game gamestate stack.
     *
     * @return size
     */
    public int getGameStateStackSize() {
        return gameStateStack.size();
    }

    /**
     * Retrieve the current active mvc bundle.
     *
     * @return mvc.
     */
    public MvcWrapper getCurrentMvc() {
        return gameStateStack.peek().getMvc();
    }

    /**
     * Retrieve the previous game gamestate.
     *
     * @return gameState
     */
    public GameState getPreviousState() {
        return gameStateStack.get(getGameStateStackSize() - 2).getGameState();
    }

    /**
     * Wrapper class for the GameState and MvcWrapper components.
     * This is used in the gameStateStack.
     */
    private class CombinedGameStateMvc {

        private GameState gameState;
        private MvcWrapper mvc;

        /**
         * Constructor.
         *
         * @param gameState
         * @param mvc
         */
        public CombinedGameStateMvc(final GameState gameState, final MvcWrapper mvc) {
            this.gameState = gameState;
            this.mvc = mvc;
        }

        /**
         * Retrieve the game gamestate.
         *
         * @return gameState
         */
        public GameState getGameState() {
            return gameState;
        }

        /**
         * Retrieve the mvc component bundle.
         *
         * @return mvc
         */
        public MvcWrapper getMvc() {
            return mvc;
        }
    }
}
