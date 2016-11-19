package com.pong.model.modifier;

import com.pong.model.entity.Entity;
import com.pong.system.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * HeightModifier is a Modifier implementation that modifies the height of the specified entity.
 *
 * @author LBEVAN
 */
public class HeightModifier extends BaseModifier {

    private int heightModifier = 50;
    private double duration = 5.0;

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public HeightModifier(int x, int y) {
        super(x, y);
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
    public boolean hasExpired() {
        long elapsedTime = System.nanoTime() - startTime;
        return elapsedTime / 1000000000.0 > duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        return ResourceManager.getInstance().getImage("MODIFIER");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValue() {
        return heightModifier;
    }
}
