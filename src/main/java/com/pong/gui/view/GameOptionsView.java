package com.pong.gui.view;

import com.pong.ai.difficulty.Difficulty;
import com.pong.ai.difficulty.EasyDifficulty;
import com.pong.ai.difficulty.HardDifficulty;
import com.pong.ai.difficulty.MediumDifficulty;
import com.pong.gui.components.MenuButton;
import com.pong.gui.components.MenuDropdown;
import com.pong.gui.components.MenuLabel;
import com.pong.gui.frame.PongFrame;
import com.pong.model.GameOptionsModel;

import javax.swing.*;
import java.awt.*;

/**
 * @author LBEVAN
 */
public class GameOptionsView extends View<GameOptionsModel> {

    private static final String VIEW_TITLE = "Game Options";

    private GameOptionsModel model;

    private LayoutManager layoutManager;

    private JLabel title;
    private JLabel gameDifficulty;
    private JComboBox<Difficulty> difficultyComboBox;
    private JButton startButton;

    /**
     * Constructor.
     *
     * @param width
     * @param height
     */
    public GameOptionsView(int width, int height) {
        super(width, height);

        setBackground(Color.BLACK);

        layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layoutManager);

        // create components
        title = new MenuLabel(VIEW_TITLE, Font.BOLD, 24);
        gameDifficulty = new MenuLabel("Game Difficulty", Font.PLAIN, 12);
        difficultyComboBox = new MenuDropdown(new Difficulty[]{new EasyDifficulty(), new MediumDifficulty(), new HardDifficulty()});
        startButton = new MenuButton("Start");

        // add to panel
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(title);
        add(Box.createRigidArea(new Dimension(0, 60)));
        add(gameDifficulty);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(difficultyComboBox);
        add(Box.createRigidArea(new Dimension(0, 60)));
        add(startButton);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getViewName() {
        return "GameOptionsView";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(GameOptionsModel model) {
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }

    public JButton getStartButton() {
        return startButton;
    }
}
