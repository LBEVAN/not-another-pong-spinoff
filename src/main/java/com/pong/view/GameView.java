package com.pong.view;

import com.pong.arena.Arena;

import java.awt.*;

/**
 * The GameView class displays the game.
 *
 * @author LBEVAN
 */
public class GameView extends View {

    private Arena arena;

    /**
     * Default constructor.
     */
    public GameView() {
        arena = new Arena();

        add(arena);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        arena.paint(graphics);
    }

    /**
     * {@inheritDoc}
     */
    public void repaint() {
        super.repaint();
        if(getParent() != null) {
            getParent().repaint();
        }

    }
}
