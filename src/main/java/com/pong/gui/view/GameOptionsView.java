package com.pong.gui.view;

import com.pong.ai.difficulty.DifficultyType;
import com.pong.gui.components.*;
import com.pong.model.GameOptionsModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * GameOptionsView is the view component of the game options.
 * It handles all of the UI components for the screen.
 *
 * @author LBEVAN
 */
public class GameOptionsView extends View {

    // region data
    private static final String VIEW_TITLE = "Game Options";

    private GameOptionsModel model;

    private LayoutManager layoutManager;

    private JPanel titlePanel;
    private JPanel optionsPanel;
    private JPanel buttonPanel;

    private JLabel titleLabel;

    private JLabel difficultyLabel;
    private JComboBox<DifficultyType> difficultyComboBox;

    private JLabel usernameLabel;
    private JTextField usernameField;

    private JLabel playerColorLabel;
    private ColourSelectorButton colourSelectorButton;

    private JButton startButton;
    private JButton backButton;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param model
     * @param width
     * @param height
     */
    public GameOptionsView(final GameOptionsModel model, int width, int height) {
        super(width, height);

        this.model = model;

        setBackground(Color.BLACK);

        layoutManager = new BorderLayout();
        setLayout(layoutManager);

        // create components
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        optionsPanel = new JPanel();
        optionsPanel.setBackground(Color.BLACK);
        optionsPanel.setLayout(new GridBagLayout());

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new GridLayout(0, 2));

        titleLabel = new MenuLabel(VIEW_TITLE, 60f);
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        difficultyLabel = new MenuLabel("Game Difficulty", 24f);

        difficultyComboBox = new MenuDropdown();
        difficultyComboBox.setModel(new DefaultComboBoxModel<>(model.getDifficulties()));
        difficultyComboBox.setSelectedItem(model.getSelectedDifficulty());

        usernameLabel = new MenuLabel("Player Name", 24f);

        usernameField = new MenuTextField(15);
        usernameField.setDocument(new TextFieldLimit(20));
        usernameField.setText(model.getPlayerName());

        playerColorLabel = new MenuLabel("Player Colour", 24f);

        colourSelectorButton = new ColourSelectorButton(Color.WHITE);

        startButton = new MenuButton("Start");
        startButton.setBorder(new EmptyBorder(20, 0, 20, 0));

        backButton = new MenuButton("Back");
        backButton.setBorder(new EmptyBorder(20, 0, 20, 0));

        // add components to containers
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.PAGE_START);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 2;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 200);
        optionsPanel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 200, 0, 0);
        optionsPanel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 200);
        optionsPanel.add(playerColorLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 200, 0, 0);
        optionsPanel.add(colourSelectorButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 0, 200);
        optionsPanel.add(difficultyLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 200, 0, 0);
        optionsPanel.add(difficultyComboBox, constraints);

        add(optionsPanel, BorderLayout.CENTER);

        buttonPanel.add(backButton);
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the start button component.
     *
     * @return startButton
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     * Retrieve the back button component.
     *
     * @return backButton
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * Retrieve the difficulty combo box.
     *
     * @return difficultyComboBox
     */
    public JComboBox<DifficultyType> getDifficultyComboBox() {
        return difficultyComboBox;
    }

    /**
     * Retrieve the username field.
     *
     * @return usernameField
     */
    public JTextField getUsernameField() {
        return usernameField;
    }

    /**
     * Retrieve the colour selected button
     *
     * @return colourSelectorButton
     */
    public ColourSelectorButton getColourSelectorButton() {
        return colourSelectorButton;
    }
    // endregion
}
