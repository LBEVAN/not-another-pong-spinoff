package com.pong.model.modifier;

import com.pong.model.entity.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The ModifierSystem handles the modifiers for a particular entity;
 * this includes its lifecycle such as updating, and removal on of the modification etc.
 *
 * @author LBEVAN
 */
public final class ModifierSystem {

    // region data
    private Map<ModifierType, AbstractModifier> modifierMap;
    private Map<ModifierType, Integer> modifierCharges;

    private int maxCharges = 2;
    // endregion

    // region init
    /**
     * Constructor.
     */
    public ModifierSystem() {
        modifierMap = new HashMap<>();
        modifierCharges = new HashMap<>();
    }
    // endregion

    // region public API
    /**
     * Perform an update of the modifier system which:
     *   - applies modifications to the entity
     *   - removed modifications if they have been applied or have expired.
     *
     * @param entity
     */
    public void update(final Entity entity) {
        Iterator<AbstractModifier> iter = modifierMap.values().iterator();
        while(iter.hasNext()) {
            AbstractModifier modifier = iter.next();

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
    public void addModifier(AbstractModifier modifier) {
        final ModifierType modifierType = modifier.getType();

        if(!isMaxNumChargesForModifierType(modifierType)) {
            if(modifierMap.containsKey(modifierType)) {
                // type exists so increase duration
                AbstractModifier mod = modifierMap.get(modifierType);
                mod.increaseDuration(modifier.getDuration());
                modifierMap.put(mod.getType(), mod);
            } else {
                // does not exist so add to map
                modifierMap.put(modifierType, modifier);
            }
        }
    }

    /**
     * Increment the number of charges for a given modifier type.
     *
     * @param modifierType
     */
    public void incrementNumCharges(ModifierType modifierType) {
        // update charges count
        if(modifierCharges.containsKey(modifierType)) {
            if(!isMaxNumChargesForModifierType(modifierType)) {
                modifierCharges.put(modifierType, modifierCharges.get(modifierType) + 1);
            }
        } else {
            modifierCharges.put(modifierType, 1);
        }
    }

    /**
     * Check to see if the modifier type has been used the maximum number of times.
     *
     * @param modifierType
     * @return isMaxNumChargesForModifierType
     */
    public boolean isMaxNumChargesForModifierType(ModifierType modifierType) {
        if(modifierCharges.containsKey(modifierType)) {
            int currentCharges = modifierCharges.get(modifierType);
            return currentCharges >= maxCharges;
        } else {
            return false;
        }
    }

    /**
     * Get the number of charges for a given modifier type.
     *
     * @param modifierType
     * @return numCharges
     */
    public int getNumChargesForModifierType(ModifierType modifierType) {
        if(modifierCharges.containsKey(modifierType)) {
            return modifierCharges.get(modifierType);
        } else {
            return maxCharges;
        }
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the modifier with the specified key.
     *
     * @param key
     * @return modifier
     */
    public AbstractModifier getModifier(final ModifierType key) {
        return modifierMap.get(key);
    }

    /**
     * Get the modifiers currently active.
     *
     * @return modifiers
     */
    public Collection<AbstractModifier> getModifiers() {
        return modifierMap.values();
    }
    // endregion
}
