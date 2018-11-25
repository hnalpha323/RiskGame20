package utility;

import java.util.Random;

/**
 * This is Dice Random number Generator
 * @author Meet_Patel
 * @version 1.0.0
 */

public class DiceRNG {
	/**
     * method for rolling a dice and picking a card
     * @param max maximum number of items. e.g. in dice max is 6
     * @param min minimum number of items e.g. in dice min is 1
     * @return int random generated number
     */
    public static int getRandomInt(int max, int min){
        Random rng = new Random();
        return  rng.nextInt((max - min) + 1) + min;
    }
}
