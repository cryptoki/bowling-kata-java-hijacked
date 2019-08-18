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
        rollWith(0, 20);
        assertEquals(0, bowlingGame.score());
    }

    @Test
    void canScoreAllRollsWithOne() {
        rollWith(1, 20);
        assertEquals(20, bowlingGame.score());
    }

    @Test
    void canScoreSpareWithWithFollowedZeros() {
        rollWith(5, 2);
        rollWith(0, 18);
        assertEquals(10, bowlingGame.score());
    }

    @Test
    void canScoreSpareFollowedByFive() {
        rollWith(5, 3);
        rollWith(0, 15);
        assertEquals(20, bowlingGame.score());
    }

    private void rollWith(int pinsDown, int times) {
        for (int i = 0; i < times; i++) {
            bowlingGame.roll(pinsDown);
        }
    }
}
