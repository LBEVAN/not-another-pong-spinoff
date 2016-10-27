package com.pong.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * MainMenuLabel component for use on the MainMenuView.
 * It wraps a JLabel, providing custom styling and events handling.
 *
 * @author LBEVAN
 */
public class MainMenuLabel extends JLabel {

    /**
     * Constructor.
     *
     * @param text
     * @param fontStyle
     * @param fontSize
     */
    public MainMenuLabel(String text, int fontStyle, int fontSize) {
        setText(text);
        setFont(new Font("Arial", fontStyle, fontSize));
        setForeground(Color.WHITE);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
