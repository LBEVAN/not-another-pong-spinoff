package com.pong.model.modifier;

import com.pong.system.Constants;
import com.pong.system.resource.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * ModifiersType enum is a defined set of modifiers available in the game (e.g. HeightModifier).
 *
 * @author LBEVAN
 */
public enum ModifierType {

    INCREASE_HEIGHT() {
        @Override
        public AbstractModifier create() {
            return new IncreaseHeightModifier();
        }

        @Override
        public BufferedImage getImage() {
            return ResourceManager.getInstance().getGraphic("HeightModifier");
        }
    },
    INCREASE_SPEED() {
        @Override
        public AbstractModifier create() {
            return new IncreaseSpeedModifier();
        }

        @Override
        public BufferedImage getImage() {
            return ResourceManager.getInstance().getGraphic(Constants.SPEED_MODIFIER_ICON);
        }
    },
    INCREASE_BALL_SPEED() {
        @Override
        public AbstractModifier create() {
            return new IncreaseSpeedModifier();
        }

        @Override
        public BufferedImage getImage() {
            return ResourceManager.getInstance().getGraphic(Constants.BALL_SPEED_MODIFIER_ICON);
        }
    },
    DECREASE_SPEED() {
        @Override
        public AbstractModifier create() {
            return new DecreaseSpeedModifier();
        }

        @Override
        public BufferedImage getImage() {
            return ResourceManager.getInstance().getGraphic(Constants.DECREASE_SPEED_MODIFIER_ICON);
        }
    },
    DECREASE_HEIGHT() {
        @Override
        public AbstractModifier create() {
            return new DecreaseHeightModifier();
        }

        @Override
        public BufferedImage getImage() {
            return ResourceManager.getInstance().getGraphic("HeightModifier");
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
}
