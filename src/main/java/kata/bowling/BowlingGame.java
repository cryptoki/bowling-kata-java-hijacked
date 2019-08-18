package kata.bowling;

public class BowlingGame {

    private int[] rolls = new int[21];
    private int index = 0;

    public void roll(int pinsDown) {
        rolls[index++] = pinsDown;
    }

    public int score() {
        int score = 0;
        int cursorInRolls = 0;
        for (int frame = 1; frame <= 10; frame++) {
            if (rolls[cursorInRolls] + rolls[cursorInRolls + 1] == 10) {
                score += 10 + rolls[cursorInRolls + 2];
            } else {
                score += rolls[cursorInRolls] + rolls[cursorInRolls + 1];
            }
            cursorInRolls += 2;
        }
        return score;
    }
}
