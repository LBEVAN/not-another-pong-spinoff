package com.pong.arena;

import com.pong.Pong;
import com.pong.entity.Entity;
import com.pong.entity.Paddle;

import java.awt.*;

/**
 * Arena class represents the game bounds, encapsulating game logic.
 *
 * @author LBEVAN
 */
public class Arena extends Canvas {

    private Entity player;

    /**
     * Default constructor.
     */
    public Arena() {
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        requestFocus();

        // create the player
        player = new Paddle(20, Pong.SCREEN_HEIGHT / 2, 20, 60);
    }

    /**
     * {@inheritDoc}
     */
    public void paint(Graphics graphics) {
        player.render(graphics);
    }
}
