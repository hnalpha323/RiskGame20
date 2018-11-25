package utility;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Test class for Gradient
 * @author Shah Mohammad Mostakim 
 * @version 3.0
 *
 */
public class GradientTest {
	
	/**
	 * Test a randomly generated Color
	 * @author Shah Mohammad Mostakim 
	 */
	@Test
	public void testListOfRandomColor() {
		Gradient g = new Gradient();
		// check if the list of random color is generated or not
		// method getRandomColor returns a random color (Gradient) object
		assertNotNull(g.getRandomColor());
	}

}
