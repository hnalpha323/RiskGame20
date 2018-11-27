package model;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;

import model.Interfaces.PlayerInterface;
import exceptions.PlayerException;

/**
 * This test is to check whether is game is able to save or not
 * @author Waleed Ahmad
 */
public class SaveMethodTest {

	Map m;
	GameDriver gm;
	SaveMethod SaveMethod;

	
	@Before
	public void setUpBeforeClass()
	{		
		SaveMethod = new SaveMethod();
	}
	
	
	/**
	 * to check if game if bale to be saved
	 * @throws PlayerException if number of players not as per game rules
	 * @throws IOException if unable to read and write game state to a file
	 */
	@Test
	public void saveGame() throws PlayerException, IOException{
		m = new Map();
        m.clearData();
        m.fakeData();

        gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);

        PlayerInterface p1 = gm.nextPlayer();
        p1.reinforcement();
        
        //save the gameMnager
        SaveMethod.saveState(gm);
        // get back new game manger
        GameDriver GameDriver = SaveMethod.getPreviousState();
        assertTrue(GameDriver.getPlayers().size() == 3);
	}
	
}
