package com.pong.gui.view;

import com.pong.gui.components.LeaderboardPanel;
import com.pong.gui.components.MenuButton;
import com.pong.gui.components.MenuLabel;
import com.pong.model.GameOverModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * GameOverView is the view component for game over.
 *
 * @author LBEVAN
 */
public class GameOverView extends View {

    // region data
    private static final String VIEW_TITLE = "Game Over";

    private GameOverModel model;

    private LayoutManager layoutManager;

    private JPanel titlePanel;
    private JPanel detailsPanel;
    private JPanel leaderboardPanel;
    private JPanel buttonPanel;

    private JLabel titleLabel;
    private JLabel winnerLabel;
    private JLabel p1ScoreLabel;
    private JLabel p2ScoreLabel;
    private JButton playAgainButton;
    private JButton exitButton;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param model
     * @param width
     * @param height
     */
    public GameOverView(GameOverModel model, int width, int height) {
        super(width, height);

        this.model = model;

        setBackground(Color.BLACK);

        layoutManager = new BorderLayout();
        setLayout(layoutManager);

        // create components
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        detailsPanel = new JPanel();
        detailsPanel.setBackground(Color.BLACK);
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        leaderboardPanel = new LeaderboardPanel(model.getHighScores());
        leaderboardPanel.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
                new EmptyBorder(20, 0, 0, 0))
        );

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new GridLayout(0, 2));

        titleLabel = new MenuLabel(VIEW_TITLE, 60f);
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        winnerLabel = new MenuLabel(24f);
        winnerLabel.setBorder(new EmptyBorder(20, 0, 20, 0));

        p1ScoreLabel = new MenuLabel(String.format("%s Score: %d", model.getP1Name(), model.getP1Score()), 24f, deriveColorFromScores(model.getP1Score(), model.getP2Score()));

        p2ScoreLabel = new MenuLabel(String.format("%s Score: %d", model.getP2Name(), model.getP2Score()), 24f, deriveColorFromScores(model.getP2Score(), model.getP1Score()));

        playAgainButton = new MenuButton("Play Again");
        playAgainButton.setBorder(new EmptyBorder(20, 0, 20, 0));

        exitButton = new MenuButton("Exit");
        exitButton.setBorder(new EmptyBorder(20, 0, 20, 0));


        // add components to containers
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.PAGE_START);

        detailsPanel.add(winnerLabel);
        detailsPanel.add(p1ScoreLabel);
        detailsPanel.add(p2ScoreLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        detailsPanel.add(leaderboardPanel);
        add(detailsPanel, BorderLayout.CENTER);

        buttonPanel.add(playAgainButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }
    // endregion

    // region private API
    private Color deriveColorFromScores(int scoreToCheck, int scoreToCompare) {
        if(scoreToCheck > scoreToCompare) {
            return Color.GREEN;
        } else if(scoreToCheck < scoreToCompare) {
            return Color.RED;
        } else {
            return Color.WHITE;
        }
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the play again button.
     *
     * @return playAgainButton
     */
    public JButton getPlayAgainButton() {
        return playAgainButton;
    }

    /**
     * Retrieve the exit button.
     *
     * @return exitButton
     */
    public JButton getExitButton() {
        return exitButton;
    }

    /**
     * Set the winner label text.
     *
     * @param text
     */
    public void setWinnerLabelText(String text) {
        winnerLabel.setText(text);
    }
    // endregion
}
