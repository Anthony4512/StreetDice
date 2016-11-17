
package project2_amm;

import java.util.Random;


public class Die {
    
    private int sides;
    private int value;
    Random r = new Random();
    
    public Die() {
        sides = 6;
        value = 1;
    }
    public Die(int numOfSizes) {
        if (numOfSizes <= 0) {
            sides = 1;
            value = 1;
        }
        else {
            sides = numOfSizes;
            value = 1;
        }
    }
    
     public int roll()
    {
        value = r.nextInt(sides) + 1;
        return value;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public int getSides()
    {
        return sides;
    }
    public void setSides(int s)
    {
        sides = s;
    }
}
