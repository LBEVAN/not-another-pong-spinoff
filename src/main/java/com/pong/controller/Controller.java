package com.pong.controller;

/**
 * Controller interface defines behaviour all controllers must have.
 *
 * @author LBEVAN
 */
public interface Controller {

    /**
     * Bind the any dependent actions or content for the model, view or controller.
     * This can include things such as view actionListeners or setting label text.
     */
    void bind();

    /**
     * Start any controller functions.
     */
    void start();
}
