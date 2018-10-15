package model;

import static util.Helpers.getRandomInt;

/**
 * Random Number Generator from 1 to 6 for Dice
 */
public class Dice {
	
	public Dice() {

    }
    public int roll(){
        return getRandomInt(6,1);
    }

}
