package com.pong.gui.view;

import com.pong.model.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Base class for a view.
 *
 * @author LBEVAN
 */
public abstract class View<M extends Model> extends JPanel {

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

    /**
     * Retrieve the view name.
     *
     * @return viewName
     */
    public abstract String getViewName();

    /**
     * Initialise the view.
     *
     * @param model
     */
    public abstract void init(M model);
}
