package sample;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Coinflip cf = new Coinflip();
        RockPaperScissors rps = new RockPaperScissors();
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = input.nextLine();
        System.out.print("Hello, " + name + ". How much would you like to bet? Enter as 10.00: ");
        double GambleAmount = input.nextDouble();
        String GambleAmountString = Double.toString(GambleAmount),
                Win = "You won! Good betting, you won $" + GambleAmountString + "!", Lose = "You lost. I regret to inform you that you lost $" + GambleAmountString + ".",
                Draw ="You have neither won nor lost. You have been refunded $" + GambleAmountString + ".";
        System.out.print("Ok, now pick your game: \n" +
                "1. Coin Flip (type CF): \n" +
                "2. Rock Paper Scissors (type RPS): \n" +
                "Choice: ");
        String userChoice = input.nextLine();
        switch (userChoice) {
            case "CF":
                cf.coinflip();
                break;
            case "RPS":
                rps.Score();
                break;
        }
        if (rps.Score() == 1 || cf.coinflip()) {
            System.out.println(Win);
        } else if (rps.Score() == 0 || !cf.coinflip()) {
            System.out.println(Lose);
        } else if (rps.Score() == 2) {
            System.out.println(Draw);
        }
    }
}
