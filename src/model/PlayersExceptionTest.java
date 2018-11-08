package model;


import org.junit.Test;

import exceptions.PlayerException;

/**
 * @author Muhammad_Hamza_Noor
 * Tests number of players with minPlayers - 1
 */
public class PlayersExceptionTest {

    /**
     * creates a game with 1 player
     * @throws PlayerException be careful
     */
    @Test(expected = PlayerException.class)
    public void testMinPlayers() throws PlayerException
    {
        GameDriver gm = new GameDriver(1);
        gm.start();
    }

}
