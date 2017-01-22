package com.pong.model.scoring;

import com.pong.model.entity.player.PlayerId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link ScoreManager}.
 *
 * @author LBEVAN
 */
public class TestScoreManager {

    // region data
    private ScoreManager scoreManager;
    // endregion

    // region init
    @Before
    public void setUp() {
        scoreManager = new ScoreManager();
    }

    @After
    public void tearDown() {
        scoreManager = null;
    }
    // endregion

    @Test
    public void testImmediateQueryOfScoresOnCreation() {
        int p1Score = scoreManager.getScore(PlayerId.ONE);
        int p2Score = scoreManager.getScore(PlayerId.TWO);

        assertTrue(p1Score == 0);
        assertTrue(p2Score == 0);
    }

    @Test
    public void testIncreaseScore() {
        scoreManager.increaseScore(PlayerId.ONE, 12);

        int p1Score = scoreManager.getScore(PlayerId.ONE);

        assertTrue(p1Score == 12);
    }

    @Test
    public void testIncreaseScoreMultipleTimes() {
        scoreManager.increaseScore(PlayerId.ONE, 12);
        scoreManager.increaseScore(PlayerId.ONE, 18);
        scoreManager.increaseScore(PlayerId.ONE, 30);

        int p1Score = scoreManager.getScore(PlayerId.ONE);

        assertTrue(p1Score == 60);
    }

    @Test
    public void testIncreaseScoreMultipleTimesForMultiplePlayers() {
        scoreManager.increaseScore(PlayerId.ONE, 4);
        scoreManager.increaseScore(PlayerId.TWO, 2);
        scoreManager.increaseScore(PlayerId.ONE, 2);
        scoreManager.increaseScore(PlayerId.ONE, 1);
        scoreManager.increaseScore(PlayerId.TWO, 4);

        int p1Score = scoreManager.getScore(PlayerId.ONE);
        int p2Score = scoreManager.getScore(PlayerId.TWO);

        assertTrue(p1Score == 7);
        assertTrue(p2Score == 6);
    }
}
