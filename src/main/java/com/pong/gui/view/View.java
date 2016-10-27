package com.pong.gui.view;

import javax.swing.*;
import java.awt.*;

/**
 * Base class for a view.
 *
 * @author LBEVAN
 */
public abstract class View extends JPanel {

    protected int width;
    protected int height;

    /**
     * Constructor.
     *
     * @param width
     * @param height
     */
    public View(int width, int height) {
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
    }
}
