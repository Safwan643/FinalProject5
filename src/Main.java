/* Main.java
 * Alessandro Krapf, Luke Langius, Safwan Ahmad
 * 12/7/2020
 * This program plays three luck games. This is the main class, mostly input/output. Calls 3 classes that run actual games.
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Coinflip cf = new Coinflip();
        RockPaperScissors rps = new RockPaperScissors();
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = input.nextLine();
        System.out.print("Hello " + name + ". ");
        do {
            System.out.print("Please pick your game.\n" +
                    "CF: Coin Flip. You will win 1x what you bet, and have a 50% chance of winning.\n" +
                    "RPS: Rock Paper Scissors. You will win 1.5x what you bet, and have a 33.33% chance of winning.\n" +
                    "Choice: ");
            String userChoice = input.nextLine();
            System.out.print("Ok, how much would you like to bet? Enter in format AB.CD: ");
            double GambleAmount = input.nextDouble();
            String GambleAmountString = Double.toString(GambleAmount),
                    Win = "You won! Congratulations, you won $" + GambleAmountString + ".", Lose = "You lost. I regret to inform you that you lost $" + GambleAmountString + ".",
                    Draw = "You have neither won nor lost. You have been refunded $" + GambleAmountString + ".";
            while(true) {
                if (userChoice.equals("CF")) {
                    System.out.println(userChoice);
                    cf.Score();
                    break;
                } else if (userChoice.equals("RPS")) {
                    System.out.println(userChoice);
                    rps.Score();
                    break;
                } else { System.out.print("Please input either CF or RPS:"); }
            }
            if (rps.Score() == 1 || cf.Score()) {
                System.out.println(Win);
            } else if (rps.Score() == 0 || !cf.Score()) {
                System.out.println(Lose);
            } else if (rps.Score() == 2) {
                System.out.println(Draw);
            }
            System.out.print("Do you want to play again? You could make more money...\n" +
                    "Y: Yes.\n" +
                    "N: No.\n" +
                    "Choice: ");
            String loops = input.nextLine();
            if (loops.equals("N")) { break; }
        }while(true);
    }
}