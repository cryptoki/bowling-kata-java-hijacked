package kata.bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frame {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private List<Integer> rolls = new ArrayList<>();

    public void roll(int pinsDown) {
        if (pinsDown < MIN_PINS
                || pinsDown > MAX_PINS) {
            throw new IllegalArgumentException("pins must between [0-10]");
        }
        rolls.add(pinsDown);
    }

    public List<Integer> getRolls() {
        return Collections.unmodifiableList(rolls);
    }

    public boolean isFinished() {
        return rolls.size() == 2
                || isStrike();
    }

    public boolean isStrike() {
        return rolls.size() == 1
                && rolls.get(0) == MAX_PINS;
    }

    public boolean isSpare() {
        return rolls.size() == 2
                && (rolls.get(0) + rolls.get(1) == MAX_PINS);
    }
}
