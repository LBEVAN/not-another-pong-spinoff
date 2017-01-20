package com.pong.controller;

import com.pong.gamestate.GameStateManager;
import com.pong.gui.view.HowToPlayView;

/**
 * Controller for the How To Play screen.
 *
 * @author LBEVAN
 */
public class HowToPlayController implements Controller {

    // region data
    private final HowToPlayView view;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param view
     */
    public HowToPlayController(HowToPlayView view) {
        this.view = view;
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        initActionListeners();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {

    }
    // endregion

    // region private API

    /**
     * Initialise the action listeners.
     */
    private void initActionListeners() {
        view.getBackButton().addActionListener((e) -> GameStateManager.getInstance().returnToPreviousState());
    }
    // endregion
}
