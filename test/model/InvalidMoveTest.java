package model;

import model.GameDriver;
import model.Map;
import model.Interfaces.PlayerInterface;
import model.Interfaces.TerritoryInterface;
import org.junit.Assert;
import org.junit.Test;
import utility.Results;
import exceptions.PlayerException;


/**
 * Tests moving all armies form a territory
 */
public class InvalidMoveTest {

    @Test()
    public void test() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        TerritoryInterface from = p.getRandomTerritory();
        TerritoryInterface to = p.getRandomTerritory();
        Results result = p.moveArmies(from, to, from.getArmies());

        Assert.assertFalse(result.getOk());

    }

}
