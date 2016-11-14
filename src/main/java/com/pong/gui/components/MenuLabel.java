package com.pong.gui.components;

import com.pong.system.ResourceManager;

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
        setFont(ResourceManager.getInstance().getCustomFont().deriveFont(fontSize));
        setForeground(Color.WHITE);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
