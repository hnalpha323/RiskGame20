package model;


//import com.sun.javafx.binding.StringFormatter;

import controller.LoggerController;
import model.Interfaces.PlayerInterface;
import model.Interfaces.StrategyInterface;
import model.Interfaces.TerritoryInterface;
import utility.DiceRNG;

import utility.Results;
import view.Message;
import utility.Gradient;
import utility.MessageEnum;
//import utility.exception.NoSufficientArmiesExption;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This is Players class
 *
 * @author WaleedAhmad
 * @version 1.0.0
 */
public class Players extends Model implements PlayerInterface, Comparable<PlayerInterface> {


    private String name;
    private Gradient color;
    private int unusedArmies = 0;
    private int usedArmies = 0;
    private ArrayList<TerritoryInterface> territories;
    private GameDriver gm;
    private double domination = 0.0;
    private ArrayList<Card> cards = new ArrayList<>();
    StrategyInterface strategy;
    private boolean status = true;
    private int trades = 1;

    /**
     * Constructor
     * @param name  player name
     * @param Gradient player Gradient
     */
    public Players(String name, Gradient color, StrategyInterface strategy){
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
        this.strategy = strategy;
    }

    /**
     *
     * @return player name
     */
    @Override
    public String getName(){
        return this.name;
    }

    /**
     *
     * @param newName new name for the  player
     */
    @Override
    public void setName(String newName){
        this.name = newName;
    }

    /**
     * get how many percent of the world is controlled by the player
     * @return percentage
     */
    @Override
    public double getDomination() {
        return this.domination;
    }

    /**
     * set how many percent of the world is controlled by the player
     * @param value
     */
    @Override
    public void setDomination(double value) {
        this.domination = value;
    }


    /**
     * set number of unused arimes for player
     * @param armies number of new armies
     */
    @Override
    public void setUnusedArmies(int armies) { this.unusedArmies = armies; }

    /**
     * set number of unused arimes for player
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
     * @param Gradient new Gradient
     */
    public void setGradient(Gradient color){ this.color = color; }

    /**
     *
     * @return player's Gradient
     */
    public Gradient getcolor() { return this.color; }

    /**
     *
     * @param territory territory to be owned
     * @return if the operation was successful or not
     */
    @Override
    public Results ownTerritory(TerritoryInterface territory) {
    	if(territory.getOwner() != null){
    	sendNotification("GameChange", territory.getOwner().getName()+ ": lost "+ territory.getName()+" because of "+this.getName());
    	}
    	territory.setOwner(this);
        this.placeArmy(1, territory);
        this.territories.add(territory);
        sendNotification("GameChange", this.getName()+ ": own "+ territory.getName());
        
        return new Results(true, String.format("%s owns %s", this.getName(),territory.getName()) );
    }

    /**
     * lose the territory
     * @param territory territory to be removed
     * @return if the operation was successful
     */
    @Override
    public Results lostTerritory(TerritoryInterface territory) {
    	this.territories.remove(territory);
        return new Results(true, String.format("%s lost %s", this.getName(),territory.getName()) );
    }

    /**
     *
     * @return strategies of player territories
     */
    @Override
    public ArrayList<TerritoryInterface> getTerritories() {
        return this.territories;
    }


