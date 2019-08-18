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
        return scoreForFrame(frames.size());
    }

    public boolean isGameOver() {
        return getLastFrameInGame() != null
                && getLastFrameInGame().isFinished()
                && numberOfAvailableBonusRolls() == 0;
    }


    private int scoreForFrame(int frameNumber) {
        int[] rolls = getRollsAsArray();
        int score = 0;
        int cursorInRolls = 0;

        for (int frameIndex = 0; frameIndex < Math.min(frameNumber, FRAMES_PER_GAME); frameIndex++) {
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

    private int numberOfAvailableBonusRolls() {
        Frame lastFrameInGame = getLastFrameInGame();
        if (lastFrameInGame != null
                && lastFrameInGame.isFinished()) {
            int numberOfBonusRolls = lastFrameInGame.isStrike() ? 2 : lastFrameInGame.isSpare() ? 1 : 0;
            int bonusRollsAlreadyDone = frames.subList(FRAMES_PER_GAME, frames.size())
                    .stream()
                    .mapToInt(frame -> frame.getRolls().size()).sum();
            return numberOfBonusRolls - bonusRollsAlreadyDone;
        } else {
            return 0;
        }
    }

    private Frame getLastFrameInGame() {
        return frames.size() >= FRAMES_PER_GAME ? frames.get(FRAMES_PER_GAME - 1) : null;
    }

    private void assertThatGameIsNotOver() {
        if (isGameOver())
            throw new IllegalArgumentException("game is over.");
    }


    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(frames.subList(0, Math.min(FRAMES_PER_GAME - 1, frames.size()))
                .stream()
                .map(Frame::toString)
                .reduce("", (acc, nextFrameOutput) -> acc + String.format("[%-3s] ", nextFrameOutput)));

        if (frames.size() >= FRAMES_PER_GAME) {
            output.append("[")
                    .append(frames.subList(FRAMES_PER_GAME - 1, frames.size())
                            .stream()
                            .map(Frame::toString)
                            .reduce("", (acc, frameOutput) -> acc + frameOutput + ","))
                    .replace(output.length() - 1, output.length(), "]");
        }

        output.append(System.lineSeparator());
        for (int frameIndex = 1; frameIndex <= Math.min(frames.size(), FRAMES_PER_GAME); frameIndex++) {
            output.append(String.format("%5s ", scoreForFrame(frameIndex)));
        }

        if (isGameOver()) {
            output.append(System.lineSeparator());
            output.append("The game is over.");
        }

        return output.toString();
    }
}
