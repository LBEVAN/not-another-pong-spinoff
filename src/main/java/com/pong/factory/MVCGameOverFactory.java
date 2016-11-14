package com.pong.factory;

import com.pong.controller.Controller;
import com.pong.controller.GameOverController;
import com.pong.gui.frame.PongFrame;
import com.pong.gui.view.GameOverView;
import com.pong.gui.view.View;
import com.pong.model.GameOverModel;
import com.pong.model.Model;

/**
 * MVC factory implementation for the game over screen.
 *
 * @author LBEVAN
 */
public class MVCGameOverFactory extends MVCFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Model getModel() {
        return new GameOverModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected View getView() {
        return new GameOverView(PongFrame.SCREEN_WIDTH, PongFrame.SCREEN_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Controller getController() {
        return new GameOverController();
    }
}
