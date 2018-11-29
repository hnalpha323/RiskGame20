package model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import exceptions.PlayerException;
import model.Interfaces.PlayerInterface;

public class AttackTest2 {

	/**
     * Attack plan will be null if there is no territory to attack
     * @throws PlayerException is not as per game rules
     */
    @Test()
    public void testTerritoryToAttack() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 4,"r,r,r,r", 600);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        AttackPlan ap = p.getTerritoryToAttack();
        assertTrue(ap==null);

    }
    
    /**
     * "attack from" territory should be owned by the attacker 
     * @throws PlayerException is number of players not as per game rules
     */
    @Test()
    public void testAttackFromTerritoryOwner() throws PlayerException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameDriver gm = new GameDriver(m, 4,"r,r,r,r", 400);
        gm.start(false);

        PlayerInterface attacker = gm.nextPlayer();
        PlayerInterface defender = gm.nextPlayer();

        AttackPlan ap = new AttackPlan(attacker.getRandomTerritory(), defender.getRandomTerritory());
        Assert.assertTrue(ap.getFrom().getOwner() != defender);

    }
    
    /**
     * in attack plan, target territory should be owned by defender
     * @throws PlayerException if number of player not as per game rules
     */
    @Test()
    public void testOwnerOfDefendingTerritory() throws PlayerException
    {
        Map map = new Map();
        map.clearData();
        map.fakeData();

        GameDriver GD = new GameDriver(map, 5,"r,r,r,a,b", 500);
        GD.start(false);

        PlayerInterface attacker = GD.nextPlayer();
        PlayerInterface defender = GD.nextPlayer();

        AttackPlan ap = new AttackPlan(attacker.getRandomTerritory(), defender.getRandomTerritory());
        Assert.assertTrue(ap.getTo().getOwner() != attacker);

    }
    
    /**
     * attacker  and defender must be different in attack plan
     * @throws PlayerException is number of player not as per game rules
     */
    @Test()
    public void testValidationOfAttackerDefender() throws PlayerException
    {
        Map map = new Map();
        map.clearData();
        map.fakeData();

        GameDriver GM = new GameDriver(map, 4,"r,r,r,a", 500);
        GM.start(false);

        PlayerInterface attacker = GM.nextPlayer();
        PlayerInterface defender = GM.nextPlayer();

        AttackPlan ap = new AttackPlan(attacker.getRandomTerritory(), defender.getRandomTerritory());
        Assert.assertFalse(attacker == defender);

    }

}
