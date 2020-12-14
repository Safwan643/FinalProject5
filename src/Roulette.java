/* Roulette.java
 * Safwan Ahmad, Alessandro Krapf, Luke Langius
 * 12/14/2020
 * This program plays roulette. The players have three options, two have 1 in 2 chance of winning, 1 has 1 in 36 chance, so will make you more money.
 * Returns int to main class where this is run. It returns: 0 (lose), 1 (win), 2 (super win).
 */
import java.util.Random;
import java.util.Scanner;
public class Roulette {
    public int Score() {
        Scanner input = new Scanner(System.in);
        Random generator = new Random();
        String evenOdd = "odd";
        int choice=-1, number=0, rouletteNum = generator.nextInt(36);  //holds the numbers and the yes and no
        System.out.println("1: Odd. 50% chance of winning, with 1x prize.\n" +
            "2: Even. 50% chance of winning, with 1x prize.\n" +
            "3: Number 1-36. 2.78% chance of winning, with 36x prize."); //choices for betting. Explain prize and percentage of winning as well.
        while (choice < 0 || choice > 2){
            System.out.print("Place your bet on: "); //asks you to place your bet on something
            choice = input.nextInt();
            rouletteNum++;
            choice--;
        }
        if (choice == 2) {
            while(number < 1 || number > 36) {
                System.out.print("Place your bet on number (1-36): "); //for the number choice when you pick 2
                number = input.nextInt();
            }
        }
        if(rouletteNum % 2 == 0) evenOdd = "even";
        System.out.println("Roulette number: " + rouletteNum + " (" + evenOdd + ")"); //prints what the number is.
        if (choice == 2) {
            if (rouletteNum == number)
                return 2;
            else
                return 0;
        } else {
            if (rouletteNum % 2 != choice)
                return 1;
            else
                return 0;
        }
    }
}