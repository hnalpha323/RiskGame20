package model;

import model.GameDriver;
import org.junit.Test;
import exceptions.PlayerException;

/**
 * Tests number of players with MaxPlayers + 1
 */
public class MaxPlayersTest {

    /**
     * Creates a game with 7 players
     * @throws PlayerException be careful
     */
    @Test(expected = PlayerException.class)
    public void testMaxPlayers() throws PlayerException
    {
        GameDriver gm = new GameDriver(7,"r,r,r,r,r,r,r", 500);
        gm.start();
    }

}