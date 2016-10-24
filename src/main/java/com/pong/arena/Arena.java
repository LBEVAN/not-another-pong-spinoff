package com.pong.arena;

import com.pong.Pong;
import com.pong.entity.Ball;
import com.pong.entity.Computer;
import com.pong.entity.Entity;
import com.pong.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Arena class represents the game bounds, encapsulating game logic.
 *
 * @author LBEVAN
 */
public class Arena extends JPanel {

    private Entity player;
    private Entity ball;
    private Entity computer;
    private Timer gameTimer;

    /**
     * Default constructor.
     */
    public Arena() {
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        requestFocus();

        // create the player
        player = new Player(20, Pong.SCREEN_HEIGHT / 2, 20, 60);
        add(player);

        // create the ball
        ball = new Ball(Pong.SCREEN_WIDTH/2, Pong.SCREEN_HEIGHT/2, 25, 25, this);
        add(ball);

        // create the computer
        computer = new Computer(Pong.SCREEN_WIDTH - 40, Pong.SCREEN_HEIGHT / 2, 20, 60, this);
        add(computer);

        // schedule the timer - simple game loop for now, limited to 60 frames per second  (FPS)
        gameTimer = new Timer(1000/60, new GameLoop());
        gameTimer.start();
    }

    /**
     * {@inheritDoc}
     */
    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Pong.SCREEN_WIDTH, Pong.SCREEN_HEIGHT);

        graphics.setColor(Color.WHITE);
        graphics.drawLine(Pong.SCREEN_WIDTH / 2, 0, Pong.SCREEN_WIDTH / 2, Pong.SCREEN_HEIGHT);

        player.render(graphics);
        ball.render(graphics);
        computer.render(graphics);
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
            computer.render(graphics);
        }

        // repaint the arena
        repaint();

        // TODO: is there a need for a parent panel? Should this just be the GameView? Look into this!
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
        computer.update();
    }

    /**
     * The game loop executor. This will be called from the timer every tick.
     */
    private final class GameLoop implements ActionListener {

        /**
         * {@inheritDoc}
         */
        public void actionPerformed(ActionEvent e) {
            update();
            render();
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

    /**
     * Retrieve the Ball entity.
     *
     * @return ball
     */
    public final Entity getBall() {
        return ball;
    }

    /**
     * Retrieve the Computer entity.
     *
     * @return computer
     */
    public final Entity getComputer() {
        return computer;
    }
}
