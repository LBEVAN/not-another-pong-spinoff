package com.pong;

import com.pong.controller.GameOptionsController;
import com.pong.factory.MVCWrapper;
import com.pong.gui.view.GameOptionsView;
import com.pong.gui.view.GameStateView;
import com.pong.model.GameOptionsModel;
import com.pong.state.GameState;
import com.pong.system.ResourceManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Stack;

import static org.junit.Assert.*;

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

        // there is a dependency on the resource manager - load it here
        ResourceManager.getInstance().loadResources();
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
        assertNotNull(GameStateManager.getInstance().getCurrentMVC());
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
        MVCWrapper mvcWrapper = GameStateManager.getInstance().getCurrentMVC();
        assertNotNull(GameStateManager.getInstance().getCurrentMVC());
        assertTrue(mvcWrapper.getModel() != null && mvcWrapper.getModel() instanceof GameOptionsModel);
        assertTrue(mvcWrapper.getView() != null && mvcWrapper.getView() instanceof GameOptionsView);
        assertTrue(mvcWrapper.getController() != null && mvcWrapper.getController() instanceof GameOptionsController);
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
