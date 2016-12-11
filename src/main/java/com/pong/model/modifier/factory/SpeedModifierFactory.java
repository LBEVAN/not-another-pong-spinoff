package com.pong.model.modifier.factory;

import com.pong.model.modifier.Modifier;
import com.pong.model.modifier.SpeedModifier;

/**
 * ModifierFactory implementation that produces SpeedModifiers.
 *
 * @author LBEVAN
 */
public class SpeedModifierFactory implements ModifierFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Modifier create(int x, int y) {
        return new SpeedModifier(x, y);
    }
}
