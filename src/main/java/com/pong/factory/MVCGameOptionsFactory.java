package com.pong.factory;

import com.pong.controller.Controller;
import com.pong.controller.GameOptionsController;
import com.pong.gui.frame.PongFrame;
import com.pong.gui.view.GameOptionsView;
import com.pong.gui.view.View;
import com.pong.model.GameOptionsModel;
import com.pong.model.Model;

/**
 * MVC factory implementation for the game options screen.
 *
 * @author LBEVAN
 */
public class MVCGameOptionsFactory extends MVCFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Model getModel() {
        return new GameOptionsModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected View getView() {
        return new GameOptionsView(PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Controller getController() {
        return new GameOptionsController();
    }
}
