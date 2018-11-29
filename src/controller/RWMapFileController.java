package controller;

import java.io.File;

import mapFiles.LoadMap;
import mapFiles.MapWriter;
import model.MapDatabase;
import model.ValidMap;

/**
 * @author Muhammad_Hamza_Noor
 * @author Meet Patel
 * To Load and Save a .map file into MapDataBase
 * A view should call this class methods to load, save and clear a .map file
 */
public class RWMapFileController
{

	/**
	 * @param File which has path to .map file
	 * @return True if .map file is valid else false
	 * If a map is disconnected it return false
	 */
	public boolean loadMap(File file) 
	{
		LoadMap loadMap = new LoadMap(file);
        boolean isValid = loadMap.load();
		return isValid;		
	}

	/**
	 * This method writes the edited map to the location passed
	 * @param file instance of {@link File} points to save destination
	 */
	public void writeMap(File file) 
	{
        MapWriter writeMap = new MapWriter(file);
        writeMap.write(); 
	}

	/**
	 * Clears the previously loaded data from Map
	 */
	public void clearData() 
	{
		MapDatabase.clear();		
	}

	/**
	 * Checks the case whether adjacent territories are valid 
	 * @return True if map satisfies above case
	 */
	public boolean validateMap() 
	{
		return ValidMap.isValidAdjacency();		
	}


}
