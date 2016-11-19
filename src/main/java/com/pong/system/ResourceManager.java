package com.pong.system;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * The ResourceManager loads and manages all of the resources in the application (e.g. Fonts, Graphics, Sounds).
 *
 * @author LBEVAN
 */
public class ResourceManager {

    private static ResourceManager instance;

    private Font customFont;
    private Map<String, BufferedImage> images = new HashMap<>();

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
     * Load all resources into memory.
     * This should be called once, preferably during application startup.
     */
    public final void loadResources() {
        loadCustomFont();
        loadImages();
    }

    /**
     * Delegate method for loading the custom font.
     */
    private void loadCustomFont() {
        InputStream is = instance.getClass().getResourceAsStream("/TECHNOID.ttf");
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(14f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFont);
        } catch (Exception e) {
            // a catch all situation
            // if any exception is thrown, set the custom font to an already existing one in the system
            customFont = new Font("Arial", Font.PLAIN, 14);
        }
    }

    /**
     * Delegate method for loading all game images.
     */
    private void loadImages() {
        InputStream is = instance.getClass().getResourceAsStream("/Modifier.png");

        try {
            images.put("MODIFIER", ImageIO.read(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve the custom application font.
     *
     * @return customFont
     */
    public Font getCustomFont() {
        return customFont;
    }

    /**
     * Get a loaded image with the specified key.
     *
     * @param key
     * @return bufferedImage
     */
    public BufferedImage getImage(String key) {
        return images.get(key);
    }
}
