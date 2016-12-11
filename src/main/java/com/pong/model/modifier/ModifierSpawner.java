package com.pong.model.modifier;

import com.pong.gui.frame.PongFrame;
import com.pong.model.PongModel;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The ModifierSpawner handles the spawning of modifiers into the game.
 *
 * @author LBEVAN
 */
public final class ModifierSpawner {

    private final int maxModifiers = 10;

    /**
     * Spawn the modifier into the game world.
     *
     * @param pongModel
     */
    public void spawn(final PongModel pongModel) {
        int currentNumberOfActiveModifiers = pongModel.getActiveModifiers().size();

        if(currentNumberOfActiveModifiers < maxModifiers) {
            spawnImpl(pongModel);
        }
    }

    /**
     * Implementation of the spawning - Spawn the modifier into a random location on the x and y axis.
     *
     * @param pongModel
     */
    private void spawnImpl(final PongModel pongModel) {
        // get random co-ordinates in the game world (bound to sane x and y values)
        int x = ThreadLocalRandom.current().nextInt(30, PongFrame.SCREEN_WIDTH - 30 + 1);
        int y = ThreadLocalRandom.current().nextInt(20, PongFrame.SCREEN_HEIGHT - 20 + 1);

        // get a random ModifierType
        ModifierType[] modifierTypes = ModifierType.values();
        ModifierType randomModifier = modifierTypes[ThreadLocalRandom.current().nextInt(0, modifierTypes.length)];

        // add a new active modifier using the factory associated to the ModifierType
        pongModel.addActiveModifier(randomModifier.getFactory().create(x, y));
    }
}
