package kata.bowling;

import java.util.LinkedList;

public class BowlingGame {

    private static final int MAX_PINS = 10;
    private static final int FRAMES_PER_GAME = 10;
    private static final int MAX_ROLLS_FOR_LAST_FRAME = 3;

    private LinkedList<Frame> frames = new LinkedList<>();
    private int rollCount = 0;
    private int rollCountForLastFrame = 0;

    public void roll(int pinsDown) {
        Frame frame = getFrame();

        assertThatGameIsNotOver();
        assertThatTheLastRollIsNotReached();

        frame.roll(pinsDown);
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

    private void assertThatGameIsNotOver() {
        if (++rollCount > 21) {
            throw new IllegalArgumentException("the game is over.");
        }
    }

    private void assertThatTheLastRollIsNotReached() {
        if (frames.size() > FRAMES_PER_GAME) {
            int maxBonusRolls = frames.get(FRAMES_PER_GAME - 1).isStrike() ? 2 : frames.get(FRAMES_PER_GAME - 1).isSpare() ? 1 : 0;
            int finishedRolls = frames
                    .subList(FRAMES_PER_GAME + 1, frames.size())
                    .stream()
                    .mapToInt(frame -> frame.getRolls().size()).sum();

            if (finishedRolls >= maxBonusRolls) {
                throw new IllegalArgumentException("the game is over.");
            }
        }
    }
}
