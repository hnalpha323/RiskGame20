package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import model.Card;

/**
 * Test class for testing Cards
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 * 
 */

public class TestCards {

	/**
	 * Tests territory name
	 * @author Shah_Mohammad_Mostakim
	 * @version 2.0.0
	 * 
	 */
	@Test
	public void testTerritory() {
		//fail("Not yet implemented");
		String territory = "Canada";
		int army = 10;
		Card c = new Card(territory, army);
		
		String territoryFound = c.getCardTerritoryName();
		String territoryExpected = "Canada";
		
		assertEquals(territoryFound, territoryExpected);
	}
	
	/**
	 * Tests Number of armies
	 * @author Shah_Mohammad_Mostakim
	 * @version 2.0.0
	 * 
	 */
	@Test
	public void testArmy() {
		//fail("Not yet implemented");
		String territory = "Canada";
		int army = 10;
		Card c = new Card(territory, army);
		
		int armyFound = c.getCardValue();
		int armyExpected = 10;
		
		assertEquals(armyFound, armyExpected);
	}

}
