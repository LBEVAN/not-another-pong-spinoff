package com.pong.controller;

import com.pong.gamestate.GameStateManager;
import com.pong.factory.MvcFactory;
import com.pong.factory.MvcWrapper;
import com.pong.gui.view.GameOverView;
import com.pong.gui.view.PongView;
import com.pong.model.GameOverModel;
import com.pong.model.PongModel;
import com.pong.model.entity.Player;
import com.pong.model.entity.component.InputComponent;
import com.pong.model.entity.player.ComputerInputComponent;
import com.pong.model.entity.player.PlayerId;
import com.pong.model.entity.player.PlayerInputComponent;
import com.pong.model.input.GlobalInputManager;
import com.pong.model.leaderboard.Leaderboard;
import com.pong.model.leaderboard.LeaderboardEntry;
import com.pong.model.scoring.ScoreManager;
import com.pong.gamestate.GameState;
import com.pong.system.Constants;
import com.pong.system.sound.Sound;
import com.pong.system.sound.SoundCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The PongController controls the Pong game, including the game loop and user input.
 * It queries both the view and the model to prompt it to repaint or update every game tick.
 *
 * @author LBEVAN
 */
public class PongController implements Controller {

    // region data
    private final PongModel model;
    private final PongView view;

    private Timer gameTimer;
    // endregion

    // region init
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
    // endregion

    // region public API
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
        model.setStartGameTime(System.nanoTime());
    }
    // endregion

    // region private API
    /**
     * Setup the user input methods.
     */
    private void initUserInput() {
        InputMap inputMap = view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = view.getActionMap();

        InputComponent<Player> playerInputComponent = new PlayerInputComponent(inputMap, actionMap);
        model.getPlayerById(PlayerId.ONE).setInputComponent(playerInputComponent);

        InputComponent<Player> computerInputComponent = new ComputerInputComponent(model);
        model.getPlayerById(PlayerId.TWO).setInputComponent(computerInputComponent);

        GlobalInputManager globalInputManager = new GlobalInputManager(inputMap, actionMap, model);
    }

    /**
     * OnGameOver event. Stop the game timer, add an entry to the leaderboard and change to the game over screen.
     */
    @Sound(soundKey = "", soundCommand = SoundCommand.STOP_MUSIC)
    private void onGameOver() {
        gameTimer.stop();

        final ScoreManager scoreManager = model.getScoreManager();

        Leaderboard leaderboard = new Leaderboard();
        leaderboard.add(new LeaderboardEntry("Player1", scoreManager.getScore(PlayerId.ONE)));
        leaderboard.save();

        MvcWrapper<GameOverModel, GameOverView, GameOverController> mvc = MvcFactory.createGameOver(scoreManager.getScore(PlayerId.ONE), scoreManager.getScore(PlayerId.TWO));
        GameStateManager.getInstance().changeState(GameState.GAME_OVER, mvc);
    }

    /**
     * The game loop executor. This will be called from the timer every tick.
     */
    private final class GameLoop implements ActionListener {

        /**
         * {@inheritDoc}
         */
        public void actionPerformed(ActionEvent e) {
            if(model.isGameOver()) {
                onGameOver();
            } else {
                model.update();
                view.repaint();
            }
        }
    }
    // endregion
}
