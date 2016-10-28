package com.pong.model.entity;

import com.pong.gui.frame.PongFrame;
import com.pong.model.PongModel;

/**
 * The Computer class represents the computer entity in the game.
 *
 * @author LBEVAN
 */
public class Computer extends Entity {

    private final PongModel pongModel;

    private int speed = 2;

    /**
     * Player constructor.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param pongModel
     */
    public Computer(int x, int y, int width, int height, final PongModel pongModel) {
        super(x, y, width, height);
        this.pongModel = pongModel;
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        int ballY = pongModel.getBall().getY();

        // check if within top bounds
        // and the ball y is less than the paddle y
        if (getY() >= 20 && ballY < getY()) {
            // move up
            setY(getY() - speed);
        }

        // check if within bottom bounds
        // and the ball y is more than the paddle y
        if(getY() <= PongFrame.SCREEN_HEIGHT - 60 && ballY > getY()) {
            // move down
            setY(getY() + speed);
        }
    }
}
