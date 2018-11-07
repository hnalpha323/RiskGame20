package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;
import model.strategy.Defensive;

/**
 * Test class for Defensive Strategy
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 */

public class TestDefensiveStrategy {

	/**
	 * Tests number of attack attempts 
	 */
	@Test
	public void testAttackAttempts() {
		//fail("Not yet implemented");
		Defensive def = new Defensive();
		int attemptsFound = def.getAttackAttempts();
		int attemptsExpected = 0;
		assertEquals(attemptsFound, attemptsExpected);
	}
	
	@Test
	public void testGetName() {
		Defensive def = new Defensive();
		String nameFound = def.getName();
		String nameExpected = "Defensive";
		assertEquals(nameFound, nameExpected);
	}

}
