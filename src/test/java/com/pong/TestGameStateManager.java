package com.pong;

import com.pong.controller.GameOptionsController;
import com.pong.controller.MainMenuController;
import com.pong.gui.view.GameOptionsView;
import com.pong.gui.view.GameStateView;
import com.pong.gui.view.MainMenuView;
import com.pong.model.GameOptionsModel;
import com.pong.model.MainMenuModel;
import com.pong.state.GameState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link GameStateManager}
 *
 * @author LBEVAN
 */
public class TestGameStateManager {

    private GameStateView gameStateView;

    @Before
    public void setUp() {
        gameStateView = new GameStateView(1280, 720);
        GameStateManager.getInstance().setGameStateView(gameStateView);
    }

    @After
    public void tearDown() {
        resetGameStateManager();
    }

    @Test
    public void testChangeState() {
        // run the test
        GameStateManager.getInstance().changeState(GameState.MENU);

        // assert that the game state has been added
        Stack<GameState> gameStateStack = GameStateManager.getInstance().getGameStateStack();
        assertEquals(1, gameStateStack.size());

        // assert that the initialisation has happened
        GameState currentGameState = gameStateStack.peek();
        assertTrue(currentGameState.getModel() != null && currentGameState.getModel() instanceof MainMenuModel);
        assertTrue(currentGameState.getView() != null && currentGameState.getView() instanceof MainMenuView);
        assertTrue(currentGameState.getController() != null && currentGameState.getController() instanceof MainMenuController);
    }

    @Test
    public void testReturnToPreviousState() {
        // set up test data - add a number of states to the stack
        GameStateManager.getInstance().changeState(GameState.MENU);
        GameStateManager.getInstance().changeState(GameState.GAME_OPTIONS);
        GameStateManager.getInstance().changeState(GameState.GAME);

        // run the test
        GameStateManager.getInstance().returnToPreviousState();

        // assert that the stack size has been reduced by one
        Stack<GameState> gameStateStack = GameStateManager.getInstance().getGameStateStack();
        assertEquals(2, gameStateStack.size());

        // assert that the current top of stack is game options
        GameState currentGameState = gameStateStack.peek();
        assertTrue(currentGameState.getModel() != null && currentGameState.getModel() instanceof GameOptionsModel);
        assertTrue(currentGameState.getView() != null && currentGameState.getView() instanceof GameOptionsView);
        assertTrue(currentGameState.getController() != null && currentGameState.getController() instanceof GameOptionsController);
    }

    /**
     * Reset the GameStateManager using reflection.
     */
    private void resetGameStateManager() {
        Field instance = null;
        try {
            instance = GameStateManager.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
