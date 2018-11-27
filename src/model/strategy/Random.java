package model.strategy;


import model.AttackPlan;
import model.Interfaces.PlayerInterface;
import model.Interfaces.StrategyInterface;
import model.Interfaces.TerritoryInterface;
import utility.DiceRNG;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Random Strategy
 */
public class Random implements StrategyInterface,Serializable {

	/**
	 * Deserialization uses this number to ensure that a loaded class corresponds 
	 * exactly to a serialized object while saving rhe game state 
	 */
	private static final long serialVersionUID = 8419616957685060753L;

	/**
     * how many attacks
     * @return number of attacks
     */
    @Override
    public int getAttackAttempts() {
        return utility.DiceRNG.getRandomInt(5,1);
    }

    /**
     * name of the startegy
     * @return name
     */
    @Override
    public String getName() {
        return "Random";
    }

    /**
     * where to reinforce
     * @param p player
     * @return territory
     */
    @Override
    public TerritoryInterface getInforcementTerritory(PlayerInterface p) {
        return p.getRandomTerritory();
    }

    /**
     * set number of reinforcement armies
     * @param p player
     * @return number of reinforcement
     */
    @Override
    public int getReinforcementArmies(PlayerInterface p) {
        return utility.DiceRNG.getRandomInt(p.getUnusedArmies(),1);
    }

    /**
     * where to fortify
     * @param p player
     * @return territory
     */
    @Override
    public TerritoryInterface getFortificationFromTerritory(PlayerInterface p) {
        return p.getRandomTerritory();
    }

    /**
     * where to move fortification armies to
     * @param p player
     * @param from origin of fortification
     * @return destination of fortification
     */
    @Override
    public TerritoryInterface getFortificationToTerritory(PlayerInterface p, TerritoryInterface from) {
        TerritoryInterface to;

        ArrayList<TerritoryInterface> neighbours = from.getAdjacentNeighbours();
        if(neighbours.size()>0)
            to = neighbours.get(0);
        else
            to = p.getRandomTerritory();

        return to;
    }

    /**
     * number of fortification armes
     * @param p player
     * @param from origin of fortification
     * @return number of armies
     */@Override
    public int getFortificationArmies(PlayerInterface p, TerritoryInterface from) {
        return DiceRNG.getRandomInt(from.getArmies(),1);
    }

    /**
     * Generates an attack plan for a player
     * @param p player
     * @return attack plan
     */
    @Override
    public AttackPlan getAttackPlan(PlayerInterface p) {
        return p.getTerritoryToAttack();
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
}
