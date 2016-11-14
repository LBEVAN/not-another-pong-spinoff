package com.pong.factory;

import com.pong.controller.Controller;
import com.pong.controller.MainMenuController;
import com.pong.gui.frame.PongFrame;
import com.pong.gui.view.MainMenuView;
import com.pong.gui.view.View;
import com.pong.model.MainMenuModel;
import com.pong.model.Model;

/**
 * MVC factory implementation for the main menu.
 *
 * @author LBEVAN
 */
public class MVCMainMenuFactory extends MVCFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Model getModel() {
        return new MainMenuModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected View getView() {
        return new MainMenuView(PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Controller getController() {
        return new MainMenuController();
    }
}
