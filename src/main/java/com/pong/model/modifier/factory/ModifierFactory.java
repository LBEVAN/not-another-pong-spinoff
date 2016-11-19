package com.pong.model.modifier.factory;

import com.pong.model.modifier.Modifier;

/**
 * Modifier factory interface defines what behaviour all modifier factory should have.
 *
 * @author LBEVAN
 */
public interface ModifierFactory {

    /**
     * Produce the specified modifier.
     *
     * @param x
     * @param y
     * @return modifier
     */
    Modifier create(int x, int y);
}
