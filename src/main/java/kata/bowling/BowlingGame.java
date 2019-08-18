package kata.bowling;

import java.util.stream.IntStream;

public class BowlingGame {

    private int[] rolls = new int[21];
    private int index = 0;

    public void roll(int pinsDown) {
        rolls[index++] = pinsDown;
    }

    public int score() {
        return IntStream.of(rolls).sum();
    }
}
