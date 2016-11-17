
package project2_amm;

public class DicePlayer {
    
    private String playerName;
    private double cash;
    
    public DicePlayer() {
        playerName = "No Name";
    }
    public DicePlayer(String playerName) {
        this.playerName = playerName;
    }
    
    public void setName(String nameOfPlayer) {
        playerName = nameOfPlayer;
    }
    public String getName() {
       return playerName; 
    }
    public void setCash(double cashToSet) {
        cash = cashToSet;
    }
    public double getCash() {
        return cash;
    }
    public boolean subtractCash(double cashToSubtract) {
        if(cashToSubtract >  cash || cashToSubtract < 0) {
            return false;
        }
        else {
            cash-=cashToSubtract;
            return true;
        }
        
        
    }
    public boolean addCash(double cashToAdd) {
        if(cashToAdd >= 0) {
            cash += cashToAdd;
            return true;
        }
        else {
            return false;
        }
    }
}
