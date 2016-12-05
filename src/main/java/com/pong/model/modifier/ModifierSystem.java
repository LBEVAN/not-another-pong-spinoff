package com.pong.model.modifier;

import com.pong.model.entity.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The ModifierSystem handles the modifiers for a particular entity;
 * this includes its lifecycle such as updating, and removal on of the modification etc.
 *
 * @author LBEVAN
 */
public final class ModifierSystem {

    private List<Modifier> modifiers;

    /**
     * Constructor.
     */
    public ModifierSystem() {
        modifiers = new ArrayList<>();
    }

    /**
     * Perform an update of the modifier system which:
     *   - applies modifications to the entity
     *   - removed modifications if they have been applied or have expired.
     *
     * @param entity
     */
    public void update(final Entity entity) {
        Iterator<Modifier> iter = modifiers.iterator();
        while(iter.hasNext()) {
            Modifier modifier = iter.next();

            // only apply the modifier if it hasn't already been applied
            if(modifier.hasApplied()) {

                if(modifier.hasExpired()) {

                    // if expired then remove
                    modifier.remove(entity);
                    iter.remove();
                }
            } else {
                modifier.apply(entity);
            }
        }
    }

    /**
     * Add a modifier into the list of modifiers on the entity.
     *
     * @param modifier
     */
    public void addModifier(Modifier modifier) {
        modifiers.add(modifier);
    }

    /**
     * Get the modifiers currently active.
     *
     * @return modifiers
     */
    public List<Modifier> getModifiers() {
        return modifiers;
    }
}
