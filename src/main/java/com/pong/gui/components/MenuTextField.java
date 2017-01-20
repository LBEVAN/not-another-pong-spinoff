package com.pong.gui.components;

import com.pong.system.resource.ResourceManager;

import javax.swing.*;
import java.awt.*;

/**
 * JTextField implementation for use on the menu screens.
 * This implementation sets default values such as font and colour.
 *
 * @author LBEVAN
 */
public class MenuTextField extends JTextField {

    // region init
    /**
     * Constructor.
     */
    public MenuTextField(int columns) {
        setColumns(columns);
        setFont(ResourceManager.getInstance().getFont().deriveFont(20f));
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
    }
    // endregion
}
