package com.pong.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * IconLabel extends the functionality of a JLabel by allowing icons and alightment to be set on creation.
 *
 * @author LBEVAN
 */
public class IconLabel extends JLabel {

    /**
     * Constructor.
     *
     * @param icon
     */
    public IconLabel(Icon icon) {
        setIcon(icon);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setHorizontalAlignment(JLabel.CENTER);
    }
}
