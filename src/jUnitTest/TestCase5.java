package jUnitTest;

import model.GameDriver;
import model.Map;
import model.Interfaces.MapInterface;
import model.Interfaces.PlayerInterface;
import org.junit.Assert;
import org.junit.Test;
import exceptions.PlayerException;

/**
 * @author Muhammad_Hamza_Noor
 * Tests minimum reinforcement armies
 */
public class TestCase5 {

    @Test()
    public void test() throws PlayerException
    {
        MapInterface m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3);
        gm.initGame();

        PlayerInterface p = gm.nextPlayer();
        Assert.assertEquals(3, gm.calculateReinforcementArmies(p));
    }

}