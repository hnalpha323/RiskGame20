
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author WaleedAhmad
 * @Version 1.0.0
 *
 */
public final class LoadMapUtility {

	
	/**
	 * @param line, is a text line from .map file
	 * @return false if unable to split by '='
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
	 * Adds Territories value to {@link MapDatabase}
	 * @param line
	 * @return false if a Territories belongs to an unknown continent
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
		MapDatabase.continents.get(tmpArray[3].toLowerCase()).put(tmpArray[0].toLowerCase(),
				new Territories(tmpArray[3].trim().toLowerCase(), 
						tmpArray[0].toLowerCase(),
						tmpArray[1]+","+tmpArray[2],
						adjacentTerritories)
				);
		return true;
	}	
}