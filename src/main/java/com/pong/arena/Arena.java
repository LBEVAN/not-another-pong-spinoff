package com.pong.arena;

import com.pong.Pong;
import com.pong.entity.Ball;
import com.pong.entity.Computer;
import com.pong.entity.Entity;
import com.pong.entity.Player;
import com.pong.listener.BallListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Arena class represents the game bounds, encapsulating game logic.
 *
 * @author LBEVAN
 */
public class Arena extends JPanel implements BallListener {

    private Player player;
    private Ball ball;
    private Computer computer;
    private Timer gameTimer;

    int maxScore = 6;

    int playerScore = 0;
    int computerScore = 0;

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
        ball.addListener(this);

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
        // draw the background
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Pong.SCREEN_WIDTH, Pong.SCREEN_HEIGHT);

        // draw the center line
        graphics.setColor(Color.WHITE);
        graphics.drawLine(Pong.SCREEN_WIDTH / 2, 0, Pong.SCREEN_WIDTH / 2, Pong.SCREEN_HEIGHT);

        // draw the scores
        graphics.drawString("Player: " + playerScore, Pong.SCREEN_WIDTH / 2 - 70, 10);
        graphics.drawString("Computer: " + computerScore, Pong.SCREEN_WIDTH / 2 + 20, 10);

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
     * Check if the Player or the Computer has won (i.e. hit the maxScore).
     */
    private void checkGameOver() {
        if(playerScore >= maxScore || computerScore >= maxScore) {
            // someone has one, end the game
            gameTimer.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void playerScored() {
        playerScore++;
    }

    /**
     * {@inheritDoc}
     */
    public void computerScored() {
        computerScore++;
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
            checkGameOver();
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
