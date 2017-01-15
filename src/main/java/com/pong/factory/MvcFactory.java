package com.pong.factory;

import com.pong.controller.*;
import com.pong.gui.frame.PongFrame;
import com.pong.gui.view.*;
import com.pong.model.*;
import com.pong.model.wrapper.GameOptions;

/**
 * MvcFactory provides producer methods for creating bundles of MVC components.
 *
 * @author LBEVAN
 */
public class MvcFactory {

    /**
     * Create all MVC components for the Main Menu screen.
     *
     * @return mvc
     */
    public static MvcWrapper<MainMenuModel, MainMenuView, MainMenuController> createMainMenu() {
        MainMenuModel model = new MainMenuModel();
        MainMenuView view = new MainMenuView(model, PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT);
        MainMenuController controller = new MainMenuController(model, view);

        controller.bind();
        controller.start();

        return new MvcWrapper<>(model, view, controller);
    }

    /**
     * Create all MVC components for the Game Options screen.
     *
     * @return mvc
     */
    public static MvcWrapper<GameOptionsModel, GameOptionsView, GameOptionsController> createGameOptions() {
        GameOptionsModel model = new GameOptionsModel();
        GameOptionsView view = new GameOptionsView(model, PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT);
        GameOptionsController controller = new GameOptionsController(model, view);

        controller.bind();
        controller.start();

        return new MvcWrapper<>(model, view, controller);
    }

    /**
     * Create all MVC components for the Pong game.
     *
     * @param gameOptions
     * @return mvc
     */
    public static MvcWrapper<PongModel, PongView, PongController> createPong(final GameOptions gameOptions) {
        PongModel model = new PongModel(gameOptions);
        PongView view = new PongView(model, PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT);
        PongController controller = new PongController(model, view);

        controller.bind();
        controller.start();

        return new MvcWrapper<>(model, view, controller);
    }

    /**
     * Create all MVC components for the Game Over screen.
     *
     * @param playerScore
     * @param computerScore
     * @return mvc
     */
    public static MvcWrapper<GameOverModel, GameOverView, GameOverController> createGameOver(final int playerScore, final int computerScore) {
        GameOverModel model = new GameOverModel(playerScore, computerScore);
        GameOverView view = new GameOverView(model, PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT);
        GameOverController controller = new GameOverController(model, view);

        controller.bind();
        controller.start();

        return new MvcWrapper<>(model, view, controller);
    }

    /**
     * Create all MVC components for the leaderboard screen.
     *
     * @return mvc
     */
    public static MvcWrapper<LeaderboardModel, LeaderboardView, LeaderboardController> createLeaderboard() {
        LeaderboardModel model = new LeaderboardModel();
        LeaderboardView view = new LeaderboardView(model, PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT);
        LeaderboardController controller = new LeaderboardController(model, view);

        controller.bind();
        controller.start();

        return new MvcWrapper<>(model, view, controller);
    }
}
