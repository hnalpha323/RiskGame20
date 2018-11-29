package model;
import model.SaveMethod;
import model.Interfaces.PlayerInterface;
import exceptions.PlayerException;
import java.io.IOException;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests save and load functionality of game objects into a file
 * @author Shah Mohammad Mostakim 
 * @version 3.0.0
 *
 */
public class SaveAndLoadTest {

	/**
	 * saves a gamedriver object and load it again 
	 * check if total number of turns is correctly loaded or not
	 */
	@Test
	public void testGamePhase() throws IOException, PlayerException{
		Map map = new Map();
		map.clearData();
		map.fakeData();
		
		// create a gamedriver
		// total number of turns is 300
		GameDriver GD = new GameDriver(map, 3, "b,a,r", 300);
		GD.start(false);
		GD.setPhase("Initialize");
		
		// save game driver
		SaveMethod sm = new SaveMethod();
		sm.saveState(GD);
		
		// load gamedriver back from file 
		GameDriver G = sm.getPreviousState();
		String expectedPhase = G.getPhase();
		// after loading GameDriver again game phase is same like before
		assertEquals("Initialize", expectedPhase);
		
	}
	
	/**
	 * Saves Game with a name and checks if its same after loading again
	 */
	@Test
	public void testGameNameAfterLoad() throws IOException, PlayerException {
		
		Map map = new Map();
		map.clearData();
		map.fakeData();
		
		// create a gamedriver
		// total number of turns is 300
		GameDriver GD = new GameDriver(map, 3, "b,a,r", 300);
		GD.start(false);
		GD.setName("Conquest");
		
		// save game driver
		SaveMethod sm = new SaveMethod();
		sm.saveState(GD);
		
		// load gamedriver back from file 
		GameDriver G = sm.getPreviousState();
		String expectedName = G.getName();
		// after loading GameDriver again game phase is same like before
		assertEquals("Conquest", expectedName);
		
	}

}
