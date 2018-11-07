package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author WaleedAhmad
 * @version 1.0.0
 */
public class DataReader {

	/**
	 * @param Continent Name
	 * @return All territories which are in a continent
	 */
	public HashMap<String, Territories> getContries(String continent) 
	{
		return MapDatabase.continents.get(continent);
	}

	/**
	 * @param Continent Name
	 * @param Country Name
	 * @return Adjacent Territories
	 */
	
	public ArrayList<String> getAdjacentTerritories(String continent, String country) 
	{
		HashMap<String, Territories> territories = getContries(continent);
		for(String teritory_name:territories.keySet()){
			   if(teritory_name.equals(country))
				   return territories.get(country).getAdjacentTerritories();
	     }
		return new ArrayList<String>();
        
	}

	/**
	* @param Continent Name
	* @return Boolean to see if the Neighboring territories exists or not
	*/
	
	public boolean hasContinent(String continentName)
	{
		return MapDatabase.continentValues.containsKey(continentName);		
	}

	/**
	* @param Continent Name
	*/
	
	public ArrayList<String> getTerritoriesNames(String continent) 
	{
		return new ArrayList<String>(MapDatabase.continents.get(continent).keySet());
	}

	/**
	* @param Continent Name
	* @return Continent Values
	*/
	public int getContinentValue(String continentName) 
	{
		return MapDatabase.continentValues.get(continentName);
	}
	
	public java.util.Set<String> getContinents() {			
		return MapDatabase.continentValues.keySet();
	

		
		/**
		 * @return
		 */
	
	}

}