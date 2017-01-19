package com.pong.model.modifier;

import com.pong.model.entity.Entity;

import java.awt.image.BufferedImage;

/**
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
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifierType getType() {
        return null;
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
