package model;

import java.util.ArrayList;
import java.util.Observable;

import model.Interfaces.ContinentInterface;
import model.Interfaces.PlayerInterface;
import model.Interfaces.TerritoryInterface;

/**
 * @author Meet_Patel
 * @author Muhammad_Hamza_Noor
 * @version 2.0.0
 */

public class Continents implements ContinentInterface{

	private String Name;
	private ArrayList<TerritoryInterface> territories;
	private int Value = 0;
	
	/**
     * Constructor to set Continent Names
     * @param continent name
     */
    public Continents(String name){
        this.Name = name;
        this.territories = new ArrayList<>();
    }
    
    /**
     * @param continent name
     */
    public void setName(String name){ 
    	this.Name=name; 
   	}
	
    /**
     * To return the continent name
     * @return continent name
     */
    public String getName() {
    	return this.Name;
    }
    
    /**
     * @return To return the continent Value as per the conquest map rules
     */
    @Override
    public int getContinentValue() {
        return this.Value;
    }
    
    /**
     * @param To set the continent Value as per the conquest map rules
     */
    @Override
    public void setContinentValue(int value) {
        this.Value = value;
    }
    
    @Override
    public boolean controlByPlayer(PlayerInterface p) {
        boolean result = true;
        for(TerritoryInterface t:this.getTerritories())
        {
            if(!t.getOwner().equals(p))
            {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public ArrayList<TerritoryInterface> getTerritories() {
         return this.territories;
    }

    @Override
    public void addTerritory(TerritoryInterface t) {
        this.territories.add(t);
    }

    @Override
    public void removeTerritory(TerritoryInterface t)
    {
        int index = this.territories.indexOf(t);
        this.territories.remove(index);
    }
}
