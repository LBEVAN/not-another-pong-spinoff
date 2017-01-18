package com.pong.system.sound;

import com.pong.system.resource.ResourceManager;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

/**
 * The SoundManager manages all of the sound/music playing and caching in the game.
 *
 * @author LBEVAN
 */
public class SoundManager {

    private static SoundManager instance;

    private final float targetDB = -30f;

    private Clip music;
    private Clip soundOverMusic;

    /**
     * Private constructor to stop external creation.
     */
    private SoundManager() {}

    /**
     * Retrieve the instance of the SoundManager.
     *
     * @return soundManager instance
     */
    public static SoundManager getInstance() {
        if(instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Play music. There can only be one music clip playing at a given time.
     *
     * @param musicKey
     */
    public void playMusic(final String musicKey) {
        music = ResourceManager.getInstance().getSound(musicKey);

        setClipVolumeToTarget(music, targetDB);

        music.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stop playing music.
     */
    public void stopMusic() {
        music.stop();
    }

    /**
     * Play a sound.
     *
     * @param soundKey
     */
    public void playSound(final String soundKey) {
        Clip clip = ResourceManager.getInstance().getSound(soundKey);
        playSound(clip);
    }

    /**
     * Play a sound over the music.
     * This reduces the volume of the music during the playing of the sound
     * and then schedules an event to return the music back to the original volume.
     *
     * @param soundKey
     */
    public void playSoundOverMusic(final String soundKey) {
        soundOverMusic = ResourceManager.getInstance().getSound(soundKey);

        // reduce the volume of the music
        setClipVolumeToTarget(music, -50f);

        // play the new sound
        playSound(soundOverMusic);

        // schedule a timer to return once the length of the sound file has finished
        int time = getLengthOfClipInSeconds(soundOverMusic);
        Timer timer = new Timer(time * 1000, arg -> setClipVolumeToTarget(music, targetDB));
        timer.setRepeats(false); // Only execute once
        timer.start(); // Go go go!
    }

    /**
     * Play sound implementation.
     * Normalise the sound volume and then start playing.
     *
     * @param clip
     */
    private void playSound(final Clip clip) {
        setClipVolumeToTarget(clip, targetDB);

        // Stop the player if it is still running, avoiding issues with multiple calls
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0); // rewind to the beginning
        clip.start();
    }

    /**
     * Set the clip volume to a target (float) value.
     *
     * @param clip
     * @param target float volume
     */
    private void setClipVolumeToTarget(final Clip clip, final float target) {
        if(clip != null) {
            FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(target);
        }
    }

    /**
     * Get the length of the supplied clip in seconds.
     *
     * @param clip
     * @return int length of clip
     */
    private int getLengthOfClipInSeconds(final Clip clip) {
        final double clipLength = clip.getMicrosecondLength() / 1000000.0;
        return Math.toIntExact((long) clipLength);
    }
}
