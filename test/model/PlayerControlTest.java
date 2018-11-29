package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import exceptions.PlayerException;
import org.junit.Assert;
import org.junit.Test;
import model.Interfaces.*;
import model.Interfaces.ContinentInterface;
/**
 * Test class for checking player control over Map
 * @author Shah Mohammad Mostakim 
 * @version 3.0
 *
 */
public class PlayerControlTest {

	/**
	 * no continent is controlled by the player at the beginning 
	 */
	@Test
	public void testNoControl() throws PlayerException {
		Map map = new Map();
        map.clearData();
        map.fakeData();

        GameDriver GD = new GameDriver(map, 4, "r,r,r,b", 400);
        GD.start(false);
        
        PlayerInterface player = GD.nextPlayer();
        ArrayList<ContinentInterface> continentList = GD.ContinentControlledBy(player);
        Assert.assertEquals(0, continentList.size());
	}
	
	/**
	 * player controls all the 3 continents loaded by fake map
	 */
	@Test
	public void testHasControl() throws PlayerException {
		Map map = new Map();
		map.clearData();
		map.fakeData();
		GameDriver GD = new GameDriver(map, 5, "b,r,r,b,b", 400);
		GD.start(false);
		
		PlayerInterface player = GD.nextPlayer();
		
		for(ContinentInterface c: map.getContinents()) {
			for(TerritoryInterface t: c.getTerritories()) {
				player.ownTerritory(t);
			}
		}
		
		ArrayList<ContinentInterface> continentList = GD.ContinentControlledBy(player);
		assertEquals(continentList.size(), 3);
	}

}
