package com.pong.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom IconButton implementation for allowing users to select a colour.
 * The colour then shows as an icon on the button.
 *
 * @author LBEVAN
 */
public class ColourSelectorButton extends IconButton {

    // region data
    private Color current;
    private List<ColourChangedListener> listeners = new ArrayList<>();
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param c
     */
    public ColourSelectorButton(Color c) {
        setSelectedColour(c);
        addActionListener(arg0 -> {
            Color newColour = JColorChooser.showDialog(null, "Choose a color", current);
            setSelectedColour(newColour);
        });
    }
    // endregion

    // region public API
    /**
     * Set the selected colour, along with whether to notify listeners.
     *
     * @param newColour
     * @param notify
     */
    public void setSelectedColour(Color newColour, boolean notify) {
        if (newColour == null) return;

        current = newColour;
        setIcon(createIcon(current, 30, 30));
        repaint();

        if (notify) {
            // Notify everybody that may be interested.
            for (ColourChangedListener l : listeners) {
                l.colourChanged(newColour);
            }
        }
    }


    /**
     * Add a ColourChangedListener to the list of listeners.
     *
     * @param toAdd
     */
    public void addColorChangedListener(ColourChangedListener toAdd) {
        listeners.add(toAdd);
    }

    /**
     * Crate an image icon with the specified color, width and height.
     *
     * @param main
     * @param width
     * @param height
     * @return
     */
    public static ImageIcon createIcon(Color main, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(main);
        graphics.fillRect(0, 0, width, height);
        graphics.setXORMode(Color.DARK_GRAY);
        graphics.drawRect(0, 0, width-1, height-1);
        image.flush();
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }

    /**
     * Custom interface for handling changing colour events.
     */
    public interface ColourChangedListener {
        void colourChanged(Color newColour);
    }
    // endregion

    // region getters and setters
    /**
     * Retrieve the selected colour.
     *
     * @return color
     */
    public Color getSelectedColour() {
        return current;
    }

    /**
     * Set the selected colour to a new colour.
     *
     * @param newColour
     */
    public void setSelectedColour(Color newColour) {
        setSelectedColour(newColour, true);
    }
    // endregion
}
