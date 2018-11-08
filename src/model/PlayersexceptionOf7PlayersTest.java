package model;

import org.junit.Test;
import exceptions.PlayerException;

/**
 * @author Muhammad_Hamza_Noor
 * Tests number of players with MaxPlayers + 1
 */
public class PlayersexceptionOf7PlayersTest {

    /**
     * Creates a game with 7 players
     * @throws InvalidNumOfPlayersException be careful
     */
    @Test(expected = PlayerException.class)
    public void testMaxPlayers() throws PlayerException
    {
        GameDriver gm = new GameDriver(7);
        gm.start();
    }

}
