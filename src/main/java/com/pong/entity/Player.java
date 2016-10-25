package com.pong.entity;

import com.pong.Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The Player class represents the player entity in the game.
 * It primarily handles the input mechanisms.
 *
 * @author LBEVAN
 */
public class Player extends Entity {

    private int speed = 8;

    /**
     * Player constructor.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);

        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        // player
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DOWN");
        actionMap.put("DOWN", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(getY() <= Pong.SCREEN_HEIGHT - getHeight()) {
                    setY(getY() + speed);
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UP");
        actionMap.put("UP", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(getY() >= 0) {
                    setY(getY() - speed);
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics graphics) {
        graphics.fillRect(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        
    }
}
