package mapFiles;

import java.util.ArrayList;
import java.util.HashMap;

import model.MapDatabase;
import model.Territories;

/**
 * @author WaleedAhmad
 * @Version 1.0.0
 */
public final class LoadMapUtility {

	
	/**
	 * @param Adds Continent Data From Map file
	 */
	public static boolean addContinet(String line){
	   String[] tmpArray = line.split("=");
	   if(tmpArray.length != 2){
		   return false;
	   }
	   MapDatabase.continents.put(tmpArray[0].toLowerCase(), new HashMap<String,Territories>());
	   MapDatabase.continentValues.put(tmpArray[0].toLowerCase(), Integer.parseInt(tmpArray[1]));
	   return true;
	}
	
	
	
	/**
	 * Adds Territories values
	 * @param Data From Map file
	 * @return  checks if territories belong to continent and return false if not
	 */
	public static boolean addTerritories(String line){			
		String[] tmpArray = line.split(",");		
		if(!MapDatabase.continentValues.containsKey(tmpArray[3].toLowerCase())){
			return false;
		}
		ArrayList<String> adjacentTerritories = new ArrayList<String>();
		for(int i=4;i<tmpArray.length;i++){
			adjacentTerritories.add(tmpArray[i].toLowerCase().trim());
		}
		
		if(adjacentTerritories.size() == 0){
			return false;
		}
		
		MapDatabase.continents.get(tmpArray[3].toLowerCase()).put(tmpArray[0].toLowerCase(),
				new Territories(tmpArray[3].trim().toLowerCase(), 
						tmpArray[0].toLowerCase(),
						tmpArray[1]+","+tmpArray[2],
						adjacentTerritories)
				);
		return true;
	}	
}