package com.pong.gui.view.pongview;

import com.pong.gui.components.MenuLabel;
import com.pong.system.Constants;
import com.pong.system.resource.ResourceManager;

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
    // endregion

    // region init

    /**
     * Constructor.
     */
    public ModifiersPanel() {
        setLayout(new GridLayout(0, 2));
        setOpaque(false);

        createP1ModifiersPanel();
        createP2ModifiersPanel();

        add(p1ModifiersPanel);
        add(p2ModifiersPanel);
    }
    // endregion

    // region public API
    public void update(Graphics2D graphics) {

    }
    // endregion

    // region private API
    /**
     * Create the modifiers panel for player 1.
     */
    private void createP1ModifiersPanel() {
        p1ModifiersPanel = new JPanel(new GridLayout(0, 5));
        p1ModifiersPanel.setOpaque(false);

        // TODO: show all other modifier icons
        for (int i = 0; i < 5; i++) {
            Icon icon = new ImageIcon(ResourceManager.getInstance().getGraphic(Constants.SPEED_MODIFIER_ICON), "" + i);
            JLabel label = new MenuLabel("" + (i + 1),icon , 12f);
            p1Labels[i] = label;
            p1ModifiersPanel.add(label);
        }
    }

    /**
     * Create the modifiers panel for player 2.
     */
    private void createP2ModifiersPanel() {
        p2ModifiersPanel = new JPanel(new GridLayout(0, 5));
        p2ModifiersPanel.setOpaque(false);

        // TODO: show all other modifier icons
        for (int i = 0; i < 5; i++) {
            Icon icon = new ImageIcon(ResourceManager.getInstance().getGraphic(Constants.SPEED_MODIFIER_ICON), "" + i);
            JLabel label = new MenuLabel("" + (i + 1),icon , 12f);
            p2Labels[i] = label;
            p2ModifiersPanel.add(label);
        }
    }
    // endregion
}
