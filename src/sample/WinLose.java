package sample;

public class WinLose {
    public Double GambleAmount;
    public String GambleAmountString = Double.toString(GambleAmount);
    public String Win = "Good betting, you won $" + this.GambleAmountString + "!";
    public String Lose = "I regret to inform you that you lost $" + this.GambleAmountString + ".";
    public String Draw ="You have neither lost nor won. You have been refunded $" + this.GambleAmountString + ".";
}
