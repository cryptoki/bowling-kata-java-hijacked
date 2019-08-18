package kata.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test
    void canScoreGutterGame() {
        rollWithTwentyTimes(0);
        assertEquals(0, bowlingGame.score());
    }

    @Test
    void canScoreAllRollsWithOne() {
        rollWithTwentyTimes(1);
        assertEquals(20, bowlingGame.score());
    }

    @Test
    void canScorePerfectGame() {
        rollWith(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        assertEquals(300, bowlingGame.score());
    }

    @Test
    void canScoreSpareWithWithFollowedZeros() {
        rollWith(5, 5,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(10, bowlingGame.score());
    }

    @Test
    void canScoreSpareFollowedByFive() {
        rollWith(5, 5,
                5, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(20, bowlingGame.score());
    }

    @Test
    void canScoreStrikeFollowedByThreeAndFive() {
        rollWith(10,
                3, 5,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(26, bowlingGame.score());
    }

    @Test
    void canScoreGameInExerciseSheet() {
        rollWith(1, 4,
                4, 5,
                6, 4,
                5, 5,
                10,
                0, 1,
                7, 3,
                6, 4,
                10,
                2, 8, 6);
        assertEquals(133, bowlingGame.score());
    }

    @Test
    void moreThanTwentyOneRollsAreNotAllowed() {
        rollWithTwentyTimes(5);
        rollWith(5);
        assertThrows(IllegalArgumentException.class, () -> rollWith(5));
    }

    @Test
    void moreThanTenFrameIsNotAllowed() {
        rollWith(10, 10, 10, 10, 10, 10, 10, 10, 10,
                0, 0);
        assertThrows(IllegalArgumentException.class, () -> rollWith(5));
    }

    @Test
    void inLastFrameOnlyTwoRollsAllowedWithoutSpareAndStrike() {
        assertThrows(IllegalArgumentException.class, () ->
                rollWith(10, 10, 10, 10, 10, 10, 10, 10, 10,
                        2, 7, 2));
    }

    @Test
    void aGameIsOverAfterTwentyThrees() {
        rollWithTwentyTimes(3);
        assertTrue(bowlingGame.isGameOver());
    }

    @Test
    void standingPinsAfterThreeRolls() {
        rollWith(10, 3, 7);
        assertEquals(10, bowlingGame.getStandingPins());
    }

    @Test
    void toStringShouldLookPretty() {
        rollWith(1, 4,
                4, 5,
                6, 4,
                5, 5,
                10,
                0, 1,
                7, 3,
                6, 4,
                10,
                2, 8, 6);
        assertEquals(133, bowlingGame.score());
        assertEquals(
                "[1,4] [4,5] [6,/] [5,/] [X  ] [0,1] [7,/] [6,/] [X  ] [2,/,6]" + System.lineSeparator() +
                        "    5    14    29    49    60    61    77    97   117   133 " + System.lineSeparator() +
                        "The game is over.", bowlingGame.toString());
    }

    @Test
    void toStringShouldLookPrettyForUnfinishedGame() {
        rollWith(1, 4,
                4, 5,
                6, 4,
                5, 5);
        assertEquals(39, bowlingGame.score());
        assertEquals(
                "[1,4] [4,5] [6,/] [5,/] " + System.lineSeparator() +
                        "    5    14    29    39 ", bowlingGame.toString());
    }


    private void rollWithTwentyTimes(int pinsDown) {
        for (int i = 0; i < 20; i++) {
            bowlingGame.roll(pinsDown);
        }
    }

    private void rollWith(int... rolls) {
        for (int pinsDown : rolls) {
            bowlingGame.roll(pinsDown);
        }
    }
}
