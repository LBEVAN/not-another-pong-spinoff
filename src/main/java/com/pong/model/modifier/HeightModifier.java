package com.pong.model.modifier;

import com.pong.model.entity.Entity;
import com.pong.system.resource.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * HeightModifier is a Modifier implementation that modifies the height of the specified entity.
 *
 * @author LBEVAN
 */
public class HeightModifier extends BaseModifier {

    private static final String NAME = "Height";

    private int heightModifier = 75;

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public HeightModifier(int x, int y) {
        super(x, y, 5.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void modify(Entity entity, int modifier) {
        entity.setHeight(entity.getHeight() + modifier);
    }

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
    public String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifierType getType() {
        return ModifierType.PADDLE_HEIGHT;
    }
}
