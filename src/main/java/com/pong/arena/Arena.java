package com.pong.arena;

import com.pong.Pong;
import com.pong.entity.Entity;
import com.pong.entity.Paddle;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Arena class represents the game bounds, encapsulating game logic.
 *
 * @author LBEVAN
 */
public class Arena extends Canvas {

    private Entity player;
    private Timer gameTimer;

    /**
     * Default constructor.
     */
    public Arena() {
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        requestFocus();

        // create the player
        player = new Paddle(20, Pong.SCREEN_HEIGHT / 2, 20, 60);

        // schedule the timer - simple game loop for now, limited to 60 frames per second  (FPS)
        gameTimer = new Timer();
        gameTimer.schedule(new GameLoop(), 0, 1000 / 60);
    }

    /**
     * {@inheritDoc}
     */
    public void paint(Graphics graphics) {
        player.render(graphics);
    }

    /**
     * Render the game.
     */
    private void render() {
        // TODO: Probably want to use some sort of BufferStrategy so both rendering and logic happen together on the same thread.
        Graphics graphics = getGraphics();
        if(graphics != null) {
            // render entities
            player.render(graphics);
        }

        // repaint the arena
        repaint();

        // repaint the parent panel
        getParent().repaint();
    }

    /**
     * Update the game.
     */
    private void update() {
        player.update();
    }

    /**
     * TimerTask for the game loop.
     */
    private class GameLoop extends TimerTask {

        /**
         * {@inheritDoc}
         */
        public void run() {
            render();
            update();
        }
    }
}
