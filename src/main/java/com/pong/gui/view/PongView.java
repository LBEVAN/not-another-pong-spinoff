package com.pong.gui.view;

import com.pong.gui.components.MenuLabel;
import com.pong.model.PongModel;
import com.pong.model.entity.Ball;
import com.pong.model.entity.Computer;
import com.pong.model.entity.Player;
import com.pong.model.environment.EnvironmentBall;
import com.pong.model.modifier.Modifier;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
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

        Graphics2D graphics2D = ((Graphics2D) graphics);

        paintArena(graphics);
        paintBall(graphics2D);
        paintPlayer(graphics2D);
        paintComputer(graphics2D);
        paintModifiers(graphics);
        paintEnvironmentBall(graphics2D);

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
        graphics.drawImage(model.getEnvironment().getImage(), 0, 0, null);

        // paint the center line
        graphics.setColor(Color.WHITE);
        graphics.drawLine(width / 2, 0, width / 2, height);
    }

    /**
     * Paint the Ball entity.
     *
     * @param graphics
     */
    private void paintBall(Graphics2D graphics) {
        final Ball ball = model.getBall();
        graphics.fill(new Ellipse2D.Double(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight()));
    }

    /**
     * Paint the Player entity.
     *
     * @param graphics
     */
    private void paintPlayer(Graphics2D graphics) {
        final Player player = model.getPlayer();

//        AffineTransform transform = new AffineTransform();
//        transform.translate(player.getX(), player.getY());
//
//        // the scaling below is a fix for modified height. When using AffineTransform, needed for double precision,
//        // we need to scale as there is no other way to affect the height of the image
//        if(player.getHeight() > player.getImage().getHeight()) {
//            transform.scale(1, 2);
//        }
//
//        graphics.drawImage(player.getImage(), transform, null);

        graphics.fill(new Rectangle2D.Double(player.getX(), player.getY(), player.getWidth(), player.getHeight()));
    }

    /**
     * Paint the Computer entity.
     *
     * @param graphics
     */
    private void paintComputer(Graphics2D graphics) {
        final Computer computer = model.getComputer();

//        AffineTransform transform = new AffineTransform();
//        transform.translate(computer.getX(), computer.getY());
//
//        // the scaling below is a fix for modified height. When using AffineTransform, needed for double precision,
//        // we need to scale as there is no other way to affect the height of the image
//        if(computer.getHeight() > computer.getImage().getHeight()) {
//            transform.scale(1, 2);
//        }
//
//        graphics.drawImage(computer.getImage(), transform, null);

        graphics.fill(new Rectangle2D.Double(computer.getX(), computer.getY(), computer.getWidth(), computer.getHeight()));
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
     * Paint the environmental ball.
     *
     * @param graphics
     */
    private void paintEnvironmentBall(Graphics2D graphics) {
        final EnvironmentBall environmentBall = model.getEnvironmentBall();
        if(environmentBall != null) {
            final BufferedImage image = environmentBall.getImage();
            AffineTransform transform = new AffineTransform();
            transform.translate(environmentBall.getX(), environmentBall.getY());
            graphics.drawImage(image, transform, null);
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