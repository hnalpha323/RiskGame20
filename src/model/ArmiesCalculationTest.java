package model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import exceptions.PlayerException;
import model.Interfaces.MapInterface;

/**
 * @author Shah_Mohammad_Mostakim
 * Tests number of initial armies
 */

public class ArmiesCalculationTest {
	
	public void test() throws PlayerException
    {
        MapInterface m = new Map();
        GameDriver gm = new GameDriver(3);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(40, expected_armies);
    }

}
