package com.pong.model.modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link ModifierSystem}.
 *
 * @author LBEVAN
 */
public class TestModifierSystem {

    // region data
    private ModifierSystem modifierSystem;
    // endregion

    // region init
    @Before
    public void setUp() {
        modifierSystem = new ModifierSystem();
    }

    @After
    public void tearDown() {
        modifierSystem = null;
    }
    // endregion

    @Test
    public void testAddModifierWhenTypeDoesNotExist() {
        modifierSystem.addModifier(new IncreaseHeightModifier());

        Collection<AbstractModifier> modifiers = modifierSystem.getModifiers();
        AbstractModifier modifier = modifierSystem.getModifier(ModifierType.INCREASE_HEIGHT);

        assertEquals(1, modifiers.size());
        assertEquals(ModifierType.INCREASE_HEIGHT, modifier.getType());
    }

    @Test
    public void testAddModifierWhenTypeDoesExist() {
        modifierSystem.addModifier(new IncreaseSpeedModifier());
        modifierSystem.addModifier(new IncreaseSpeedModifier());

        Collection<AbstractModifier> modifiers = modifierSystem.getModifiers();
        AbstractModifier modifier = modifierSystem.getModifier(ModifierType.INCREASE_SPEED);

        assertEquals(1, modifiers.size());
        assertEquals(ModifierType.INCREASE_SPEED, modifier.getType());
        assertTrue(Double.compare(10.0, modifier.getDuration()) == 0);
    }

    @Test
    public void testIncrementNumChargesWhenTypeDoesNotExist() {
        modifierSystem.incrementNumCharges(ModifierType.INCREASE_HEIGHT);

        boolean isMaxNumCharges = modifierSystem.isMaxNumChargesForModifierType(ModifierType.INCREASE_HEIGHT);

        assertFalse(isMaxNumCharges);
    }

    @Test
    public void testIncrementNumChargesWhenTypeDoesExist() {
        modifierSystem.incrementNumCharges(ModifierType.INCREASE_HEIGHT);
        modifierSystem.incrementNumCharges(ModifierType.INCREASE_HEIGHT);

        boolean isMaxNumCharges = modifierSystem.isMaxNumChargesForModifierType(ModifierType.INCREASE_HEIGHT);

        assertTrue(isMaxNumCharges);
    }

    @Test
    public void testIncrementNumChargesWhenTypeDoesExistButDoesNotExceedMax() {
        modifierSystem.incrementNumCharges(ModifierType.INCREASE_HEIGHT);
        modifierSystem.incrementNumCharges(ModifierType.INCREASE_HEIGHT);
        modifierSystem.incrementNumCharges(ModifierType.INCREASE_HEIGHT);
        modifierSystem.incrementNumCharges(ModifierType.INCREASE_HEIGHT);
        modifierSystem.incrementNumCharges(ModifierType.INCREASE_HEIGHT);

        boolean isMaxNumCharges = modifierSystem.isMaxNumChargesForModifierType(ModifierType.INCREASE_HEIGHT);
        int numCharges = modifierSystem.getNumChargesForModifierType(ModifierType.INCREASE_HEIGHT);

        assertTrue(isMaxNumCharges);
        assertTrue(numCharges == 2);
    }
}
