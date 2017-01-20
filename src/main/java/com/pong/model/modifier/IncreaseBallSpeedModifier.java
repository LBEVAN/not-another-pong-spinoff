package com.pong.model.modifier;

/**
 * Modifier implementation, use for increase the speed of the ball.
 * Specific class needed for ball, over standard IncreaseSpeedModifier class,
 * due to how the ModifierSystem caching of used modifiers works; ModifierType is tracked.
 *
 * @author LBEVAN
 */
public class IncreaseBallSpeedModifier extends IncreaseSpeedModifier {

    // region data
    private final int speedModifier = 3;
    // endregion

    // region public API
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
        return ModifierType.INCREASE_BALL_SPEED;
    }
    // endregion
}
