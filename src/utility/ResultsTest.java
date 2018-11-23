package utility;

import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;
/**
 * 
 * Test class for checking Results 
 * @author Shah Mohammad Mostakim 
 * @version 3.0
 *
 */
public class ResultsTest {
	
	/**
	 * Test the first property of Results
	 * @author Shah Mohammad Mostakim 
	 */
	@Test
	public void testOkay() {
		Results r = new Results();
		r.setOk(true);
		
		boolean expected = true;
		boolean found = r.getOk();
		assertEquals(expected, found);
	}
	
	/**
	 * Test the second property of Results
	 * @author Shah Mohammad Mostakim 
	 */
	@Test 
	public void testMessage() {
		Results r = new Results();
		r.setMessage("New Message");
		
		String expected = "New Message";
		String found = r.getMessage();
		assertEquals(expected, found);
	}

}
