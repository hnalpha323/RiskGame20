package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import model.GameDriver;
import model.Map;
import model.Interfaces.MapInterface;
import model.Interfaces.PlayerInterface;

import exceptions.PlayerException;

/**
 * Test card exchange functionality with different trades
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 */

public class TestCardExchange {

	@Test()
    public void test1() throws PlayerException {
		MapInterface m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        int exchangeValue = gm.exchangeCard(p);
        Assert.assertEquals(0, exchangeValue);
        
    }
	
	/**
     * First trade
     * @throws InvalidNumOfPlayersException
     */
	@Test()
    public void test2() throws PlayerException {
        MapInterface m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        int exchangeValue = gm.exchangeCard(p);
        Assert.assertEquals(4, exchangeValue);
    }
	
	/**
     * 2nd trade
     * @throws InvalidNumOfPlayersException
     */
    @Test()
    public void test3() throws PlayerException {
    	MapInterface m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        int exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);
        Assert.assertEquals(6, exchangeValue);


    }

    /**
     * 3rd trade
     * @throws InvalidNumOfPlayersException
     */
    @Test()
    public void test4() throws PlayerException {
    	MapInterface m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        int exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);


        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);
        Assert.assertEquals(8, exchangeValue);


    }

    /**
     * 4th trade
     * @throws InvalidNumOfPlayersException
     */
    @Test()
    public void test5() throws PlayerException {
    	MapInterface m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        int exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);
        Assert.assertEquals(10, exchangeValue);

    }

    /**
     * 5th trade
     * @throws InvalidNumOfPlayersException
     */
    @Test()
    public void test6() throws PlayerException {
    	MapInterface m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        int exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);
        Assert.assertEquals(12, exchangeValue);

    }

    /**
     * 6th trade
     * @throws InvalidNumOfPlayersException
     */
    @Test()
    public void test7() throws PlayerException {
    	MapInterface m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        int exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);
        Assert.assertEquals(15, exchangeValue);

    }

    /**
     * 7th trade
     * @throws InvalidNumOfPlayersException
     */
    @Test()
    public void test8() throws PlayerException {
    	MapInterface m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        int exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);

        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        gm.cardDeck.grantCard(p);
        exchangeValue = gm.exchangeCard(p);
        Assert.assertEquals(20, exchangeValue);

    }
	
}
