package model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.AttackPlan;
import model.GameDriver;
import model.Map;
import model.Interfaces.MapInterface;
import model.Interfaces.PlayerInterface;
import model.Interfaces.TerritoryInterface;
import org.junit.Assert;
import org.junit.Test;
import exceptions.PlayerException;

/**
 * testing attack logic
 */
public class AttackTest {

    /**
     * There should be no territory to attack
     * @throws PlayerException is not as per game rules
     */
    @Test()
    public void test1() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        AttackPlan ap = p.getTerritoryToAttack();
        Assert.assertNull(ap);

    }

    /**
     * checks if attack from territory is owned by the attacker
     * @throws PlayerException is number of players not as per game rules
     */
    @Test()
    public void test2() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);

        PlayerInterface p1 = gm.nextPlayer();
        PlayerInterface p2 = gm.nextPlayer();

        AttackPlan ap = new AttackPlan(p1.getRandomTerritory(), p2.getRandomTerritory());
        Assert.assertTrue(ap.getFrom().getOwner() == p1);

    }


    /**
     * checks if attack to territory is owned by the defender
     * @throws PlayerException if number of player not as per game rules
     */
    @Test()
    public void test3() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);

        PlayerInterface p1 = gm.nextPlayer();
        PlayerInterface p2 = gm.nextPlayer();

        AttackPlan ap = new AttackPlan(p1.getRandomTerritory(), p2.getRandomTerritory());
        Assert.assertTrue(ap.getTo().getOwner() == p2);

    }

    /**
     * checks if attacker and defender are not the same
     * @throws PlayerException is number of player not as per game rules
     */
    @Test()
    public void test4() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3,"r,r,r", 500);
        gm.start(false);

        PlayerInterface p1 = gm.nextPlayer();
        PlayerInterface p2 = gm.nextPlayer();

        AttackPlan ap = new AttackPlan(p1.getRandomTerritory(), p2.getRandomTerritory());
        Assert.assertTrue(p1 != p2);

    }