package kata.bowling;

import java.util.Scanner;

public class BowlingRunner {
    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter pins [0 - 9] ");
            bowlingGame.roll(scanner.nextInt());
            System.out.println(bowlingGame.toString());
        }
        while (!bowlingGame.isGameOver());
    }
}
