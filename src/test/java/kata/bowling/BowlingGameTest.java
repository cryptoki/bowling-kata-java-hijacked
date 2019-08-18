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
        for (int i = 0; i < 20; i++) {
            bowlingGame.roll(0);
        }
        assertEquals(0, bowlingGame.score());
    }

    @Test
    void canScoreAllRollsWithOne() {
        for (int i = 0; i < 20; i++) {
            bowlingGame.roll(1);
        }
        assertEquals(20, bowlingGame.score());
    }

    @Test
    void canScoreSpareWithWithFollowedZeros() {
        bowlingGame.roll(5);
        bowlingGame.roll(5);
        for (int i = 0; i < 18; i++) {
            bowlingGame.roll(0);
        }
        assertEquals(10, bowlingGame.score());
    }
}
