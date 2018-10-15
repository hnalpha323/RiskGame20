/**
 * @author WaleedAhmad
 * @version 1.0.0 
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Write a new map to specified #outputPath
 */
public class MapWriter {

	File outputPath;
	
	/**
	 * @param file takes file path of destination
	 */
	public MapWriter(File file){
		outputPath  = file;
	}
	
	
	/**
	 *  write a new map for #outputPath from MapDataBase
	 */
	public void write(){		
		try {
			FileWriter fw = new FileWriter(outputPath);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("[Continents]");
			bw.newLine();
			for(String key : MapDatabase.continentValues.keySet()){
				bw.write(key+"="+MapDatabase.continentValues.get(key));
				bw.newLine();
			}
			bw.write("[Territories]");
			bw.newLine();
			for(String key : MapDatabase.continents.keySet()){
                HashMap<String,Territories> territories = MapDatabase.continents.get(key);
                for(Territories t:territories.values()){
                	String tmpStorage = "";
                	for(String s:t.getAdjacentTerritories()){
                		tmpStorage +=","+s; 
                	}
                	bw.write(t.getTerritoryName()+","+t.getCoordinates()+","+t.getContinentName()+tmpStorage);
    			    bw.newLine();
    			}
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
