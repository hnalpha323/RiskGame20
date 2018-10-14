package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Muhammad_Hamza_Noor
 * @version 1.0.0
 */
public class DataWriter {

	/**
	 * @param continentName
	 */
	public void createContinent(String continentName) {
		MapDataBase.continents.put(continentName, new HashMap<String,Territory>());
	}

	/**
	 * Used to delete continent
	 * @param continent
	 */
	public void deleteContinent(String continent) {
		Set<String> contriesContinenthas = MapDatabase.continents.get(continent).keySet();
		MapDatabase.continentValues.remove(continent);
		for(HashMap<String,Territory> contries  : MapDatabase.continents.values()){
		   	for(Territory t:contries.values()){
		   		for(String s:contriesContinenthas){
		   			if(t.getAdjacentTerritories().contains(s)){
			   			t.getAdjacentTerritories().remove(s); 
			   		}
		   		}
		   	}
		}
		MapDatabase.continents.remove(continent);		
	}

	/**
	 * @param continent
	 * @param country
	 */
	public void deleteCountry(String continent, String country) {
		for(HashMap<String,Territory> contries  : MapDatabase.continents.values()){
		   	for(Territory t:contries.values()){
		   			if(t.getAdjacentTerritories().contains(country)){
			   			t.getAdjacentTerritories().remove(country); 
		   		}
		   	}
		}
		MapDatabase.continents.get(continent).remove(country);		
	}

	/**
	 * @param continent
	 * @param country
	 * @param continentValue
	 * @param new_adjacentContries
	 */
	public void overWriteData(String continent, String country, String continentValue,
			ArrayList<String> new_adjacentContries) {
		if(!MapDatabase.continents.containsKey(continent)){
			MapDatabase.continents.put(continent, new HashMap<String,Territory>());
			if(!country.isEmpty() && !country.equals("Countries")){
				Territory territory =  new Territory(continent, country, "x,y", new_adjacentContries);
				territory.setAdjacentTerritories(new_adjacentContries);
				MapDatabase.continents.get(continent).put(country, territory);
			}
		} else if(!MapDatabase.continents.get(continent).containsKey(country)){
			Territory territory =  new Territory(continent, country, "x,y", new_adjacentContries);
			territory.setAdjacentTerritories(new_adjacentContries);
			MapDatabase.continents.get(continent).put(country, territory);
		}else{
		    MapDatabase.continents.get(continent).get(country).setAdjacentTerritories(new_adjacentContries);
		}
		MapDatabase.continentValues.put(continent,Integer.parseInt(continentValue));
	}

}
