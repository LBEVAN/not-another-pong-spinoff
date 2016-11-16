package com.pong.factory;

import com.pong.controller.Controller;
import com.pong.gui.view.View;
import com.pong.model.Model;

/**
 * Generic wrapper class for holding the components of the MVC pattern: Model, View, Controller.
 *
 * @author LBEVAN
 */
public final class MvcWrapper<M extends Model, V extends View, C extends Controller> {

    private final M model;
    private final V view;
    private final C controller;

    /**
     * Constructor.
     *
     * @param model
     * @param view
     * @param controller
     */
    public MvcWrapper(final M model, final V view, final C controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
    }

    /**
     * Retrieve the model.
     *
     * @return model
     */
    public M getModel() {
        return model;
    }

    /**
     * Retrieve the view.
     *
     * @return view
     */
    public V getView() {
        return view;
    }

    /**
     * Retrieve the controller.
     *
     * @return controller
     */
    public C getController() {
        return controller;
    }
}