package kata.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test
    void canRollBall() {
        bowlingGame.roll(0);
    }

    @Test
    void canScoreGame() {
        assertEquals(0, bowlingGame.score());
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
    void canScoreSpareWithWithFollowedZeros() {
        rollWith(5,5,  0,0,  0,0,  0,0,  0,0,  0,0,  0,0,  0,0,  0,0,  0,0);
        assertEquals(10, bowlingGame.score());
    }

    @Test
    void canScoreSpareFollowedByFive() {
        rollWith(5,5,  5,0,  0,0,  0,0,  0,0,  0,0,  0,0,  0,0,  0,0,  0,0);
        assertEquals(20, bowlingGame.score());
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
