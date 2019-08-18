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
}
