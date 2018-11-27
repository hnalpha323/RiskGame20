package model;

import model.GameDriver;
import model.Map;
import org.junit.Test;
import exceptions.PlayerException;

/**
 * Tests number of players with minPlayers - 1
 */
public class MinPlayerTest {

    /**
     * creates a game with 1 player
     * @throws PlayerException be careful
     */
    @Test(expected = PlayerException.class)
    public void testMinPlayers() throws PlayerException
    {
        GameDriver gm = new GameDriver(1,"r", 500);
        gm.start();
    }


    /**
     * creates a game with 3 player which is correct
     * @throws PlayerException be careful
     */
    @Test()
    public void testNormalPlayers() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
    }

}
