package model.strategy;

import static org.junit.Assert.*;

import org.junit.Test;
import model.strategy.Defensive;

/**
 * Testing functionalities of Defensive Strategy
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 *
 */

public class TestDefensiveStrategy {

	/**
	 * Testing attack attemps
	 * @author Shah_Mohammad_Mostakim
	 * @version 2.0.0
	 *
	 */
	@Test
	public void testAttackAttempts() {
		Defensive def = new Defensive();
		int foundAttackAttempts = def.getAttackAttempts();
		int expectedAttackAttempts = 0;
		assertEquals(foundAttackAttempts, expectedAttackAttempts);
	}
	
	/**
	 * Testing attack startegy name 
	 * @author Shah_Mohammad_Mostakim
	 * @version 2.0.0
	 *
	 */
	@Test
	public void testName() {
		Defensive def = new Defensive();
		String foundName = def.getName();
		String expectedName = "Defensive";
		assertEquals(foundName, expectedName);
	}

}
