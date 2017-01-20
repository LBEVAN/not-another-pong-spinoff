package com.pong.model.leaderboard;

import com.pong.Pong;
import com.pong.system.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * The leaderboard class handles the high scores for the game.
 * The leaderboard exposes the ability to load and save persisted high scores from the preferences.
 *
 * @author LBEVAN
 */
public class Leaderboard {

    // region data
    private List<LeaderboardEntry> leaderboardEntries;
    private final int maxEntries = 5;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * The act of constructing the leaderboard will load persisted values in preferences.
     */
    public Leaderboard() {
        load();
        leaderboardEntries.sort(new LeaderboardEntryComparator());
    }
    // endregion

    // region public API
    /**
     * Add a LeaderboardEntry into the leaderboard.
     * If the current leaderboard size is not greater than the max size then the entry is added.
     * If the leaderboard is full then the last entry in the list is compared; if the new entry has a higher score or is
     * lexicographically more than the last entry then it replaces the last entry.
     * The leaderboard is then sorted.
     *
     * @param leaderboardEntry
     * @return hasBeenAdded
     */
    public boolean add(final LeaderboardEntry leaderboardEntry) {
        boolean result;
        if(leaderboardEntries.size() == maxEntries) {
            final LeaderboardEntry lastEntry = leaderboardEntries.get(leaderboardEntries.size() - 1);

            if(leaderboardEntry.compareTo(lastEntry) < 0) {
                // the new entry is considered a new high score
                leaderboardEntries.set(leaderboardEntries.size() - 1, leaderboardEntry);
                result = true;
            } else {
                result = false;
            }
        } else {
            // not at max so just add it
            leaderboardEntries.add(leaderboardEntry);
            result = true;
        }

        leaderboardEntries.sort(new LeaderboardEntryComparator());

        return result;
    }

    /**
     * Save the current list of high scores into the preferences.
     */
    public void save() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(leaderboardEntries);
            out.flush();
            byte[] bytes = bos.toByteArray();

            Preferences preferences = getPreferences();
            preferences.putByteArray(Constants.KEY_LEADERBOARD, bytes);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

    /**
     * Load the persisted high scores from the preferences.
     */
    public void load() {
        Preferences preferences = getPreferences();
        byte[] leaderboardBytes = preferences.getByteArray(Constants.KEY_LEADERBOARD, null);

        if(leaderboardBytes == null) {
            leaderboardEntries = new ArrayList<>();
            return;
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(leaderboardBytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            leaderboardEntries = (List<LeaderboardEntry>) in.readObject();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

    /**
     * Clear the persisted high scores.
     */
    public void clear() {
        Preferences preferences = getPreferences();
        preferences.remove(Constants.KEY_LEADERBOARD);
        load();
    }
    // endregion

    // region getters & setters
    /**
     * Retrieve the high scores.
     *
     * @return
     */
    public List<LeaderboardEntry> getHighScores() {
        return leaderboardEntries;
    }

    /**
     * Retrieve the preferences for the Pong node.
     *
     * @return preferences
     */
    private static Preferences getPreferences() {
        return Preferences.userNodeForPackage(Pong.class);
    }
    // endregion
}
