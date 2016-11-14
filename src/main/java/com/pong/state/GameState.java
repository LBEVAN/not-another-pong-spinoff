package com.pong.state;

import com.pong.factory.*;

/**
 * A defined set of Game states - only one state is active at a given time.
 *
 * @author LBEVAN
 */
public enum GameState {

    MENU(
            new MVCMainMenuFactory()
    ),
    GAME(
            new MVCPongFactory()
    ),
    GAME_OPTIONS(
            new MVCGameOptionsFactory()
    ),
    GAME_OVER(
            new MVCGameOverFactory()
    );

    private MVCFactory factory;

    /**
     * Enum constructor.
     *
     * @param factory
     */
    GameState(MVCFactory factory) {
        this.factory = factory;
    }

    /**
     * Retrieve the associated MVC factory.
     *
     * @return factory
     */
    public MVCFactory getFactory() {
        return factory;
    }
}
