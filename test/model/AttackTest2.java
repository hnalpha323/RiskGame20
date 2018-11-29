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

        GameDriver gm = new GameDriver(m, 4,"r,r,r", 600);
        gm.start(false);

        PlayerInterface p = gm.nextPlayer();
        AttackPlan ap = p.getTerritoryToAttack();
        assertTrue(ap==null);

    }

}
