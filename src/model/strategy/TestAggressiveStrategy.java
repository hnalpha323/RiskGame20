package model.strategy;

import static org.junit.Assert.*;

import org.junit.Test;
import model.strategy.Aggressive;

/**
 * Testing functionalities of Aggressive Strategy
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 *
 */
public class TestAggressiveStrategy {

	/**
	 * Testing attack attemps
	 * @author Shah_Mohammad_Mostakim
	 * @version 2.0.0
	 *
	 */
	@Test
	public void testAttackAttempts() {
		Aggressive agg = new Aggressive();
		int foundAttackAttempts = agg.getAttackAttempts();
		int expectedAttackAttempts = 6;
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
		Aggressive agg = new Aggressive();
		String foundName = agg.getName();
		String expectedName = "Aggressive";
		assertEquals(foundName, expectedName);
	}

}
