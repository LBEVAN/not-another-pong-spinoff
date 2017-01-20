package com.pong.model.modifier.action;

import com.pong.model.eventhandler.ModifierEventHandler;
import com.pong.model.modifier.AbstractModifier;

/**
 * ModifierAction defines an action to execute a specified modifier,
 * either by consuming a modifier or applying it to an entity.
 *
 * @author LBEVAN
 */
public abstract class ModifierAction {

    // region data
    protected AbstractModifier modifier;
    protected ModifierEventHandler applyTo;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param modifier
     * @param applyTo
     */
    protected ModifierAction(AbstractModifier modifier, ModifierEventHandler applyTo) {
        this.modifier = modifier;
        this.applyTo = applyTo;
    }
    // endregion

    // region abstract methods
    /**
     * Execute against the modifier.
     */
    public abstract void execute();
    // endregion
}
