package com.pong.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * JButton implementation for allow Icons on buttons.
 * Also implements MouseListener to change cursors on mouse events.
 *
 * @author LBEVAN
 */
public class IconButton extends JButton implements MouseListener {

    // region data
    private final Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    private final Cursor hoverCursor = new Cursor(Cursor.HAND_CURSOR);
    // endregion

    // region init
    /**
     * Constructor.
     */
    public IconButton() {
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.addMouseListener(this);
    }
    // endregion

    // region events
    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * {@inheritDoc}
     */
    public void mouseReleased(MouseEvent e) {
        this.setCursor(defaultCursor);
    }

    /**
     * {@inheritDoc}
     */
    public void mouseEntered(MouseEvent e) {
        this.setCursor(hoverCursor);
    }

    /**
     * {@inheritDoc}
     */
    public void mouseExited(MouseEvent e) {
        this.setCursor(defaultCursor);
    }
    // endregion
}
