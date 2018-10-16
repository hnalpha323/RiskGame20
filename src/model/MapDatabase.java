

package model;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Muhammad_Hamza_Noor
 * @version 1.0.0
 */
public final class MapDatabase {
	public static HashMap<String, HashMap<String,Territories>> continents = new HashMap<String, HashMap<String,Territories>>();
	public static HashMap<String, Integer> continentValues = new HashMap<String, Integer>();		


	/**
	 * @return Returns Nodes that are independent or not connected
	 */
	public static boolean isValidAdjacency(){
		Set<String> continentNames = MapDatabase.continents.keySet();
		for(HashMap<String,Territories> territories : MapDatabase.continents.values()){
			for(Territories territory:territories.values()){
			     for(String s : territory.getAdjacentTerritories()){
			    	 boolean foundTerritory = false;
			    	 for(String continent: continentNames){
			    	    if(MapDatabase.continents.get(continent).containsKey(s))
			    	    	foundTerritory = true;
			    	 }
			    	 if(!foundTerritory) return false;
			     }			    	 
			}
			
		}
		
		return true;
	}


	/**
	 * Clear Data
	 */
	public static void clear() {
		continents.clear();
		continentValues.clear();
	}


}