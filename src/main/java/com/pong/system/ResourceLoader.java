package com.pong.system;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ResourceLoader loads all of the application resources (e.g. graphics, sounds, fonts)
 * into memory, storing the loaded resources in the ResourceManager.
 *
 * @author LBEVAN
 */
public class ResourceLoader {

    private static final Logger LOGGER = Logger.getLogger(ResourceLoader.class.getName());

    private static final String GRAPHICS_PATH = "/graphics/";
    private static final String FONTS_PATH = "/fonts/";

    /**
     * Load all resources (i.e graphics, sounds, fonts...)
     */
    public void loadResources() {
        try {
            loadFonts();
            loadGraphics();
        } catch (IOException e) {
            LOGGER.log(Level.CONFIG, "IOException loading resources: " + e);
        }
    }

    /**
     * Retrieve the available resources for the specified path.
     *
     * @param path
     * @return availableResources
     * @throws IOException
     */
    public List<String> getAvailableResources(final String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try(
            InputStream in = getResourceAsStream(path);

            BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }


    /**
     * Delegate method to load all graphics.
     *
     * @throws IOException
     */
    private void loadGraphics() throws IOException {
        ResourceManager.getInstance().addLoadedGraphic(getFilenameWithoutExtension("Modifier.png"), loadGraphic(GRAPHICS_PATH + "Modifier.png"));
    }

    /**
     * Delegate method to load all fonts.
     *
     * @throws IOException
     */
    private void loadFonts() throws IOException {
        try {
            ResourceManager.getInstance().registerCustomFont(loadFont(FONTS_PATH + "Technoid.ttf"));
        } catch (FontFormatException e) {
            LOGGER.log(Level.CONFIG, "FontFormatException loading font: " + e);
        }
    }

    /**
     * Get the resource, in the form of path + filename, as an InputStream.s
     *
     * @param resource
     * @return inputStream
     */
    private InputStream getResourceAsStream(final String resource) {
        return getClass().getResourceAsStream(resource);
    }

    /**
     * Retrieve the filename without the extension (e.g. test.txt would return test).
     * Return null if fileName is null and fileName does not contain '.'
     *
     * @param fileName
     * @return filenameWithoutExtension
     */
    private String getFilenameWithoutExtension(String fileName) {
        if(fileName != null && fileName.contains(".")) {
            final String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
            return fileNameWithoutExtension;
        } else {
            return null;
        }
    }

    /**
     * Load a graphic, passing resource as path + filename.
     *
     * @param resource
     * @return bufferedImage
     * @throws IOException
     */
    public BufferedImage loadGraphic(final String resource) throws IOException {
        InputStream inputStream = getResourceAsStream(resource);
        try {
            return ImageIO.read(inputStream);
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            inputStream.close();
        }
    }

    /**
     * Load a font, passing resource as path + filename.
     *
     * @param resource
     * @return font
     */
    public Font loadFont(final String resource) throws IOException, FontFormatException {
        return Font.createFont(Font.TRUETYPE_FONT, getResourceAsStream(resource)).deriveFont(14f);
    }
}
