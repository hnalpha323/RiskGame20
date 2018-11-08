package model.strategy;

import static org.junit.Assert.*;

import org.junit.Test;
import model.strategy.Defensive;

public class TestDefensiveStrategy {

	@Test
	public void testAttackAttempts() {
		Defensive def = new Defensive();
		int foundAttackAttempts = def.getAttackAttempts();
		int expectedAttackAttempts = 0;
		assertEquals(foundAttackAttempts, expectedAttackAttempts);
	}
	
	@Test
	public void testName() {
		Defensive def = new Defensive();
		String foundName = def.getName();
		String expectedName = "Defensive";
		assertEquals(foundName, expectedName);
	}

}
