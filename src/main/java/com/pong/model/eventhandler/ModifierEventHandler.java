package com.pong.model.eventhandler;

import com.pong.model.modifier.AbstractModifier;
import com.pong.model.modifier.ModifierType;

/**
 * @author LBEVAN
 */
public interface ModifierEventHandler {

    void onConsumeModifier(AbstractModifier modifier);
    void onUseOffensiveModifier(ModifierType modifierType);
}
