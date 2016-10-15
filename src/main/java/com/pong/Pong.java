package com.pong;

import com.pong.view.GameView;

import javax.swing.*;

/**
 * The Pong class is the swing window for the application.
 * It acts as the application entry point and initialises the first view.
 *
 * @author LBEVAN
 */
public class Pong extends JFrame {

    private static final String TITLE = "Not Another Pong Spin-off";
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 400;

    public Pong() {
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setVisible(true);
        setResizable(false);
        setTitle(TITLE);
        getContentPane().add(new GameView());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Pong();
    }
}
