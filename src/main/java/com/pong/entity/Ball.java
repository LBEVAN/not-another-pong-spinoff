package com.pong.entity;

import com.pong.arena.Arena;

import java.awt.*;

/**
 * @author LBEVAN
 */
public class Ball extends Entity {

    private final Arena arena;

    private int deltaX = 8;
    private int deltaY = 8;

    public Ball(int x, int y, int width, int height, final Arena arena) {
        super(x, y, width, height);
        this.arena = arena;
    }

    public void render(Graphics graphics) {
        graphics.drawOval(x, y, width, height);
    }

    public void update() {
        move();

        checkCollisionWithPaddle();
    }

    /**
     * Move the ball, called every tick, by the delta (speed).
     */
    private void move() {
        x += deltaX;
        y += deltaY;
    }

    /**
     * Check if the Ball collides with the paddle.
     * If so inverse the x delta (direction).
     */
    private void checkCollisionWithPaddle() {
        if(doesIntersect(arena.getPlayer())) {
            deltaX = -deltaX;
        }
    }

    /**
     * Check if this entity intersects another entity.
     *
     * @param entity
     * @return boolean
     */
    private boolean doesIntersect(final Entity entity) {
        if (entity.getWidth() <= 0 || entity.getHeight() <= 0) {
            return false;
        }
        return (entity.getX() + entity.getWidth() > this.getX() &&
                entity.getY() + entity.getHeight() > this.getY() &&
                entity.getX() < this.getX() + getWidth() &&
                entity.getY() < this.getY() + getHeight());
    }
}
