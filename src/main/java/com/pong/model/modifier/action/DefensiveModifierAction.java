package com.pong.model.modifier.action;

import com.pong.model.eventhandler.ModifierEventHandler;
import com.pong.model.modifier.AbstractModifier;

/**
 * ModifierAction implementation for executing defensive modifiers.
 *
 * @author LBEVAN
 */
public class DefensiveModifierAction extends ModifierAction {

    // region init
    /**
     * Constructor.
     *
     * @param modifier
     * @param applyTo
     */
    public DefensiveModifierAction(AbstractModifier modifier, ModifierEventHandler applyTo) {
        super(modifier, applyTo);
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        applyTo.onConsumeDefensiveModifier(modifier);
    }
    // endregion
}
