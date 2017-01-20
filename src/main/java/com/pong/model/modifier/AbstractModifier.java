package com.pong.model.modifier;

import com.pong.model.entity.Entity;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Base class for all types of modifiers.
 *
 * @author LBEVAN
 */
public abstract class AbstractModifier<E extends Entity> {

    // region data
    private boolean hasApplied = false;
    private boolean isActive = false;
    private long startTime;
    private double duration;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param duration
     */
    protected AbstractModifier(double duration) {
        this.duration = duration;
    }
    // endregion

    // region public API
    /**
     * Apply the modification to the specified entity.
     *
     * @param entity
     */
    public final void apply(final E entity) {
        modify(entity, getValue());
        hasApplied = true;
        isActive = true;
        startTime = System.nanoTime();
    }

    /**
     * Remove the modification on the specified entity.
     *
     * @param entity
     */
    public void remove(final E entity) {
        modify(entity, -getValue());
    }

    /**
     * Increase the duration of the modifier.
     *
     * @param duration
     */
    public void increaseDuration(double duration) {
        this.duration += duration;
    }

    /**
     * Get the time remaining for this modifier.
     *
     * @return timeRemaining
     */
    public double getTimeRemaining() {
        long elapsedTime = System.nanoTime() - startTime;
        return new BigDecimal(duration - (elapsedTime / 1000000000.0)).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }
    // endregion

    // region checks
    /**
     * Check whether the modification has been applied to an entity.
     *
     * @return hasApplied
     */
    public final boolean hasApplied() {
        return hasApplied;
    }

    /**
     * Check whether the modification has expired.
     *
     * @return hasExpired
     */
    public final boolean hasExpired() {
        long elapsedTime = System.nanoTime() - startTime;
        return elapsedTime / 1000000000.0 > getDuration();
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the duration of the modifier.
     *
     * @return duration
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Retrieve whether the modifier is active in the game world.
     *
     * @return isActive
     */
    public boolean isActive() {
        return isActive;
    }
    // endregion

    // region abstract methods
    /**
     * Retrieve the image associated to the modifier.
     *
     * @return bufferedImage
     */
    public abstract BufferedImage getImage();

    /**
     * Retrieve the modifier value.
     *
     * @return value
     */
    public abstract int getValue();

    /**
     * Get the type of this modifier.
     *
     * @return type
     */
    public abstract ModifierType getType();

    /**
     * Modify the entity given the modifier.
     *
     * @param entity
     * @param modifier
     */
    protected abstract void modify(E entity, int modifier);
    // endregion
}
