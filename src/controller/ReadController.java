package controller;

import java.util.ArrayList;

import model.DataReader;

/**
 * Reads the data of map file
 * @author Muhammad_Hamza_Noor
 * @author Meet_Patel
 * @author WaleedAhmad
 * @version 1.0.0
 */
public class ReadController{

	public DataReader dataReader;
	
	/**
	 * @param DataReader object of package model.DataReader
	 */
	public ReadController(DataReader new_dataReader){
	    dataReader = new_dataReader;	
	}
	
	/**
	 * @param Continent Name
	 * @param Country Name
	 * @return List of neighboring nodes of that Territory
	 */
	public ArrayList<String> getAdjacentTerritories(String continent,String country){		
		if(dataReader.hasContinent(continent)){
			return dataReader.getAdjacentTerritories(continent,country);			
		}else{			
			return new ArrayList<String>();
		}
	}
	

	/**
	 * @param Continent Name
	 * @return List of territories in that continent
	 */
	public ArrayList<String> getTerritoriesNames(String continent) {
		if(!dataReader.hasContinent(continent)){
			return new ArrayList<String>();
		}else{
			return dataReader.getTerritoriesNames(continent);
		}
	}



	/**
	 * @param Continent Name
	 * @return ContinentValue
	 */
	public int getContinentValue(String continentName) {
		if(dataReader.hasContinent(continentName))
			return dataReader.getContinentValue(continentName);
		else
			return 0;
	}
	
	
}
