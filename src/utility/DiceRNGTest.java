package utility;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceRNGTest {

	/**
	 * test if any random integer for Dice is returned or not
	 * @author Shah Mohammad Mostakim
	 */
	@Test
	public void testDiceRandomInteger() {
		int result = DiceRNG.getRandomInt(6,1);
		assertTrue(0<result);
	}

}
