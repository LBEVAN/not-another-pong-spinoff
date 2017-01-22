package com.pong.gui.view.pongview;

import com.pong.gui.components.MenuLabel;
import com.pong.model.PongModel;
import com.pong.model.entity.player.Player;
import com.pong.model.entity.player.PlayerId;
import com.pong.model.modifier.AbstractModifier;
import com.pong.model.scoring.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 * The GameUIPanel is the parent panel for all UI elements in the game.
 *
 * @author LBEVAN
 */
public class GameUIPanel extends JPanel {

    // region data
    private JPanel topWrapperPanel;
    private JPanel bottomWrapperPanel;

    private JPanel playerModifiersPanel;
    private JPanel computerModifiersPanel;

    private ModifiersPanel modifiersPanel;
    private GameInfoPanel gameInfoPanel;

    private JLabel playerModifiersLabel;
    private JLabel computerModifiersLabel;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param width
     * @param height
     */
    public GameUIPanel(final int width, final int height) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);

        // create wrapper components
        topWrapperPanel = new JPanel(new GridLayout(2, 0));
        topWrapperPanel.setOpaque(false);

        bottomWrapperPanel = new JPanel(new GridLayout(0, 2));
        bottomWrapperPanel.setOpaque(false);

        // create inner components
        modifiersPanel = new ModifiersPanel();
        gameInfoPanel = new GameInfoPanel();

        playerModifiersPanel = new JPanel();
        playerModifiersPanel.setOpaque(false);
        playerModifiersLabel = new MenuLabel(16f);

        computerModifiersPanel = new JPanel();
        computerModifiersPanel.setOpaque(false);
        computerModifiersLabel = new MenuLabel(16f);

        // add components
        topWrapperPanel.add(modifiersPanel);
        topWrapperPanel.add(gameInfoPanel);
        add(topWrapperPanel, BorderLayout.PAGE_START);

        playerModifiersPanel.add(playerModifiersLabel);
        computerModifiersPanel.add(computerModifiersLabel);
        bottomWrapperPanel.add(playerModifiersPanel);
        bottomWrapperPanel.add(computerModifiersPanel);
        add(bottomWrapperPanel, BorderLayout.PAGE_END);
    }
    // endregion

    // region public API
    /**
     * Update the game UI.
     *
     * @param graphics
     * @param model
     */
    public void update(Graphics2D graphics, final PongModel model) {
        final ScoreManager scoreManager = model.getScoreManager();

        final Player player1 = model.getPlayerById(PlayerId.ONE);
        final Player player2 = model.getPlayerById(PlayerId.TWO);

        gameInfoPanel.updateP1Score(player1.getName(), scoreManager.getScore(PlayerId.ONE));
        gameInfoPanel.updateP2Score(player2.getName(), scoreManager.getScore(PlayerId.TWO));
        gameInfoPanel.updateGameTime(model.getTimeRemaining());

        modifiersPanel.update(player1, player2);

        playerModifiersLabel.setText("Modifiers: " + createModifierString(player1.getModifiers()));
        computerModifiersLabel.setText("Modifiers: " + createModifierString(player2.getModifiers()));
    }
    // endregion

    // region private API
    /**
     * Create the string for a list of modifiers.
     *
     * @param modifiers
     * @return modifiersString
     */
    private String createModifierString(Collection<AbstractModifier> modifiers) {
        StringBuilder stringBuilder = new StringBuilder();

        modifiers.forEach(modifier -> stringBuilder.append(modifier.toString()));

        return stringBuilder.toString();
    }
    // endregion
}
