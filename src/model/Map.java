package model;

import model.Interfaces.ContinentInterface;
import model.Interfaces.MapInterface;
import model.Interfaces.TerritoryInterface;
import util.ActionResponse;

import java.util.ArrayList;

/**
 * @author Muhammad_Hamza_Noor
 * @version 1.0.0
 * represents Map in the game
 */
public class Map implements MapInterface {


    private ArrayList<ContinentInterface> continents = new ArrayList<>();

    /**
     * Constructor
     */
    public Map()
    {
        this.loadData();
    }


    /**
     *
     * @return list of continents in the map
     */
    @Override
    public ArrayList<ContinentInterface> getContinents() {
        return this.continents;
    }


    private void loadData(){
    	this.continents = new ArrayList<>();
    	for(String continent: MapDatabase.continents.keySet()){
    		ContinentInterface c = new Continent(continent);
    		for(Territories Territories: MapDatabase.continents.get(continent).values()){    			
    			c.addTerritories(Territories);
    		}
    		this.continents.add(c);	
    	}
    }

    /**
     * clears the continents from the map
     */
    public void clearData()
    {
        this.continents = new ArrayList<>();
    }

    /**
     * fills the map with the fake data
     */
    public void fakeData()
    {
        this.continents = new ArrayList<>();


        ContinentInterface c1 = new Continent("Asia");
        this.continents.add(c1);
        c1.addTerritories(new Territories("Iran",c1.getName()));
        c1.addTerritories(new Territories("India",c1.getName()));
        c1.addTerritories(new Territories("Mexico",c1.getName()));
        c1.addTerritories(new Territories("Russia",c1.getName()));

        ContinentInterface c2 = new Continent("Africa");
        this.continents.add(c2);
        c2.addTerritories(new Territories("Egypt",c2.getName()));
        c2.addTerritories(new Territories("Kenya",c2.getName()));

        ContinentInterface c3 = new Continent("America");
        this.continents.add(c3);
        c3.addTerritories(new Territories("China", c3.getName()));
        c3.addTerritories(new Territories("Canada", c3.getName()));
        c3.addTerritories(new Territories("Argentina", c3.getName()));

    }
}
