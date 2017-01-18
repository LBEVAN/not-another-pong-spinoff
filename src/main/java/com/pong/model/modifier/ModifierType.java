package com.pong.model.modifier;

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
            return ResourceManager.getInstance().getGraphic("SpeedModifier");
        }
    };

    public abstract AbstractModifier create();
    public abstract BufferedImage getImage();
}
