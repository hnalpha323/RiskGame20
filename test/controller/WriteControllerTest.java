package controller;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.DataReader;
import model.DataWriter;
import model.MapDatabase;
import model.Territories;

/**
 * @author Shah Mohammad Mostakim
 *
 */
public class WriteControllerTest {

	static WriteController writeController;
	DataReader dataReader;
	
	/**
	 * Load map file from it location path
	 * and use controllers to load it into memory
	 */
	@Before
	public void setUpBeforeClass()
	{
		 MapDatabase.continentValues.put("asia", 3);
		 MapDatabase.continentValues.put("africa", 2);
		 MapDatabase.continentValues.put("america", 2);
		 ArrayList<String> adjacents = new ArrayList<>();
		 adjacents.add("usa");
		 Territories Territories = new Territories("america", "canada", "x,y", adjacents);
		 HashMap<String,Territories> t = new HashMap<>();
		 t.put("canada", Territories);
		 MapDatabase.continents.put("america",t);
		 DataWriter dataWriter = new DataWriter();
	     writeController = new WriteController(dataWriter);
	     dataReader = new DataReader();
	}
	
	
	/**
	 * check if bale to writes new continent to map
	 */
	@Test
	public void writenewContinentTest(){
		writeController.writenewContinent("africa");
		assertTrue(dataReader.hasContinent("africa"));
	}
	
	
	/**
	 * This method adds and deletes a Territories based on following parameters
	 */
	@Test
	public void addDataTest(){
		writeController.addData("[usa,greenland]", "america", "canada", "2", false, false);
	    assertTrue(dataReader.getAdjacentTerritories("america", "canada").size() == 2);
	    assertTrue(dataReader.getAdjacentTerritories("america", "canada").contains("greenland"));
	}
	
	
	/**
	 * This method adds and deletes a Territories based on following parameters
	 * Gives null exception as Territories is deleted
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void addDataTest1(){
		writeController.addData("[usa,greenland]", "america", "canada", "2", true, false);
	    assertTrue(dataReader.getContries("america").size() == 0);
	}
	
}
