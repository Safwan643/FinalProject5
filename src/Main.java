/* Main.java
 * Alessandro Krapf, Luke Langius, Safwan Ahmad
 * 12/7/2020
 * This program plays three luck games. This is the main class, mostly input/output. Calls 3 classes that run the actual games.
 */
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    double total=0;
    String userName;
    boolean exists = false;
    public void AskName() {
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        userName = input.nextLine();
    }
    public void CreateFile() {
        try {
            File myObj = new File("The Casino.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void ReadFile() {
        AskName();
        System.out.println("This is what is currently in the file: ");
        String dataName;
        double data;
        try {
            File myObj = new File("The Casino.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                //data = myReader.nextLine();
                dataName = myReader.next();
                data = myReader.nextDouble();
                System.out.println(dataName + " $" + data);
                if(dataName.equals(userName)) {
                    total=data;
                    exists = true;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Main main = new Main();
        main.CreateFile();
        main.ReadFile();
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        Coinflip cf = new Coinflip();
        RockPaperScissors rps = new RockPaperScissors();
        Roulette rtt = new Roulette();
        main.total = main.total + 500;
        System.out.print("Hello " + main.userName + ". ");
        while (loop) {
            System.out.print("Please pick your game.\n" +
                    "1. Coin Flip. You will win 1x what you bet, and have a 50% chance of winning.\n" +
                    "2. Rock Paper Scissors. You will win 1.5x what you bet, and have a 33.33% chance of winning.\n" +
                    "3. Roulette. You have the choice of a 50% win, winning 1x what you bet, or a 2.78%(1 in 36) win, winning 36x what you bet.\n" +
                    "Choice: ");
            int userChoice = input.nextInt();
            double win = 1, GambleAmount, WinAmount = 0, LoseAmount = 0;
            do {
                System.out.print("Ok, your total is $" + main.total + ". How much of that would you like to bet? ");
                GambleAmount = input.nextDouble();
            } while (GambleAmount > main.total || GambleAmount <= 0);
            if (userChoice == 2) win = 1.5;
            String Win = "You won! Congratulations, you won $" + GambleAmount * win + ".",
                    Lose = "You lost. I regret to inform you that you lost $" + GambleAmount + ".",
                    Draw = "You have neither won nor lost. You have been refunded $" + GambleAmount + ".",
                    SuperWin = "You won! Congratulations, you won $" + GambleAmount * 36 + ".";
            System.out.println("The first round is a practice round. You're welcome.");
            switch (userChoice) {
                case 1:
                    cf.Score();
                    if (cf.Score()) {
                        System.out.println(Win);
                        WinAmount = GambleAmount * win;
                    } else {
                        System.out.println(Lose);
                        LoseAmount = GambleAmount;
                    }
                    break;
                case 2:
                    rps.Score();
                    switch (rps.Score()) {
                        case 0:
                            System.out.println(Lose);
                            LoseAmount = GambleAmount;
                            break;
                        case 1:
                            System.out.println(Win);
                            WinAmount = GambleAmount * win;
                            break;
                        case 2:
                            System.out.println(Draw);
                            break;
                    }
                    break;
                case 3:
                    rtt.Score();
                    switch (rtt.Score()) {
                        case 0:
                            System.out.println(Lose);
                            LoseAmount = GambleAmount;
                            break;
                        case 1:
                            System.out.println(Win);
                            WinAmount = GambleAmount * win;
                            break;
                        case 2:
                            WinAmount = GambleAmount * 36;
                            System.out.println(SuperWin);
                            break;
                    }
                    break;
            }
            main.total = (main.total + WinAmount) - LoseAmount;
            if (main.total > 1000000000) {
                System.out.println("CONGRATULATIONS, " + main.userName + "!!!!!!!!!! WE HAVE A WINNER!! YOU HAVE MADE MORE THAN 1,000,000,000 (1 BILLION) DOLLARS!!!! $" + main.total + ", to be EXACT.!!" + "\uD83C\uDFC6\uD83C\uDFC6\uD83C\uDFC6 \n" +
                        "You have spent everything but $1,000, and bought yourself a mansion on the moon. You should buy yourself another though...");
                main.total = 1000;
            }
            if (main.total == 0) {
                System.out.println("I am sorry to say that you, " + main.userName + ", have a problem. You have lost all of your money. You lose, now please leave.");
                break;
            }
            System.out.print("Do you want to play again? Your current total is " + main.total + "\n" +
                    "1. Yes.\n" +
                    "2. No.\n" +
                    "Choice: ");
            int userLoop = input.nextInt();
            if (userLoop == 2) {
                loop = false;
            }
        }
        main.WriteToFileTotal();
    }

    public void WriteToFileTotal() {
        if (exists) {
            try (PrintWriter outputBackUp = new PrintWriter("Backup.txt")
            ) {
                File myObj1 = new File("The Casino.txt");
                Scanner input = new Scanner(myObj1);
                String dataName;
                double data;
                while (input.hasNext()) {
                    dataName = input.next();
                    data = input.nextDouble();
                    if (dataName.equals(userName))
                        outputBackUp.println(userName + " " + total);
                    else
                        outputBackUp.println(dataName + " " + data);
                    }
                input.close();
            } catch (IOException ignore) {
            }
            try( PrintWriter out = new PrintWriter("The Casino.txt")
            ){
                File myObj = new File("Backup.txt");
                java.io.File file = new java.io.File("Backup.txt");
                Scanner input = new Scanner(myObj);
                String backingUp;
                while(input.hasNextLine()) {
                    backingUp = input.nextLine();
                    out.println(backingUp);
                }
                input.close();
                file.delete();
            } catch (IOException ignored) {
            }
        } else {
            try (FileWriter fw = new FileWriter("The Casino.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw))
            {
            out.println(userName + " " + total);
        } catch(IOException ignored){
        }
    }
    }
}