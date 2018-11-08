package model;

import static org.junit.Assert.*;

import org.junit.Test;
import model.Players;
import utility.Gradient;
import model.strategy.*;

/**
 * Test class for Players
 * @author MS
 * @version 2.0.0
 */

public class PlayersTest {

	/**
	 * Test player name
	 */
	@Test
	public void testName() {
		String name = "Maksud";
		Gradient g = new Gradient();
		Aggressive agg = new Aggressive();
		Players p = new Players(name, g, agg);
		
		String nameFound = p.getName();
		String nameExpected = "Maksud";
		
		assertEquals(nameFound, nameExpected);
	}
	
	/**
	 * Test player domination 
	 */
	@Test
	public void testDomination() {
		String name = "Maksud";
		Gradient g = new Gradient();
		Aggressive agg = new Aggressive();
		Players p = new Players(name, g, agg);
		
		p.setDomination(80.00);
		Double domFound = p.getDomination();
		Double domExpected = 80.00;
		
		assertEquals(domFound, domExpected);
	}
	
	/**
	 * Test armies used by player
	 */
	@Test
	public void testUsedArmies() {
		String name = "Rogers";
		Gradient g = new Gradient();
		Aggressive agg = new Aggressive();
		Players p = new Players(name, g, agg);
		
		p.setUsedArmies(10);
		int usedArmyFound = p.getUsedArmies();
		int usedArmyExpected = 10;
		
		assertEquals(usedArmyFound, usedArmyExpected);
	}

}
