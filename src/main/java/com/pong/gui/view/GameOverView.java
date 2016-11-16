package com.pong.gui.view;

import com.pong.gui.components.MenuButton;
import com.pong.gui.components.MenuLabel;
import com.pong.model.GameOverModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author LBEVAN
 */
public class GameOverView extends View {

    private static final String VIEW_TITLE = "Game Over";

    private GameOverModel model;

    private LayoutManager layoutManager;

    private JPanel titlePanel;
    private JPanel detailsPanel;
    private JPanel buttonPanel;

    private JLabel titleLabel;
    private JLabel winnerLabel;
    private JButton playAgainButton;
    private JButton exitButton;

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
        detailsPanel.setLayout(new GridBagLayout());

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new GridLayout(0, 2));

        titleLabel = new MenuLabel(VIEW_TITLE, 60f);
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        winnerLabel = new MenuLabel(24f);

        playAgainButton = new MenuButton("Play Again");
        playAgainButton.setBorder(new EmptyBorder(20, 0, 20, 0));

        exitButton = new MenuButton("Exit");
        exitButton.setBorder(new EmptyBorder(20, 0, 20, 0));


        // add components to containers
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.PAGE_START);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 1;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 125);
        detailsPanel.add(winnerLabel);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 125, 0, 0);
        add(detailsPanel, BorderLayout.CENTER);

        buttonPanel.add(playAgainButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getViewName() {
        return "GameOverView";
    }

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
}
