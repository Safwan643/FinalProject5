package sample;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Coinflip cf = new Coinflip();
        RockPaperScissors rps = new RockPaperScissors();
        Scanner input = new Scanner(System.in);
        System.out.print("Hello user, pick your game: \n" +
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
                if (rps.Score() == 0) {
                    System.out.print("That's unfortunate but you lost");
                } else if (rps.Score() == 1) {
                    System.out.print("Good job you won");
                } else if (rps.Score() == 2) {
                    System.out.print("Well you did not win or lose");
                }
                break;
        }

    }
}
