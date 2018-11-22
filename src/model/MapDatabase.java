/**
 * 
 */
package model;

import java.util.HashMap;

/**
 * This class holds the all data related to the Map
 * Whenever a .map file is read it is stored in this class memebers
 */
public final class MapDatabase {
	
	/**
	 * {@link #continents} contains all contents 
	 * <tt>The fist key is Continent name</tt>
	 * <tt>The second key is Country name</tt>
	 */
	public static HashMap<String, HashMap<String,Territories>> continents = new HashMap<String, HashMap<String,Territories>>();
	
	/**
	 * {@link #continentValues} contains the values associated to continents
	 */
	public static HashMap<String, Integer> continentValues = new HashMap<String, Integer>();		




	/**
	 * resets the DataBase
	 */
	public static void clear() {
		continents.clear();
		continentValues.clear();
	}


}
