package com.pong.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * MenuDropdown component for use in the views.
 * It wraps JComboBox providing custom styling and events handling.
 *
 * @author LBEVAN
 */
public class MenuDropdown extends JComboBox {

    /**
     * Constructor.
     */
    public MenuDropdown() {
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setOpaque(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMaximumSize() {
        Dimension max = super.getMaximumSize();
        max.height = getPreferredSize().height;
        max.width = getPreferredSize().width;
        return max;
    }
}
