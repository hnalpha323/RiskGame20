

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
				if(territory.getAdjacentTerritories().size() == 0){
					return false;
				}
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


	public static boolean isAnyDiconnectivity(){		
		HashSet<String> allAdjacencies = new HashSet<>();
		HashMap<String,String> waitingForConnection = new HashMap<>();
		
		ArrayList<String> tmp = new ArrayList<>();
		for(String continent : continents.keySet()){
			Set<String> countries = continents.get(continent).keySet();
			for(String territory: countries){

				tmp.clear();
				tmp.addAll(continents.get(continent).get(territory).getAdjacentTerritories());
				
				if(!allAdjacencies.contains(territory)){
					waitingForConnection.put(territory,continent);
				}else{
					waitingForConnection.remove(territory);
				}
			
				for(String s: tmp){
					if(waitingForConnection.containsKey(s)){
						String c = waitingForConnection.get(s);
						
						if(MapDatabase.continents.get(c).get(s).getAdjacentTerritories().size() == 1){			
								 if(MapDatabase.continents.get(c).get(territory).getAdjacentTerritories().size() == 1 ){
									 if(!MapDatabase.continents.get(c).get(s).getAdjacentTerritories().get(0).equals(territory)){
										 waitingForConnection.remove(s);	
									 }
								 }else{
									 waitingForConnection.remove(s);	
								 }
							
						}else{
							waitingForConnection.remove(s);	
						}
						
					}						
				}
				
				allAdjacencies.addAll(tmp);	
			}
			
		}
	
		
		if(waitingForConnection.size() != 0)
			return false;
		else
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