package controller;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;

import model.DataReader;
import model.DataWriter;
import model.MapDatabase;
import model.Territories;

/**
 * @author Muhammad_Hamza_Noor
 *
 */
public class ReadControllerTest {

	
	static WriteController writeController;
	static ReadController readController;
	DataReader dataReader;
	
	/**
	 * Input some data in maps by passing values
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
	     readController = new ReadController(dataReader);
	}
	
	/**
	 * Checks whether {@link ReadController} able to read the data as
	 * to get a continent value
	 */
	@Test
	public void getContinentValueTest(){
		writeController.addData("[canada,newzland]", "Kontinent", "Kontry", "4", false, false);
		assertTrue(readController.getContinentValue("Kontinent") == 4);
	    assertTrue(readController.getAdjacentTerritories("Kontinent", "Kontry").contains("canada"));
	}
	
	
	@Test
	public void getAdjacentTerritoriesTest(){
		assertTrue(readController.getAdjacentTerritories("Kontinent", "Kontry").contains("canada"));
	}
	
	
}
