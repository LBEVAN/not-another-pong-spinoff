package com.pong;

import com.pong.gui.frame.PongFrame;

import javax.swing.*;

/**
 * The Pong class is the application entry point.
 * It is responsible for creating the frame and switching to the inital view.
 *
 * @author LBEVAN
 */
public class Pong {

    /**
     * Main method - application entry point.
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PongFrame pongFrame = new PongFrame();
            }
        });
    }
}
