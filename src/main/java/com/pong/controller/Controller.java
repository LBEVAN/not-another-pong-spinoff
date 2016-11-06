package com.pong.controller;

import com.pong.gui.view.View;
import com.pong.model.Model;

/**
 * Controller interface defines behaviour all controllers must have.
 *
 * @author LBEVAN
 */
public interface Controller<M extends Model, V extends View> {

    /**
     * Initialise the view.
     *
     * @param model
     * @param view
     */
    void init(M model, V view);
}
