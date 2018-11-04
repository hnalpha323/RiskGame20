package model;

import model.Interfaces.TerritoryInterface;

/**
 * @author Meet
 * @version 2.0.0
 * Class used for maintaining attacker and defender territories during an attack
 */
public class AttackPlan {

	TerritoryInterface from, to;

    /**
     * Constructor to initialize attacker and defender territories 
     * @param Attacked from territory
     * @param Attacked to territory 
     */
    public AttackPlan(TerritoryInterface from, TerritoryInterface to)
    {
        this.to = to;
        this.from = from;
    }
    

    public TerritoryInterface getFrom() 
    {
    	return this.from;
    }
    
    

    public TerritoryInterface getTo()
    {
    	return this.to;
    }

}