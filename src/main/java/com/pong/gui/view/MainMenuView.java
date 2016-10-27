package com.pong.gui.view;

import com.pong.gui.frame.PongFrame;
import com.pong.gui.components.MainMenuButton;
import com.pong.gui.components.MainMenuLabel;
import com.pong.model.MainMenuModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The MainMenuView is the view component of the main menu.
 *
 * @author LBEVAN
 */
public class MainMenuView extends View {

    private MainMenuModel mainMenuModel;

    private LayoutManager layoutManager;

    private JLabel gameTitle;
    private JLabel gameVersion;

    private JButton exitButton;
    private JButton playButton;

    /**
     * Constructor.
     *
     * @param mainMenuModel
     * @param width
     * @param height
     */
    public MainMenuView(MainMenuModel mainMenuModel, int width, int height) {
        super(width, height);

        this.mainMenuModel = mainMenuModel;

        setBackground(Color.BLACK);

        layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layoutManager);

        gameTitle = new MainMenuLabel(PongFrame.TITLE, Font.BOLD, 32);
        gameVersion = new MainMenuLabel("Version: " + PongFrame.VERSION, Font.PLAIN, 12);

        exitButton = new MainMenuButton("Exit");
        playButton = new MainMenuButton("Play");

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
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        List<MainMenuModel.AnimatedBall> animatedBalls = mainMenuModel.getAnimatedBalls();

        // draw each ball
        for(MainMenuModel.AnimatedBall ball : animatedBalls) {
            graphics.setColor(ball.getColor());
            graphics.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
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
