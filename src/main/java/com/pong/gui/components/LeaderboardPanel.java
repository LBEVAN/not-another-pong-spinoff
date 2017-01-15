package com.pong.gui.components;

import com.pong.model.leaderboard.LeaderboardEntry;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A JPanel component for displaying the leaderboard (high scores).
 *
 * @author LBEVAN
 */
public class LeaderboardPanel extends JPanel {

    // region data
    private JLabel[] highScoreLabels;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param leaderboardEntries
     */
    public LeaderboardPanel(final List<LeaderboardEntry> leaderboardEntries) {
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        generateHighScoreLabels(leaderboardEntries);
    }
    // endregion

    // region public API
    /**
     * Update the display of high scores, passing in a new set of leaderboard values to display.
     *
     * @param leaderboardEntries
     */
    public void updateHighScoreDisplay(final List<LeaderboardEntry> leaderboardEntries) {
        removeAll();
        generateHighScoreLabels(leaderboardEntries);
        revalidate();
        repaint();
    }
    // endregion

    // region private API
    /**
     * Generate an array of high score labels (JLabels) from a set of leaderboard values.
     *
     * @param leaderboardEntries
     */
    private void generateHighScoreLabels(final List<LeaderboardEntry> leaderboardEntries) {
        highScoreLabels = new MenuLabel[leaderboardEntries.size()];
        for (int i = 0; i < leaderboardEntries.size(); i++) {
            LeaderboardEntry leaderboardEntry = leaderboardEntries.get(i);
            highScoreLabels[i] = new MenuLabel((i + 1) + ".    " + leaderboardEntry.getPlayerName() + "  :  " + leaderboardEntry.getScore(), 24f);
        }

        for(JLabel highScoreLabel : highScoreLabels) {
            add(highScoreLabel);
        }
    }
    // endregion
}
