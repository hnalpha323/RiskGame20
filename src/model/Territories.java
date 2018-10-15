package model;

import model.Interfaces.PlayerInterface;
import model.Interfaces.TerritoryInterface;
import utility.Results;
import utility.MessageEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import controller.LoggerController;

	/**
	 * 
	 * @author Muhammad_Hamza_Noor
	 * @version 1.0.0
	 *
	 */
public class Territories implements TerritoryInterface {
    private String continentName;
	private String territoryName;
	private String coordinates;
    private ArrayList<String> adjacentTerritories = new ArrayList<String>();    
    private int numberOfArmies;
    private String currentPlayer;
	
    public Territories(String n_continentName, String n_territoryName,String n_coordinates, ArrayList<String> n_adjacentTerritories) {
		this.continentName = n_continentName;
		this.territoryName = n_territoryName;
		this.coordinates = n_coordinates;
		this.adjacentTerritories = n_adjacentTerritories;
	}

	/**
	 * returns name of the continent
	 * @return the continentName
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * set name of the continent
	 * @param continentName the continentName to set
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * returns name of the territory
	 * @return the territoryName
	 */
	public String getTerritoryName() {
		return territoryName;
	}

	/**
     * sets name of the territory
	 * @param territoryName the territoryName to set
	 */
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	/**
	 * @return the adjacentTerritories
	 */
	public ArrayList<String> getAdjacentTerritories() {
		return adjacentTerritories;
	}

	/**
	 * @param adjacentTerritories the adjacentTerritories to set
	 */
	public void setAdjacentTerritories(ArrayList<String> adjacentTerritories) {
		this.adjacentTerritories = adjacentTerritories;
	}

	/**
	 * @return the numberOfArmies territory has
	 */
	public int getNumberOfArmies() {
		return numberOfArmies;
	}

	/**
	 * @param numberOfArmies the numberOfArmies to set
	 */
	public void setNumberOfArmies(int numberOfArmies) {
		this.numberOfArmies = numberOfArmies;
	}

	/**
	 * @return the currentPlayer
	 */
	public String getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * @return the coordinates
	 */
	public String getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}


    /**
     * sets the owner of the territory
     * @param player new owner
     */
	@Override
    public void setOwner(PlayerInterface player) {
        this.owner = player;
        LoggerController.log(this.territoryName + " is owned by " + player.getName());
    }

    /**
     * sets owner of the territory
     * @return owner
     */
    @Override
    public PlayerInterface getOwner() {
        return this.owner;
    }

    
    private PlayerInterface owner;

    /**
     * Constructor
     * @param name name of territory
     * @param continentName continent name
     */
    public Territories(String name, String continentName)
    {
        this.territoryName=name;
        this.continentName=continentName;
    }

    /**
     * @return name of the territory
     */
    public String getName()
    {
        return this.territoryName;
    }

    /**
     * @return number of armies in the territory
     */
    @Override
    public int getArmies() {
        return this.numberOfArmies;
    }

    /**
     * adds given number of armies to the current armies
     * @param count number of armies to add
     */
    @Override
    public void placeArmies(int count) {
        this.numberOfArmies += count;
    }


    /**
     * Checks if there are at least one army in the territory then
     * removes armies from the territory
     * @param count number or armies to remove
     * @return remove process result
     */
    @Override
	public Results removeArmies(int count) {
		Results res = new Results();

		if ((this.getArmies() - count) >= 1)
		{
			this.setNumberOfArmies(this.getArmies() - count);
			res.setOk(true);
			LoggerController.log(String.format("%s armies left %s", count, this.getName()));
		}
		else
		{
			LoggerController.log(MessageEnum.ERROT, String.format("%s!, At least 1 army should be in %s", count, this.getName()));
			res.setOk(false);
		}

		return res;
	}

    /**
     * checks if this territory has Adjacency with the given territory
     * @param t second territory to check
     * @return if there is or not
     */
	@Override
	public boolean hasAdjacencyWith(TerritoryInterface t) {
		if(this.adjacentTerritories.contains(t.getName())){
			return true;
		}
		return false;		
	}
	
	
	/**
	 * @return all adjacent territories Objects
	 */
	public ArrayList<TerritoryInterface> getAdjacentTerritoryObjects(){
		ArrayList<TerritoryInterface> adjacentTerritoriesObjects = new ArrayList<TerritoryInterface>();
		for(HashMap<String,Territories> territories : MapDatabase.continents.values()){
			for(Territories territory:territories.values()){
				if(this.adjacentTerritories.contains(territory.getTerritoryName())){
					adjacentTerritoriesObjects.add(territory);
				}
			}
		}
		return adjacentTerritoriesObjects;		
	}
   
}