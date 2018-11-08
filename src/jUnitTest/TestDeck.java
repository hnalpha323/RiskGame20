package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;
import model.Deck;
import model.Interfaces.PlayerInterface;
import model.Card;

/**
 * Tests functionalities of Deck class
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 *
 */

public class TestDeck {

	/**
	 * Tests Deck class whether it grants a card 
	 * @author Shah_Mohammad_Mostakim
	 * @version 2.0.0
	 */
	@Test
	public void testDeckObject() {
		Deck d = new Deck();
		PlayerInterface p = null;
		Card c = d.grantCard(p);
		assertTrue(c instanceof Card);
	}

}
