package kata.bowling;

import java.util.LinkedList;

public class BowlingGame {

    private static final int MAX_PINS = 10;

    private static final int FRAMES_PER_GAME = 10;
    private static final int TRIES_PER_FRAME = 2;

    private LinkedList<Frame> frames = new LinkedList<>();

    public void roll(int pinsDown) {
        getFrame().roll(pinsDown);
    }

    public int score() {
        int[] rolls = frames
                .stream()
                .flatMapToInt(frame -> frame.getRolls().stream().mapToInt(Integer::intValue))
                .toArray();
        int score = 0;
        int cursorInRolls = 0;

        for (int frame = 1; frame <= FRAMES_PER_GAME; frame++) {

            if (isStrikeFrame(rolls, cursorInRolls)) {
                score += MAX_PINS + rolls[cursorInRolls + 1] + rolls[cursorInRolls + 2];
                cursorInRolls += 1;
            } else if (isSpareFrame(rolls, cursorInRolls)) {
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


    private Frame getFrame() {
        if (frames == null
                || frames.isEmpty()
                || frames.getLast().isFinished()) {
            frames.add(new Frame());
        }
        return frames.getLast();
    }

    private boolean isStrikeFrame(int[] rolls, int cursorInRolls) {
        return rolls[cursorInRolls] == MAX_PINS;
    }

    private boolean isSpareFrame(int[] rolls, int cursorInRolls) {
        return rolls[cursorInRolls] + rolls[cursorInRolls + 1] == MAX_PINS;
    }
}
