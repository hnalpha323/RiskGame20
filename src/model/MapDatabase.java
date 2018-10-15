

package model;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Muhammad_Hamza_Noor
 * @version 1.0.0
 */
public final class MapDatabase {
	public static HashMap<String, HashMap<String,Territory>> continents = new HashMap<String, HashMap<String,Territory>>();
	public static HashMap<String, Integer> continentValues = new HashMap<String, Integer>();		


	/**
	 * @return false if any adjacent territory is not declared in any continent
	 */
	public static boolean isValidAdjacency(){
		Set<String> continentNames = MapDatabase.continents.keySet();
		for(HashMap<String,Territory> territories : MapDatabase.continents.values()){
			for(Territory territory:territories.values()){
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
	 * resets the DataBase
	 */
	public static void clear() {
		continents.clear();
		continentValues.clear();
	}


}