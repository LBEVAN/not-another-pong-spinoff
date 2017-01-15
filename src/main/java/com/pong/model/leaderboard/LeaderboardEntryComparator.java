package com.pong.model.leaderboard;

import java.util.Comparator;

/**
 * @author LBEVAN
 */
public class LeaderboardEntryComparator implements Comparator<LeaderboardEntry> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final LeaderboardEntry entry1, final LeaderboardEntry entry2) {
        return entry1.compareTo(entry2);
    }
}
