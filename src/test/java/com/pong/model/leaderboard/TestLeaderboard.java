package com.pong.model.leaderboard;

import com.pong.Pong;
import com.pong.system.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.junit.Assert.*;

/**
 * Test class for {@link Leaderboard}.
 *
 * @author LBEVAN
 */
public class TestLeaderboard {

    private Leaderboard leaderboard;

    // region init
    @Before
    public void setUp() {
        leaderboard = new Leaderboard();
    }

    @After
    public void tearDown() throws BackingStoreException {
        leaderboard = null;
        Preferences.userNodeForPackage(Pong.class).clear();
    }
    // endregion

    // region load tests
    @Test
    public void testLoad_LeaderboardEntriesExist() {
        List<LeaderboardEntry> leaderboardEntries = new ArrayList<>();
        LeaderboardEntry leaderboardEntry = new LeaderboardEntry("Player1", 2);
        leaderboardEntries.add(leaderboardEntry);

        // manually persist test values into preferences
        Preferences preferences = Preferences.userNodeForPackage(Pong.class);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(leaderboardEntries);
            out.flush();
            byte[] bytes = bos.toByteArray();

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

        // call load to force load of new entries
        leaderboard.load();

        // check values have been loaded
        final List<LeaderboardEntry> listToCheck = leaderboard.getHighScores();
        assertNotNull(listToCheck);
        LeaderboardEntry leaderboardEntryToCheck = listToCheck.get(0);
        assertNotNull(leaderboardEntryToCheck);
        assertEquals(leaderboardEntryToCheck.getPlayerName(), leaderboardEntry.getPlayerName());
        assertEquals(leaderboardEntryToCheck.getScore(), leaderboardEntry.getScore());
    }

    @Test
    public void testLoad_LeaderboardEntriesDoNotExist() {
        leaderboard.load();

        // check no values have been loaded
        final List<LeaderboardEntry> listToCheck = leaderboard.getHighScores();
        assertNotNull(listToCheck);
        assertTrue(listToCheck.size() == 0);
    }
    // endregion

    // region save tests
    @Test
    public void testSave() {
        // pre populate with a couple of entries
        leaderboard.add(new LeaderboardEntry("Player1", 10));
        leaderboard.add(new LeaderboardEntry("Player2", 8));

        // run test
        leaderboard.save();

        // manually check the preferences
        Preferences preferences = Preferences.userNodeForPackage(Pong.class);
        byte[] byteArrayToCheck = preferences.getByteArray(Constants.KEY_LEADERBOARD, null);
        assertNotNull(byteArrayToCheck);

        // parse values
        List<LeaderboardEntry> leaderboardEntries = new ArrayList<>();
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArrayToCheck);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            leaderboardEntries = (List<LeaderboardEntry>) in.readObject();
        } catch(IOException ioe) {
            fail();
        } catch(ClassNotFoundException cnfe) {
            fail();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                fail();
            }
        }

        assertEquals(2, leaderboardEntries.size());
        assertTrue(leaderboardEntries.get(0).getScore() == 10);
        assertTrue(leaderboardEntries.get(1).getScore() == 8);
    }
    // endregion

    // region add tests
    @Test
    public void testAdd_LeaderBoardEmpty() {
        // run test
        LeaderboardEntry leaderboardEntry = new LeaderboardEntry("Player1", 10);
        boolean result = leaderboard.add(leaderboardEntry);

        // asserts
        assertTrue(result);
    }

    @Test
    public void testAdd_LeaderBoardNotFull() {
        // pre populate with a couple of entries
        leaderboard.add(new LeaderboardEntry("Player1", 10));
        leaderboard.add(new LeaderboardEntry("Player2", 8));

        // run test
        LeaderboardEntry leaderboardEntry = new LeaderboardEntry("Player3", 9);
        boolean result = leaderboard.add(leaderboardEntry);

        // asserts
        assertTrue(result);
        assertEquals(leaderboardEntry, leaderboard.getHighScores().get(1));
    }

    @Test
    public void testAdd_LeaderBoardFull_EntryValid() {
        // pre populate with 5 entries to make leaderboard full
        leaderboard.add(new LeaderboardEntry("Player1", 10));
        leaderboard.add(new LeaderboardEntry("Player2", 9));
        leaderboard.add(new LeaderboardEntry("Player3", 8));
        leaderboard.add(new LeaderboardEntry("Player4", 7));
        leaderboard.add(new LeaderboardEntry("Player5", 5));

        // run test
        LeaderboardEntry leaderboardEntry = new LeaderboardEntry("Player6", 6);
        boolean result = leaderboard.add(leaderboardEntry);

        // asserts
        assertTrue(result);
        assertEquals(5, leaderboard.getHighScores().size());
        assertEquals(leaderboardEntry, leaderboard.getHighScores().get(leaderboard.getHighScores().size() - 1));
    }

    @Test
    public void testAdd_LeaderBoardFull_EntryNotValid() {
        // pre populate with 5 entries to make leaderboard full
        leaderboard.add(new LeaderboardEntry("Player1", 10));
        leaderboard.add(new LeaderboardEntry("Player2", 9));
        leaderboard.add(new LeaderboardEntry("Player3", 8));
        leaderboard.add(new LeaderboardEntry("Player4", 7));
        leaderboard.add(new LeaderboardEntry("Player5", 6));

        // run test
        LeaderboardEntry leaderboardEntry = new LeaderboardEntry("Player6", 5);
        boolean result = leaderboard.add(leaderboardEntry);

        // asserts
        assertFalse(result);
        assertEquals(5, leaderboard.getHighScores().size());
        assertNotSame(leaderboardEntry, leaderboard.getHighScores().get(leaderboard.getHighScores().size() - 1));
    }
    // endregion

    // region clear tests
    @Test
    public void testClear() {
        // add test entries to leaderboard
        leaderboard.add(new LeaderboardEntry("P1", 10));
        leaderboard.add(new LeaderboardEntry("P2", 12));

        // persist entries
        leaderboard.save();

        // run test
        leaderboard.clear();

        // asserts
        final List<LeaderboardEntry> leaderboardEntries = leaderboard.getHighScores();
        assertTrue(leaderboardEntries.size() == 0);
    }
    // endregion
}
