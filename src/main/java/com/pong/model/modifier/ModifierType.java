package com.pong.model.modifier;

import com.pong.model.PongModel;
import com.pong.model.entity.player.PlayerId;
import com.pong.model.modifier.action.DefensiveModifierAction;
import com.pong.model.modifier.action.ModifierAction;
import com.pong.model.modifier.action.OffensiveModifierAction;
import com.pong.system.Constants;
import com.pong.system.resource.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * ModifiersType enum is a defined set of modifiers available in the game.
 *
 * @author LBEVAN
 */
public enum ModifierType {

    INCREASE_HEIGHT() {
        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractModifier create() {
            return new IncreaseHeightModifier();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BufferedImage getImage() {
            return ResourceManager.getInstance().getGraphic(Constants.HEIGHT_MODIFIER_ICON);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ModifierAction generateAction(PongModel data, PlayerId initiator) {
            return new DefensiveModifierAction(create(), data.getPlayerById(initiator));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Increases the height of the user's paddle.";
        }
    },
    INCREASE_SPEED() {
        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractModifier create() {
            return new IncreaseSpeedModifier();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BufferedImage getImage() {
            return ResourceManager.getInstance().getGraphic(Constants.SPEED_MODIFIER_ICON);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ModifierAction generateAction(PongModel data, PlayerId initiator) {
            return new DefensiveModifierAction(create(), data.getPlayerById(initiator));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Increases the speed of the user's paddle.";
        }
    },
    INCREASE_BALL_SPEED() {
        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractModifier create() {
            return new IncreaseBallSpeedModifier();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BufferedImage getImage() {
            return ResourceManager.getInstance().getGraphic(Constants.BALL_SPEED_MODIFIER_ICON);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ModifierAction generateAction(PongModel data, PlayerId initiator) {
            return new OffensiveModifierAction(create(), data.getBall(), data.getPlayerById(initiator));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Increases the speed of the ball.";
        }
    },
    DECREASE_SPEED() {
        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractModifier create() {
            return new DecreaseSpeedModifier();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BufferedImage getImage() {
            return ResourceManager.getInstance().getGraphic(Constants.DECREASE_SPEED_MODIFIER_ICON);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ModifierAction generateAction(PongModel data, PlayerId initiator) {
            return new OffensiveModifierAction(create(), data.getPlayerById(PlayerId.getOther(initiator)), data.getPlayerById(initiator));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Decreases the speed of the opponents paddle.";
        }
    },
    DECREASE_HEIGHT() {
        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractModifier create() {
            return new DecreaseHeightModifier();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BufferedImage getImage() {
            return ResourceManager.getInstance().getGraphic(Constants.DECREASE_HEIGHT_MODIFIER_ICON);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ModifierAction generateAction(PongModel data, PlayerId initiator) {
            return new OffensiveModifierAction(create(), data.getPlayerById(PlayerId.getOther(initiator)), data.getPlayerById(initiator)
            );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Decreases the height of the opponent's paddle.";
        }
    };

    /**
     * Create the Modifier.
     *
     * @return modifier
     */
    public abstract AbstractModifier create();

    /**
     * Retrieve the image associated to the type.
     *
     * @return image
     */
    public abstract BufferedImage getImage();

    /**
     * Generate an action for the modifier type, given data and an initiator.
     *
     * @param data
     * @param initiator
     * @return action
     */
    public abstract ModifierAction generateAction(PongModel data, PlayerId initiator);

    /**
     * Retrieve a string description for this modifier type.
     *
     * @return dedcription
     */
    public abstract String getDescription();
}
