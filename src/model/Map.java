package model;

import model.Interfaces.ContinentInterface;
import model.Interfaces.MapInterface;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Muhammad_Hamza_Noor
 * @version 1.0.0
 * 
 */
public class Map implements MapInterface,Serializable {

	/**
	 * serialVersionUID is used during deserialization to verify that the sender and receiver 
	 * of a serialized object have loaded classes for that object that are compatible with respect to 
	 * serialization. If the receiver has loaded a class for the object that has a different {@link #serialVersionUID}
	 */
	private static final long serialVersionUID = 5631589509991237355L;
    private ArrayList<ContinentInterface> continents = new ArrayList<>();
    
    private String name = "Map";
    
    private int totalnumberOfTerritories = 0;

    public Map()
    {
        this.loadData();
    }


    /**
     * @return List of all the continents in the map
     */
    @Override
    public ArrayList<ContinentInterface> getContinents() {
        return this.continents;
    }


    private void loadData(){
    	this.continents = new ArrayList<>();
    	setTotalnumberOfTerritories(0);
    	for(String continent: MapDatabase.continents.keySet()){
    		ContinentInterface c = new Continents(continent);
    		for(Territories Territories: MapDatabase.continents.get(continent).values()){    			
    			c.addTerritory(Territories);
    		}
    		this.continents.add(c);	
    	}
    }

    /**
     * clears all the continent data
     */
    public void clearData()
    {
        this.continents = new ArrayList<>();
    }

    /**
     * Fill map with temp data
     */
    public void fakeData()
    {
        this.continents = new ArrayList<>();


        ContinentInterface c1 = new Continents("Asia");
        this.continents.add(c1);
        c1.addTerritory(new Territories("Iran",c1.getName()));
        c1.addTerritory(new Territories("India",c1.getName()));
        c1.addTerritory(new Territories("Mexico",c1.getName()));
        c1.addTerritory(new Territories("Russia",c1.getName()));

        ContinentInterface c2 = new Continents("Africa");
        this.continents.add(c2);
        c2.addTerritory(new Territories("Egypt",c2.getName()));
        c2.addTerritory(new Territories("Kenya",c2.getName()));

        ContinentInterface c3 = new Continents("America");
        this.continents.add(c3);
        c3.addTerritory(new Territories("China", c3.getName()));
        c3.addTerritory(new Territories("Canada", c3.getName()));
        c3.addTerritory(new Territories("Argentina", c3.getName()));

    }
    
    /**
     * Sets name of the map
     * @param Map Name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets name of the map
     * @return Map Name
     */
    @Override
    public String getName() {
        return this.name;
    }

	/**
	 * @return Total Number Of Territories
	 */
	public int getTotalnumberOfTerritories() {
		return totalnumberOfTerritories;
	}

	public void setTotalnumberOfTerritories(int totalnumberOfTerritories) {
		this.totalnumberOfTerritories = totalnumberOfTerritories;
	}
}