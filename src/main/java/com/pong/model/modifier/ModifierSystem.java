package com.pong.model.modifier;

import com.pong.model.entity.Entity;

import java.util.*;

/**
 * The ModifierSystem handles the modifiers for a particular entity;
 * this includes its lifecycle such as updating, and removal on of the modification etc.
 *
 * @author LBEVAN
 */
public final class ModifierSystem {

    private Map<ModifierType, Modifier> modifierMap;

    /**
     * Constructor.
     */
    public ModifierSystem() {
        modifierMap = new HashMap<>();
    }

    /**
     * Perform an update of the modifier system which:
     *   - applies modifications to the entity
     *   - removed modifications if they have been applied or have expired.
     *
     * @param entity
     */
    public void update(final Entity entity) {
        Iterator<Modifier> iter = modifierMap.values().iterator();
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
     * If the modifier already exists then it will increase the duration.
     *
     * @param modifier
     */
    public void addModifier(Modifier modifier) {
        if(modifierMap.containsKey(modifier.getType())) {
            // type exists so increase duration
            Modifier mod = modifierMap.get(modifier.getType());
            mod.increaseDuration(modifier.getDuration());
            modifierMap.put(mod.getType(), mod);
        } else {
            // does not exist so add to map
            modifierMap.put(modifier.getType(), modifier);
        }
    }

    /**
     * Retrieve the modifier with the specified key.
     *
     * @param key
     * @return modifier
     */
    public Modifier getModifier(final ModifierType key) {
        return modifierMap.get(key);
    }

    /**
     * Get the modifiers currently active.
     *
     * @return modifiers
     */
    public Collection<Modifier> getModifiers() {
        return modifierMap.values();
    }
}
