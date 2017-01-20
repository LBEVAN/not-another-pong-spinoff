package com.pong.model.modifier;

import com.pong.model.entity.Entity;

import java.awt.image.BufferedImage;

/**
 * Modifier implementation, use for decreasing the speed of an entity.
 *
 * @author LBEVAN
 */
public class DecreaseSpeedModifier extends AbstractModifier<Entity> {

    // region data
    private final int speedModifier = -2;
    // endregion

    // region init
    /**
     * Constructor.
     */
    public DecreaseSpeedModifier() {
        super(5.0);
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValue() {
        return speedModifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifierType getType() {
        return ModifierType.DECREASE_SPEED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "--SPEED  " + getTimeRemaining() + "  ";
    }
    // endregion

    // region protected API
    /**
     * {@inheritDoc}
     */
    @Override
    protected void modify(Entity entity, int modifier) {
        entity.setModifiedSpeed(entity.getModifiedSpeed() + modifier);
    }
    // endregion
}
