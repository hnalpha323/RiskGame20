package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class to Save a created or edited map
 * @author WaleedAhmad
 * @version 1.0.0
 */
public class MapWriter {

	File outputPath;
	
	/**
	 * @param File object that contains path of the map file
	 */
	public MapWriter(File file){
		outputPath  = file;
	}	
	
	/**
	 *  Method that creates the map file from the input according the conquest map rules
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
