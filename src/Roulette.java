/* Roulette.java
 * Safwan Ahmad, Alessandro Krapf, Luke Langius
 * 12/7/2020
 * This program plays roulette. The players have three options, two have 1 in 2 chance of winning, 1 has 1 in 36 chance, so will make you more money.
 * Returns int to main class where this is run. It returns: 0 (lose), 1 (win), 2 (super win).
 */
import java.util.Random;
import java.util.Scanner;
public class Roulette {
    public int Score() {
        Scanner input = new Scanner(System.in);
        Random generator = new Random();
        int choice=0, number=0, rouletteNum = generator.nextInt(36);  //holds the numbers and the yes and no
        System.out.print("1: Odd. 50% chance of winning, with 1x prize.\n" +
            "2: Even. 50% chance of winning, with 1x prize.\n" +
            "3: Number 1-36. 2.78% chance of winning, with 36x prize.\n"); //choices for betting. Explain prize and percentage of winning as well.
        while (choice < 1 || choice > 3){
            System.out.print("Place your bet on: "); //asks you to place your bet on something
            choice = input.nextInt();
            rouletteNum++;
        }
        if (choice == 2) {
            while(number < 1 || number > 36) {
                System.out.print("Place your bet on number (1-36): "); //for the number choice when you pick 2
                number = input.nextInt();
            }
        }
        System.out.println("Roulette number: " + rouletteNum); //the if else for when they pick the roulette number
        if (choice == 2) {
            if (rouletteNum == number)
                return 3;
            else
                return 0;
        } else {
            if (rouletteNum % 2 == choice)
                return 1;
            else
                return 0;
        }
    }
}
