package com.pong.model.modifier;

import com.pong.model.entity.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BaseModifier serves as a base class for all modifiers.
 * This will never be instantiated but should be extended to allow for specialised modifiers.
 *
 * @author LBEVAN
 */
public abstract class BaseModifier implements Modifier<Entity> {

    private int x;
    private int y;

    private int width = 20;
    private int height = 20;

    protected double duration;

    protected boolean hasApplied = false;
    protected long startTime;
    protected boolean isActive = true;

    /**
     * Constructor.
     * Protected resolution so that its only available to subclasses.
     *
     * @param x
     * @param y
     * @param duration
     */
    protected BaseModifier(int x, int y, double duration) {
        this.x = x;
        this.y = y;
        this.duration = duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final Entity entity) {
        modify(entity, getValue());
        hasApplied = true;
        startTime = System.nanoTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasApplied() {
        return hasApplied;
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
    public void remove(Entity entity) {
        modify(entity, -getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onHit() {
        isActive = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return isActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTimeRemaining() {
        long elapsedTime = System.nanoTime() - startTime;
        return new BigDecimal(duration - (elapsedTime / 1000000000.0)).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDuration() {
        return duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseDuration(double duration) {
        this.duration += duration;
    }

    /**
     * Modify the entity given the modifier.
     *
     * @param entity
     * @param modifier
     */
    protected abstract void modify(final Entity entity, int modifier);
}
