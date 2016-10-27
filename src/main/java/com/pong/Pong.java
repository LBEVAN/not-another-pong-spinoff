package com.pong;

import com.pong.gui.frame.PongFrame;
import com.pong.gui.view.Views;

import javax.swing.*;

/**
 * The Pong class is the application entry point.
 * It is responsible for creating the frame and switching to the inital view.
 *
 * @author LBEVAN
 */
public class Pong extends JFrame {

    /**
     * Main method - application entry point.
     *
     * @param args
     */
    public static void main(String[] args) {
        PongFrame pongFrame = new PongFrame();
        pongFrame.switchView(Views.MAIN_MENU);
    }
}
