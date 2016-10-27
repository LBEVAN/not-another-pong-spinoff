package com.pong.controller;

import com.pong.gui.frame.PongFrame;
import com.pong.model.PongModel;
import com.pong.gui.view.PongView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * The PongController controls the Pong game, including the game loop and user input.
 * It queries both the view and the model to prompt it to repaint or update every game tick.
 *
 * @author LBEVAN
 */
public class PongController {

    private PongModel pongModel;
    private PongFrame pongFrame;
    private PongView pongView;

    private Timer gameTimer;

    public PongController(PongModel pongModel, PongFrame pongFrame, PongView pongView) {
        this.pongModel = pongModel;
        this.pongFrame = pongFrame;
        this.pongView = pongView;

        this.gameTimer = new Timer(1000/60, new GameLoop());

        initUserInput();
    }

    private void initUserInput() {
        InputMap inputMap = pongView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = pongView.getActionMap();

        // player
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DOWN");
        actionMap.put("DOWN", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pongModel.getPlayer().moveDown();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UP");
        actionMap.put("UP", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                pongModel.getPlayer().moveUp();
            }
        });
    }

    /**
     * Start the game.
     */
    public void start() {
        gameTimer.start();
    }

    /**
     * The game loop executor. This will be called from the timer every tick.
     */
    private final class GameLoop implements ActionListener {

        /**
         * {@inheritDoc}
         */
        public void actionPerformed(ActionEvent e) {
            if(!pongModel.checkGameOverScenario()) {
                pongModel.update();
                pongView.repaint();
            } else {
                gameTimer.stop();
            }
        }
    }
}
