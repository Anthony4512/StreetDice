
package project2_amm;

//imports
import java.util.Scanner;


//This is the class that manages the game.
public class Craps {
    
    // fields for this class
    private final Die die1;
    private final Die die2;
    private final DicePlayer player;
    private final Bank bank;
    private boolean playerWon = false;
    private boolean playerLoses = false;
    private boolean wantsToPlay = true;
    private int counter = 0;
    private int point;
    private double playerCash;
    private double payout;
    
    
    // Constructor takes a DicePlayer, a Bank, and 2 dice as parameters
    public Craps(DicePlayer player, Bank bank, Die die1,Die die2 ) {
        this.die1 = die1; //assigned the first passed die to die1 
        this.die2 = die2; // assigned the second passed die to die2
        this.player = player; // assigned the DicePlayer passed to player
        this.bank = bank; //assigned the bank passed to bank
    }    
    
    // created a scanner of name scan
    Scanner scan = new Scanner(System.in);
    
    // only runs when the dice are equals after rolling, then prints msg if so
    private void printMessage(int s) {  //the s is the SUM of the 2 equal dice
        if(s == 2) {
          System.out.println("Snake Eyes!"); 
       }
       if(s == 4) {
          System.out.println("Hard Four!");
       }
       if(s == 6) {
          System.out.println("Six, The Hard Way!");
       }
       if(s == 8) {
          System.out.println("Hard Eight!");
       }
       if(s == 10) {
          System.out.println("Ten, The Hard Way");
       }
       if(s == 12) {
          System.out.println("Box Cars!");
       }
    }
    
    // this method takes a bet and places it in the bank
    private void takeBet(double bet) {
        bank.placeBet(bet);  // uses the bet passed as parameter to play againts the player
        player.subtractCash(bet); // substract the bet from the player cash
        
    }
    
    // this method roll the dice and prints their value
    private void shoot() {
        int sum;
        die1.roll(); // rolling die1
        die2.roll(); // rolling die2
        int die1value = die1.getValue(); // seeing the value of the die1 after roll
        int die2value = die2.getValue(); // seeing the value of the die2 after roll
        // printing their value
        System.out.println("You rolled a " + die1value + " and a " + die2value);
        // calling the printRoll function and passing the dice values as arguments
        printRoll(die1value, die2value);
        sum = die1value + die2value;
        if((sum == 7 || sum == 11) && counter == 0) {  // player won on first roll
            playerWon = true;
        }
        else if((sum == 2 || sum == 3 || sum == 12) && counter == 0) { 
            playerLoses = true;
        }        
        if(sum == point && counter > 0) { // player won by thowing the point
            playerWon = true;
        }
        else if (sum == 7 && point > 0) {  // if sum of dice is 7 while rolling for point
            playerLoses = true; // then player loses
            point = 0;  // set the point to 0
            playerWon = false;
        }
        // player loses on first hand by throwing a 2 a 3 or a 12
        if(!playerLoses) { // if he did not lose and the point is less then one 
                point = sum; // then set point to what the sum of the dice is
        }
        else if (point == sum) {  // if the point is the same as the sum then player won
            playerWon = true;
            playerLoses = false;
            point = 0;
        }
        
        
    }
    
    // this method takes the value of the dice after roll as parameters and do some logic
    private void printRoll(int die1value, int die2value) {
        int sum = die1value + die2value;
        if(die1value == die2value) { // if dice have an equal value
           System.out.println("your total roll is " + sum); // print the sum 
           printMessage(sum);  // and call the printMessage method 
        }
        else {  // otherwise just print the sum 
            System.out.println("Your total roll is " + sum);
        }
    }
    // defines when a player wins 
    private void shooterWins() {
        
        payout = bank.payout(); // the payout when player wins
        playerCash = player.getCash();  // the cash that the player has at the moment
        player.setCash(payout + playerCash);  // adding the payout to the players cash
        System.out.println("Shooter wins"); // prints the string 
        System.out.println("You won $" + payout    // prints what you won and what you have
                + ". You now have " + player.getCash());
    }
    // method that defines when a player loses
    private void shooterLoses() {
        System.out.println("Shooter loses");
        System.out.println("The bank won $" + bank.payout() + ". You now have " 
                + player.getCash());
        
    }
    // ask for name and initial money 
    public void setup() {
        double initialCash;
        System.out.println("Welcome to Street Dice. What is your name?");
        String playerName = scan.next();
        player.setName(playerName);
        do {
            System.out.println("How much money are you starting with?");
            initialCash = scan.nextDouble();
            player.setCash(initialCash); 
        } while(initialCash <= 0);   
    }
    
    // play function where the logic for plain the game is
    public void play() {
        char userInput;  
        double playerBet;
        
        while(wantsToPlay) {      // loops while player wants to play
            String playerName = player.getName();  // gets player name
            double playerMoney = player.getCash(); // get players money at the moment
            System.out.println(playerName + ", you are the shooter. you have $" + playerMoney);
            do {  // checks for 0 and negative numbers as bets... keep asking 
                System.out.println("How much would you like to bet? $0 or more");
                playerBet = scan.nextDouble();
            }while(playerBet <= 0);  
            takeBet(playerBet);  // takes the bet
            System.out.println("The bank will match your bet");
            shoot(); // calls the shoot function
            if(playerWon) { // if player won 
                shooterWins();  // call the shooter wins function
            }
            else if(playerLoses) { // if player loses
                shooterLoses();  // call the shooterLoses function
            }
            // if the player didn't win nor loose then roll for point
            else if (!playerWon && !playerLoses) {  
                System.out.println("Rolling for point...");
                System.out.println("The point is " + point);
                while(point > 0) {  // while player hasn't win nor loose
                    shoot();        // keep shooting the dice
                }
                if(playerWon) {  // call shooterWins if player won
                    shooterWins();
                }
                else if(playerLoses) {  // call shooterLoses if player loose
                    shooterLoses();
                }

            }
            if(playerWon || playerLoses) {  // ofter player won or lost
                // ask if they want to play or not
                System.out.println("Would you like to play again? [Y]es or [N]o");
                userInput = scan.next().toLowerCase().charAt(0);
                if(userInput == 'y') { // if the letter is a 'y' then play again
                    //resetting the fields to its original values
                    wantsToPlay = true;
                    playerWon = false;
                    playerLoses = false;
                    point = 0;
                    counter = 0;
                }
                else if(userInput == 'n') { // if the letter is a 'n' then quit
                wantsToPlay = false;
                }                 
            }            
        }   
    }  
}
