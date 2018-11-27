package mapFiles;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


import model.MapDatabase;
import model.ValidMap;
import model.Territories;

/**
 * @author Shah Mohammad Mostakim
 *
 */
public class ValidMapTest {

	/**
	 * Load map file from into HashMap
	 */
	@Before
	public void setUpBeforeClass()
	{
		 MapDatabase.clear();
		 MapDatabase.continentValues.put("asia", 3);
		 MapDatabase.continentValues.put("africa", 2);
		 MapDatabase.continentValues.put("america", 2);
		 
		 //  Adding the Territories Canada
		 ArrayList<String> adjacents = new ArrayList<>();
		 adjacents.add("usa");
		 Territories Territories = new Territories("america", "canada", "x,y", adjacents);
		 HashMap<String,Territories> america = new HashMap<>();
		 america.put("canada", Territories);
		 
		 //adding Territories usa
		 adjacents = new ArrayList<>();
		 adjacents.add("south africa");
		 Territories = new Territories("america", "usa", "x,y", adjacents);
		 america.put("usa", Territories);
		 
		 MapDatabase.continents.put("america",america);
		 
		 //adding Territories south Africa
		 HashMap<String,Territories> africa = new HashMap<>();
		 adjacents = new ArrayList<>();
		 adjacents.add("usa");
		 Territories = new Territories("africa", "south africa", "x,y", adjacents);
		 africa.put("south africa", Territories);
		 MapDatabase.continents.put("africa",africa);
	 }
	
	
	 @Test
	 public void isValidAdjacencyTest1(){
		 assertTrue(ValidMap.isValidAdjacency());		 
	 }
	
	 @Test
	 public void isAnyDiconnectivityTest1(){
		 assertFalse(ValidMap.isAnyDiconnectivity());		 
	 }
	  
}
