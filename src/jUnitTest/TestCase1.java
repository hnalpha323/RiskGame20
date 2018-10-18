package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.GameDriver;

import exceptions.PlayerException;

/**
 * @author Muhammad_Hamza_Noor
 * Tests number of players with minPlayers - 1
 */
public class TestCase1 {

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
