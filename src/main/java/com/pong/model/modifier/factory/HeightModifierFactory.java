package com.pong.model.modifier.factory;

import com.pong.model.modifier.HeightModifier;
import com.pong.model.modifier.Modifier;

/**
 * ModifierFactory implementation that produces HeightModifiers.
 *
 * @author LBEVAN
 */
public class HeightModifierFactory implements ModifierFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Modifier create(int x, int y) {
        return new HeightModifier(x, y);
    }
}
