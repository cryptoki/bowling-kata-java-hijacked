package kata.bowling;

import java.util.Scanner;
import static java.lang.System.out;

public class BowlingRunner {
    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame();
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                out.print("Enter pins [0 -  " + bowlingGame.getStandingPins() + "] ");
                bowlingGame.roll(scanner.nextInt());
                out.println(bowlingGame.toString());
            }
            catch (IllegalArgumentException exc) {
                out.println("please correct your input");
            }
        }
        while (!bowlingGame.isGameOver());
    }
}
