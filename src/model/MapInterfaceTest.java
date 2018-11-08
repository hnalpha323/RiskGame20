package model;

import model.Interfaces.MapInterface;
import org.junit.Assert;
import org.junit.Test;
import exceptions.PlayerException;

/**
 * @author Muhammad_Hamza_Noor
 * Players have used all their initial armies before playing
 */
public class MapInterfaceTest {

    @Test()
    public void InitialArmiesUse() throws PlayerException
    {
        MapInterface m = new Map();
        m.clearData();
        m.fakeData();
        GameDriver gm = new GameDriver(m, 3);
        gm.initGame();
        Assert.assertEquals(0, gm.nextPlayer().getUnusedArmies());
    }

}