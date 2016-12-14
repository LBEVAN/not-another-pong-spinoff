package com.pong.gui.view;

import com.pong.gui.components.MenuLabel;
import com.pong.model.PongModel;
import com.pong.model.entity.Ball;
import com.pong.model.entity.Computer;
import com.pong.model.entity.Player;
import com.pong.model.modifier.Modifier;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 * The PongView is the view component of the game.
 * It handles the repainting every tick.
 *
 * @author LBEVAN
 */
public class PongView extends View {

    private PongModel model;

    private JPanel infoPanel;
    private JPanel top;
    private JPanel bottom;
    private JPanel playerModifiersPanel;

    private JLabel playerScore;
    private JLabel computerScore;

    private JLabel playerModifiers;
    private JLabel computerModifiers;

    /**
     * Constructor.
     *
     * @param model
     * @param width
     * @param height
     */
    public PongView(final PongModel model, int width, int height) {
        super(width, height);

        this.model = model;

        initInfoPanel();

        setOpaque(true);
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        requestFocus();
        setFocusable(true);

        add(infoPanel);
    }

    /**
     * Initialise the InfoPanel (i.e. the in-game UI).
     */
    private void initInfoPanel() {
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setPreferredSize(new Dimension(width, height));
        infoPanel.setOpaque(false);

        top = new JPanel(new GridLayout(1, 2));
        top.setOpaque(false);

        JPanel playerScorePanel = new JPanel();
        playerScore = new MenuLabel(16f);
        playerScorePanel.setOpaque(false);
        playerScorePanel.add(playerScore);

        JPanel computerScorePanel = new JPanel();
        computerScore = new MenuLabel(16f);
        computerScorePanel.setOpaque(false);
        computerScorePanel.add(computerScore);

        top.add(playerScorePanel);
        top.add(computerScorePanel);

        infoPanel.add(top, BorderLayout.PAGE_START);

        bottom = new JPanel(new GridLayout(1, 2));
        bottom.setOpaque(false);

        playerModifiersPanel = new JPanel();
        playerModifiers = new MenuLabel(16f);
        playerModifiersPanel.setOpaque(false);
        playerModifiersPanel.add(playerModifiers);

        JPanel computerModifiersPanel = new JPanel();
        computerModifiers = new MenuLabel(16f);
        computerModifiersPanel.setOpaque(false);
        computerModifiersPanel.add(computerModifiers);

        bottom.add(playerModifiersPanel);
        bottom.add(computerModifiersPanel);

        infoPanel.add(bottom, BorderLayout.PAGE_END);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getViewName() {
        return "PongView";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        paintArena(graphics);
        paintBall(graphics);
        paintPlayer(graphics);
        paintComputer(graphics);
        paintModifiers(graphics);

        updateScores();
        updateModifiers();
    }

    /**
     * Paint the arena bounds and its details (e.g. center line).
     *
     * @param graphics
     */
    private void paintArena(Graphics graphics) {
        // draw the background
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);

        // paint the center line
        graphics.setColor(Color.WHITE);
        graphics.drawLine(width / 2, 0, width / 2, height);
    }

    /**
     * Paint the Ball entity.
     *
     * @param graphics
     */
    private void paintBall(Graphics graphics) {
        final Ball ball = model.getBall();
        graphics.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
    }

    /**
     * Paint the Player entity.
     *
     * @param graphics
     */
    private void paintPlayer(Graphics graphics) {
        final Player player = model.getPlayer();
        graphics.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
    }

    /**
     * Paint the Computer entity.
     *
     * @param graphics
     */
    private void paintComputer(Graphics graphics) {
        final Computer computer = model.getComputer();
        graphics.drawImage(computer.getImage(), computer.getX(), computer.getY(), computer.getWidth(), computer.getHeight(), null);
    }

    /**
     * Paint the modifiers in the game.
     *
     * @param graphics
     */
    private void paintModifiers(Graphics graphics) {
        for(Modifier modifier : model.getActiveModifiers()) {
            graphics.drawImage(modifier.getImage(), modifier.getX(), modifier.getY(), modifier.getWidth(), modifier.getHeight(), null);
        }
    }

    /**
     * Update the scores text for the Player and Computer.
     */
    private void updateScores() {
        playerScore.setText(String.format("Player Score: %s", model.getPlayerScore()));
        computerScore.setText(String.format("Computer Score: %s", model.getComputerScore()));
    }

    /**
     * Update the modifiers text for the Player and Computer
     */
    private void updateModifiers() {
        playerModifiers.setText("Modifiers: " + createModifierString(model.getPlayer().getModifiers()));
        computerModifiers.setText("Modifiers: " + createModifierString(model.getComputer().getModifiers()));
    }

    /**
     * Create the string for a list of modifiers.
     *
     * @param modifiers
     * @return modifiersString
     */
    private String createModifierString(Collection<Modifier> modifiers) {
        StringBuilder stringBuilder = new StringBuilder();

        modifiers.forEach(modifier -> {
            stringBuilder.append(modifier.getName());
            stringBuilder.append(" ");
            stringBuilder.append(modifier.getTimeRemaining());
            stringBuilder.append(" | ");
        });

        return stringBuilder.toString();
    }
}
