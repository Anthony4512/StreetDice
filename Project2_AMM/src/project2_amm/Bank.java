package project2_amm;


public class Bank {
    
    private double tempValue;
    private double totalPayout;
    
    public double getTotal() {
        return totalPayout;
    }
    public boolean placeBet(double bet) {
        if(bet > 0) {
            totalPayout = totalPayout + (bet*2);
            return true;
        }
        else {
            return false;
        }
        
    } 
    public double payout() {
        tempValue = totalPayout;
        totalPayout = 0;
        return tempValue;
    }
}

