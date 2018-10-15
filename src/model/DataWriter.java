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
	* @param Continent Name
	*/
	public void createContinent(String continentName) {
		MapDatabase.continents.put(continentName, new HashMap<String,Territories>());
	}

	/**
	 * To delete continents
	 * @param Continent Name that needs to be deleted
	 */
	public void deleteContinent(String continent) {
		Set<String> contriesContinenthas = MapDatabase.continents.get(continent).keySet();
		MapDatabase.continentValues.remove(continent);
		for(HashMap<String,Territories> contries  : MapDatabase.continents.values()){
		   	for(Territories t:contries.values()){
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
	 * @param Continent Name
	 * @param Country Name
	 */
	public void deleteCountry(String continent, String country) {
		for(HashMap<String,Territories> contries  : MapDatabase.continents.values()){
		   	for(Territories t:contries.values()){
		   			if(t.getAdjacentTerritories().contains(country)){
			   			t.getAdjacentTerritories().remove(country); 
		   		}
		   	}
		}
		MapDatabase.continents.get(continent).remove(country);		
	}

	/**
	 * @param Continent Name
	 * @param Country Name
	 * @param ContinentValue Name
	 * @param Adjacent Countries
	 */
	public void overWriteData(String continent, String country, String continentValue,
			ArrayList<String> new_adjacentContries) {
		if(!MapDatabase.continents.containsKey(continent)){
			MapDatabase.continents.put(continent, new HashMap<String,Territories>());
			if(!country.isEmpty() && !country.equals("Countries")){
				Territories territory =  new Territories(continent, country, "x,y", new_adjacentContries);
				territory.setAdjacentTerritories(new_adjacentContries);
				MapDatabase.continents.get(continent).put(country, territory);
			}
		} else if(!MapDatabase.continents.get(continent).containsKey(country)){
			Territories territory =  new Territories(continent, country, "x,y", new_adjacentContries);
			territory.setAdjacentTerritories(new_adjacentContries);
			MapDatabase.continents.get(continent).put(country, territory);
		}else{
		    MapDatabase.continents.get(continent).get(country).setAdjacentTerritories(new_adjacentContries);
		}
		MapDatabase.continentValues.put(continent,Integer.parseInt(continentValue));
	}

}
