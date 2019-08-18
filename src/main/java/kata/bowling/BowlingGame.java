package kata.bowling;

public class BowlingGame {

    private static final int MAX_PINS = 10;
    private int[] rolls = new int[21];
    private int index = 0;

    public void roll(int pinsDown) {
        rolls[index++] = pinsDown;
    }

    public int score() {
        int score = 0;
        int cursorInRolls = 0;
        for (int frame = 1; frame <= 10; frame++) {

            if (rolls[cursorInRolls] == MAX_PINS) {
                score += 10 + rolls[cursorInRolls + 1] + rolls[cursorInRolls + 2];
                cursorInRolls += 1;
            }
            else if (isSpareFrame(cursorInRolls)) {
                score += 10 + rolls[cursorInRolls + 2];
                cursorInRolls += 2;
            } else {
                score += rolls[cursorInRolls] + rolls[cursorInRolls + 1];
                cursorInRolls += 2;
            }
        }
        return score;
    }

    private boolean isSpareFrame(int cursorInRolls) {
        return rolls[cursorInRolls] + rolls[cursorInRolls + 1] == MAX_PINS;
    }
}
