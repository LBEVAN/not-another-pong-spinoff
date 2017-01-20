package com.pong.gui.view;

import com.pong.gui.components.IconLabel;
import com.pong.gui.components.MenuButton;
import com.pong.gui.components.MenuLabel;
import com.pong.gui.components.MenuTextArea;
import com.pong.model.modifier.ModifierType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * View component for the How To Play screen, accessed via the main menu.
 *
 * @author LBEVAN
 */
public class HowToPlayView extends View {

    // region data
    private static final String VIEW_TITLE = "How To Play";

    private LayoutManager layoutManager;

    private JPanel titlePanel;
    private JPanel instructionsPanel;
    private JPanel buttonPanel;

    private JPanel gameInstructions;
    private JPanel modifierDisplay;
    private JPanel modifierInstructions;

    private JLabel titleLabel;

    private JButton backButton;
    // endregion

    /**
     * Constructor.
     *
     * @param width
     * @param height
     */
    public HowToPlayView(int width, int height) {
        super(width, height);

        // setup this view
        setBackground(Color.BLACK);
        layoutManager = new BorderLayout();
        setLayout(layoutManager);

        // create components
        createTitlePanel();
        createInstructionsPanel();
        createButtonPanel();

        // add components to containers
        add(titlePanel, BorderLayout.PAGE_START);
        add(instructionsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
    }
    // endregion

    // region private API
    /**
     * Create the titlePanel component and it's child components.
     */
    private void createTitlePanel() {
        // create components
        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(Color.BLACK);

        titleLabel = new MenuLabel(VIEW_TITLE, 60f);
        titleLabel.setBorder(new EmptyBorder(10, 0, 40, 0));

        // add components to container
        titlePanel.add(titleLabel);
    }

    /**
     * Create the instructionsPanel component and it's child components.
     */
    private void createInstructionsPanel() {
        // create components
        instructionsPanel = new JPanel(new BorderLayout());
        instructionsPanel.setBackground(Color.BLACK);

        gameInstructions = new JPanel(new GridLayout(1, 0));
        gameInstructions.setBackground(Color.BLACK);
        gameInstructions.setBorder(new EmptyBorder(20, 100, 20, 100));

        JTextArea gameText = new MenuTextArea(
                "Not Another Pong Spin-Off is a fast paced, strategic, real-time game whereby the " +
                        "goal is to score as many points as you can (while getting more than the opponent) " +
                        "in 90 seconds to win the game. Track and hit the moving ball with the paddle and attempt " +
                        "to knock it past your opponent - doing so will score you points. \n\n", 16f
        );
        gameText.append("Move the paddle using the  UP ARROW  and  DOWN ARROW keys. Use modifiers with specified keys below. ");

        modifierDisplay = new JPanel(new GridLayout(ModifierType.values().length + 1, 3));
        modifierDisplay.setBackground(Color.BLACK);
        modifierDisplay.setBorder(new MatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        JLabel modifierHeader = new MenuLabel("Modifier Icon", 16f);
        modifierHeader.setHorizontalAlignment(JLabel.CENTER);
        JLabel keyHeader = new MenuLabel("Key (Shortcut)", 16f);
        keyHeader.setHorizontalAlignment(JLabel.CENTER);
        JLabel actionHeader = new MenuLabel("Action", 16f);
        actionHeader.setHorizontalAlignment(JLabel.CENTER);

        modifierInstructions = new JPanel(new GridLayout(1, 0));
        modifierInstructions.setBackground(Color.BLACK);
        modifierInstructions.setBorder(new EmptyBorder(20, 100, 20, 100));

        JTextArea modifierText = new MenuTextArea(
                "Modifiers, also known as Power-Ups/Power-Downs, are used to change the way the gane is played. " +
                        "They can be used both defensively and offensively in order to gain an advantage over the opponent. " +
                        "Be sure to use them at the right time to gain maximum points!",
                16f
        );

        // add components to container
        gameInstructions.add(gameText);

        instructionsPanel.add(gameInstructions, BorderLayout.PAGE_START);

        modifierDisplay.add(modifierHeader);
        modifierDisplay.add(keyHeader);
        modifierDisplay.add(actionHeader);

        ModifierType[] modifierTypes = ModifierType.values();
        for(int i = 0; i < modifierTypes.length; i++) {
            ModifierType modifierType = modifierTypes[i];
            JLabel iconLabel = new IconLabel(new ImageIcon(modifierType.getImage()));
            JLabel keyLabel = new MenuLabel("" + (i + 1),13f);
            keyLabel.setHorizontalAlignment(JLabel.CENTER);
            JLabel actionLabel = new MenuLabel(modifierType.getDescription(), 13f);

            modifierDisplay.add(iconLabel);
            modifierDisplay.add(keyLabel);
            modifierDisplay.add(actionLabel);
        }

        instructionsPanel.add(modifierDisplay, BorderLayout.CENTER);

        modifierInstructions.add(modifierText);

        instructionsPanel.add(modifierInstructions, BorderLayout.PAGE_END);
    }

    /**
     * Create the buttonPanel component and it's child components.
     */
    private void createButtonPanel() {
        // create components
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new GridLayout(0, 2));

        backButton = new MenuButton("Back");
        backButton.setBorder(new EmptyBorder(20, 0, 20, 0));

        // add components to container
        buttonPanel.add(backButton);
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the backButton.
     *
     * @return backButton
     */
    public JButton getBackButton() {
        return backButton;
    }
    // endregion
}
