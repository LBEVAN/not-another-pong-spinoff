package com.pong.entity;

import com.pong.Pong;
import com.pong.arena.Arena;

import java.awt.*;

/**
 * The Computer class represents the computer entity in the game.
 *
 * @author LBEVAN
 */
public class Computer extends Entity {

    private final Arena arena;
    private int speed = 6;

    /**
     * Player constructor.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Computer(int x, int y, int width, int height, Arena arena) {
        super(x, y, width, height);
        this.arena = arena;
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics graphics) {
        graphics.drawRect(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        int ballY = arena.getBall().getY();

        // check if within top bounds
        // and the ball y is less than the paddle y
        if (getY() >= 20 && ballY < getY()) {
            // move up
            setY(getY() - speed);
        }

        // check if within bottom bounds
        // and the ball y is more than the paddle y
        if(getY() <= Pong.SCREEN_HEIGHT - 60 && ballY > getY()) {
            // move down
            setY(getY() + speed);
        }
    }
}
