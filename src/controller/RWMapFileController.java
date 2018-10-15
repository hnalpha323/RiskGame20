package controller;

import java.io.File;

import model.LoadMap;
import model.MapDatabase;
import model.MapWriter;


/**
 * @author Muhammad_Hamza_Noor
 * This class controls read and writes on the map
 */
public class RWMapFileController{

	/**
	 * @param file instance of {@link File} points to .map file
	 */
	public boolean loadMap(File file) {
		LoadMap loadMap = new LoadMap(file);
        return loadMap.load();		
	}

	

	/**
	 * @param file instance of {@link File} points to new .map file
	 */
	public void writeMap(File file) {
        MapWriter writeMap = new MapWriter(file);
        writeMap.write(); 
	}



	/**
	 * Checks the case whether adjacent territories are valid 
	 * @return true if map satisfies above case
	 */
	public boolean validateMap() {
		return MapDatabase.isValidAdjacency();		
	}



	/**
	 * Clears the previously loaded data from Map
	 */
	public void clearData() {
		MapDatabase.clear();		
	}	

}
