package com.pong.gui.view.pongview;

import com.pong.gui.components.MenuLabel;

import javax.swing.*;
import java.awt.*;

/**
 * GameInfoPanel handles the game UI information elements such as player scores and game time.
 *
 * @author LBEVAN
 */
public class GameInfoPanel extends JPanel {

    // region data
    private JPanel p1ScorePanel;
    private JPanel p2ScorePanel;
    private JPanel gameTimePanel;

    private JLabel p1ScoreLabel;
    private JLabel p2ScoreLabel;
    private JLabel gameTimeLabel;
    // endregion

    // region init

    /**
     * Constructor.
     */
    public GameInfoPanel() {
        setLayout(new GridLayout(0, 3));
        setOpaque(false);

        // init components
        p1ScorePanel = new JPanel();
        p1ScorePanel.setOpaque(false);

        p2ScorePanel = new JPanel();
        p2ScorePanel.setOpaque(false);

        gameTimePanel = new JPanel();
        gameTimePanel.setOpaque(false);

        p1ScoreLabel = new MenuLabel(16f);

        p2ScoreLabel = new MenuLabel(16f);

        gameTimeLabel = new MenuLabel(16f);

        // add components
        p1ScorePanel.add(p1ScoreLabel);

        p2ScorePanel.add(p2ScoreLabel);

        gameTimePanel.add(gameTimeLabel);

        add(p1ScorePanel);
        add(gameTimePanel);
        add(p2ScorePanel);
    }
    // endregion

    // region public API

    /**
     * Update the UI element for player 1's score.
     *
     * @param name
     * @param score
     */
    public void updateP1Score(final String name, final int score) {
        p1ScoreLabel.setText(String.format("%s Score: %d", name, score));
    }

    /**
     * Update the UI element for player 2's score.
     *
     * @param name
     * @param score
     */
    public void updateP2Score(final String name, final int score) {
        p2ScoreLabel.setText(String.format("%s Score: %d", name, score));
    }

    /**
     * Update the UI element for the remaining game time.
     *
     * @param gameTime
     */
    public void updateGameTime(final double gameTime) {
        gameTimeLabel.setText("Time: " + gameTime);
    }
    // endregion
}
