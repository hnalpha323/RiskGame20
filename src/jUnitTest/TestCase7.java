package jUnitTest;

import model.GameDriver;
import org.junit.Test;
import exceptions.PlayerException;

/**
 * @author Shah_Mohammad_Mostakim
 * Tests number of players with MaxPlayers + 2
 */
public class TestCase7 {

    /**
     * Creates a game with 8 players
     * @throws InvalidNumOfPlayersException be careful
     */
    @Test(expected = PlayerException.class)
    public void testMaxPlayers() throws PlayerException
    {
        GameDriver gm = new GameDriver(8);
        gm.start();
    }

}
