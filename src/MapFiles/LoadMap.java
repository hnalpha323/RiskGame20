package MapFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.MapDatabase;

public class LoadMap {
    
	private File mapFileLocation = null;
    private Scanner txtScanner = null;
    
    
	/**
	 * @author WaleedAhmad
	 * @Version 1.0.0
	 * 
	 * @param File Object constructor that contains the map to be loaded
	 */
	public LoadMap(File file){
		mapFileLocation = file;
	}
		
	/**
	 * @param Gets the map data
	 * @param Tells what that Data is
	 * @return boolean depending on if the map is valid or not
	 */
	private boolean loadMapToModel(String data,String context){
		boolean isValidMap = true;
		switch(context){
			case "continents":
				isValidMap = LoadMapUtility.addContinet(data);
			    break;
			case "territories":
				isValidMap = LoadMapUtility.addTerritories(data);
			default:
				break;
		}
		
		if(!isValidMap){
		   	return false;
		}
		return true;
	}
	
	/**
	 * @return Checks if the adjacent territories is valid or not
	 */
	public boolean load(){
		
	try {
	txtScanner = new Scanner(new FileInputStream(mapFileLocation));
	} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
		
		String currentLine = null;
		String beforeContext = "none";
		
		while(txtScanner.hasNextLine()){
			currentLine = txtScanner.nextLine().trim();
			if(currentLine.equalsIgnoreCase("[map]")){
				beforeContext = "map";	
			}else if(currentLine.equalsIgnoreCase("[continents]")){
				beforeContext = "continents";
			} else if(currentLine.equalsIgnoreCase("[territories]")){
				beforeContext = "territories";
			}else if(currentLine.length() != 0){
				if(!loadMapToModel(currentLine, beforeContext))
					return false;					
			}else{
			   	
			}
		}
		
		return MapDatabase.isValidAdjacency() && MapDatabase.isAnyDiconnectivity();
	}
	
}