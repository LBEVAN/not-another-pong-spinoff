package com.pong.gui.components;

import com.pong.system.resource.ResourceManager;

import javax.swing.*;
import java.awt.*;

/**
 * JTextArea implementation for use of the menu screens.
 * This implementation sets default values for line warp, wrap style, fonts and colours.
 *
 * @author LBEVAN
 */
public class MenuTextArea extends JTextArea {

    public MenuTextArea(String text, float fontSize) {
        setText(text);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFont(ResourceManager.getInstance().getFont().deriveFont(fontSize));
        setLineWrap(true);
        setWrapStyleWord(true);
        setEditable(false);
    }
}
