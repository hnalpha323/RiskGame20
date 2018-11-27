package model;

import model.GameDriver;
import model.Map;
import model.Interfaces.PlayerInterface;
import org.junit.Assert;
import org.junit.Test;
import exceptions.PlayerException;

/**
 * Tests minimum reinforcement armies
 */
public class MinReinforceTest {

    @Test()
    public void test() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        Assert.assertEquals(3, gm.calculateReinforcementArmies(p));
    }

}