    /**
     * fancy representation of the player status
     * @return fancy toString
     */
    @Override
    public String toString(){
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
    public Results placeArmy(int count, TerritoryInterface territory) {

        if(this.unusedArmies - count < 0)
            return new Results("No sufficient armies.");


        this.setUnusedArmies(this.unusedArmies - count);
        this.setUsedArmies(this.usedArmies + count);
        territory.placeArmies(count);
        
        if(!this.gm.getPhase().equals("Startup")){
        	sendNotification("GameChange", this.getName()+ ": placed " + Integer.toString(count)+" armies into " + territory.getName());
        }
        LoggerController.log(this.getName() + " placed " + Integer.toString(count)+" armies into " + territory.getName());
        LoggerController.log(this.getName() + " Unused armies = " + Integer.toString(this.getUnusedArmies()) +
                ", Used armies = " + Integer.toString(this.getUsedArmies()) );
        return new Results(true, String.format("%d armies placed in %s", count, territory.getName()));
    }

    /**
     * another fancy representation of player status
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
     *
     * @return randomly selected territory
     */
    @Override
    public TerritoryInterface getRandomTerritory() {
        int max = this.getTerritories().size()-1;
        return this.getTerritories().get(utility.DiceRNG.getRandomInt(max,0));
    }


    /**
     * move armies from a territory to another
     * @param from origin territory
     * @param to destination territory
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
            LoggerController.log(MessageEnum.ERROR, String.format(
                    "Player %s wants to move %s armies from %s to %s, but there is no adjacencies.", this.getName()
                    , number, from.getName(), to.getName() ));
        }

        return result;
    }

    /**
     * find a territory to attack
     * @return territory to attack from and attack to
     */
    @Override
    public AttackPlan getTerritoryToAttack() {
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
     * set the game manager for player
     * @param gm game manager
     */
    @Override
    public void setGameDriver(GameDriver gm) {
        this.gm = gm;
    }


    /**
     * set the game manager for player
     * @return used game manager
     */
    @Override
    public GameDriver getGameDriver() {
        return this.gm;
    }

    /**
     * Handles reinforcement phase by :
     * calculating the number of armies each player should get
     * let the given player decide where to place the given armies
     */
    public void reinforcement()
    {
        LoggerController.log(String.format("---------%s Starting Reinforcement---------", this.getName()));
        this.gm.setPhase("REINFORCEMENT PHASE");
        sendNotification("PhaseChange", "PhaseChange:"+this.getName()+" Reinforcement");
        //Step 1: Reinforcement
        int newArmies = this.gm.calculateReinforcementArmies(this);
        this.setUnusedArmies(newArmies);

        //Step 2: Place armies
        this.gm.placeArmies(this);
        
        LoggerController.log(String.format("---------%s Reinforcement Finished---------", this.getName()));
    }


    /**
     * simple attack
     */
    public void attack()
    {
    	sendNotification("PhaseChange", "PhaseChange:"+this.getName()+" Attack");
        this.attack(strategy.getAttackAttempts());
    }

    /**
     * This will handle attack phase but not implemented yet
     */
    public void attack(int attempts)
    {
        LoggerController.log(String.format("---------%s Starting Attack Phase---------", this.getName()));
        LoggerController.log(String.format("Strategy is %s", this.strategy.getName()));
        this.gm.setPhase("ATTACK PHASE");

        for(int a=1; a<= attempts; a++)
        {
            LoggerController.log(String.format("Attack %s ", a));

            // Step 1: Design a attack plan
            AttackPlan ap = this.getTerritoryToAttack();
            if (ap == null)
            {
                LoggerController.log(MessageEnum.WARNING,"No territory found to attack.");
                break;
            }

            TerritoryInterface attackFrom = ap.from;
            TerritoryInterface attackTo = ap.to;
            
            //Notify the attack to View 
            sendNotification("GameChange", this.getName()+": Attacked from "+attackFrom.getName()+" to "+attackTo.getName()+"("+attackTo.getOwner().getName()+")");
            
            // Step 2: Number of armies(Dices) for the battle
            int diceAttack = DiceRNG.getRandomInt(3,1);

            //Step 3: Checking sufficient armies to attack
            if (attackFrom.getArmies() - diceAttack >= 1)
            {
                int diceDefend = DiceRNG.getRandomInt(2,1);
                //Step 4: Checking sufficient armies to defend
                if(diceDefend == 2 && attackTo.getArmies() < 2)
                {
                    diceDefend = 1;
                }

                Message.log(String.format("%s attacks %s from %s with %s armies and %s defends with %s armies", this.getName(), attackTo.getName(),
                        attackFrom.getName(), diceAttack, attackTo.getName(), diceDefend ));

                //Step 5: Rolling dices
                ArrayList<Integer> attackDices = new ArrayList<>();
                ArrayList<Integer> defendDices = new ArrayList<>();
                Dice dice = new Dice();
                for(int i=0; i<diceAttack; i++)
                    attackDices.add(dice.roll());
                for(int i=0; i<diceDefend; i++)
                    defendDices.add(dice.roll());

                //Step 6: Sorting die rolls
                Collections.sort(attackDices);
                Collections.sort(defendDices);

                LoggerController.log(String.format("%s(Attacker) rolled these dices %s",  attackFrom.getOwner().getName(),attackDices.toString()));
                LoggerController.log(String.format("%s(Defender) rolled these dices %s",  attackTo.getOwner().getName(),defendDices.toString()));

                //Step 7: calculating number of fights
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
                        LoggerController.log(attackTo.getOwner().getState());

                        sendNotification("GameChange", attackTo.getOwner().getName()+": Killed one Of Armies of "+attackFrom.getOwner().getName()+" in "+attackTo.getName()+" as I won the dice");
                        
                        Message.log(String.format("1 of the armies in %s(Defender) was killed.", attackTo.getName()));
                        attackTo.killArmies(1);
                        LoggerController.log(attackTo.getOwner().getState());
                        
                        
                        // checking for occupying the territory
                        if(attackTo.getArmies()==0)
                        {
                            LoggerController.log(String.format("%s occupies %s", attackFrom.getOwner().getName(),
                                    attackTo.getName()));
                            LoggerController.log(attackFrom.getOwner().getState());
                            attackTo.getOwner().lostTerritory(attackTo);
                            attackFrom.getOwner().ownTerritory(attackTo);
                            Card crd = this.getGameDriver().cardDeck.grantCard(this);
                            LoggerController.log(String.format("%s gets one card %s, %s", this.getName(),
                                    crd.getCardTerritoryName(), crd.getCardValue()));
                            LoggerController.log(attackFrom.getOwner().getState());
                        }
                    }
                    else
                    {
                        // Defender wins
                        LoggerController.log(attackFrom.getOwner().getState());
                        sendNotification("GameChange", attackFrom.getOwner().getName()+": Killed one Of Armies of "+attackTo.getOwner().getName()+" in "+attackFrom.getName()+" as I won the dice");
                        Message.log(String.format("1 of the armies in %s(Attacker) was killed.",attackFrom.getName()));
                        attackFrom.killArmies(1);
                        LoggerController.log(attackFrom.getOwner().getState());
                    }

                    attackDices.remove(0);
                    defendDices.remove(0);
                }

                //Step 9: after attack if occupied move remaining attack armies to new territory
                if(attackTo.getOwner() == attackFrom.getOwner())
                {
                    //Step 10: calculating moving armies to new territory
                    int movingArmies = 1;
                    attackFrom.removeArmies(movingArmies);
                    attackTo.placeArmies(movingArmies);
                    LoggerController.log(String.format("%s places %s armies to occupied territory(%s)",
                            attackTo.getOwner().getName(), movingArmies, attackTo.getName()));
                    Message.log(this.getState());
                }

            }
            else
            {
            	Message.log(String.format("Attacking %s from %s with %s armies canceled. %s -> %s", attackTo.getName(),
                        attackFrom.getName(), diceAttack, attackFrom.getArmies() , attackTo.getArmies()));
            }


            LoggerController.log(String.format("Attack %s finished.", a));
        }


        LoggerController.log(String.format("============%s ATTACK DONE===========", this.getName()));
    }


