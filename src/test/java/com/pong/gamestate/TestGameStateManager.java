package com.pong.gamestate;

import com.pong.controller.GameOptionsController;
import com.pong.factory.MvcFactory;
import com.pong.factory.MvcWrapper;
import com.pong.gamestate.GameStateManager;
import com.pong.gui.view.GameOptionsView;
import com.pong.gui.view.GameStateView;
import com.pong.model.GameOptionsModel;
import com.pong.gamestate.GameState;
import com.pong.system.resource.ResourceManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Test class for {@link GameStateManager}
 *
 * @author LBEVAN
 */
public class TestGameStateManager {

    private GameStateView gameStateView;

    // region init
    @Before
    public void setUp() {
        gameStateView = new GameStateView(1280, 720);
        GameStateManager.getInstance().setGameStateView(gameStateView);

        ResourceManager.getInstance().registerCustomFont(new Font("Test", 100, 100));
    }

    @After
    public void tearDown() {
        resetGameStateManager();
    }
    // endregion

    // region changeState tests
    @Test
    public void testChangeState() {
        // run the test
        GameStateManager.getInstance().changeState(GameState.MENU, MvcFactory.createMainMenu());

        // assert the stack size is 1
        assertEquals(1, GameStateManager.getInstance().getGameStateStackSize());

        // assert the mvc component is not null
        assertNotNull(GameStateManager.getInstance().getCurrentMvc());
    }
    // endregion

    // region previousState tests
    @Test
    public void testReturnToPreviousState() {
        // set up test data - add a number of states to the stack
        GameStateManager.getInstance().changeState(GameState.MENU, MvcFactory.createMainMenu());
        GameStateManager.getInstance().changeState(GameState.GAME_OPTIONS, MvcFactory.createGameOptions());
        GameStateManager.getInstance().changeState(GameState.LEADERBOARD, MvcFactory.createLeaderboard());

        // run the test
        GameStateManager.getInstance().returnToPreviousState();

        // assert that the stack size has been reduced by one
        assertEquals(2, GameStateManager.getInstance().getGameStateStackSize());

        // assert that the current top of stack is game options
        MvcWrapper mvcWrapper = GameStateManager.getInstance().getCurrentMvc();
        assertNotNull(mvcWrapper);
        assertTrue(mvcWrapper.getModel() != null && mvcWrapper.getModel() instanceof GameOptionsModel);
        assertTrue(mvcWrapper.getView() != null && mvcWrapper.getView() instanceof GameOptionsView);
        assertTrue(mvcWrapper.getController() != null && mvcWrapper.getController() instanceof GameOptionsController);
    }

    @Test
    public void testGetPreviousState() {
        // set up test data - add a number of states to the stack
        GameStateManager.getInstance().changeState(GameState.MENU, MvcFactory.createMainMenu());
        GameStateManager.getInstance().changeState(GameState.GAME_OPTIONS, MvcFactory.createGameOptions());

        // run the test
        GameState gameState = GameStateManager.getInstance().getPreviousState();

        // assert the previous gamestate is Menu
        assertEquals(GameState.MENU, gameState);
    }
    // endregion

    // region helpers
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
    // endregion
}
