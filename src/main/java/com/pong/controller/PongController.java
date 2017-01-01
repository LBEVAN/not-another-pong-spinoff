package com.pong.controller;

import com.pong.GameStateManager;
import com.pong.controller.input.Direction;
import com.pong.factory.MvcFactory;
import com.pong.factory.MvcWrapper;
import com.pong.gui.view.GameOverView;
import com.pong.gui.view.PongView;
import com.pong.model.GameOverModel;
import com.pong.model.PongModel;
import com.pong.model.entity.Player;
import com.pong.state.GameState;
import com.pong.system.Constants;
import com.pong.system.sound.Sound;
import com.pong.system.sound.SoundCommand;

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
public class PongController implements Controller {

    private final PongModel model;
    private final PongView view;

    private Timer gameTimer;

    /**
     * Constructor.
     *
     * @param model
     * @param view
     */
    public PongController(final PongModel model, final PongView view) {
        this.model = model;
        this.view = view;

        this.gameTimer = new Timer(1000/60, new GameLoop());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        initUserInput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Sound(soundKey = Constants.GAME_MUSIC, soundCommand = SoundCommand.PLAY_MUSIC)
    public void start() {
        gameTimer.start();
    }

    /**
     * Setup the user input methods.
     */
    private void initUserInput() {
        InputMap inputMap = view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = view.getActionMap();

        // player
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DOWN");
        actionMap.put("DOWN", new MoveAction(model.getPlayer(), 3, Direction.DOWN));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "RELEASED_DOWN");
        actionMap.put("RELEASED_DOWN", new MoveAction(model.getPlayer(), 0, Direction.DOWN));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UP");
        actionMap.put("UP", new MoveAction(model.getPlayer(), -3, Direction.UP));

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "RELEASED_UP");
        actionMap.put("RELEASED_UP", new MoveAction(model.getPlayer(), 0, Direction.UP));
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
            // set the base speed to the deltaY (i.e. moving or not moving)
            player.setBaseSpeed(deltaY);

            // get the move speed from the player itself as it needs to calculate base and modified speed
            player.move(player.getSpeed(), direction);
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
            if(!model.checkGameOverScenario()) {
                model.update();
                view.repaint();
            } else {
                onGameOver();
            }
        }
    }

    @Sound(soundKey = "", soundCommand = SoundCommand.STOP_MUSIC)
    private void onGameOver() {
        gameTimer.stop();
        MvcWrapper<GameOverModel, GameOverView, GameOverController> mvc = MvcFactory.createGameOver(model.getPlayerScore(), model.getComputerScore());
        GameStateManager.getInstance().changeState(GameState.GAME_OVER, mvc);
    }
}
