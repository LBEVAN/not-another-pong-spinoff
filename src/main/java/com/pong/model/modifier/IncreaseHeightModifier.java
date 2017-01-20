package com.pong.model.modifier;

import com.pong.model.entity.Player;
import com.pong.system.resource.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * @author LBEVAN
 */
public class IncreaseHeightModifier extends AbstractModifier<Player> {

    // region data
    private final int heightModifier = 75;
    // endregion

    // region init
    /**
     * Constructor.
     */
    public IncreaseHeightModifier() {
        super(5.0);
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        return ResourceManager.getInstance().getGraphic("HeightModifier");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValue() {
        return heightModifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifierType getType() {
        return ModifierType.INCREASE_HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "++HEIGHT  " + getTimeRemaining() + "  ";
    }
    // endregion

    // region protected API
    /**
     * {@inheritDoc}
     */
    @Override
    protected void modify(Player entity, int modifier) {
        entity.setHeight(entity.getHeight() + modifier);
    }
    // endregion
}
