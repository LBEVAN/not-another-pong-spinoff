package com.pong.system.resource;

import com.pong.system.Constants;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
    private static final String MODIFIERS_PATH = "/graphics/modifiers/";
    private static final String FONTS_PATH = "/fonts/";
    private static final String SOUNDS_PATH = "/sounds/";

    /**
     * Load all resources (i.e graphics, sounds, fonts...)
     */
    public void loadResources() {
        try {
            loadFonts();
            loadGraphics();
            loadSounds();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException loading resources: " + e);
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
        ResourceManager.getInstance().addLoadedGraphic(Constants.SPACE_BACKGROUND, loadGraphic(GRAPHICS_PATH + "SpaceBackground.png"));
        ResourceManager.getInstance().addLoadedGraphic(Constants.ICE_BACKGROUND, loadGraphic(GRAPHICS_PATH + "IceBackground.png"));
        ResourceManager.getInstance().addLoadedGraphic(Constants.DESERT_BACKGROUND, loadGraphic(GRAPHICS_PATH + "DesertBackground.png"));

        ResourceManager.getInstance().addLoadedGraphic(Constants.HEIGHT_MODIFIER, loadGraphic(GRAPHICS_PATH + "HeightModifier.png"));
        ResourceManager.getInstance().addLoadedGraphic(Constants.SPEED_MODIFIER, loadGraphic(GRAPHICS_PATH + "SpeedModifier.png"));

        ResourceManager.getInstance().addLoadedGraphic(Constants.SPACE_ENVIRONMENT_ICON, loadGraphic(GRAPHICS_PATH + "SpaceEnvironmentIcon.png"));
        ResourceManager.getInstance().addLoadedGraphic(Constants.ICE_ENVIRONMENT_ICON, loadGraphic(GRAPHICS_PATH + "IceEnvironmentIcon.png"));
        ResourceManager.getInstance().addLoadedGraphic(Constants.DESERT_ENVIRONMENT_ICON, loadGraphic(GRAPHICS_PATH + "DesertEnvironmentIcon.png"));

        ResourceManager.getInstance().addLoadedGraphic(Constants.SPEED_MODIFIER_ICON, loadGraphic(MODIFIERS_PATH + "SpeedModifierIcon.png"));
        ResourceManager.getInstance().addLoadedGraphic(Constants.DECREASE_SPEED_MODIFIER_ICON, loadGraphic(MODIFIERS_PATH + "DecreaseSpeedModifierIcon.png"));
        ResourceManager.getInstance().addLoadedGraphic(Constants.BALL_SPEED_MODIFIER_ICON, loadGraphic(MODIFIERS_PATH + "BallSpeedModifierIcon.png"));
        ResourceManager.getInstance().addLoadedGraphic(Constants.HEIGHT_MODIFIER_ICON, loadGraphic(MODIFIERS_PATH + "HeightModifierIcon.png"));
        ResourceManager.getInstance().addLoadedGraphic(Constants.DECREASE_HEIGHT_MODIFIER_ICON, loadGraphic(MODIFIERS_PATH + "DecreaseHeightModifierIcon.png"));
    }

    /**
     * Delegate method to load all fonts.
     *
     * @throws IOException
     */
    private void loadFonts() throws IOException {
        try {
//            ResourceManager.getInstance().registerCustomFont(loadFont(FONTS_PATH + "Technoid.ttf"));
            ResourceManager.getInstance().registerCustomFont(loadFont(FONTS_PATH + "earthorbiterbold.ttf"));
        } catch (FontFormatException e) {
            LOGGER.log(Level.SEVERE, "FontFormatException loading font: " + e);
        }
    }

    /**
     * Delegate method to load all sounds.
     *
     * @throws IOException
     */
    private void loadSounds() throws IOException {
        try {
            ResourceManager.getInstance().addLoadedSound(Constants.GAME_MUSIC, loadSound(SOUNDS_PATH + "GameMusic.wav"));

            ResourceManager.getInstance().addLoadedSound(Constants.BALL_DEATH_SOUND, loadSound(SOUNDS_PATH + "BallDeath.wav"));

            ResourceManager.getInstance().addLoadedSound(Constants.SPACE_ENVIRONMENT_SWITCH_SOUND, loadSound(SOUNDS_PATH + "SpaceEnvironmentSwitch.wav"));
            ResourceManager.getInstance().addLoadedSound(Constants.ICE_ENVIRONMENT_SWITCH_SOUND, loadSound(SOUNDS_PATH + "IceEnvironmentSwitch.wav"));
            ResourceManager.getInstance().addLoadedSound(Constants.DESERT_ENVIRONMENT_SWITCH_SOUND, loadSound(SOUNDS_PATH + "DesertEnvironmentSwitch.wav"));
        } catch (UnsupportedAudioFileException e) {
            LOGGER.log(Level.SEVERE, "UnsupportedAudioFileException loading sound: " + e);
        } catch (LineUnavailableException e) {
            LOGGER.log(Level.SEVERE, "LineUnavailableException loading sound: " + e);
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
     * Load a sound, passing resource as path + filename.
     *
     * @param resource
     * @return clip
     * @throws IOException
     */
    public Clip loadSound(final String resource) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        BufferedInputStream inputStream = new BufferedInputStream(getResourceAsStream(resource));
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (IOException ioe) {
            throw ioe;
        } catch (UnsupportedAudioFileException uafe) {
            throw uafe;
        } catch (LineUnavailableException lue) {
            throw lue;
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
