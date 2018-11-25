package model.strategy;

import java.io.Serializable;

import model.AttackPlan;
import model.Interfaces.PlayerInterface;
import model.Interfaces.StrategyInterface;
import model.Interfaces.TerritoryInterface;
import utility.DiceRNG;

/**
 * Aggressive Strategy
 */
public class Aggressive implements StrategyInterface,Serializable {
    
	/**
	 * Deserialization uses this number to ensure that a loaded class corresponds 
	 * exactly to a serialized object while saving the game state 
	 */
	private static final long serialVersionUID = 3617596243082091692L;


	/**
     * how many attacks
     * @return number of attacks
     */
    @Override
    public int getAttackAttempts() {
        return 5;
    }

    /**
     * name of the startegy
     * @return name
     */
    @Override
    public String getName() {
        return "Aggressive";
    }

    /**
     * where to reinforce
     * @param p player
     * @return territory
     */
    @Override
    public TerritoryInterface getInforcementTerritory(PlayerInterface p) {
        return p.getStrongestTerritory();
    }

    /**
     * set number of reinforcement armies
     * @param p player
     * @return number of reinforcement
     */
    @Override
    public int getReinforcementArmies(PlayerInterface p) {
        return p.getUnusedArmies();
    }

    /**
     * Generates an attack plan for a player
     * @param p player
     * @return attack plan
     */
    @Override
    public AttackPlan getAttackPlan(PlayerInterface p) {

        AttackPlan plan = null;
        TerritoryInterface from = p.getStrongestTerritory();

        try
        {
            TerritoryInterface to = from.getAdjacentTerritoryObjects().get(0);
            for(TerritoryInterface t : from.getAdjacentTerritoryObjects())
            {
                if (t.getArmies() < to.getArmies())
                    to = t;
            }

            plan = new AttackPlan(from, to);
        }
        catch (Exception e)
        {
            plan = null;
        }

        return plan;
    }

    /**
     * role dice for attacking
     * @param p player
     * @return int dice
     */
    @Override
    public int diceToAttack(PlayerInterface p) {
        return DiceRNG.getRandomInt(3,1);
    }

    /**
     * role dice to defend
     * @param p player
     * @return dice
     */
    @Override
    public int diceToDefend(PlayerInterface p) {
        return DiceRNG.getRandomInt(2,1);
    }

    /**
     * how many armies should move to new territory
     * @param p player
     * @return number of armies
     */
    @Override
    public int getMovingArmiesToNewTerritory(PlayerInterface p) {

        //return p.getWeakestTerritory().getArmies()-1;
        int result = p.getWeakestTerritory().getArmies()-1;
        if (result<0)
            result = 0;
        return result;

    }

    /**
     * where to fortify
     * @param p player
     * @return territory
     */
    @Override
    public TerritoryInterface getFortificationFromTerritory(PlayerInterface p) {
        return p.getWeakestTerritory();
    }

    /**
     * where to move fortification armies to
     * @param p player
     * @param from origin of fortification
     * @return destination of fortification
     */
    @Override
    public TerritoryInterface getFortificationToTerritory(PlayerInterface p, TerritoryInterface from) {
        return p.getStrongestTerritory();
    }


    /**
     * number of fortification armes
     * @param p player
     * @param from origin of fortification
     * @return number of armies
     */
    @Override
    public int getFortificationArmies(PlayerInterface p, TerritoryInterface from) {
    	System.out.println("Agressive "+from.getArmies()+" "+1);
        return DiceRNG.getRandomInt(from.getArmies(),1);
    }
}