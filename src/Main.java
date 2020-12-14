/* Main.java
 * Alessandro Krapf, Luke Langius, Safwan Ahmad
 * 12/7/2020
 * This program plays three luck games. This is the main class, mostly input/output. Calls 3 classes that run the actual games.
 * It uses file io to remember players, and they're money amount.
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
    public void AskName() {           //asks name, takes input. this has to be separate from main so that it can be used in ReadFile.
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        userName = input.next();
    }
    public void CreateFile() {         //creates file if none made prev.
        try {
            File myObj = new File("The Casino.txt");
            if (myObj.createNewFile()) {           //if the file already exists, don't make. if it doesn't exist, create file.
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
        //reads the file to the program. name asked in this program, this program run in main. will print file info for user to see.
        AskName();     //runs ask name program
        System.out.println("This is what is currently in the file: ");
        String dataName;
        double data;
        try {
            File myObj = new File("The Casino.txt");
            Scanner myReader = new Scanner(myObj);         //scans the file.
            while (myReader.hasNext()) {   //runs for each name string. uses next() so that spaces separate.
                dataName = myReader.next();
                data = myReader.nextDouble();
                System.out.println(dataName + " $" + data);     //prints user name then total.
                if(dataName.equals(userName)) {     //if the user has already played. makes their total what it used to be.
                    total=data;
                    exists = true; //used later. exists is if the user already exists.
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Main main = new Main();    //programs can't run using .this because they aren't static. calling it's own class allows them to be used.
        main.CreateFile();    //runs create file.
        main.ReadFile();      //runs read file.
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        Coinflip cf = new Coinflip();   //each game class.
        RockPaperScissors rps = new RockPaperScissors();
        Roulette rtt = new Roulette();
        main.total = main.total + 500;      //each time you play you get 500. that way if u lose u can play again.
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
            } while (GambleAmount > main.total || GambleAmount <= 0);          //asks every time someone puts in amount they don't have or can't bet.
            if (userChoice == 2) win = 1.5;
            String Win = "You won! Congratulations, you won $" + GambleAmount * win + ".",
                    Lose = "You lost. I regret to inform you that you lost $" + GambleAmount + ".",
                    Draw = "You have neither won nor lost. You have been refunded $" + GambleAmount + ".",
                    SuperWin = "You won! Congratulations, you won $" + GambleAmount * 36 + ".";
            System.out.println("The first round is a practice round. You're welcome.");
            switch (userChoice) {    //switch case for each game. in each case there is another case or if statement for if they win.
                case 1:
                    cf.Score();     //plays coinflip.
                    if (cf.Score()) {
                        System.out.println(Win);
                        WinAmount = GambleAmount * win;       //specifies win amount, which is 0 if not specified.
                    } else {
                        System.out.println(Lose);
                        LoseAmount = GambleAmount;    //specify lose amount similar to win amount.
                    }
                    break;
                case 2:
                    rps.Score();                  //rock paper scissors.
                    switch (rps.Score()) {
                        case 0:     //you can tie rps, so 0 is lose, 1 is win, 2 is tie.
                            System.out.println(Lose);
                            LoseAmount = GambleAmount;
                            break;
                        case 1:
                            System.out.println(Win);
                            WinAmount = GambleAmount * win;
                            break;
                        case 2:
                            System.out.println(Draw);  //draw refunds money, neither lose or win amount specified.
                            break;
                    }
                    break;
                case 3:
                    rtt.Score();           //roulette.
                    switch (rtt.Score()) {
                        case 0:    //can't tie roulette, but can win diff. amounts.
                            System.out.println(Lose);
                            LoseAmount = GambleAmount;
                            break;
                        case 1:
                            System.out.println(Win);
                            WinAmount = GambleAmount * win;
                            break;
                        case 2:
                            WinAmount = GambleAmount * 36;         //if you win this, you get 36 times what u bet. SUPER WIN!!!!
                            System.out.println(SuperWin);
                            break;
                    }
                    break;
            }
            main.total = (main.total + WinAmount) - LoseAmount;     //adds win amount, subs lose amount. if they arent specified they r 0, so it works.
            if (main.total > 1000000000) {      //whoever has over $1 bill wins. money then resets to 1000
                System.out.println("CONGRATULATIONS, " + main.userName + "!!!!!!!!!! WE HAVE A WINNER!! YOU HAVE MADE MORE THAN 1,000,000,000 (1 BILLION) DOLLARS!!!! $" + main.total + ", to be EXACT.!!" + "\uD83C\uDFC6\uD83C\uDFC6\uD83C\uDFC6 \n" +
                        "You have spent everything but $1,000, and bought yourself a mansion on the moon. You should buy yourself another though...");
                main.total = 1000;
            }
            if (main.total == 0) {     //if you lose all money, u lose. the program stops automatically. if you play again u get $500.
                System.out.println("I am sorry to say that you, " + main.userName + ", have a problem. You have lost all of your money. You lose, now please leave.");
                break;
            }
            System.out.print("Do you want to play again? Your current total is " + main.total + "\n" +
                    "1. Yes.\n" +
                    "2. No.\n" +
                    "Choice: ");
            int userLoop = input.nextInt();    //can play as much as u want as long as u don't lose.
            if (userLoop == 2) {
                loop = false;
            }
        }
        main.WriteToFileTotal();       //runs write to file, look below.
    }

    public void WriteToFileTotal() {         //this program runs at end of main. writes new info to file.
        if (!exists) {      //if the user has not played before.
            try (FileWriter fw = new FileWriter("The Casino.txt", true);      //fw appends. but using fw is not user friendly, pw is much better.
                 BufferedWriter bw = new BufferedWriter(fw);        //fw goes through bw, then to pw. this is easiest to have nice format.
                 PrintWriter out = new PrintWriter(bw))
            {
                out.println(userName + " " + total);             //prints the users name and the total into a new line in the file. uses space to differentiate when reading file.
            } catch(IOException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {             //else the user has played before. must now input new balance into old player spot.
            try (PrintWriter outputBackUp = new PrintWriter("Backup.txt")   //pw doesn't append.
                 /*
                 the program makes a backup file. what was in the original file is copied over.
                 but if the username is the same as a previous name, it inputs the new total instead of making a new line.
                 then the backup file is copied over to the original file. append is never used, so that it replaces information since everything is copied over.
                 then the backup file is deleted.
                  */
            ) {
                File myObj1 = new File("The Casino.txt");
                Scanner input = new Scanner(myObj1);      //scans the original file, copies to the backup file.
                String dataName;
                double data;
                while (input.hasNext()) {       //while loop goes through every name.
                    dataName = input.next();
                    data = input.nextDouble();
                    if (dataName.equals(userName))           //if this specific user has played before.
                        outputBackUp.println(userName + " " + total);   //simply replaces total instead of making new line.
                    else
                        outputBackUp.println(dataName + " " + data); //normal copying over.
                }
                input.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try( PrintWriter out = new PrintWriter("The Casino.txt")       //now will copy back to original file. append still not used.
            ){
                File myObj = new File("Backup.txt");
                java.io.File file = new java.io.File("Backup.txt");     //this import is used to delete the file.
                Scanner input = new Scanner(myObj);        //now scans backup file, copies to original file.
                String backingUp;
                while(input.hasNextLine()) {
                    backingUp = input.nextLine();
                    out.println(backingUp);        //copies backup to original.
                }
                input.close();
                file.delete();          //deletes backup file.
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}