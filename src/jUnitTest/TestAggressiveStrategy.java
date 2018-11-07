package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;
import model.strategy.Aggressive;

/**
 * Test class for Aggressive Strategy
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 */

public class TestAggressiveStrategy {

	/**
	 * Tests number of attack attempts 
	 */
	@Test
	public void testAttackAttempts() {
		//fail("Not yet implemented");
		Aggressive agg = new Aggressive();
		int attemptsFound = agg.getAttackAttempts();
		int attemptsExpected = 6;
		assertEquals(attemptsFound, attemptsExpected);
	}
	
	@Test
	public void testGetName() {
		Aggressive agg = new Aggressive();
		String nameFound = agg.getName();
		String nameExpected = "Aggressive";
		assertEquals(nameFound, nameExpected);
	}

}
