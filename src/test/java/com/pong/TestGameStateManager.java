package com.pong;

import com.pong.ai.difficulty.EasyDifficulty;
import com.pong.controller.GameOptionsController;
import com.pong.factory.MvcFactory;
import com.pong.factory.MvcWrapper;
import com.pong.gui.view.GameOptionsView;
import com.pong.gui.view.GameStateView;
import com.pong.model.GameOptionsModel;
import com.pong.model.wrapper.GameOptions;
import com.pong.state.GameState;
import com.pong.system.resource.ResourceLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

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
        new ResourceLoader().loadResources();
    }

    @After
    public void tearDown() {
        resetGameStateManager();
    }

    @Test
    public void testChangeState() {
        // run the test
        GameStateManager.getInstance().changeState(GameState.MENU, MvcFactory.createMainMenu());

        // assert the stack size is 1
        assertEquals(1, GameStateManager.getInstance().getGameStateStackSize());

        // assert the mvc component is not null
        assertNotNull(GameStateManager.getInstance().getCurrentMvc());
    }

    @Test
    public void testReturnToPreviousState() {
        // set up test data - add a number of states to the stack
        GameStateManager.getInstance().changeState(GameState.MENU, MvcFactory.createMainMenu());
        GameStateManager.getInstance().changeState(GameState.GAME_OPTIONS, MvcFactory.createGameOptions());
        GameStateManager.getInstance().changeState(GameState.GAME, MvcFactory.createPong(new GameOptions(new EasyDifficulty())));

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

        // assert the previous state is Menu
        assertEquals(GameState.MENU, gameState);
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
