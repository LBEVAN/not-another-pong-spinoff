package com.pong.controller;

import com.pong.GameStateManager;
import com.pong.controller.input.Direction;
import com.pong.gui.view.PongView;
import com.pong.model.PongModel;
import com.pong.model.entity.Player;
import com.pong.state.GameState;

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
public class PongController implements Controller<PongModel, PongView> {

    private PongModel pongModel;
    private PongView pongView;

    private Timer gameTimer;

    /**
     * Constructor.
     */
    public PongController() {
        this.gameTimer = new Timer(1000/60, new GameLoop());
    }

    /**
     * Setup the user input methods.
     */
    private void initUserInput() {
        InputMap inputMap = pongView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = pongView.getActionMap();

        // player
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DOWN");
        actionMap.put("DOWN", new MoveAction(pongModel.getPlayer(), 3, Direction.DOWN));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "RELEASED_DOWN");
        actionMap.put("RELEASED_DOWN", new MoveAction(pongModel.getPlayer(), 0, Direction.DOWN));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UP");
        actionMap.put("UP", new MoveAction(pongModel.getPlayer(), -3, Direction.UP));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "RELEASED_UP");
        actionMap.put("RELEASED_UP", new MoveAction(pongModel.getPlayer(),0, Direction.UP));
    }

    /**
     * {@inheritDoc}
     */
    public void init(PongModel model, PongView view) {
        this.pongModel = model;
        this.pongView = view;

        initUserInput();

        gameTimer.start();
    }

    /**
     * MoveAction defines a action on the Player entity
     * in order to move it in a direction at a specified speed.
     */
    private class MoveAction extends AbstractAction {

        private Player player;
        private int deltaY;
        private Direction direction;

        /**
         * Constructor.
         *
         * @param player
         * @param deltaY
         * @param direction
         */
        public MoveAction(Player player, int deltaY, Direction direction) {
            this.player = player;
            this.deltaY = deltaY;
            this.direction = direction;
        }

        /**
         * {@inheritDoc}
         */
        public void actionPerformed(ActionEvent e) {
            player.move(deltaY, direction);
        }
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
                GameStateManager.getInstance().changeState(GameState.GAME_OVER);
            }
        }
    }
}
