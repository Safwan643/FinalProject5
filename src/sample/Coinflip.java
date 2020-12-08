package sample;
/* Coinflip.java
 * Alessandro Krapf
 * 4 December 2020
 * Java 161
 * This program plays the luck game coin flip, and keeps track of the score (using a random number generator).
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
