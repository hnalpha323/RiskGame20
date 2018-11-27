package model.strategy;


import model.AttackPlan;
import model.Interfaces.PlayerInterface;
import model.Interfaces.StrategyInterface;
import model.Interfaces.TerritoryInterface;
import utility.DiceRNG;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * cheater strategy
 */
public class Cheater implements StrategyInterface,Serializable{
    
	/**
	 * Deserialization uses this number to ensure that a loaded class corresponds 
	 * exactly to a serialized object while saving the game state 
	 */
	private static final long serialVersionUID = 8524490775170198242L;

	/**
     * how many attacks
     * @return number of attacks
     */
    @Override
    public int getAttackAttempts() {
        return 1;
    }

    /**
     * name of the strategy
     * @return name
     */
    @Override
    public String getName() {
        return "Cheater";
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
        for(TerritoryInterface t : p.getTerritories())
        {
            t.placeArmies( t.getArmies() * 2 );
        }
        return utility.DiceRNG.getRandomInt(p.getUnusedArmies(),1);
    }

    /**
     * Generates an attack plan for a player
     * @param p player
     * @return attack plan
     */
    @Override
    public AttackPlan getAttackPlan(PlayerInterface p) {

        ArrayList<TerritoryInterface> newTerritories = new ArrayList<>();
        for(TerritoryInterface t : p.getTerritories())
        {
            for(TerritoryInterface n : t.getAdjacentTerritoryObjects())
            {
                if (n.getOwner() != p)
                {
                    newTerritories.add(n);
                }
            }
        }

        for(TerritoryInterface t : newTerritories) {
            t.getOwner().lostTerritory(t);
            p.ownTerritory(t);
        }
        return null;
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
        return 1;
    }

    /**
     * where to fortify
     * @param p player
     * @return territory
     */
    @Override
    public TerritoryInterface getFortificationFromTerritory(PlayerInterface p) {
        return p.getStrongestTerritory();
    }

    /**
     * where to move fortification armies to
     * @param p player
     * @param from origin of fortification
     * @return destination of fortification
     */
    @Override
    public TerritoryInterface getFortificationToTerritory(PlayerInterface p, TerritoryInterface from) {
        for(TerritoryInterface t : p.getTerritories())
        {
            for(TerritoryInterface n : t.getAdjacentTerritoryObjects())
            {
                if (n.getOwner() != p)
                {
                    p.placeArmy(t.getArmies() * 2, t);
                }
            }
        }
        return p.getWeakestTerritory();
    }

    /**
     * number of fortification armies
     * @param p player
     * @param from origin of fortification
     * @return number of armies
     */
    @Override
    public int getFortificationArmies(PlayerInterface p, TerritoryInterface from) {
        return 1;
    }
}
