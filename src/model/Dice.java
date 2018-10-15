package model;

import static utility.DiceRNG.getRandomInt;

/**
 * Random Number Generator from 1 to 6 for Dice
 * 
 * @author Meet_Patel
 * @version 1.0.0
 */
public class Dice {
	
	public Dice() {

    }
    public int roll(){
        return getRandomInt(6,1);
    }

}
