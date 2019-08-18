package kata.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    Frame frame;

    @BeforeEach
    void setUp() {
        frame = new Frame();
    }

    @Test
    void isStrike() {
        frame.roll(10);
        assertTrue(frame.isStrike());
    }

    @Test
    void isNoStrike() {
        frame.roll(2);
        frame.roll(8);
        assertFalse(frame.isStrike());
    }

    @ParameterizedTest
    @CsvSource({
            "0,10",
            "1,9",
            "2,8",
            "3,7",
            "4,6",
            "5,5",
            "6,4",
            "7,3",
            "8,2",
            "9,1",
    })
    void isSpare(int firstRoll, int secondRoll) {
        frame.roll(firstRoll);
        frame.roll(secondRoll);
        assertTrue(frame.isSpare());
    }

    @Test
    void isNoSpare() {
        frame.roll(3);
        frame.roll(6);
        assertFalse(frame.isSpare());
    }

    @Test
    void aStrikeIsNoSpare() {
        frame.roll(10);
        assertFalse(frame.isSpare());
    }

    @Test
    void aFrameIsFinishedForStrike() {
        frame.roll(10);
        assertTrue(frame.isFinished());
    }

    @ParameterizedTest
    @CsvSource({
            "2, 1",
            "0, 0",
            "2, 3"
    })
    void aFrameIsFinished(int firstRoll, int secondRoll) {
        frame.roll(firstRoll);
        frame.roll(secondRoll);
        assertTrue(frame.isFinished());
    }

    @Test
    void aFrameIsNotFinished() {
        frame.roll(2);
        assertFalse(frame.isFinished());
    }

    @Test
    void valuesAboveMaxPinsNotAllowed() {
        assertThrows(IllegalArgumentException.class, () -> frame.roll(11));
    }
    @Test
    void valuesBelowZeroAreNotAllowed() {
        assertThrows(IllegalArgumentException.class, () -> frame.roll(-1));
    }
    @Test
    void nextRollForFinishedFrameIsNotAllowed() {
        frame.roll(10);
        assertThrows(IllegalArgumentException.class, () -> frame.roll(0));
    }


    @Test
    void toStringOutput() {
        frame.roll(2);
        frame.roll(4);
        assertEquals("2,4", frame.toString());
    }
    @Test
    void toStringOutputForSpare() {
        frame.roll(2);
        frame.roll(8);
        assertEquals("2,/", frame.toString());
    }
    @Test
    void toStringOutputForStrike() {
        frame.roll(10);
        assertEquals("X", frame.toString());
    }
}