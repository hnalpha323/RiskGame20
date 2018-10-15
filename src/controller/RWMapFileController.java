package controller;

import java.io.File;

import model.LoadMap;
import model.MapDatabase;
import model.MapWriter;


/**
 * This class is used to Load and Save the maps
 * @author Muhammad_Hamza_Noor
 */
public class RWMapFileController{

	/**
	 * @param File object of map to load 
	 */
	public boolean loadMap(File file) {
		LoadMap loadMap = new LoadMap(file);
        return loadMap.load();		
	}

	/**
	 * @param File object of map to Write
	 */
	public void writeMap(File file) {
        MapWriter writeMap = new MapWriter(file);
        writeMap.write(); 
	}


	/**
	 * Validation of Map to check if the neighboring territories are valid or not
	 * @return true if map satisfies above case
	 */
	public boolean validateMap() {
		return MapDatabase.isValidAdjacency();		
	}


	/**
	 * To clear an already loaded map
	 */
	public void clearData() {
		MapDatabase.clear();		
	}	

}
