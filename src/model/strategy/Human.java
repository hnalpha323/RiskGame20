package model.strategy;


import model.AttackPlan;
import model.Players;
import model.Interfaces.PlayerInterface;
import model.Interfaces.StrategyInterface;
import model.Interfaces.TerritoryInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This class deals with all Human strategies interactions fron user
 * Human Strategy
 */
public class Human extends Observable implements StrategyInterface,Serializable {

	/**
	 * Deserialization uses this number to ensure that a loaded class corresponds 
	 * exactly to a serialized object while saving rhe game state 
	 */
	private static final long serialVersionUID = 5308462513531998981L;
	
	/**
	 * shared variable to hold temporary data
	 */
	public static String sharedTmp = null;
	
    /**
     * This method asks how many attacks the player want to do
     * @return number of attacks
     */
    @Override
    public int getAttackAttempts() {
        sendNotification("How many times do you want to attack?");
        int times = Integer.parseInt(sharedTmp);
        return times;
    }

    /**
     * name of the strategy
     * @return name
     */
    @Override
    public String getName() {
        return "Human";
    }

    /**
     * where to reinforce
     * @param p player 
     * @return territory
     */
    @Override
    public TerritoryInterface getInforcementTerritory(PlayerInterface p) {
      
    	sendNotification("Tip: your weakest territory is : "+p.getWeakestTerritory().getName()+" \n"+
    			"Which territory do you want to reinforce?");
        
        String territoryName = sharedTmp;
        return p.getTerritoryByName(territoryName);
    }

    /**
     * set number of reinforcement armies
     * @param p player
     * @return number of reinforcement
     */
    @Override
    public int getReinforcementArmies(PlayerInterface p) {

        sendNotification("How many armies do you want to put into?");
        int reinforcementArmies = Integer.parseInt(sharedTmp);
        return reinforcementArmies;
    }

    /**
     * Generates an attack plan for a player
     * @param player is the player want to attack
     * @return attack plan of type {@link AttackPlan}
     */
    @Override
    public AttackPlan getAttackPlan(PlayerInterface player)
    {
        AttackPlan result = null;
        TerritoryInterface strongetTerritory = player.getStrongestTerritory();
        
        sendNotification("Tip: your strongest territory is :"+strongetTerritory.getName() + "(" + strongetTerritory.getArmies()  +") \n "+player.getState()
        		+"\n Which territory do you want to attack from?");
        
        String territoryName = sharedTmp;
        
        TerritoryInterface from = player.getTerritoryByName(territoryName);

        ArrayList<TerritoryInterface> neighbours = from.getAdjacentNeighbours();
        TerritoryInterface to;

        if(neighbours.size()>0)
        {

            //list neighbors to attack
            ArrayList<TerritoryInterface> attackList = new ArrayList<>();
            for(TerritoryInterface a: from.getAdjacentTerritoryObjects())
            {
                if(a.getOwner() != from.getOwner())
                {
                    attackList.add(a);
                }
            }

            sharedTmp = String.format("From %s, you can attack to these:", from.getName());
            
            for(TerritoryInterface t:attackList)
                sharedTmp += String.format("\n *) %s (current armies: %s)(%s)", t.getName(), t.getArmies(), t.getOwner().getName());

            sharedTmp += "\n Which territory do you want to attack to?";
            sendNotification(sharedTmp);
            territoryName = sharedTmp;
            to = player.getGameDriver().getTerritory(territoryName);
            result = new AttackPlan(from, to);
        }
        else
        {
            sendNotification(String.format("There is no neighbour for %s. Attack is canceled.\n To continue press enter.", from.getName()));
            result = null;
        }

        return result;

    }

    /**
     * role dice for attacking
     * @param player who want to rool dice to attack
     * @return int dice
     */
    @Override
    public int diceToAttack(PlayerInterface player) {
        sendNotification("How many dice do you want to use for the attack?");
        int dice = Integer.parseInt(sharedTmp);
        return dice;
    }

    /**
     * role dice to defend
     * @param player, type of {@link Player} who want to defend  
     * @return the number of dice he want to roll 
     */
    @Override
    public int diceToDefend(PlayerInterface player) {
        sendNotification("How many dice do you want to use to defend?");
        int dice = Integer.parseInt(sharedTmp);
        return dice;
    }

    /**
     * how many armies should move to new territory
     * @param player who is going to do fortification
     * @return number of armies 
     */
    @Override
    public int getMovingArmiesToNewTerritory(PlayerInterface player) {
        sendNotification("How many armies do you want to move to the new territory?");
        int armies = Integer.parseInt(sharedTmp);
        return armies;
    }

    /**
     * This method asks for fortification 
     * @param player who want to do fortification
     * @return territory he choose to do fortification 
     */
    @Override
    public TerritoryInterface getFortificationFromTerritory(PlayerInterface player) {

        TerritoryInterface strongTerritory = player.getStrongestTerritory();
        TerritoryInterface weakTerritory = player.getWeakestTerritory();
        sharedTmp = player.getState();
        sharedTmp += "\n Tip: your strongest territory is : ";
        sharedTmp += (strongTerritory.getName() + "(" + strongTerritory.getArmies()  +")");
        sharedTmp += ("Tip: your weakest territory is : ");
        sharedTmp += (weakTerritory.getName() + "(" + weakTerritory.getArmies()  +")");
        sharedTmp += ("\nWhich territory do you want to move armies from?");
        sendNotification(sharedTmp);
        String territoryName = sharedTmp;
        return player.getTerritoryByName(territoryName);
    }

    /**
     * where to move fortification armies to
     * @param player is the player who want to fortify armies
     * @param fromTerritory origin territory of fortification
     * @return destination of fortification
     */
    @Override
    public TerritoryInterface getFortificationToTerritory(PlayerInterface player, TerritoryInterface fromTerritory) {
        ArrayList<TerritoryInterface> neighbours = new ArrayList<>();
        ArrayList<TerritoryInterface> temp = fromTerritory.getAdjacentNeighbours();
        for(TerritoryInterface t : temp)
            if(t.getOwner() == player)
                neighbours.add(t);

        TerritoryInterface toTerritory;

        if(neighbours.size()>0)
        {
            sharedTmp = (String.format("From %s, you can move to these:", fromTerritory.getName()));
            for(TerritoryInterface t:neighbours)
                sharedTmp += (String.format("\n *) %s (current armies: %s)(%s)", t.getName(), t.getArmies(),t.getOwner().getName()));

            sharedTmp += ("\n Which territory do you want to move to?");
            sendNotification(sharedTmp);
            String territoryName = sharedTmp;
            toTerritory = player.getTerritoryByName(territoryName);
        }
        else
        {
            sharedTmp = (String.format("There is no neighbour for %s. Fortification is canceled.", fromTerritory.getName()));
            sharedTmp += "To continue press enter.";
            sendNotification(sharedTmp);
            toTerritory = player.getRandomTerritory();
        }

        return toTerritory;
    }

    /**
     * number of fortification armies
     * @param player to chose number of armies he want to fortify
     * @param fromTerritory is territory from which u want to do fortification
     * @return number of armies he choose
     */
    @Override
    public int getFortificationArmies(PlayerInterface player, TerritoryInterface fromTerritory) {
        sendNotification("How many armies do you want to fortify?");
        int armies = Integer.parseInt(sharedTmp);
        return armies;
    }
    
    

	/**
	 * @param message is the message to be notified to Observers
	 */
	public void sendNotification(String message) {
		setChanged();
		notifyObservers(message);
	}

    
}
