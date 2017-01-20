package com.pong.model.modifier;

import com.pong.model.entity.Entity;

import java.awt.image.BufferedImage;

/**
 * Modifier implementation, use for decreasing the height of an entity.
 *
 * @author LBEVAN
 */
public class DecreaseHeightModifier extends AbstractModifier<Entity> {

    // region data
    public int decreaseHeightModifier = -25;
    // endregion

    // region init
    /**
     * Constructor.
     */
    public DecreaseHeightModifier() {
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
        return decreaseHeightModifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifierType getType() {
        return ModifierType.DECREASE_HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "--HEIGHT  " + getTimeRemaining() + "  ";
    }
    // endregion

    // region protected API
    /**
     * {@inheritDoc}
     */
    @Override
    protected void modify(Entity entity, int modifier) {
        entity.setHeight(entity.getHeight() + modifier);
    }
    // endregion
}
