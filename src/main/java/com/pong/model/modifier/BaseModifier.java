package com.pong.model.modifier;

import com.pong.model.entity.Entity;

/**
 * BaseModifier serves as a base class for all modifiers.
 * This will never be instantiated but should be extended to allow for specialised modifiers.
 *
 * @author LBEVAN
 */
public abstract class BaseModifier implements Modifier<Entity> {

    private int x;
    private int y;

    protected boolean hasApplied = false;
    protected long startTime;

    /**
     * Constructor.
     * Protected resolution so that its only available to subclasses.
     *
     * @param x
     * @param y
     */
    protected BaseModifier(int x, int y) {
        this.x = x;
        this.y = y;
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
     * Modify the entity given the modifier.
     *
     * @param entity
     * @param modifier
     */
    protected abstract void modify(final Entity entity, int modifier);
}
