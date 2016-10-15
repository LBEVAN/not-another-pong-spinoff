package com.pong.view;

import com.pong.Pong;

import javax.swing.*;
import java.awt.*;

/**
 * Base class for a view.
 *
 * @author LBEVAN
 */
public abstract class View extends JPanel {

    public View() {
        setPreferredSize(new Dimension(Pong.SCREEN_WIDTH, Pong.SCREEN_HEIGHT));
    }
}
