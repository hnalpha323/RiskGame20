package model;

import model.Interfaces.ContinentInterface;
import model.Interfaces.MapInterface;

import java.util.ArrayList;

/**
 * @author Muhammad_Hamza_Noor
 * @version 1.0.0
 * 
 */
public class Map implements MapInterface {


    private ArrayList<ContinentInterface> continents = new ArrayList<>();

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


        ContinentInterface c1 = new Continents("EU");
        this.continents.add(c1);
        c1.addTerritory(new Territories("Germany",c1.getName()));
        c1.addTerritory(new Territories("France",c1.getName()));
        c1.addTerritory(new Territories("Italy",c1.getName()));
        c1.addTerritory(new Territories("Croatia",c1.getName()));

        ContinentInterface c2 = new Continents("NA");
        this.continents.add(c2);
        c2.addTerritory(new Territories("Canada",c2.getName()));
        c2.addTerritory(new Territories("USA",c2.getName()));

        ContinentInterface c3 = new Continents("Asia");
        this.continents.add(c3);
        c3.addTerritory(new Territories("Japan", c3.getName()));
        c3.addTerritory(new Territories("Russia", c3.getName()));
        c3.addTerritory(new Territories("China", c3.getName()));

    }
}
