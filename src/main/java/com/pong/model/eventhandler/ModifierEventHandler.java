package com.pong.model.eventhandler;

import com.pong.model.modifier.AbstractModifier;
import com.pong.model.modifier.ModifierType;

/**
 * Modifier events interface. Defines a selection of modifiers events that need to be handled.
 *
 * @author LBEVAN
 */
public interface ModifierEventHandler {

    /**
     * Event - to consume a defensive modifier.
     *
     * @param modifier
     */
    void onConsumeDefensiveModifier(AbstractModifier modifier);

    /**
     * Event - to consume an offensive modifier.
     *
     * @param modifier
     */
    void onConsumeOffensiveModifier(AbstractModifier modifier);

    /**
     * Event - to use an offensive modifier.
     * @param modifierType
     */
    void onUseOffensiveModifier(ModifierType modifierType);
}
