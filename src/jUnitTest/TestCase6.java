package jUnitTest;


import model.GameDriver;
import model.Map;
import model.Interfaces.MapInterface;
import model.Interfaces.PlayerInterface;
import model.Interfaces.TerritoryInterface;
import org.junit.Assert;
import org.junit.Test;
import utility.Results;
import exceptions.PlayerException;


/**
 * @author Muhammad_Hamza_Noor
 * Tests moving all armies form a territory
 */
public class TestCase6 {

    @Test()
    public void test() throws PlayerException
    {
        MapInterface m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3);
        gm.initGame();

        PlayerInterface p = gm.nextPlayer();
        TerritoryInterface from = p.getRandomTerritory();
        TerritoryInterface to = p.getRandomTerritory();
        Results result = p.moveArmies(from, to, from.getArmies());

        Assert.assertFalse(result.getOk());

    }

}
