package kata.bowling;

public class BowlingGame {

    private static final int MAX_PINS = 10;
    public static final int FRAMES_PER_GAME = 10;
    private int[] rolls = new int[21];
    private int index = 0;

    public void roll(int pinsDown) {
        rolls[index++] = pinsDown;
        score();
    }

    public int score() {
        int score = 0;
        int cursorInRolls = 0;
        for (int frame = 1; frame <= FRAMES_PER_GAME; frame++) {

            if (isStrikeFrame(cursorInRolls)) {
                score += MAX_PINS + rolls[cursorInRolls + 1] + rolls[cursorInRolls + 2];
                cursorInRolls += 1;
            } else if (isSpareFrame(cursorInRolls)) {
                score += MAX_PINS + rolls[cursorInRolls + 2];
                cursorInRolls += 2;
            } else if (rolls[cursorInRolls] + rolls[cursorInRolls + 1] > MAX_PINS) {
                throw new IllegalArgumentException();
            } else {
                score += rolls[cursorInRolls] + rolls[cursorInRolls + 1];
                cursorInRolls += 2;
            }
        }
        return score;
    }

    private boolean isStrikeFrame(int cursorInRolls) {
        return rolls[cursorInRolls] == MAX_PINS;
    }

    private boolean isSpareFrame(int cursorInRolls) {
        return rolls[cursorInRolls] + rolls[cursorInRolls + 1] == MAX_PINS;
    }
}
