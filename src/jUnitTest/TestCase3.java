package jUnitTest;

import model.GameDriver;
import model.Map;
import model.Interfaces.*;

import org.junit.Test;
import exceptions.PlayerException;
import org.junit.Assert;

/**
 * @author Muhammad_Hamza_Noor
 * Tests number of initial armies
 */
public class TestCase3 {

    @Test
    public void test() throws PlayerException
    {
        MapInterface m = new Map();
        GameDriver gm = new GameDriver(3);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(35, expected_armies);
    }


}
