package model;


//import com.sun.javafx.binding.StringFormatter;

//import controller.LoggerController;
import model.Interfaces.PlayerInterface;
import model.Interfaces.StrategyInterface;
import model.Interfaces.TerritoryInterface;
import utility.DiceRNG;
import view.Message;
import utility.Results;
import utility.Gradient;
import utility.MessageEnum;
//import utility.exception.NoSufficientArmiesExption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

/**
 * This is Players class
 *
 * @author Team
 * @version 1.0.0
 */
public class Players extends Observable implements PlayerInterface, Comparable<PlayerInterface>, Serializable 
{

	private static final long serialVersionUID = 1012842278301009514L;
    private String name;
    private Gradient color;
    private int unusedArmies = 0;
    private int usedArmies = 0;
    private ArrayList<TerritoryInterface> territories;
    private GameDriver gd;
    private double domination = 0.0;
    private ArrayList<Card> cards = new ArrayList<>();
    StrategyInterface strategy;
    private String status;
	private boolean isActive = true;
    private int trades = 1;

    /**
     * Constructor
     * @param name  player name
     * @param player Color
     * @param Strategy the Player
     */
    public Players(String name, Gradient color, StrategyInterface strategy)
    {
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
        this.strategy = strategy;
    }

    /**
     * Constructor to Initialize player object with his name
	 */
	public Players(String playerName) {
		this.name = playerName;
		this.territories = new ArrayList<>();
	}
	
    /**
     * @return player name
     */
    @Override
    public String getName()
    {
        return this.name;
    }

    /**
     *
     * @param New name for the  player
     */
    @Override
    public void setName(String newName)
    {
        this.name = newName;
    }

    /**
     * To get how many percent of the world is controlled by a player
     * @return percentage
     */
    @Override
    public double getDomination() 
    {
        return this.domination;
    }

    /**
     * To set how many percent of the world is controlled by a player
     */
    @Override
    public void setDomination(double value) 
    {
        this.domination = value;
    }


    /**
     * To set number of unused armies for a player
     * @param Number of new armies
     */
    @Override
    public void setUnusedArmies(int armies) 
    { 
    	this.unusedArmies = armies; 
    }

    /**
     * set number of unused arimes for player
     */
    @Override
    public int getUnusedArmies()
    { 
    	return this.unusedArmies; 
    }

    /**
     *
     * @param armies number or unused armies to be set
     */
    @Override
    public void setUsedArmies(int armies) 
    { 
    	this.usedArmies = armies; 
    }

    /**
     * sets number of new armies
     * @return new armies
     */
    @Override
    public int getUsedArmies()
    { 
    	return this.usedArmies; 
    }

    /**
     *
     * @param Gradient new Gradient
     */
    public void setGradient(Gradient color)
    { 
    	this.color = color; 
    }

    /**
     *
     * @return player's Gradient
     */
    public Gradient getcolor() 
    { 
    	return this.color; 
    }

    /**
     *
     * @param territory territory to be owned
     * @return if the operation was successful or not
     */
    @Override
    public Results ownTerritory(TerritoryInterface territory) 
    {
    	if(territory.getOwner() != null)
    	{
    		sendNotification(territory.getOwner().getName()+ ": lost "+ territory.getName()+" because of "+this.getName());
    	}
    	territory.setOwner(this);
        this.placeArmy(1, territory);
        this.territories.add(territory);
        this.status = String.format("%s owns %s", this.getName(),territory.getName());
        sendNotify();
        return new Results(true, status);
    }

    /**
     * When player loses the territory
     * @param Territory to be removed
     * @return Operation was successful or not
     */
    @Override
    public Results lostTerritory(TerritoryInterface territory) 
    {
    	this.territories.remove(territory);
    	this.status = String.format("%s lost %s", this.getName(),territory.getName());
    	sendNotify();
        return new Results(true, this.status);
    }

    /**
     *
     * @return strategies of player territories
     */
    @Override
    public ArrayList<TerritoryInterface> getTerritories() 
    {
        return this.territories;
    }


