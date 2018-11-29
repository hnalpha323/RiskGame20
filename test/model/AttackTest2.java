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

}
