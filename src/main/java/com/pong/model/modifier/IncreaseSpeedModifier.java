package com.pong.model.modifier;

import com.pong.model.entity.Entity;
import com.pong.system.resource.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * @author LBEVAN
 */
public class IncreaseSpeedModifier extends AbstractModifier<Entity> {

    // region data
    private final int speedModifier = 4;
    // endregion

    // region init
    /**
     * Constructor.
     */
    public IncreaseSpeedModifier() {
        super(5.0);
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        return ResourceManager.getInstance().getGraphic("SpeedModifier");
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
        return ModifierType.INCREASE_SPEED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "++SPEED  " + getTimeRemaining() + "  ";
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
