package com.pong.gui.components;

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
     * @param fontStyle
     * @param fontSize
     */
    public MenuLabel(String text, int fontStyle, int fontSize) {
        setText(text);
        setFont(new Font("Arial", fontStyle, fontSize));
        setForeground(Color.WHITE);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
