
/* RockPaperScissors.java
 * Luke Langius
 * 12/7/2020
 * This program will let you play rock paper scissors against the computer. It tracks who won each round by returning an integer. If, int = 0 computer won, int = 1 user won, int = 2 they tied.
 */
import java.util.Date;
import java.util.Random;
import java.util.Scanner;


public class RockPaperScissors {
    public int Score() {
        int score = 2;
            Scanner input = new Scanner(System.in);
            long timeSeed = new Date().getTime();
            Random random = new Random(timeSeed);
            String[] choices = {"Rock ", "Paper", "Scissors"};

            System.out.print("\nWhich do you want to choose? \n"
                    + "1. Rock \n"
                    + "2. Paper \n"
                    + "3. Scissors \n"
                    + "Choice: ");
            int userChoice = input.nextInt();
            userChoice--;

            int compChoice = random.nextInt(3);

            System.out.print("The computer chose " + choices[compChoice] + "\n");
            if (userChoice + 1 == compChoice) {
                score = 0;
            } else if (userChoice + 2 == compChoice) {
                score = 1;
            } else if (userChoice - 1 == compChoice) {
                score = 1;
            } else if (userChoice - 2 == compChoice) {
                score = 0;
            }
        return score;
    }
}


