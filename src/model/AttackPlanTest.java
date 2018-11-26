package model;

import org.junit.Before;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Test class for testing attack plan 
 * @author Shah Mohammad Mostakim
 * @version 3.0
 *
 */
public class AttackPlanTest {

	/**
	 * creates attackplan and check its 'to' and 'from' territories 
	 */
	// elements of territory object for using as 'from'
	String continentName = "Asia";
	String territorryName = "China";
	String coordinate = "dummy-coordinates";
	ArrayList<String> adjacentTerritories = new ArrayList<String>();
		
	// elements of territory object for using as 'to'
	String to_continentName = "Asia";
	String to_territorryName = "Taiwan";
	String to_coordinate = "dummy-coordinates";
	ArrayList<String> to_adjacentTerritories = new ArrayList<String>();
		
	@Before
	public void initialize() {
		// initialize adjacent territories of 'from' 
		adjacentTerritories.add("Mongolia");
		adjacentTerritories.add("Tibbet");
		adjacentTerritories.add("Taiwan");
		
		// initialize adjacent territories of 'to'
		adjacentTerritories.add("Mongolia");
		adjacentTerritories.add("Tibbet");
		adjacentTerritories.add("China");
	}
			
	@Test
	public void testAttackFrom() {		
		Territories from = new Territories(continentName, territorryName, coordinate, adjacentTerritories);
		Territories to = new Territories(to_continentName, to_territorryName, to_coordinate, to_adjacentTerritories);
			
		// create attack plan with 'to' and 'from'
		AttackPlan ap = new AttackPlan(to, from);
		// test generated attack plan 
		String foundFromTerritory = from.getTerritoryName();
		String expectedFromTerritory = "China";
		
		assertEquals(foundFromTerritory, expectedFromTerritory);
	}
	
	@Test
	public void testAttackTo() {
		Territories from = new Territories(continentName, territorryName, coordinate, adjacentTerritories);
		Territories to = new Territories(to_continentName, to_territorryName, to_coordinate, to_adjacentTerritories);
			
		// create attack plan with 'to' and 'from'
		AttackPlan ap = new AttackPlan(to, from);
		// test generated attack plan 
		String foundToTerritory = to.getTerritoryName();
		String expectedToTerritory = "Taiwan";
		
		assertEquals(foundToTerritory, expectedToTerritory);
	}

}
