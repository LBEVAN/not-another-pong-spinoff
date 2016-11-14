package com.pong.factory;

import com.pong.controller.Controller;
import com.pong.controller.PongController;
import com.pong.gui.frame.PongFrame;
import com.pong.gui.view.PongView;
import com.pong.gui.view.View;
import com.pong.model.Model;
import com.pong.model.PongModel;

/**
 * MVC factory implementation for the actual game.
 *
 * @author LBEVAN
 */
public class MVCPongFactory extends MVCFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Model getModel() {
        return new PongModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected View getView() {
        return new PongView(PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Controller getController() {
        return new PongController();
    }
}
