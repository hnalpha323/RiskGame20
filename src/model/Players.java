package model;


//import com.sun.javafx.binding.StringFormatter;

import controller.LoggerController;
import model.Interfaces.TerritoryInterface;
import model.Interfaces.PlayerInterface;
import utility.Results;
import utility.Gradient;
import utility.MessageEnum;
//import utility.exception.NoSufficientArmiesExption;

import java.util.ArrayList;

/**
 * This is Players class
 *
 * @author WaleedAhmad
 * @version 1.0.0
 */
public class Players implements PlayerInterface {


    private String name;
    private Gradient color;
    private int unusedArmies = 0;
    private int usedArmies = 0;
    private ArrayList<TerritoryInterface> territories;

    /**
     * @param Player Name
     * @param Color of that player
     */
    public Players(String name, Gradient color){
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
    }

    /**
     *
     * @return Player Name
     */
    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void setName(String newName){
        this.name = newName;
    }

    @Override
    public void setUnusedArmies(int armies) { 
    	this.unusedArmies = armies; 
    }

    @Override
    public int getUnusedArmies(){
    	return this.unusedArmies; 
    }

    @Override
    public void setUsedArmies(int armies) { 
    	this.usedArmies = armies; 
    }

    @Override
    public int getUsedArmies(){ 
    	return this.usedArmies; 
    }

    public void setColor(Gradient color){ 
    	this.color = color; 
    }

    public Gradient getColor() { 
    	return this.color; 
    }

    /**
     * @param Territories owned by player
     * @return Player name and his territories
     */
    @Override
    public Results ownTerritory(TerritoryInterface Territories) {
        Territories.setOwner(this);
        this.placeArmy(1, Territories);
        this.territories.add(Territories);
        return new Results(true, String.format("%s has %s", this.getName(),Territories.getName()) );
    }

    @Override
    public ArrayList<TerritoryInterface> getTerritories() {
        return this.territories;
    }

    @Override
    public String toString(){
        String delimiter = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(delimiter);
        sb.append(this.getColor().getName());
        sb.append(delimiter);
        sb.append("Territories:");
        sb.append(this.getTerritories().size());
        sb.append(delimiter);
        sb.append("Unused Armies:");
        sb.append(this.getUnusedArmies());
        sb.append(delimiter);
        sb.append("Used Armies:");
        sb.append(this.getUsedArmies());
        return sb.toString();
    }

    /**
     *
     * @param Armies to be place in the Territories
     * @param Territories
     * @return if successfully placed armies or not
     */
    @Override
    public Results placeArmy(int count, TerritoryInterface Territories) {

        if(this.unusedArmies - count < 0)
            return new Results("No sufficient armies.");


        this.setUnusedArmies(this.unusedArmies - count);
        this.setUsedArmies(this.usedArmies + count);
        Territories.placeArmies(count);
        LoggerController.log(this.getName() + " placed " + Integer.toString(count)+" armies into " + Territories.getName());
        LoggerController.log(this.getName() + " Unused armies = " + Integer.toString(this.getUnusedArmies()) +
                ", Used armies = " + Integer.toString(this.getUsedArmies()) );
        return new Results(true, String.format("%d armies placed in %s", count, Territories.getName()));
    }

    @Override
    public String getState()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n-----------------------------");
        sb.append("\n");
        sb.append(this.getName());
        sb.append("\n");
        sb.append("-------------------------------");
        sb.append("\n");
        sb.append("Armies: ");
        sb.append("\n");
        sb.append("Used Armies: ");
        sb.append(this.getUsedArmies());
        sb.append("\n");
        sb.append("Unused Armies: ");
        sb.append(this.getUnusedArmies());
        sb.append("\n-----------------------------");
        sb.append("\n");
        sb.append("Territories: ");
        sb.append("\n");
        for(TerritoryInterface t: this.territories)
        {
            sb.append("   "+t.getName());
            sb.append(", ");
            sb.append("Armies : ");
            sb.append(t.getArmies());
            sb.append("\n");
        }
        sb.append("\n");

        sb.append("-----------------------------");
        return sb.toString();
    }

    /**
     * Search Territories
     * @param Territory Name
     * @return Territory Name if found
     */
    @Override
    public TerritoryInterface getTerritoryByName(String TerritoriesName)
    {
        TerritoryInterface result = null;
        for(TerritoryInterface t:this.territories)
            if(t.getName().equalsIgnoreCase(TerritoriesName))
                result = t;
        return result;
    }

    /**
     *
     * @return select Territories randomly 
     */
    @Override
    public TerritoryInterface getRandomTerritory() {
        int max = this.getTerritories().size()-1;
        return this.getTerritories().get(utility.DiceRNG.getRandomInt(max,0));
    }


    /**
     * This method moves armies from one to another owned by the same player
     * @param Origin Territory
     * @param Destination Territory
     * @param Number of Armies
     * @return boolean if the move was successful of not.
     */
    @Override
    public Results moveArmies(TerritoryInterface from, TerritoryInterface to, int number) {
        Results result = new Results();

        if(from.hasAdjacencyWith(to))
        {
            LoggerController.log(this.getState());
            Results r = from.removeArmies(number);
            if (r.getOk())
            {
                to.placeArmies(number);
                LoggerController.log(String.format("Player %s moved %s armies from %s to %s.", this.getName(),
                        number, from.getName(),to.getName()));
                LoggerController.log(this.getState());
            }
        }
        else
        {
            LoggerController.log(MessageEnum.ERROR, String.format("Player %s Cannot move %s armies from %s to %s because there is no adjacencies.", this.getName()
                    , number, from.getName(), to.getName() ));
        }

        return result;
    }

}
