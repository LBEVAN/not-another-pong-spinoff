package com.pong.gui.components;

import com.pong.system.ResourceManager;

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
        this.setFont(ResourceManager.getInstance().getCustomFont().deriveFont(20f));
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
