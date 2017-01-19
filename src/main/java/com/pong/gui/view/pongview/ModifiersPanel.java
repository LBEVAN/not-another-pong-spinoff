package com.pong.gui.view.pongview;

import com.pong.gui.components.IconLabel;
import com.pong.model.entity.Player;
import com.pong.model.modifier.ModifierType;

import javax.swing.*;
import java.awt.*;

/**
 * The ModifiersPanel handles all of the UI elements for the consumable modifiers.
 *
 * @author LBEVAN
 */
public class ModifiersPanel extends JPanel {

    // region data
    private JPanel p1ModifiersPanel;
    private JPanel p2ModifiersPanel;

    private JLabel[] p1Labels = new JLabel[5];
    private JLabel[] p2Labels = new JLabel[5];
    private ModifierType[] modifierTypePos = new ModifierType []{ModifierType.INCREASE_HEIGHT, ModifierType.INCREASE_SPEED, ModifierType.INCREASE_BALL_SPEED, ModifierType.DECREASE_SPEED, ModifierType.DECREASE_HEIGHT};
    // endregion

    // region init
    /**
     * Constructor.
     */
    public ModifiersPanel() {
        setLayout(new GridLayout(0, 2));
        setOpaque(false);

        p1ModifiersPanel = new JPanel(new GridLayout(0, 5));
        p1ModifiersPanel.setOpaque(false);

        p2ModifiersPanel = new JPanel(new GridLayout(0, 5));
        p2ModifiersPanel.setOpaque(false);

        createModifiersPanel(p1Labels, p1ModifiersPanel);
        createModifiersPanel(p2Labels, p2ModifiersPanel);

        add(p1ModifiersPanel);
        add(p2ModifiersPanel);
    }
    // endregion

    // region public API
    public void update(Player player1, Player player2) {
        updateModifierLabels(p1Labels, player1);
        updateModifierLabels(p2Labels, player2);
    }
    // endregion

    // region private API
    /**
     * Create the modifiers panel for each player.
     */
    private void createModifiersPanel(JLabel[] labels, JPanel modifiersPanel) {
        for (int i = 0; i < labels.length; i++) {
            ModifierType modifierType = modifierTypePos[i];

            Icon icon = new ImageIcon(modifierType.getImage());
            JLabel label = new IconLabel(icon);
            labels[i] = label;
            modifiersPanel.add(label);
        }
    }

    /**
     * Update the modifier labels for a player
     *
     * @param labels
     * @param player
     */
    private void updateModifierLabels(JLabel[] labels, Player player) {
        for (int i = 0; i < p1Labels.length; i++) {
            ModifierType modifierType = modifierTypePos[i];
            if(modifierType == null) {
                continue;
            }
            boolean isMax = player.getModifierSystem().isMaxNumChargesForModifierType(modifierType);

            if(isMax) {
                p1Labels[i].setEnabled(false);
            }
        }
    }
    // endregion
}
