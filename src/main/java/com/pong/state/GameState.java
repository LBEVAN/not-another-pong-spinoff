package com.pong.state;

import com.pong.controller.Controller;
import com.pong.controller.GameOptionsController;
import com.pong.controller.MainMenuController;
import com.pong.controller.PongController;
import com.pong.gui.frame.PongFrame;
import com.pong.gui.view.GameOptionsView;
import com.pong.gui.view.MainMenuView;
import com.pong.gui.view.PongView;
import com.pong.gui.view.View;
import com.pong.model.GameOptionsModel;
import com.pong.model.MainMenuModel;
import com.pong.model.Model;
import com.pong.model.PongModel;

/**
 * A defined set of Game states - only one state is active at a given time.
 *
 * @author LBEVAN
 */
public enum GameState {

    MENU(
            new MainMenuModel(), new MainMenuView(PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT), new MainMenuController()
    ),
    GAME(
            new PongModel(), new PongView(PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT), new PongController()
    ),
    GAME_OPTIONS(
            new GameOptionsModel(), new GameOptionsView(PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT), new GameOptionsController()
    );

    /**
     * Enum constructor.
     *
     * @param model
     * @param view
     * @param controller
     */
    GameState(Model model, View view, Controller controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
    }

    private Model model;
    private View view;
    private Controller controller;

    /**
     * Retrieve the model.
     *
     * @return model
     */
    public Model getModel() {
        return model;
    }

    /**
     * Retrieve the view.
     *
     * @return view
     */
    public View getView() {
        return view;
    }

    /**
     * Retrieve the controller.
     *
     * @return controller
     */
    public Controller getController() {
        return controller;
    }
}
