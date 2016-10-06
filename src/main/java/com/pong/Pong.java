package com.pong;

import javax.swing.*;

/**
 * Created by LBEVAN on 04/10/2016.
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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
    }
}