    /**
     * does the fortification phase and randomly move armies to another territories
     * owned by the player
     */
    public void fortification()
    {
        LoggerController.log(String.format("============%s FORTIFICATION STARTS===========", this.getName()));
        this.gm.setPhase("FORTIFICATION PHASE");
        sendNotification("PhaseChange", "PhaseChange:"+this.getName()+" Fortification");
        
        TerritoryInterface from = this.getRandomTerritory();
        TerritoryInterface to;

        ArrayList<TerritoryInterface> neighbours = from.getAdjacentNeighbours();
        if(neighbours.size()>0)
            to = neighbours.get(0);
        else
            to = this.getRandomTerritory();

        int number = DiceRNG.getRandomInt(from.getArmies(),1);
        this.moveArmies(from, to, number);


        LoggerController.log(String.format("============%s FORTIFICATION DONE===========", this.getName()));
        sendNotification("GameChange", this.getName()+": Done his fortification");
    }


    /**
     * implementation of Compareable
     * @param o player to compare to
     * @return if they are equal or not
     */
    @Override
    public int compareTo(PlayerInterface o) {
        return (int)(this.getDomination() - o.getDomination());
    }

    /**
     * What strategy is being used.
     * @return the strategy
     */
    @Override
    public StrategyInterface getStrategy() {
        return this.strategy;
    }

    /**
     * set the strategy to play with
     * @param strategy new strategy
     */
    @Override
    public void setStrategy(StrategyInterface strategy) {
        this.strategy = strategy;
    }


    /**
     * set the if player is active or not
     * @param status new status
     */
    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * get the if player is active or not
     * @return status
     */
    @Override
    public boolean getStatus() {
        return this.status;
    }

    @Override
    public void addCard(Card crd) {
        this.cards.add(crd);
    }

    @Override
    public ArrayList<Card> getCardSet() {
        ArrayList<Card> result  = null;

        if(this.cards.size()>=3)
        {
            result = new ArrayList<>();
            for(int i=0; i<3; i++)
                result.add(this.cards.get(i));

            this.cards.removeAll(result);
        }

        return result;
    }

    @Override
    public void increaseTrades()
    {
        this.trades++;
    }

    @Override
    public int getCardsSize() {
        return this.cards.size();
    }

    @Override
    public int getTrades()
    {
        return this.trades;
    }
}
