package kata.bowling;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class BowlingGame {

    private static final int FRAMES_PER_GAME = 10;
    private LinkedList<Frame> frames = new LinkedList<>();


    public void roll(int pinsDown) {
        Frame frame = getFrame();

        assertThatGameIsNotOver();

        frame.roll(pinsDown);
    }

    public int score() {
        int[] rolls = getRollsAsArray();
        int score = 0;
        int cursorInRolls = 0;

        for (int frameIndex = 0; frameIndex < FRAMES_PER_GAME; frameIndex++) {
            Frame frame = frames.get(frameIndex);
            score += frame.getPinsDown();

            cursorInRolls += frame.getRolls().size();
            if (frame.isStrike())
                score += rolls[cursorInRolls] + rolls[cursorInRolls + 1];
            else if (frame.isSpare())
                score += rolls[cursorInRolls];
        }

        return score;
    }

    public boolean isGameOver() {
        boolean gameIsOver = false;
        if (frames.size() > FRAMES_PER_GAME) {
            int maxBonusRolls = frames.get(FRAMES_PER_GAME - 1).isStrike() ? 2 : frames.get(FRAMES_PER_GAME - 1).isSpare() ? 1 : 0;
            int finishedRolls = frames
                    .subList(FRAMES_PER_GAME, frames.size())
                    .stream()
                    .mapToInt(frame -> frame.getRolls().size()).sum();

            if (finishedRolls >= maxBonusRolls) {
                gameIsOver = true;
            }
        }
        return gameIsOver;
    }


    private int[] getRollsAsArray() {
        return IntStream.concat(
                frames.stream().flatMapToInt(frame -> frame.getRolls().stream().mapToInt(Integer::intValue)),
                IntStream.of(0, 0))
                .toArray();
    }

    private Frame getFrame() {
        if (frames == null
                || frames.isEmpty()
                || frames.getLast().isFinished()) {
            frames.add(new Frame());
        }
        return frames.getLast();
    }

    private void assertThatGameIsNotOver() {
        if (isGameOver())
            throw new IllegalArgumentException("game is over.");
    }
}
