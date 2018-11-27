package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import utility.Results;
import org.junit.Before;
import org.junit.Test;

import model.Interfaces.TerritoryInterface;
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
	
	/**
	 * Test remaining number of armies 
	 * after armies killed in an attack
	 */
	@Test
	public void testRemainingArmies() {
		Territories t = new Territories(continentName, territorryName, coordinate, adjacentTerritories);
		t.setCurrentPlayer("Sensei");
		t.setNumberOfArmies(15);
		
		t.killArmies(10);
		// there should be 5 armies left
		int foundArmies = t.getNumberOfArmies();
		int expectedArmies = 5;
		
		assertEquals(foundArmies, expectedArmies);
	}
	
	/**
	 * test a territory with another territory object
	 * checks if other territory has an adjancency 
	 * with the territory being tested
	 * the other territory's name should be in the 
	 * adjacent territory  list of the 'territory being tested'
	 */
	@Test
	public void verifyAdjacencyWithAnotherTerritory() {
		// create two territory objects  
		// name of second territory should appear in the neighbers list 
		// of first territory 
		
		String continentName = "Asia";
		String territorryName = "China";
		String coordinate = "dummy-coordinates";
		ArrayList<String> adjacentTerritories = new ArrayList<String>();
		adjacentTerritories.add("Mongolia");
		adjacentTerritories.add("Tibbet");
		adjacentTerritories.add("Taiwan");
		Territories t1 = new Territories(continentName, territorryName, coordinate, adjacentTerritories);
		t1.setCurrentPlayer("John");
		t1.setNumberOfArmies(25);
		
			
		// 2nd territory object 
		String continentName2 = "Asia";
		String territorryName2 = "Taiwan";
		String coordinate2 = "dummy-coordinates";
		ArrayList<String> adjacentTerritories2 = new ArrayList<String>();
		adjacentTerritories2.add("Mongolia");
		adjacentTerritories2.add("Tibbet");
		adjacentTerritories2.add("China");
		Territories t2 = new Territories(continentName2, territorryName2, coordinate2, adjacentTerritories2);
		t2.setCurrentPlayer("Doe");
		t2.setNumberOfArmies(20);
		
		//comparing 
		boolean foundResult = t1.hasAdjacencyWith(t2);
		boolean expectedResult = true;
		assertEquals(foundResult, expectedResult);
	}
}
