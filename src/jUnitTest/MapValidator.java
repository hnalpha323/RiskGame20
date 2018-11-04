package jUnitTest;



import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;
import model.DataReader;
import model.DataWriter;
import model.MapDatabase;
import model.Interfaces.TerritoryInterface;

/**
 * @author Muhammad_Hamza_Noor
 * @version 1.0.0
 *
 */
public class MapValidator {

	static WriteController writeController;
	static ReadController readController;
	
	@Before
	public void setUpBeforeClass()
	{
		 RWMapFileController rw = new RWMapFileController();
	     rw.loadMap(new File("C:\\Users\\hnalp\\OneDrive\\Documents\\GitHub\\RiskGame20\\bin\\Earth Alternate.map"));
	     DataReader dataReader = new DataReader();
	     readController = new ReadController(dataReader);
	     DataWriter dataWriter = new DataWriter();
	     writeController = new WriteController(dataWriter); 
	}
	
	
	
	@Test
	public void readerTest(){
	     assertTrue(readController.getContinentValue("north america") == 5);
	     assertTrue(readController.dataReader.hasContinent("north america"));
	}
	
	@Test
	public void addToMapTest(){
		writeController.addData("[America,Newzland]", "Kontinent", "Kontry", "4", false, false);
		String[] arr = {"America","Newzland"} ;
	    System.out.println(readController.getAdjacentTerritories("Kontinent", "Kontry"));
		assertTrue(readController.getContinentValue("Kontinent") == 4);
	    assertTrue(readController.getAdjacentTerritories("Kontinent", "Kontry").get(0).equals("America"));
	}
	
	@Test
	public void deleteOnMapTest(){
		writeController.addData("[America,Newzland]", "Kontinent", "Kontry", "4", false, false);
		writeController.addData("America,Newzland", "Kontinent", "Kontry", "4", true, false);
		assertFalse(readController.dataReader.hasContinent("Kontinent"));
	}
	
	
	 @Test
	public void adjacencyTest(){
		TerritoryInterface t= MapDatabase.continents.get("africa").get("east africa");
		ArrayList<TerritoryInterface> tmp= t.getAdjacentTerritoryObjects();
		assertTrue(tmp.size() > 0);
		for(TerritoryInterface t2:tmp){
			System.out.println(t2.getName());
		}
	}
	


}
