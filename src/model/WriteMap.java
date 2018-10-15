package model;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Write a new map to specified #outputPath
 * @author Muhammad_Hamza_Noor
 */
public class WriteMap {

	File outputPath;
	
	/**
	 * @param file takes file path of destination
	 */
	public WriteMap(File file){
		outputPath  = file;
	}
	
	
	/**
	 *  write a new map at #outputPath from MapDataBase
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
                HashMap<String,Territory> territories = MapDatabase.continents.get(key);
                for(Territory t:territories.values()){
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