    /**
     * Representation of the player status
     * @return fancy toString
     */
    @Override
    public String toString()
    {
        String delimiter = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(delimiter);
        sb.append(this.getStrategy().getName());
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
     * @param count number of armies to be place into the territory
     * @param territory the territory
     * @return if the action is done or not
     */
    @Override
    public Results placeArmy(int count, TerritoryInterface territory) 
    {

        if(this.unusedArmies - count < 0)
            return new Results("No sufficient armies.");


        this.setUnusedArmies(this.unusedArmies - count);
        this.setUsedArmies(this.usedArmies + count);
        territory.placeArmies(count);
        
        this.status = this.getName() + " placed " + Integer.toString(count)+" armies into " + territory.getName();
        sendNotify();
        Log.log(this.status);
        
        Log.log(this.getName() + " Unused armies = " + Integer.toString(this.getUnusedArmies()) +
                ", Used armies = " + Integer.toString(this.getUsedArmies()));
        
        return new Results(true, String.format("%d armies placed in %s", count, territory.getName()));
    }

    /**
     * Representation of player status
     * @return table
     */
    @Override
    public String getState()
    {
    	StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------");
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

        sb.append("--------------------");
        return sb.toString();
    }

    /**
     * finds a territory by its name
     * @param territoryName name
     * @return the territory
     */
    @Override
    public TerritoryInterface getTerritoryByName(String territoryName)
    {
        TerritoryInterface result = null;
        for(TerritoryInterface t:this.territories)
            if(t.getName().equalsIgnoreCase(territoryName))
                result = t;
        return result;

    }

    /**
     * @return Selects random territory
     */
    @Override
    public TerritoryInterface getRandomTerritory() 
    {
        int max = this.getTerritories().size()-1;
        return this.getTerritories().get(utility.DiceRNG.getRandomInt(max,0));
    }


    /**
     * Move armies from one territory to another
     * @param Origin territory
     * @param Destination territory
     * @param Number of armies
     * @return if the operation is done or not
     */
    @Override
    public Results moveArmies(TerritoryInterface from, TerritoryInterface to, int number) 
    {
    	Results result = new Results();

        if(from.hasAdjacencyWith(to))
        {
            Log.log(this.getState());
            Results r = from.removeArmies(number);
            if (r.getOk())
            {
                to.placeArmies(number);
                this.status = String.format("%s moved %s armies from %s to %s.", this.getName(),
                        number, from.getName(),to.getName());
                
                sendNotify();
                Log.log(this.status);
                Log.log(this.getState());
            }
        }
        else
        {
        	 this.status = String.format(
                     "%s wanted to move %s armies from %s to %s, but there is no adjacencies.", this.getName()
                     , number, from.getName(), to.getName() );
             
             sendNotify();
            Log.log(MessageEnum.ERROR, this.status);
        }

        return result;
    }

    /**
     * To Find a territory to attack
     * @return origin territory to attack from and attack to
     */
    @Override
    public AttackPlan getTerritoryToAttack() 
    {
        AttackPlan result = null;
        TerritoryInterface t = this.getRandomTerritory();
        for(TerritoryInterface a: t.getAdjacentTerritoryObjects())
        {
            if(a.getOwner() != this)
            {
                result = new AttackPlan(t,a);
                return result;
            }
        }
        return result;
    }

    /**
     * Sets the game driver for player
     */
    @Override
    public void setGameDriver(GameDriver gd) 
    {
        this.gd = gd;
    }


    /**
     * Gets the game driver for player
     */
    @Override
    public GameDriver getGameDriver() 
    {
        return this.gd;
    }

    /**
     * Handles Reinforcement:
     * Calculates the number of armies each player should get
     * Allows a given player to decide where to place the given armies
     */
    public void reinforcement()
    {
        Log.log(String.format("---------%s Starting Reinforcement---------", this.getName()));
        this.gd.setPhase("Reinforcement Phase");
        int newArmies = this.gd.calculateReinforcementArmies(this);
        sendNotification("Phase Change:"+this.getName()+" got "+newArmies+" armies");
        //Step 1: Reinforcement
        this.setUnusedArmies(newArmies);
        //Step 2: Place armies
        this.gd.placeArmies(this);
        
        Log.log(String.format("---------%s Reinforcement Finished---------", this.getName()));
    }


    /**
     * simple attack
     */
    public void attack()
    {
    	sendNotification("Phase Change:"+this.getName()+" Attack");    	
        this.attack(strategy.getAttackAttempts());
    }

    /**
     * This will handle attack phase
     */
    public void attack(int attempts)
    {
        Log.log(String.format("---------%s Starting Attack Phase---------", this.getName()));
        Log.log(String.format("Strategy is %s", this.strategy.getName()));
        this.gd.setPhase("ATTACK PHASE");

        for(int a=1; a<= attempts; a++)
        {
            Log.log(String.format("Attack %s ", a));

            // Step 1: Design a attack plan
            AttackPlan ap = this.strategy.getAttackPlan(this);
            if (ap == null)
            {
            	this.status = "No territory found to attack.";
            	sendNotify();
                Log.log(MessageEnum.WARNING,"No territory found to attack.");
                break;
            }

            TerritoryInterface attackFrom = ap.from;
            TerritoryInterface attackTo = ap.to;
            
            //Notify the attack to View 
            sendNotification("Game Change: Attacked from "+attackFrom.getName()+" to "+attackTo.getName()+"("+attackTo.getOwner().getName()+")");
            
            // Step 2: Number of armies(Dices) for the battle
            int diceAttack = attackFrom.getOwner().getStrategy().diceToAttack(attackFrom.getOwner());

            //Step 3: Checking sufficient armies to attack
            if (attackFrom.getArmies() - diceAttack >= 1)
            {
            	int diceDefend = attackTo.getOwner().getStrategy().diceToDefend(attackTo.getOwner());
                //Step 4: Checking sufficient armies to defend
                if(diceDefend == 2 && attackTo.getArmies() < 2)
                {
                    diceDefend = 1;
                }

                this.status = String.format("%s attacks %s from %s with %s armies and %s defends with %s armies", this.getName(), attackTo.getName(),
                        attackFrom.getName(), diceAttack, attackTo.getName(), diceDefend );
            	sendNotify();
                
                Message.log(this.status);

                //Step 5: Rolling dices
                ArrayList<Integer> attackDices = new ArrayList<>();
                ArrayList<Integer> defendDices = new ArrayList<>();
                Dice dice = new Dice();
                for(int i=0; i<diceAttack; i++)
                    attackDices.add(dice.roll());
                for(int i=0; i<diceDefend; i++)
                    defendDices.add(dice.roll());

                //Step 6: Sorting dice rolls
                Collections.sort(attackDices);
                Collections.sort(defendDices);

                this.status = String.format("%s(Attacker) rolled these dices %s",  attackFrom.getOwner().getName(),attackDices.toString());
            	sendNotify();
                Log.log(String.format("%s(Attacker) rolled these dices %s",  attackFrom.getOwner().getName(),attackDices.toString()));
                
                this.status = String.format("%s(Defender) rolled these dices %s",  attackTo.getOwner().getName(),defendDices.toString());
            	sendNotify();
                Log.log(String.format("%s(Defender) rolled these dices %s",  attackTo.getOwner().getName(),defendDices.toString()));

                //Step 7: Calculating number of fights
                int fights = 0;
                if(attackDices.size() > defendDices.size())
                    fights = defendDices.size();
                else if(attackDices.size() < defendDices.size())
                    fights = attackDices.size();
                else
                    fights = attackDices.size();

                //Step 8: Deciding the battle
                for(int f=0; f<fights; f++)
                {
                    if(attackDices.get(0) > defendDices.get(0))
                    {
                        // Attacker wins
                        Log.log(attackTo.getOwner().getState());

                        this.status = String.format("One of the armies in %s(Defender) was killed.", attackTo.getName());
                        sendNotify();
                        Message.log(String.format("One of the armies in %s(Defender) was killed.", attackTo.getName()));
                        attackTo.killArmies(1);
                        Log.log(attackTo.getOwner().getState());
                        
                        
                        // checking for occupying the territory
                        if(attackTo.getArmies()==0)
                        {
                        	this.status = String.format("%s occupies %s", attackFrom.getOwner().getName(),
                                    attackTo.getName());
                        	sendNotify();
                            Log.log(String.format("%s occupies %s", attackFrom.getOwner().getName(),
                                    attackTo.getName()));
                            Log.log(attackFrom.getOwner().getState());
                            attackTo.getOwner().lostTerritory(attackTo);
                            attackFrom.getOwner().ownTerritory(attackTo);
                            Card crd = this.getGameDriver().cardDeck.grantCard(this);
                            this.status = String.format("%s gets one card %s, %s", this.getName(),
                                    crd.getCardName(), crd.getCardValue());
                        	sendNotify();
                            Log.log(String.format("%s gets one card %s, %s", this.getName(),
                                    crd.getCardName(), crd.getCardValue()));
                            Log.log(attackFrom.getOwner().getState());
                        }
                    }
                    else
                    {
                        // Defender wins
                        Log.log(attackFrom.getOwner().getState());
                        this.status = String.format("1 of the armies in %s(Attacker) was killed.",attackFrom.getName());
                        sendNotify();
                        Message.log(String.format("1 of the armies in %s(Attacker) was killed.",attackFrom.getName()));
                        attackFrom.killArmies(1);
                        Log.log(attackFrom.getOwner().getState());
                    }

                    attackDices.remove(0);
                    defendDices.remove(0);
                }

                //Step 9: After attack if occupied move remaining attack armies to new territory
                if(attackTo.getOwner() == attackFrom.getOwner())
                {
                    //Step 10: calculating moving armies to new territory
                    int movingArmies = attackFrom.getOwner().getStrategy().getMovingArmiesToNewTerritory(this);
                    attackFrom.removeArmies(movingArmies);
                    attackTo.placeArmies(movingArmies);
                    this.status = String.format("%s places %s armies to occupied territory(%s)",
                            attackTo.getOwner().getName(), movingArmies, attackTo.getName());
                    sendNotify();
                    Log.log(String.format("%s places %s armies to occupied territory(%s)",
                            attackTo.getOwner().getName(), movingArmies, attackTo.getName()));
                    Message.log(this.getState());
                }

            }
            else
            {
            	//Attack Canceled
                this.status = String.format("Attacking %s from %s with %s armies canceled. %s -> %s", attackTo.getName(),
                        attackFrom.getName(), diceAttack, attackFrom.getArmies() , attackTo.getArmies());
                sendNotify();
                Message.log(String.format("Attacking %s from %s with %s armies canceled. %s -> %s", attackTo.getName(),
                        attackFrom.getName(), diceAttack, attackFrom.getArmies() , attackTo.getArmies()));
            }

            this.status = String.format("Attack %s finished.", a);
            sendNotify();
            Log.log(String.format("Attack %s finished.", a));
        }


        Log.log(String.format("--------------------%s Attack Phase Done--------------------", this.getName()));
    }


    /**
     * Fortification phase
     */
    public void fortification()
    {
        Log.log(String.format("--------------------%s Fortification Phase Starts--------------------", this.getName()));
        this.gd.setPhase("FORTIFICATION PHASE");
        sendNotification("Phase Change:"+this.getName()+" Fortification");
        
        TerritoryInterface from = this.strategy.getFortificationFromTerritory(this);
        int number = this.strategy.getFortificationArmies(this, from);
        TerritoryInterface to = this.strategy.getFortificationToTerritory(this, from);


        this.moveArmies(from, to, number);


        Log.log(String.format("--------------------%s Fortification Phase Finnished--------------------", this.getName()));
        sendNotification("Done his fortification");
    }
    
    private void sendNotification(String string) 
    {
		setChanged();
		notifyObservers(string);	
	}


    /**
     * Implementation of Compareable
     * @param Player to compare to
     * @return If they are equal or not
     */
    @Override
    public int compareTo(PlayerInterface o) 
    {
        return (int)(this.getDomination() - o.getDomination());
    }

    /**
     * What strategy is being used.
     * @return the strategy
     */
    @Override
    public StrategyInterface getStrategy() 
    {
        return this.strategy;
    }

    /**
     * To set the strategy to play with
     */
    @Override
    public void setStrategy(StrategyInterface strategy) 
    {
        this.strategy = strategy;
    }


    /**
     * To set the if player is active or not
     * @param status new status
     */
    @Override
    public void setStatus(boolean status) 
    {
    	this.isActive = status;
    }

    /**
     * Get the if player is active or not
     * @return status
     */
    @Override
    public boolean getStatus() 
    {
    	return this.isActive;
    }
    
    /**
     * Adds a new card
     * @param newCard is the card that this player got
     */
    @Override
    public void addCard(Card newCard) 
    {
        this.cards.add(newCard);
    }
    
    /**
     * Returns the cards owned by this player
     */
    @Override
    public ArrayList<Card> getCardSet() 
    {
    	ArrayList<Card> result  = new ArrayList<Card>();

        if(this.cards.size()>=3)
        {
            result = new ArrayList<>();
            for(int i=0; i<3; i++)
                result.add(this.cards.get(i));

            this.cards.removeAll(result);
        }

        return result;
    }
    
    /**
     * Returns The number of cards owned by this player
     */
    public ArrayList<Card> getCards() 
    {
    	return this.cards;
    }
    
    /**
     * Returns the number of cards this player has
     */
    @Override
    public int getCardsSize() 
    {
        return this.cards.size();
    }

    
    /**
     * Notify the observers with his player status message
     */
    public void sendNotify()
    {
    		setChanged();
    		notifyObservers(this.status); 	
    }
    
    
    /**
     * Notify the observers with the message
     */
    public void sendNotify(String message)
    {    	
    		setChanged();
    		notifyObservers(message);
  
    }

    /**
     * Finds the Weakest Territory
     */
    @Override
    public TerritoryInterface getWeakestTerritory() {
        TerritoryInterface result = null;

        result = this.territories.get(0);
        for(TerritoryInterface t: this.territories)
        {
                if(t.getArmies() < result.getArmies() && t.getArmies()>1)
                result = t;
        }

        return result;
    }

    /**
     * Finds the Strongest Territory
     */
    @Override
    public TerritoryInterface getStrongestTerritory() {
        TerritoryInterface result = null;

        result = this.territories.get(0);
        for(TerritoryInterface t: this.territories)
        {
            if(t.getArmies() > result.getArmies())
                result = t;
        }

        return result;
    }


    /**
     * Returns how many trades the players has done so far
     */
    @Override
    public int getTrades()
    {
        return this.trades;
    }


    /**
     * Reduces the number of trades for card exchange
     */
    @Override
    public void increaseTrades()
    {
        this.trades++;
    }
}
