/**
 * @version 1.0.0
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author getWaleed
 *
 */
public class DataReader {

	/**
	 * @param continent
	 * @return All territories which are in a continent
	 */
	public HashMap<String, Territories> getContries(String continent) {
		return MapDatabase.continents.get(continent);
	}

	/**
	 * @param continent
	 * @param country
	 * @return
	 */
	
	public ArrayList<String> getAdjacentTerritories(String continent, String country) {
		HashMap<String, Territories> territories = getContries(continent);
		for(String teritory_name:territories.keySet()){
			   if(teritory_name.equals(country))
				   return territories.get(country).getAdjacentTerritories();
	     }
		return new ArrayList<String>();
        
	}


		/**
		 * @param continentName
		 * @return true on condition if continent exists
		 */
		public boolean hasContinent(String continentName){
			return MapDatabase.continentValues.containsKey(continentName);		
		}

		/**
		 * @param continent
		 * @return
		 */
		public ArrayList<String> getTerritoriesNames(String continent) {
			return new ArrayList<String>(MapDatabase.continents.get(continent).keySet());
		}

		/**
		 * @param continentName
		 * @return
		 */
		public int getContinentValue(String continentName) {
			return MapDatabase.continentValues.get(continentName);
		}


	
	
}
