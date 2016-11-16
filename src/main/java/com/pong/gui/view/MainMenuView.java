package com.pong.gui.view;

import com.pong.gui.components.MenuButton;
import com.pong.gui.components.MenuLabel;
import com.pong.gui.frame.PongFrame;
import com.pong.model.MainMenuModel;

import javax.swing.*;
import java.awt.*;

/**
 * The MainMenuView is the view component of the main menu.
 *
 * @author LBEVAN
 */
public class MainMenuView extends View {

    private final MainMenuModel model;

    private LayoutManager layoutManager;

    private JLabel gameTitle;
    private JLabel gameVersion;

    private JButton exitButton;
    private JButton playButton;

    /**
     * Constructor.
     *
     * @param width
     * @param height
     */
    public MainMenuView(final MainMenuModel model, int width, int height) {
        super(width, height);

        this.model = model;

        setBackground(Color.BLACK);

        layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layoutManager);

        gameTitle = new MenuLabel(PongFrame.TITLE, 60f);
        gameVersion = new MenuLabel("Version: " + PongFrame.VERSION, 20f);

        exitButton = new MenuButton("Exit");
        playButton = new MenuButton("Play");

        // title and version
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(gameTitle);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(gameVersion);

        // buttons
        add(Box.createRigidArea(new Dimension(0, 60)));
        add(playButton);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(exitButton);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getViewName() {
        return "MainMenuView";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if(model != null) {
            java.util.List<MainMenuModel.AnimatedBall> animatedBalls = model.getAnimatedBalls();

            // draw each ball
            for (MainMenuModel.AnimatedBall ball : animatedBalls) {
                graphics.setColor(ball.getColor());
                graphics.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
            }
        }
    }

    /**
     * Retrieve the exit button.
     *
     * @return exitButton
     */
    public JButton getExitButton() {
        return exitButton;
    }

    /**
     * Retrieve the play button.
     *
     * @return playButton
     */
    public JButton getPlayButton() {
        return playButton;
    }
}
