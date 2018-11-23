package model;

import java.util.HashMap;

/**
 * Stores Map Related Data
 * @author Meet Patel
 * @version 3.0.0
 */
public final class MapDatabase {
	
	/**
	 * The fist key is Continent name
	 * The second key is Country name
	 */
	public static HashMap<String, HashMap<String,Territories>> continents = new HashMap<String, HashMap<String,Territories>>();
	/**
	 * Contains continents values
	 */
	public static HashMap<String, Integer> continentValues = new HashMap<String, Integer>();		

	/**
	 * resets the Database
	 */
	public static void clear() {
		continents.clear();
		continentValues.clear();
	}


}
