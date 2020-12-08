
/* Coinflip.java
 * Alessandro Krapf
 * 12/7/2020
 * This program plays the game coin flip using a random number generator. It sends information to a main class.
 */
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
public class Coinflip {
    public boolean coinflip() {
        Scanner input = new Scanner(System.in);
        long timeSeed = new Date().getTime();
        Random random = new Random(timeSeed);
        String[] choices = {"Heads", "Tails"};
        int callIt, compChoice = random.nextInt(2);
        System.out.print("Which do you want to choose? \n"
                + "1. Heads \n"
                + "2. Tails \n"
                + "Choice: ");
        callIt = input.nextInt();
        System.out.print("The flip landed as " + choices[compChoice] + ".\n");

        compChoice = compChoice + 1;
        return compChoice == callIt;
    }

}
