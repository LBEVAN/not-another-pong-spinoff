package com.pong.model.leaderboard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link LeaderboardEntryComparator}.
 *
 * @author LBEVAN
 */
public class TestLeaderboardEntryComparator {

    @Test
    public void testCompare_FirstEntryHigherScore() {
        LeaderboardEntry entry1 = new LeaderboardEntry("One", 10);
        LeaderboardEntry entry2 = new LeaderboardEntry("Two", 5);

        int result = new LeaderboardEntryComparator().compare(entry1, entry2);

        assertTrue(result == -1);
    }

    @Test
    public void testCompare_SecondEntryHigherScore() {
        LeaderboardEntry entry1 = new LeaderboardEntry("One", 1);
        LeaderboardEntry entry2 = new LeaderboardEntry("Two", 9);

        int result = new LeaderboardEntryComparator().compare(entry1, entry2);

        assertTrue(result == 1);
    }

    @Test
    public void testCompare_EntryScoresMatch_FirstEntryAlphabeticName() {
        LeaderboardEntry entry1 = new LeaderboardEntry("Aaa", 10);
        LeaderboardEntry entry2 = new LeaderboardEntry("Bbb", 10);

        int result = new LeaderboardEntryComparator().compare(entry1, entry2);

        assertTrue(result == -1);
    }

    @Test
    public void testCompare_EntryScoresMatch_SecondEntryAlphabeticName() {
        LeaderboardEntry entry1 = new LeaderboardEntry("Zzz", 10);
        LeaderboardEntry entry2 = new LeaderboardEntry("Www", 10);

        int result = new LeaderboardEntryComparator().compare(entry1, entry2);

        assertTrue(result == 1);
    }

    @Test
    public void testCompare_EntryScoresMatch_EntryNamesMatch() {
        LeaderboardEntry entry1 = new LeaderboardEntry("Eee", 10);
        LeaderboardEntry entry2 = new LeaderboardEntry("Eee", 10);

        int result = new LeaderboardEntryComparator().compare(entry1, entry2);

        assertTrue(result == 0);
    }

    @Test
    public void testComparatorWithList() {
        List<LeaderboardEntry> leaderboardEntries = new ArrayList<>(2);
        LeaderboardEntry entry1 = new LeaderboardEntry("Zzz", 10);
        LeaderboardEntry entry2 = new LeaderboardEntry("Bbb", 8);
        leaderboardEntries.add(entry1);
        leaderboardEntries.add(entry2);

        leaderboardEntries.sort(new LeaderboardEntryComparator());

        assertTrue(leaderboardEntries.get(0).equals(entry1));
        assertTrue(leaderboardEntries.get(1).equals(entry2));
    }
}
