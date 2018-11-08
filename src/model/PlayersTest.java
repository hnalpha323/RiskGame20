package model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import model.Players;
import utility.Gradient;
import model.strategy.*;
import model.Card;
import model.Interfaces.StrategyInterface;

/**
 * Test class for Players
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 */

public class PlayersTest {
	
	private Players p;
	
	/**
	 * initialize player object before running each test case
	 */
	@Before
	public void setUp() {
		this.p = new Players("Jamil", new Gradient(), new Aggressive());
	}

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
	 * Test player domination of map 
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
	 * Test number of armies used by player
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
	
	/**
	 * Test number of cards owned by player
	 */
	@Test
	public void testPlayerCardNumber() {
		String name = "Rogers";
		Gradient g = new Gradient();
		Aggressive agg = new Aggressive();
		Players p = new Players(name, g, agg);
		
		Card card = new Card("China", 5);
		
		p.addCard(card);
		int foundCardSize = p.getCardsSize();
		int expectedCardSize = 1;
		
		assertEquals(foundCardSize, expectedCardSize);
	}
	
	/**
	 * Testing strategy type used by player
	 * object used here was initialized by setup() method
	 */
	@Test
	public void testPlayerStrategy() {
		StrategyInterface strategy = this.p.getStrategy();
		String foundStrategy = strategy.getName();
		String expectedStrategy = "Aggressive";
		
		assertEquals(foundStrategy, expectedStrategy);
	}

}
