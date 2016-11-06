package com.pong.gui.view;

import com.pong.model.PongModel;
import com.pong.model.entity.Ball;
import com.pong.model.entity.Computer;
import com.pong.model.entity.Player;

import java.awt.*;

/**
 * The PongView is the view component of the game.
 * It handles the repainting every tick.
 *
 * @author LBEVAN
 */
public class PongView extends View<PongModel> {

    private PongModel pongModel;

    /**
     * Constructor.
     *
     * @param width
     * @param height
     */
    public PongView(int width, int height) {
        super(width, height);

        setOpaque(true);
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        requestFocus();
        setFocusable(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // draw the background
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);

        // paint the center line
        graphics.setColor(Color.WHITE);
        graphics.drawLine(width / 2, 0, width / 2, height);

        // paint the scores
        graphics.drawString("Player: " + pongModel.getPlayerScore(), width / 2 - 70, 10);
        graphics.drawString("Computer: " + pongModel.getComputerScore(), width / 2 + 20, 10);

        paintBall(graphics);
        paintPlayer(graphics);
        paintComputer(graphics);
    }

    /**
     * Paint the Ball entity.
     *
     * @param graphics
     */
    private void paintBall(Graphics graphics) {
        final Ball ball = pongModel.getBall();
        graphics.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
    }

    /**
     * Paint the Player entity.
     *
     * @param graphics
     */
    private void paintPlayer(Graphics graphics) {
        final Player player = pongModel.getPlayer();
        graphics.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    /**
     * Paint the Computer entity.
     *
     * @param graphics
     */
    private void paintComputer(Graphics graphics) {
        final Computer computer = pongModel.getComputer();
        graphics.fillRect(computer.getX(), computer.getY(), computer.getWidth(), computer.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getViewName() {
        return "PongView";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(PongModel model) {
        this.pongModel = model;
    }
}
