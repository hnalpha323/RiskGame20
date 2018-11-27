package model.strategy;

import model.AttackPlan;
import model.GameDriver;
import model.Map;
import model.Interfaces.PlayerInterface;
import model.Interfaces.StrategyInterface;
import model.Interfaces.TerritoryInterface;
import model.strategy.Aggressive;
import model.strategy.Benevolent;
import model.strategy.Random;
import org.junit.Test;
import org.junit.Assert;
import exceptions.PlayerException;

/**
 * this class tests all strategies to make sure they are correct
 */
public class StrategyTest {


    /**
     * this will test attack numbers by Defensive strategy
     */
    @Test
    public void Test1()
    {
        StrategyInterface s = new Benevolent();
        int attacks = s.getAttackAttempts();
        Assert.assertEquals(0, attacks);
    }

    /**
     * checks the dice to attack
     */
    @Test
    public void Test2()
    {
        StrategyInterface s = new Benevolent();
        int attacks = s.diceToAttack(null);
        Assert.assertEquals(0, attacks);
    }


    /**
     * checks the dice to defend
     */
    @Test
    public void Test3()
    {
        StrategyInterface s = new Benevolent();
        int attacks = s.diceToDefend(null);
        Assert.assertNotEquals(0, attacks);
    }


    /**
     * test fortification armies
     */
    @Test
    public void Test4()
    {
        StrategyInterface s = new Benevolent();
        int attacks = s.getFortificationArmies(null, null);
        Assert.assertNotEquals(0, attacks);
    }


    /**
     * test fortification territory from
     * @throws PlayerException if number of player not as per game rules
     */
    @Test
    public void Test5() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3, "r,r,r", 500);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        StrategyInterface s = new Benevolent();
        p.setStrategy(s);
        TerritoryInterface from = s.getFortificationFromTerritory(p);
        Assert.assertNotNull(from);
    }


    /**
     * test fortification territory to
     * @throws PlayerException if number of player not as per game rules
     */
    @Test
    public void Test6() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3, "r,r,r", 500);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        StrategyInterface s = new Benevolent();
        p.setStrategy(s);
        TerritoryInterface from = s.getFortificationFromTerritory(p);
        TerritoryInterface to = s.getFortificationToTerritory(p, from);
        Assert.assertNotNull(to);
    }

    /**
     * tests attack plan
     */
    @Test
    public void Test7()
    {
        StrategyInterface s = new Benevolent();
        AttackPlan attack = s.getAttackPlan(null);
        Assert.assertNull(attack);
    }

    /**
     * this will test attack numbers by aggressive strategy
     */
    @Test
    public void Test8()
    {
        StrategyInterface s = new Aggressive();
        int attacks = s.getAttackAttempts();
        Assert.assertEquals(5, attacks);
    }

    /**
     * checks the dice to attack
     */
    @Test
    public void Test9()
    {
        StrategyInterface s = new Aggressive();
        int attacks = s.diceToAttack(null);
        Assert.assertNotEquals(0, attacks);
    }


    /**
     * checks the dice to defend
     */
    @Test
    public void Test10()
    {
        StrategyInterface s = new Aggressive();
        int attacks = s.diceToDefend(null);
        Assert.assertNotEquals(0, attacks);
    }


    /**
     * test fortification territory from aggressive
     * @throws PlayerException if number of player not as per game rules
     */
    @Test
    public void Test11() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3, "r,r,r", 500);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        StrategyInterface s = new Aggressive();
        p.setStrategy(s);
        TerritoryInterface from = s.getFortificationFromTerritory(p);
        Assert.assertNotNull(from);
    }


    /**
     * test fortification territory to
     * @throws PlayerException if number of player not as per game rules
     */
    @Test
    public void Test12() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 3, "r,r,r", 500);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        StrategyInterface s = new Aggressive();
        p.setStrategy(s);
        TerritoryInterface from = s.getFortificationFromTerritory(p);
        TerritoryInterface to = s.getFortificationToTerritory(p, from);
        Assert.assertNotNull(to);
    }


    /**
     * this will test attack numbers by Defensive strategy
     * 
     */
    @Test
    public void Test13()
    {
        StrategyInterface s = new Random();
        int attacks = s.getAttackAttempts();
        Assert.assertNotEquals(0, attacks);
    }

    /**
     * checks the dice to attack
     */
    @Test
    public void Test14()
    {
        StrategyInterface s = new Random();
        int attacks = s.diceToAttack(null);
        Assert.assertNotEquals(0, attacks);
    }


    /**
     * checks the dice to defend
     */
    @Test
    public void Test15()
    {
        StrategyInterface s = new Random();
        int attacks = s.diceToDefend(null);
        Assert.assertNotEquals(0, attacks);
    }


}
