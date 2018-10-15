package utility;

import java.util.Random;

/**
 * This is Dice Random number Generator
 * 
 * @author Meet_Patel
 * @version 1.0.0
 */

public class DiceRNG {
	
    public static int getRandomInt(int max, int min){
        Random rng = new Random();
        return  rng.nextInt((max - min) + 1) + min;
    }
}
