package com.pong.system.resource;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * The ResourceManager manages all of the resources in the application (e.g. Fonts, Graphics, Sounds).
 *
 * @author LBEVAN
 */
public class ResourceManager {

    private static ResourceManager instance;

    private Font customFont;
    private Map<String, BufferedImage> graphics = new HashMap<>();
    private Map<String, Clip> sounds = new HashMap<>();

    /**
     * Private constructor to stop external creation.
     */
    private ResourceManager() {}

    /**
     * Retrieve the instance of the ResourceManager.
     *
     * @return menuManager
     */
    public static ResourceManager getInstance() {
        if(instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    /**
     * Add a loaded graphics, in the form of a BufferedImage, to the graphics map.
     *
     * @param key
     * @param bufferedImage
     */
    public void addLoadedGraphic(final String key, final BufferedImage bufferedImage) {
        graphics.put(key, bufferedImage);
    }

    /**
     * Add a loaded sound file, in the form of a Clip, to the sounds map.
     *
     * @param key
     * @param clip
     */
    public void addLoadedSound(final String key, final Clip clip) {
        sounds.put(key, clip);
    }

    /**
     * Register the custom font.
     *
     * @param font
     */
    public void registerCustomFont(Font font) {
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        customFont = font;
    }

    /**
     * Retrieve the custom application font.
     *
     * @return customFont
     */
    public Font getFont() {
        return customFont;
    }

    /**
     * Get a loaded graphic with the specified key.
     *
     * @param key
     * @return bufferedImage
     */
    public BufferedImage getGraphic(String key) {
        return graphics.get(key);
    }

    /**
     * Get a loaded sound with the specified key.
     *
     * @param key
     * @return clip
     */
    public Clip getSound(String key) {
        return sounds.get(key);
    }
}
