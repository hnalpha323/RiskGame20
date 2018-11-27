package model;

import model.GameDriver;
import model.Map;
import model.Interfaces.ContinentInterface;

import model.Interfaces.PlayerInterface;
import model.Interfaces.TerritoryInterface;
import org.junit.Assert;
import org.junit.Test;
import exceptions.PlayerException;

import java.util.ArrayList;



/**
 * tests if a continent controls by a player
 */
public class ControlPlayerTest {

    @Test()
    public void test1() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3, "r,r,r", 500);
        gm.start(false);
        ArrayList<ContinentInterface> cList = gm.ContinentControlledBy(gm.nextPlayer());
        Assert.assertEquals(0, cList.size());

    }

    @Test()
    public void test2() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);
        PlayerInterface p = gm.nextPlayer();

        for(ContinentInterface c : m.getContinents())
            for(TerritoryInterface t: c.getTerritories())
                p.ownTerritory(t);

        ArrayList<ContinentInterface> cList = gm.ContinentControlledBy(p);
        Assert.assertEquals(3, cList.size());

    }

}
