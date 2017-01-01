package com.pong.model.modifier;

import com.pong.model.entity.Entity;
import com.pong.system.resource.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * HeightModifier is a Modifier implementation that modifies the speed of the specified entity.
 *
 * @author LBEVAN
 */
public class SpeedModifier extends BaseModifier {

    private static final String NAME = "Speed";

    private int speedModifier = 4;

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public SpeedModifier(int x, int y) {
        super(x, y, 5.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void modify(Entity entity, int modifier) {
        entity.setModifiedSpeed(entity.getModifiedSpeed() + modifier);
    }

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
    public String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifierType getType() {
        return ModifierType.PADDLE_SPEED;
    }
}