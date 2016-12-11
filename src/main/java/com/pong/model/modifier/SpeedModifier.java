package com.pong.model.modifier;

import com.pong.model.entity.Entity;
import com.pong.system.ResourceManager;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * HeightModifier is a Modifier implementation that modifies the speed of the specified entity.
 *
 * @author LBEVAN
 */
public class SpeedModifier extends BaseModifier {

    private static final String NAME = "Speed";

    private int speedModifier = 4;
    private double duration = 5.0;

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public SpeedModifier(int x, int y) {
        super(x, y);
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
    public boolean hasExpired() {
        long elapsedTime = System.nanoTime() - startTime;
        return elapsedTime / 1000000000.0 > duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage() {
        return ResourceManager.getInstance().getGraphic("Modifier");
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
    public double getTimeRemaining() {
        long elapsedTime = System.nanoTime() - startTime;
        return new BigDecimal(duration - (elapsedTime / 1000000000.0)).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }
}
