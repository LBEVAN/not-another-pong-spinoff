package com.pong.arena;

import com.pong.Pong;
import com.pong.entity.Ball;
import com.pong.entity.Entity;
import com.pong.entity.Paddle;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Arena class represents the game bounds, encapsulating game logic.
 *
 * @author LBEVAN
 */
public class Arena extends JPanel {

    private Entity player;
    private Entity ball;
    private Timer gameTimer;

    /**
     * Default constructor.
     */
    public Arena() {
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        requestFocus();

        // create the player
        player = new Paddle(20, Pong.SCREEN_HEIGHT / 2, 20, 60);
        add(player);

        // create the ball
        ball = new Ball(Pong.SCREEN_WIDTH/2, Pong.SCREEN_HEIGHT/2, 25, 25, this);
        add(ball);

        // schedule the timer - simple game loop for now, limited to 60 frames per second  (FPS)
        gameTimer = new Timer();
        gameTimer.schedule(new GameLoop(), 0, 1000 / 16);
    }

    /**
     * {@inheritDoc}
     */
    public void paint(Graphics graphics) {
        player.render(graphics);
        ball.render(graphics);
    }

    /**
     * Render the game.
     */
    private void render() {
        Graphics graphics = getGraphics();
        if(graphics != null) {
            // render entities
            player.render(graphics);
            ball.render(graphics);
        }

        // repaint the arena
        repaint();

        // repaint the parent panel
        if(getParent() != null) {
            getParent().repaint();
        }
    }

    /**
     * Update the game.
     */
    private void update() {
        player.update();
        ball.update();
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

    /**
     * Retrieve the Player entity.
     *
     * @return player
     */
    public final Entity getPlayer() {
        return player;
    }
}
