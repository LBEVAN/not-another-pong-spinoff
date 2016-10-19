package com.pong.entity;

import java.awt.*;

/**
 * @author LBEVAN
 */
public class Ball extends Entity {

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void render(Graphics graphics) {
        graphics.drawOval(x, y, width, height);
    }

    public void update() {

    }
}
