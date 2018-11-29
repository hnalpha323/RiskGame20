package model;

import model.Interfaces.*;
import exceptions.PlayerException;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Checks if the calculated initial armies are allocated among players
 * @author MS
 *
 */
public class AllocationOfInitialArmyTest {

	@Test
	public void testInitialArmyAllocation() throws PlayerException{
		
		Map map = new Map();
		map.clearData();
		map.fakeData();
		
		GameDriver GD = new GameDriver(map, 3, "b,b,b", 400);
		GD.start(false);
		
		GD.allocateInitialArmies();
		
		PlayerInterface player = GD.nextPlayer();
		int unusedArmies = player.getUnusedArmies();
		// initial unused armies should be 35 for 3 players
		assertEquals(unusedArmies, 35);
		
	}

}
