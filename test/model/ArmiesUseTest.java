package model;

import model.GameDriver;
import model.Map;
import org.junit.Assert;
import org.junit.Test;
import exceptions.PlayerException;

/**
 * Players have used all their initial armies before playing
 */
public class ArmiesUseTest {

    @Test()
    public void test() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();
        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);
        Assert.assertEquals(0, gm.nextPlayer().getUnusedArmies());
    }

}
