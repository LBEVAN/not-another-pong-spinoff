package com.pong.model.modifier.action;

import com.pong.model.eventhandler.ModifierEventHandler;
import com.pong.model.modifier.AbstractModifier;

/**
 * ModifierAction implementation for executing offensive modifiers.
 *
 * @author LBEVAN
 */
public class OffensiveModifierAction extends ModifierAction {

    // region data
    private ModifierEventHandler onUse;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param modifier
     * @param applyTo
     * @param onUse
     */
    public OffensiveModifierAction(AbstractModifier modifier, ModifierEventHandler applyTo, ModifierEventHandler onUse) {
        super(modifier, applyTo);
        this.onUse = onUse;
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        onUse.onUseOffensiveModifier(modifier.getType());
        applyTo.onConsumeOffensiveModifier(modifier);
    }
    // endregion
}
