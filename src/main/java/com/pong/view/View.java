package com.pong.view;

import com.pong.Pong;

import javax.swing.*;
import java.awt.*;

/**
 * Base class for a view.
 *
 * Created by LBEVAN on 04/10/2016.
 */
public abstract class View extends JPanel {

    public View() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Pong.SCREEN_WIDTH, Pong.SCREEN_HEIGHT));
    }
}
