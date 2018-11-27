package model;

import model.GameDriver;
import model.Map;
import model.Interfaces.PlayerInterface;
import org.junit.Assert;
import org.junit.Test;
import exceptions.PlayerException;


/**
 * testing card exchange logic with different trades
 */
public class CardExchangeTest {

    @Test()
    public void test1() throws PlayerException {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        int exchangeValue = gm.exchangeCard(p);
        Assert.assertEquals(0, exchangeValue);


    }

    /**
     * 1st trade
     * @throws PlayerException if number of player not as per game rules
     */
    @Test()
    public void test2() throws PlayerException {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
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
     * @throws PlayerException if number of player not as per game rules
     */
    @Test()
    public void test3() throws PlayerException {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3, "r,r,r", 500);
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
     * @throws PlayerException if number of player not as per game rules
     */
    @Test()
    public void test4() throws PlayerException {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
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
     * @throws PlayerException if number of player not as per game rules
     */
    @Test()
    public void test5() throws PlayerException {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
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
     * @throws PlayerException if number of player not as per game rules
     */
    @Test()
    public void test6() throws PlayerException {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
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
     * @throws PlayerException if number of player not as per game rules
     */
    @Test()
    public void test7() throws PlayerException {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3, "r,r,r", 500);
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
     * @throws PlayerException if number of player not as per game rules
     */
    @Test()
    public void test8() throws PlayerException {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
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
