package kata.bowling;

import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    @Test
    void canCreateBowlingGame() {
        new BowlingGame();
    }

    @Test
    void canRollBall() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.roll(0);
    }
}
