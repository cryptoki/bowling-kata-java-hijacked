package kata.bowling;

public class BowlingGame {

    private int score = 0;

    public void roll(int pinsDown) {
        score += pinsDown;
    }

    public int score() {
        return score;
    }
}
