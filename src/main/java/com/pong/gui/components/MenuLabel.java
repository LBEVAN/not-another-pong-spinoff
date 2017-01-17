package com.pong.gui.components;

import com.pong.system.resource.ResourceManager;

import javax.swing.*;
import java.awt.*;

/**
 * MenuLabel component for use on the MainMenuView.
 * It wraps a JLabel, providing custom styling and events handling.
 *
 * @author LBEVAN
 */
public class MenuLabel extends JLabel {

    /**
     * Constructor.
     *
     * @param text
     * @param fontSize
     */
    public MenuLabel(String text, float fontSize) {
        setText(text);
        setFont(ResourceManager.getInstance().getFont().deriveFont(fontSize));
        setForeground(Color.WHITE);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Constructor.
     * Ensure label text is set from another source.
     *
     * @param fontSize
     */
    public MenuLabel(float fontSize) {
        setFont(ResourceManager.getInstance().getFont().deriveFont(fontSize));
        setForeground(Color.WHITE);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Constructor.
     *
     * @param text
     * @param icon
     * @param fontSize
     */
    public MenuLabel(String text, Icon icon, float fontSize) {
        setText(text);
        setIcon(icon);
        setFont(ResourceManager.getInstance().getFont().deriveFont(fontSize));
        setForeground(Color.WHITE);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setHorizontalAlignment(JLabel.CENTER);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
    }
}
