package com.pong.view;

import java.awt.*;

/**
 * Created by LBEVAN on 04/10/2016.
 */
public class GameView extends View {

    public GameView() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
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
