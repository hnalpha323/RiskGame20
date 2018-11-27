package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import utility.Results;
import org.junit.Before;
import org.junit.Test;
/**
 * Testing territory model properties and its functionalities 
 * @author Shah Mohammad Mostakim 
 * @version 3.0
 *
 */
public class TerritoriesTest {

	/**
	 * prepare a territory object 
	 * @author Mostakim
	 */
	String continentName = "Asia";
	String territorryName = "China";
	String coordinate = "dummy-coordinates";
	ArrayList<String> adjacentTerritories = new ArrayList<String>();
	
	/**
	 * initialize territory object with primary data
	 */
	@Before
	public void initialize() {
		// initialize adjacent territories of 'from' 
		adjacentTerritories.add("Mongolia");
		adjacentTerritories.add("Tibbet");
		adjacentTerritories.add("Taiwan");
	}
	
	/**
	 * check the number adjacent territories 
	 */
	@Test
	public void testAdjacencies() {
		
		Territories t = new Territories(continentName, territorryName, coordinate, adjacentTerritories);
		t.setCurrentPlayer("John Doe");
		t.setNumberOfArmies(10); 
		
		ArrayList<String> listOfAdjacentTerritorries = t.getAdjacentTerritories();
		int numberOfAdjacencies = listOfAdjacentTerritorries.size();
		
		assertTrue(numberOfAdjacencies>0);
	}
	
	/**
	 * check the total number of armies after adding new armies 
	 * with existing armies in a territory
	 */
	@Test
	public void testAdditionOfArmies() {
		Territories t = new Territories(continentName, territorryName, coordinate, adjacentTerritories);
		t.setCurrentPlayer("Akashi");
		t.setNumberOfArmies(15);
		
		t.placeArmies(5); 
		// new army count should be 20
		int expectedArmy = 20;
		int foundArmy = t.getNumberOfArmies();
		
		assertEquals(expectedArmy, foundArmy);
	}
	
	/**
	 * test the function of army removal from a territory 
	 */
	@Test
	public void testArmyRemoval() {
		Territories t = new Territories(continentName, territorryName, coordinate, adjacentTerritories);
		t.setCurrentPlayer("Sensei");
		t.setNumberOfArmies(15);
		
		Results r = t.removeArmies(5); 
		// Results->ok property will be true after 
		// successful deletion of armies   
		boolean foundResult = r.getOk();
		boolean expectedResult = true;
		
		assertEquals(foundResult, expectedResult);
	}
	
	/**
	 * another method of testing army removal from territory
	 */
	@Test
	public void testArmyRemoval2() {
		Territories t = new Territories(continentName, territorryName, coordinate, adjacentTerritories);
		t.setCurrentPlayer("Sensei");
		t.setNumberOfArmies(15);
		
		Results r = t.removeArmies(5); 
		// Territories->getNumberOfArmies() should provide the reduced  
		// number of armies after a successful army removal
		int foundArmies = t.getNumberOfArmies();
		int expectedArmies = 10;
		
		assertEquals(foundArmies, expectedArmies);
	}
}
