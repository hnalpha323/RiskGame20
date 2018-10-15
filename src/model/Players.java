package model;


import com.sun.javafx.binding.StringFormatter;

import controller.LoggerController;
import model.Interfaces.TerritoryInterface;
import model.Interfaces.PlayerInterface;
import utility.Results;
import utility.Gradient;
import utility.LogMessageEnum;
import utility.exception.NoSufficientArmiesExption;

import java.util.ArrayList;

/**
 * This is Players class
 *
 * @author WaleedAhmad
 * @version 1.0.0
 */
public class Players implements PlayerInterface {


    private String name;
    private Color color;
    private int unusedArmies = 0;
    private int usedArmies = 0;
    private ArrayList<TerritoryInterface> territories;

    /**
     * Constructor
     * @param name  Players name
     * @param color Players color
     */
    public Players(String name, Color color){
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
    }

    /**
     *
     * @return Players name
     */
    @Override
    public String getName(){
        return this.name;
    }

    /**
     *
     * @param newName new name for the  Players
     */
    @Override
    public void setName(String newName){
        this.name = newName;
    }


    /**
     * set number of unused armies for Players
     * @param armies number of new armies
     */
    @Override
    public void setUnusedArmies(int armies) { this.unusedArmies = armies; }

    /**
     * set number of unused armies for Players
     */
    @Override
    public int getUnusedArmies(){ return this.unusedArmies; }

    /**
     *
     * @param armies number or unused armies to be set
     */
    @Override
    public void setUsedArmies(int armies) { this.usedArmies = armies; }

    /**
     * sets number of new armies
     * @return new armies
     */
    @Override
    public int getUsedArmies(){ return this.usedArmies; }

    /**
     *
     * @param color new color
     */
    public void setColor(Color color){ this.color = color; }

    /**
     *
     * @return Players's color
     */
    public Color getColor() { return this.color; }

    /**
     *
     * @param Territories Territories to be owned
     * @return if the operation was successful or not
     */
    @Override
    public Results ownTerritory(TerritoryInterface Territories) {
        Territories.setOwner(this);
        this.placeArmy(1, Territories);
        this.territories.add(Territories);
        return new Results(true, String.format("%s owns %s", this.getName(),Territories.getName()) );
    }

    /**
     *
     * @return list of Players territories
     */
    @Override
    public ArrayList<TerritoryInterface> getTerritories() {
        return this.territories;
    }


    /**
     * fancy representation of the Players status
     * @return fancy toString
     */
    @Override
    public String toString(){
        String delimiter = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(delimiter);
        //sb.append(this.getColor().getName());
        //sb.append(delimiter);
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
     * @param count number of armies to be place into the Territories
     * @param Territories the Territories
     * @return if the action is done or not
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

    /**
     * another fancy representation of Players status
     * @return table
     */
    @Override
    public String getState()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n====================");
        sb.append("\n");
        sb.append(this.getName());
        sb.append("\n");
        sb.append("--------------------");
        sb.append("\n");
        sb.append("Armies: ");
        sb.append("\n");
        sb.append("   Used: ");
        sb.append(this.getUsedArmies());
        sb.append("\n");
        sb.append("   Unused: ");
        sb.append(this.getUnusedArmies());
        sb.append("\n--------------------");
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

        sb.append("====================");
        return sb.toString();
    }

    /**
     * finds a Territories by its name
     * @param TerritoriesName name
     * @return the Territories
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
     * @return randomly selected Territories
     */
    @Override
    public TerritoryInterface getRandomTerritory() {
        int max = this.getTerritories().size()-1;
        return this.getTerritories().get(utility.DiceRNG.getRandomInt(max,0));
    }


    /**
     * move armies from a Territories to another
     * @param from origin Territories
     * @param to destination Territories
     * @param number number of armies
     * @return if the operation is done or not
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
                LoggerController.log(String.format("%s moved %s armies from %s to %s.", this.getName(),
                        number, from.getName(),to.getName()));
                LoggerController.log(this.getState());
            }
        }
        else
        {
            LoggerController.log(LogMessageEnum.ERROT, String.format(
                    "%s wanted to move %s armies from %s to %s, but there is no adjacencies.", this.getName()
                    , number, from.getName(), to.getName() ));
        }

        return result;
    }

}
