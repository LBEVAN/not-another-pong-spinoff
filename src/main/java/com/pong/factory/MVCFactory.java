package com.pong.factory;

import com.pong.controller.Controller;
import com.pong.gui.view.View;
import com.pong.model.Model;

/**
 * Base abstract class for MVC factory.
 * This defines a template method 'create' to produce and initialise MVC components.
 *
 * @author LBEVAN
 */
public abstract class MVCFactory {

    /**
     * Create and initialise the MVC components.
     *
     * @return mvc
     */
    public MVCWrapper create() {
        Model model = getModel();
        View view = getView();
        Controller controller = getController();

        view.init(model);
        controller.init(model, view);

        return new MVCWrapper<>(model, view, controller);
    }

    /**
     * Delegate method for creating the model.
     *
     * @return model
     */
    protected abstract Model getModel();

    /**
     * Delegate method for creating the view.
     *
     * @return view
     */
    protected abstract View getView();

    /**
     * Delegate method for creating the controller.
     *
     * @return controller
     */
    protected abstract Controller getController();
}
