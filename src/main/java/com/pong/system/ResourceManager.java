package com.pong.system;

import java.awt.*;
import java.io.InputStream;

/**
 * The ResourceManager loads and manages all of the resources in the application (e.g. Fonts, Graphics, Sounds).
 *
 * @author LBEVAN
 */
public class ResourceManager {

    private static ResourceManager instance;

    private Font customFont;

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
     * Retrieve the custom application font.
     *
     * @return customFont
     */
    public Font getCustomFont() {
        return customFont;
    }
}
