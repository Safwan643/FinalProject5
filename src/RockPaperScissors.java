/* RockPaperScissors.java
 * Luke Langius, Alessandro Krapf, Safwan Ahmad
 * 12/14/2020
 * This program will let you play rock paper scissors against the computer. It tracks who won each round by returning an integer.
 * If, int = 0 computer won, int = 1 user won, int = 2 they tied. It returns the outcome to a main class.
 */
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
public class RockPaperScissors {
    public int Score() {
        Scanner input = new Scanner(System.in);
        long timeSeed = new Date().getTime();
        Random random = new Random(timeSeed);
        String[] choices = {"Rock", "Paper", "Scissors"};
        System.out.print("Which do you want to choose? \n"
                + "1. Rock \n"
                + "2. Paper \n"
                + "3. Scissors \n"
                + "Choice: ");
        int userChoice = input.nextInt();
        userChoice--;         //comp uses 0,1. To make more comfy, this allows user to input 1,2.
        int compChoice = random.nextInt(3);        //creates random integer.
        System.out.print("The computer chose " + choices[compChoice] + ".\n");  //uses random integer to call it.
        if (userChoice == compChoice) {
            return 2;        //Runs through scenarios for wins, and losses. Int assumes automatically it will be draw.
        } else if (userChoice + 2 == compChoice || userChoice - 1 == compChoice) {
           return 1;
        } else return 0;
    }
}