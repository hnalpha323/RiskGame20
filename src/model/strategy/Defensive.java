package model.strategy;

import java.io.Serializable;

import model.AttackPlan;
import model.Interfaces.PlayerInterface;
import model.Interfaces.StrategyInterface;
import model.Interfaces.TerritoryInterface;
import utility.DiceRNG;

/**
 * Defensive strategy
 */
public class Defensive implements StrategyInterface,Serializable {
    
	/**
	 * Deserialization uses this number to ensure that a loaded class corresponds 
	 * exactly to a serialized object while saving rhe game state 
	 */
	private static final long serialVersionUID = -4744936499083518966L;

	/**
     * how many attacks
     * @return number of attacks
     */
    @Override
    public int getAttackAttempts() {
        return 0;
    }

    /**
     * name of the startegy
     * @return name
     */
    @Override
    public String getName() {
        return "Defensive";
    }

    /**
     * where to reinforce
     * @param p player
     * @return territory
     */
    @Override
    public TerritoryInterface getInforcementTerritory(PlayerInterface p) {

        return p.getWeakestTerritory();
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
        return null;
    }

    /**
     * role dice for attacking
     * @param p player
     * @return int dice
     */
    @Override
    public int diceToAttack(PlayerInterface p) {
        return 0;
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
        return DiceRNG.getRandomInt(2,1);
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
        return DiceRNG.getRandomInt(2,1);
    }
}
