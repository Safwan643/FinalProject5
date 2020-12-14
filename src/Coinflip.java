/* Coinflip.java
 * Alessandro Krapf, Luke Langius, Safwan Ahmad
 * 12/14/2020
 * This program plays the game coin flip using a random number generator. It sends information to a main class.
 */
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
public class Coinflip {
    public boolean Score() {
        Scanner input = new Scanner(System.in);
        long timeSeed = new Date().getTime();
        Random random = new Random(timeSeed);
        String[] choices = {"Heads", "Tails"};
        int userChoice, compChoice = random.nextInt(2);         //creates random integer.
        System.out.print("Which do you want to choose? \n"
                + "1. Heads \n"
                + "2. Tails \n"
                + "Choice: ");
        userChoice = input.nextInt();
        System.out.print("The flip landed as " + choices[compChoice] + ".\n");          //uses random integer to call it.
        compChoice++;                      //comp uses 0,1. To make more comfy, this allows user to input 1,2.
        return compChoice == userChoice;                 //returns boolean. If user input = computers random input, true. else false.
    }                                               //this allows true/false to = win/lose. there is no draw in flip.

}