package com.pong.gui.components;

import com.pong.system.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * MenuButton component for use on the MainMenuView.
 * It wraps a JButton, providing custom styling and events handling.
 *
 * @author LBEVAN
 */
public class MenuButton extends JButton implements MouseListener {

    private final Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    private final Cursor hoverCursor = new Cursor(Cursor.HAND_CURSOR);

    private final Font defaultFont = ResourceManager.getInstance().getFont().deriveFont(50f);
    private final Font hoverFont = ResourceManager.getInstance().getFont().deriveFont(70f);

    /**
     * Constructor.
     *
     * @param text
     */
    public MenuButton(String text) {
        this.setText(text);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setFont(defaultFont);
        this.setForeground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.addMouseListener(this);
        this.setFocusPainted(false);
    }

    /**
     * {@inheritDoc}
     */
    public void mouseClicked(MouseEvent e) {
        // no action required
    }

    /**
     * {@inheritDoc}
     */
    public void mousePressed(MouseEvent e) {
        // no action required
    }

    /**
     * {@inheritDoc}
     */
    public void mouseReleased(MouseEvent e) {
        this.setFont(defaultFont);
        this.setCursor(defaultCursor);
    }

    /**
     * {@inheritDoc}
     */
    public void mouseEntered(MouseEvent e) {
        this.setFont(hoverFont);
        this.setCursor(hoverCursor);
    }

    /**
     * {@inheritDoc}
     */
    public void mouseExited(MouseEvent e) {
        this.setFont(defaultFont);
        this.setCursor(defaultCursor);
    }
}
