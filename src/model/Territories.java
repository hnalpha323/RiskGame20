package model;

import model.Interfaces.PlayerInterface;
import model.Interfaces.TerritoryInterface;
import model.contract.IContinent;
import model.Interfaces.ContinentInterface;
import utility.Results;
import utility.MessageEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import controller.LoggerController;

	/**
	 * @author Muhammad_Hamza_Noor
	 * @version 1.0.0
	 */
public class Territories implements TerritoryInterface,Serializable 
{
	private static final long serialVersionUID = -8134828229182086176L;
	private String continentName;
	private String territoryName;
	private String coordinates;
    private ArrayList<String> adjacentTerritories = new ArrayList<String>();    
    private int numberOfArmies;
    private String currentPlayer;
    private ContinentInterface continent;
	
    public Territories(String n_continentName, String n_territoryName,String n_coordinates, ArrayList<String> n_adjacentTerritories) 
    {
		this.continentName = n_continentName;
		this.territoryName = n_territoryName;
		this.coordinates = n_coordinates;
		this.adjacentTerritories = n_adjacentTerritories;
	}

	/**
	 * @return Continent Name
	 */
	public String getContinentName() 
	{
		return continentName;
	}

	/**
	 * @param Continent Name that was set
	 */
	public void setContinentName(String continentName) 
	{
		this.continentName = continentName;
	}

	/**
	 * @return Territory Name
	 */
	public String getTerritoryName() 
	{
		return territoryName;
	}

	/**
	 * @param Territory Name that was set
	 */
	public void setTerritoryName(String territoryName) 
	{
		this.territoryName = territoryName;
	}

	/**
	 * @return Adjacent Territories
	 */
	public ArrayList<String> getAdjacentTerritories() 
	{
		return adjacentTerritories;
	}

	/**
	 * @param List of Adjacent Territories that are needed to be set
	 */
	public void setAdjacentTerritories(ArrayList<String> adjacentTerritories) 
	{
		this.adjacentTerritories = adjacentTerritories;
	}

	/**
	 * @return Number Of Armies in a territory
	 */
	public int getNumberOfArmies() 
	{
		return numberOfArmies;
	}

	/**
	 * @param Number Of Armies in a territory to set
	 */
	public void setNumberOfArmies(int numberOfArmies) 
	{
		this.numberOfArmies = numberOfArmies;
	}

	/**
	 * @return Player name who has the turn
	 */
	public String getCurrentPlayer() 
	{
		return currentPlayer;
	}

	/**
	 * @param Set Player name who has the turn
	 */
	public void setCurrentPlayer(String currentPlayer) 
	{
		this.currentPlayer = currentPlayer;
	}

	public String getCoordinates() 
	{
		return coordinates;
	}

	public void setCoordinates(String coordinates) 
	{
		this.coordinates = coordinates;
	}


    /**
     * To Set the owner of a territory
     * @param player/owner Name
     */
	@Override
    public void setOwner(PlayerInterface player) 
	{
        this.owner = player;
        Log.log(this.territoryName + " is owned by " + player.getName());
    }

    @Override
    public PlayerInterface getOwner() 
    {
        return this.owner;
    }

    private PlayerInterface owner;

    /**
     * @param Territory Name
     * @param Continent Name
     */
    public Territories(String name, String continentName)
    {
        this.territoryName=name;
        this.continentName=continentName;
    }

    /**
     * @return Territory Name
     */
    public String getName()
    {
        return this.territoryName;
    }
    
    /**
	 * gets continents object to which this territory belongs
	 * @return continent
	 */
    @Override
	public ContinentInterface getContinent() 
    {
		return this.continent;
	}

	/**
	 * sets continent
	 * @param c continent value
	 */
	@Override
	public void setContinent(ContinentInterface c) 
	{
		this.continent = c;
	}

    /**
     * @return Present number of armies in the territory
     */
    @Override
    public int getArmies() 
    {
        return this.numberOfArmies;
    }

    /**
     * Add armies to existing armies
     * @param Number of armies that is need to be aded
     */
    @Override
    public void placeArmies(int count) 
    {
        this.numberOfArmies += count;
    }


    /**
     * @param Number or armies that are needed to be removed
     */
    @Override
	public Results removeArmies(int count) 
    {
		Results res = new Results();

		if ((this.getArmies() - count) >= 1)
		{
			this.setNumberOfArmies(this.getArmies() - count);
			res.setOk(true);
			Log.log(String.format("%s Armies Left %s", count, this.getName()));
		}
		else
		{
			Log.log(MessageEnum.ERROR, String.format("%s!, At least 1 army should be in %s", count, this.getName()));
			res.setOk(false);
		}

		return res;
	}
    
    
    @Override
	public Results killArmies(int count) {
		Results res = new Results();

		this.setNumberOfArmies(this.getArmies() - count);
		res.setOk(true);
		Log.log(String.format("%s Armies got Killed in %s", count, this.getName()));

		return res;
	}

    /**
     * @return boolean if the territory has Adjacent territory or not
     */
	@Override
	public boolean hasAdjacencyWith(TerritoryInterface t) 
	{
		if(this.adjacentTerritories.contains(t.getName()))
		{
			return true;
		}
		return false;		
	}
	
	
	/**
	 * @return All Adjacent Territory Objects
	 */
	public ArrayList<TerritoryInterface> getAdjacentTerritoryObjects()
	{
		ArrayList<TerritoryInterface> adjacentTerritoriesObjects = new ArrayList<TerritoryInterface>();
		for(HashMap<String,Territories> territories : MapDatabase.continents.values())
		{
			for(Territories territory:territories.values()){
				if(this.adjacentTerritories.contains(territory.getTerritoryName()))
				{
					adjacentTerritoriesObjects.add(territory);
				}
			}
		}
		return adjacentTerritoriesObjects;		
	}
	
	@Override
	public ArrayList<TerritoryInterface> getAdjacentNeighbours() {
		ArrayList<TerritoryInterface> result = new ArrayList<>();

		for(TerritoryInterface t: this.getAdjacentTerritoryObjects())
		{
			if (t.getOwner() == this.getOwner())
				result.add(t);
		}

		return result;
	}
	
	
   
}