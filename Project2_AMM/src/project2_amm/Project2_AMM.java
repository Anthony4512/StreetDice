/* 
*   __author__== "Anthony Mirely" &&  __NECC#__ == 00261404
*   This is a game that simulates playing street craps
*   
*   The code was done entirely on my own, with some of the classes taken from the 
*   zybook labs that I have already completed.
*   Idea from Michael Penta
*/

package project2_amm;

// Projects main class
public class Project2_AMM {
    
    
    // Here is the main function 
    public static void main(String[] args) {
        Die die1 = new Die(6); // Created a die  oject with 6 sides
        Die die2 = new Die(6); // created die object with 6 sides 
        Bank bank = new Bank(); // created a Bank object
        DicePlayer player1 = new DicePlayer(); //created a DicePlayer object
        
        //created a craps object that takes a player, a bank and 2 dies as arguments
        Craps game = new Craps(player1, bank, die1,die2);
        
        game.setup(); // setup the game
        game.play();  // play the game
    }   
}
