package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import model.Continents;

/**
 * This class tests the functionalities of Continents 
 * @author Shah_Mohammad_Mostakim
 * @version 2.0.0
 */

public class TestContinents {

	/**
	 * Test for creating continent object with required properties 
	 * @author Shah_Mohammad_Mostakim
	 * @version 2.0.0
	 */
	
	@Test
	public void testContinentName() {
		
		Continents c = new Continents("");
		c.setName("Africa");
		c.setContinentValue(100);
		
		String nameExpected = "Africa";
		String nameFound = c.getName();
		
		assertEquals(nameExpected, nameFound);
		
	}
	
	@Test
	public void testContinentValue() {
		
		Continents c = new Continents("");
		c.setName("Africa");
		c.setContinentValue(100);
			
		int continentValueExpected = 100;
		int continentValueFound = c.getContinentValue();
	
		assertEquals(continentValueExpected, continentValueFound);
	}
	
}
