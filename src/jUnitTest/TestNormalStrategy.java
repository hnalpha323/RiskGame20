package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;
import model.strategy.Normal;

/**
 * Test class for Normal Strategy
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 */

public class TestNormalStrategy {

	/**
	 * Tests number of attack attempts 
	 */
	@Test
	public void testAttackAttempts() {
		//fail("Not yet implemented");
		Normal agg = new Normal();
		int attemptsFound = agg.getAttackAttempts();
		int attemptsExpected = 1;
		assertEquals(attemptsFound, attemptsExpected);
	}
	
	@Test
	public void testGetName() {
		Normal agg = new Normal();
		String nameFound = agg.getName();
		String nameExpected = "Normal";
		assertEquals(nameFound, nameExpected);
	}

}
