package controller;

import java.util.ArrayList;
import java.util.Arrays;

import model.DataWriter;

/**
 * @author Muhammad_Hamza_Noor
 * @author Meet Patel
 * Controls Write operations on {@link MapDataBase}
 * In order to do 
 * <ul>
 * <li>Add a country</li>
 * <li>Delete a country</li>
 * <li>Delete a continent</li>
 * <li>Add a continent</li>
 * <li>Add adjacent territories</li>
 * </ul>
 * call the methods in this class 
 */
public class WriteController{

	/**
	 * Model wrapper that do's different write operations on Map
	 */
	DataWriter dataWriter;	
	
	/**
	 * Constructor to initializes {@link #dataWriter}  
	 * @param new_dataWriter instance of {@link model.DataWriter}
	 */
	public WriteController(DataWriter new_dataWriter){
		dataWriter = new_dataWriter;
	}
	
	/**
	 * Writes new continent to map
	 * @param continentName is the name of the continent u want to create
	 */
	public void writenewContinent(String continentName){
		dataWriter.createContinent(continentName);
	}

	/**
	 * This method adds and deletes a territory based on following parameters
	 * @param editedadjacentCountries are comma separated adjacent territories given by user
	 * @param continent should be the Continent Name
	 * @param country should be the Country Name
	 * @param continentValue is the continent Value
	 * @param isdeleteContinent is true if a continent is deleted by user
	 * @param isdeleteCountry is true if a country is deleted by user 
	 */
	public void addData(String editedadjacentCountries,String continent,String country,String continentValue,boolean isdeleteContinent,boolean isdeleteCountry){
		
		//As adjacent countries are given as comma separated convert them to ArrayList
		editedadjacentCountries = editedadjacentCountries.replace("[", "").replace("]", "");
		
		//Split them by comma and store it in an ArrayList
		ArrayList<String> new_adjacentContries = null;		
		if(editedadjacentCountries.length() > 2){
			new_adjacentContries = new ArrayList<>(Arrays.asList(editedadjacentCountries.split(",")));
		}else{
			new_adjacentContries = new ArrayList<String>();
		}		 
        
		//if isdeleteContinent is true delete the continent
		if(isdeleteContinent){
			dataWriter.deleteContinent(continent);
		}else if(isdeleteCountry){
			dataWriter.deleteCountry(continent,country);
		}
		else{
			dataWriter.overWriteData(continent,country,continentValue,new_adjacentContries);
		} 
		
	}
	
	
}
