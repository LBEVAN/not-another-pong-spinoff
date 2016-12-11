package com.pong.model.modifier;

import com.pong.model.modifier.factory.HeightModifierFactory;
import com.pong.model.modifier.factory.ModifierFactory;
import com.pong.model.modifier.factory.SpeedModifierFactory;

/**
 * ModifiersType enum is a defined set of modifiers available in the game (e.g. HeightModifier).
 *
 * @author LBEVAN
 */
public enum ModifierType {

    PADDLE_HEIGHT(new HeightModifierFactory()),
    PADDLE_SPEED(new SpeedModifierFactory());

    private ModifierFactory factory;

    /**
     * Enum constructor.
     *
     * @param factory
     */
    ModifierType(ModifierFactory factory) {
        this.factory = factory;
    }

    /**
     * Retrieve the factory.
     *
     * @return factory
     */
    public ModifierFactory getFactory() {
        return factory;
    }
}
