package model;

import model.Interfaces.*;
import exceptions.PlayerException;
import static org.junit.Assert.*;

import org.junit.Test;

public class EndGameTest {

	/**
	 * no player has 100% domination so there is no winner at the beginning
	 * @throws PlayerException
	 */
	@Test
	public void test() throws PlayerException{

		Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);
        
        PlayerInterface p = gm.getWinner();
        assertTrue(p==null);
		
	}

}
