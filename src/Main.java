/* Main.java
 * Alessandro Krapf, Luke Langius, Safwan Ahmad
 * 12/7/2020
 * This program plays three luck games. This is the main class, mostly input/output. Calls 3 classes that run actual games.
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        boolean loop = true;
        Coinflip cf = new Coinflip();
        RockPaperScissors rps = new RockPaperScissors();
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = input.nextLine();
        System.out.print("Hello " + name + ". ");
        while (loop) {
            System.out.print("Please pick your game.\n" +
                    "1. Coin Flip. You will win 1x what you bet, and have a 50% chance of winning.\n" +
                    "2. Rock Paper Scissors. You will win 1.5x what you bet, and have a 33.33% chance of winning.\n" +
                    "Choice: ");
            int userChoice = input.nextInt();
            System.out.print("Ok, how much would you like to bet? Enter in format AB.CD: ");
            double win = 1, GambleAmount = input.nextDouble();
            if (userChoice == 2) win = 1.5;
            String Win = "You won! Congratulations, you won $" + GambleAmount * win + ".",
                    Lose = "You lost. I regret to inform you that you lost $" + GambleAmount + ".",
                    Draw = "You have neither won nor lost. You have been refunded $" + GambleAmount + ".";
            System.out.println("The first round is a practice round, you are welcome.");
            switch (userChoice) {
                   case 1:
                       cf.Score();
                       if (cf.Score()) { System.out.println(Win); } else System.out.println(Lose);
                       break;
                   case 2:
                       rps.Score();
                       switch (rps.Score()) {
                           case 0:
                               System.out.println(Lose);
                               break;
                           case 1:
                               System.out.println(Win);
                               break;
                           case 2:
                               System.out.println(Draw);
                               break;
                       }
                       break;
               }
            System.out.print("Do you want to play again? You could make more money...\n" +
                    "1. Yes.\n" +
                    "2. No.\n" +
                    "Choice: ");
            int userLoop = input.nextInt();
            if (userLoop == 2) { loop = false; }
        }
    }
}