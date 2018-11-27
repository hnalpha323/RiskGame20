package model;

import model.GameDriver;
import model.Map;
import model.Interfaces.MapInterface;
import org.junit.Test;
import exceptions.PlayerException;
import org.junit.Assert;

/**
 * Tests number of initial armies
 */
public class InitialArmiesTest {

    /**
     * 3 Players
     * @throws PlayerException if number of player not as per game rules
     */
    @Test
    public void test3_1() throws PlayerException
    {
        MapInterface m = new Map();
        GameDriver gm = new GameDriver(3, "r,r,r", 500);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(35, expected_armies);
    }

    /**
     * 2 Players
     * @throws PlayerException if number of player not as per game rules
     */
    @Test
    public void test3_2() throws PlayerException
    {
        MapInterface m = new Map();
        GameDriver gm = new GameDriver(2,"r,r", 500);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(40, expected_armies);
    }

    /**
     * 4 Players
     * @throws PlayerException if number of player not as per game rules
     */
    @Test
    public void test3_3() throws PlayerException
    {
        MapInterface m = new Map();
        GameDriver gm = new GameDriver(4,"r,r,r,r", 500);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(30, expected_armies);
    }

    /**
     * 5 Players
     * @throws PlayerException if number of player not as per game rules
     */
    @Test
    public void test3_4() throws PlayerException
    {
        MapInterface m = new Map();
        GameDriver gm = new GameDriver(5,"r,r,r,r,r", 500);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(25, expected_armies);
    }

    /**
     * 6 Players
     * @throws PlayerException if number of player not as per game rules
     */
    @Test
    public void test3_5() throws PlayerException
    {
        MapInterface m = new Map();
        GameDriver gm = new GameDriver(6, "r,r,r,r,r,r", 500);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(20, expected_armies);
    }


}
