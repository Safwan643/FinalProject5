package sample;
/* RockPaperScissors2.java
 * Luke Langius
 * 1/22/2020
 * This program will let you play rock paper scissors against a computer, with a try catch statement, and will outprint the wins and win percentage for the computer, human and percentage of ties.
 */
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.Random;
public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long timeSeed = new Date().getTime();
        Random random = new Random(timeSeed);
        String choices[] = {"Rock ", "Paper", "Scissors"};
        double humanWins = 0;
        double ties = 0;
        double computerWins = 0;
        double humanPercent = 0;
        double computerPercent = 0;
        double tiesPercent = 0;
        boolean loop = true;
        int gamesPlayed = 0;
        DecimalFormat df = new DecimalFormat("0.00");                               //Creating the decimal format to truncate the wins at two decimal places
        while (loop) {
            gamesPlayed++;
            int userChoice = 0;
            boolean failCheck = false;                                                // Starting the check for the try/catch.
            System.out.print("Which do you want to choose? \n"
                    + "1. Rock \n"
                    + "2. Paper \n"
                    + "3. Scissors \n"
                    + "Choice: ");
            do {
                try {
                    userChoice = input.nextInt();                                    // This checks to make sure that only a number is entered.
                    if (userChoice < 1 || userChoice > 3) {
                        throw new ArithmeticException("Please type in a number 1 - 3: ");
                    }
                    userChoice--;
                    failCheck = false;
                } catch (Exception e) {
                    System.out.print("Please type in a number 1 - 3: ");                 //The exception asks to print out a number from 1 - 3.
                    input.nextLine();
                    failCheck = true;
                }
            } while (failCheck);
            int compChoice = random.nextInt(3);
            System.out.print("The computer chose " + choices[compChoice] + "\n");
            if (userChoice == compChoice) {
                System.out.println("Good game we tied \n");
                ties++;
            } else if (userChoice + 1 == compChoice) {
                System.out.println("Good game the computer won \n");
                computerWins++;
            } else if (userChoice + 2 == compChoice) {
                System.out.println("Good game you won \n");
                humanWins++;
            } else if (userChoice - 1 == compChoice) {
                System.out.println("Good game you won \n");
                humanWins++;
            } else if (userChoice - 2 == compChoice) {
                System.out.println("Good game the computer won \n");
                computerWins++;
            }
            humanPercent = (humanWins / gamesPlayed) * 100;
            computerPercent = (computerWins / gamesPlayed) * 100;                                                         //Calculating the percentage of wins for computer, human, and ties.
            tiesPercent = (ties / gamesPlayed) * 100;
            System.out.print("You have " + humanWins + " win(s) and a win percentage of " + df.format(humanPercent) + " % \n"
                    + "The computer has won " + computerWins + " time(s) and a win percentage of " + df.format(computerPercent) + " % \n"
                    + "You have tied with the computer " + ties + " time(s) and a win percentage of " + df.format(tiesPercent) + " % \n");
            System.out.print("Would you like to go again? \n"
                    + "1. Yes \n"
                    + "2. No \n"
                    + "Choice: ");
            int userChoice2 = input.nextInt();
            if (userChoice2 == 2) {
                loop = false;
            }
        }
    }
}