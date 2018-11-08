package model.strategy;

import static org.junit.Assert.*;

import org.junit.Test;
import model.strategy.Normal;

/**
 * Testing functionalities of Normal Strategy
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 *
 */

public class TestNormalStrategy {

	/**
	 * Testing attack attemps
	 * @author Shah_Mohammad_Mostakim
	 * @version 2.0.0
	 *
	 */
	@Test
	public void testAttackAttempts() {
		Normal nor = new Normal();
		int foundAttackAttempts = nor.getAttackAttempts();
		int expectedAttackAttempts = 1;
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
		Normal nor = new Normal();
		String foundName = nor.getName();
		String expectedName = "Normal";
		assertEquals(foundName, expectedName);
	}

}
