package com.pong.model;

import com.pong.gui.frame.PongFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * MainMenuModel represents the model of the main menu.
 * It stores animation entities and performs any necessary
 * updates when prompted by the controller.
 *
 * @author LBEVAN
 */
public class MainMenuModel implements Model {

    private int maxAnimatedBalls = 30;
    private List<AnimatedBall> animatedBalls = new ArrayList<AnimatedBall>(maxAnimatedBalls);

    /**
     * Update the animation entities.
     */
    public void update() {
        Iterator<AnimatedBall> animatedBallIter = animatedBalls.iterator();
        while(animatedBallIter.hasNext()) {
            AnimatedBall ball = animatedBallIter.next();
            ball.update();
            if(ball.shouldDestroy) {
                animatedBallIter.remove();
            }
        }

        if(animatedBalls.size() < maxAnimatedBalls) {
            Random random = new Random();
            int rand = random.nextInt(4);

            if(rand == 1) {
                animatedBalls.add(new AnimatedBall(
                        random.nextInt(PongFrame.SCREEN_WIDTH + 1 + 0),
                        random.nextInt(PongFrame.HEIGHT + 1 + 0),
                        new Color(random.nextFloat(), random.nextFloat(), random.nextFloat())
                ));
            }
        }
    }

    /**
     * Retrieve the list of animated balls.
     *
     * @return animatedBalls
     */
    public List<AnimatedBall> getAnimatedBalls() {
        return animatedBalls;
    }

    /**
     * This class represents an animated ball on the main menu.
     */
    public final class AnimatedBall {
        private final Random random = new Random();

        private int x;
        private int y;
        private final int width = 25;
        private final int height = 25;

        private final Color color;
        private int deltaY;
        private int deltaX;

        private boolean shouldDestroy = false;

        /**
         * Constructor.
         *
         * @param x
         * @param y
         * @param color
         */
        public AnimatedBall(int x, int y, final Color color) {
            this.x = x;
            this.y = y;
            this.color = color;

            deltaX = generateDelta();
            deltaY = generateDelta();
        }

        /**
         * Recursive function to generate a delta that is between -4 and 4, but not 0.
         *
         * @return delta
         */
        private int generateDelta() {
            int delta = random.nextInt(4 + 1 + 4) -4;
            return  delta == 0 ? generateDelta() : delta;
        }

        /**
         * Update the animated ball.
         */
        public void update() {
            // move
            x += deltaX;
            y += deltaY;

            checkCollisionWithTopOrBottomBounds();
            checkIfOffScreen();
        }

        /**
         * Check if the animated ball has hit the top or the bottom view bound.
         * If so inverse the x or y delta.
         */
        private void checkCollisionWithTopOrBottomBounds() {
            // check if hit bottom or top (y-axis)
            if(y < 0) {
                deltaY = Math.abs(deltaY);
            } else if(y > PongFrame.SCREEN_HEIGHT -height) {
                deltaY = -deltaY;
            }
        }

        /**
         * Check if the animated ball is off screen.
         * If so set the 'shouldDestroy' flag to true,
         * so that the system can remove from the current animated ball list.
         */
        private void checkIfOffScreen() {
            // if off screen, remove
            if(x <= 0 - width || x >= PongFrame.SCREEN_WIDTH + width) {
                shouldDestroy = true;
            }
        }

        /**
         * Retrieve the x coordinate.
         *
         * @return x
         */
        public int getX() {
            return x;
        }

        /**
         * Retrieve the y coordinate.
         *
         * @return y
         */
        public int getY() {
            return y;
        }

        /**
         * Retrieve the width of the animated ball.
         *
         * @return width
         */
        public int getWidth() {
            return width;
        }

        /**
         * Retrieve the height of the animated ball.
         *
         * @return height
         */
        public int getHeight() {
            return height;
        }

        /**
         * Retrieve the color of the animated ball.
         *
         * @return color
         */
        public Color getColor() {
            return color;
        }
    }
}
