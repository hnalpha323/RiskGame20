package mapFiles;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.DataReader;
import model.MapDatabase;
import model.Territories;

/**
 * @author SA
 *
 */
public class ReaderTest {

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
		 
	     //DataReader only do read operation on map 
	     dataReader = new DataReader();
	}
	
	
	@Test
	public void getContinentTest(){
		assertTrue(dataReader.getContries("america").size() == 1);
	}
	
	
	@Test
	public void getAdjacentTerritoriesTest(){
		System.out.println(dataReader.getAdjacentTerritories("america", "canada"));
		assertTrue(dataReader.getAdjacentTerritories("america", "canada").contains("usa"));
	}
	
	@Test
	public void hasContienntTest(){		
		assertTrue(dataReader.hasContinent("asia"));
		assertFalse(dataReader.hasContinent("canada"));
	}
	
	@Test
	public void getContinentValueTest(){		
		assertTrue(dataReader.getContinentValue("asia") == 3);
	}
	
	
}
