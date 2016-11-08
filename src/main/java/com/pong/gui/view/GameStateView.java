package com.pong.gui.view;

import javax.swing.*;
import java.awt.*;

/**
 * The GameStateView is the top level JPanel in the application that has a BorderLayout to
 * contain all other possible views in the system.
 *
 * @author LBEVAN
 */
public class GameStateView extends JPanel {

    private int width;
    private int height;

    /**
     * Constructor.
     *
     * @param width
     * @param height
     */
    public GameStateView(int width, int height) {
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
    }

    /**
     * Change the current view on the panel.
     *
     * @param view
     */
    public void changeView(View view) {
        removeAll();
        add(view, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
