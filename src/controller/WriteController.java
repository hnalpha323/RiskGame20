package controller;

import java.util.ArrayList;
import java.util.Arrays;
import model.MapDatabase;
import model.DataWriter;

/**
 * Controller that handles Saving the maps
 * 
 * @author Muhammad_Hamza_Noor
 * @author WaleedAhmad
 * @author Meet_Patel
 * @author Shah Mohammad Mostakim
 * @version 2.0.0
 */
public class WriteController
{

	DataWriter dataWriter;
	
	
	/**
	 * @param DataWriter Object 
	 */
	public WriteController(DataWriter new_dataWriter)
	{
		dataWriter = new_dataWriter;
	}
	
	/**
	 * To create new continent in a map
	 * @param Continent Name
	 */
	public void writenewContinent(String continentName)
	{
		dataWriter.createContinent(continentName);
	}

	/**
	 * @param Neighboring territories given by user
	 * @param Continent Name
	 * @param Country Name
	 * @param Continent Value
	 */
	public void addData(String editedadjacentCountries,String continent,String country,String continentValue,boolean isdeleteContinent,boolean isdeleteCountry){
		
		editedadjacentCountries = editedadjacentCountries.replace("[", "").replace("]", "");
		ArrayList<String> new_adjacentContries = null;
        
		
		if(editedadjacentCountries.length() > 2){
			new_adjacentContries = new ArrayList<>(Arrays.asList(editedadjacentCountries.split(",")));
		}else{
			new_adjacentContries = new ArrayList<String>();
		}
		
		if(isdeleteContinent)
		{
			dataWriter.deleteContinent(continent);
		}
        else if(isdeleteCountry)
		{
			dataWriter.deleteCountry(continent,country);
		}
		else
		{
			dataWriter.overWriteData(continent,country,continentValue,new_adjacentContries);
		} 
		
	}
	
	
	
}
