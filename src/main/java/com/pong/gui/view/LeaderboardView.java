package com.pong.gui.view;

import com.pong.gui.components.LeaderboardPanel;
import com.pong.gui.components.MenuButton;
import com.pong.gui.components.MenuLabel;
import com.pong.model.LeaderboardModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Leaderboard is the view component for leaderboard screen.
 * It displays the the current set of high scores.
 *
 * @author LBEVAN
 */
public class LeaderboardView extends View {

    // region data
    private static final String VIEW_TITLE = "Leaderboard";

    private LeaderboardModel model;

    private LayoutManager layoutManager;

    private JPanel titlePanel;
    private JPanel leaderboardPanel;
    private JPanel buttonPanel;

    private JLabel titleLabel;

    private JButton clearButton;
    private JButton backButton;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param width
     * @param height
     */
    public LeaderboardView(LeaderboardModel model, int width, int height) {
        super(width, height);
        this.model = model;

        setBackground(Color.BLACK);

        layoutManager = new BorderLayout();
        setLayout(layoutManager);

        // create components
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        leaderboardPanel = new LeaderboardPanel(model.getHighScores());

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new GridLayout(0, 2));

        titleLabel = new MenuLabel(VIEW_TITLE, 60f);
        titleLabel.setBorder(new EmptyBorder(10, 0, 40, 0));

        clearButton = new MenuButton("Clear");
        clearButton.setBorder(new EmptyBorder(20, 0, 20, 0));

        backButton = new MenuButton("Back");
        backButton.setBorder(new EmptyBorder(20, 0, 20, 0));

        // add components to containers
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.PAGE_START);

        add(leaderboardPanel, BorderLayout.CENTER);

        buttonPanel.add(backButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }
    // endregion

    // region public API
    /**
     * Update the display of high scores.
     */
    public void updateHighScoresDisplay() {
        ((LeaderboardPanel) leaderboardPanel).updateHighScoreDisplay(model.getHighScores());
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the clear button.
     *
     * @return clearButton
     */
    public JButton getClearButton() {
        return clearButton;
    }

    /**
     * Retrieve the back button.
     *
     * @return backButton
     */
    public JButton getBackButton() {
        return backButton;
    }
    // endregion
}