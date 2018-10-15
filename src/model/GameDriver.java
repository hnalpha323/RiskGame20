package model;

import model.Interfaces.ContinentInterface;
import model.Interfaces.MapInterface;
import model.Interfaces.PlayerInterface;
import model.Interfaces.TerritoryInterface;
import utility.Gradient;
import utility.DiceRNG;
//import utility.MessageEnum;
//import utility.exception.InvalidNumOfPlayersException;
//import utility.Results;
import java.util.ArrayList;
import controller.LoggerController;

/**
 * This Class does the following.
 * A startup phase for setting up the map and players
 * Adding and Allocating Initial Armies to Players
 * Allocating random countries to players
 * 
 * @author Meet_Patel
 * @author Muhammad_Hamza_Noor
 * @author WaleedAhmad
 */

public class GameDriver {
	
	//private static int MINIMUM_PLAYERS = 2;
    //private static int MAXIMUM_PLAYERS = 6;

    private int numberOfPlayers = 0;
    private int turn = -1;

    private boolean isGameOn=false;

    private MapInterface map;
    private ArrayList<PlayerInterface> playerlist = new ArrayList<PlayerInterface>();


    
    public GameDriver(int players) {

        this.numberOfPlayers = players;
        this.map = new Map();
    }
    
    
    public GameDriver(MapInterface m,int players) {

        this.numberOfPlayers = players;
        this.map = m;

    }

    /**
     * Function that calls necessary methods to start the game
     */
    public void start() //throws InvalidNumberofException
    {
        this.initGame();
        this.isGameOn = true;
        this.play();
    }

    /**
     * Initialization method that calls necessary other methods needed for the Startup Phase
     */
    public void initGame() //throws InvalidNumOfPlayersException
    {
        //Addin Initial Armies to Players
        LoggerController.log("Adding Players...");
        addPlayers();

        //Allocating Initial Armies to Players
        LoggerController.log("Allocating Initial Armies...");
        allocateInitialArmies();

        //Randomly allocating the countries to Players
        LoggerController.log("Allocating Territories...");
        allocateTerritories();

        //Place armies to all the Players
        LoggerController.log("Placing armies to all players into territories...");
        placeInitialArmies();

    }

    /**
    * Method that calls Reinforcement and Fortification phases
    */
    public void play()
    {
        this.resetTurn();
        int i = 1;
        LoggerController.log("PLAYING...");
        while(this.isGameOn)
        {
            LoggerController.log(String.format("Turn Number %s", i));
            PlayerInterface p = nextPlayer();
            reinforcement(p);
            attack(p);
            fortification(p);
            i++;
            if (i==this.numberOfPlayers)
                this.isGameOn=false;
        }

    }


    public void attack(PlayerInterface p)
    {
        LoggerController.log(String.format("Starting Attack Phase...", p.getName()));
        LoggerController.log(String.format("Attack Phase Done", p.getName()));
    }

    /**
     * Fortification Phase:
     * This Program Randomly moves armies to other territories owned by that player
     * @param Player
     */
    public void fortification(PlayerInterface p)
    {
        LoggerController.log(String.format("Starting Fortification Phase...", p.getName()));

        TerritoryInterface from = p.getRandomTerritory();
        TerritoryInterface to;

        ArrayList<TerritoryInterface> neighbours = from.getAdjacentTerritoryObjects();
        if(neighbours.size()>0)
            to = neighbours.get(0);
        else
            to = p.getRandomTerritory();

        int number = DiceRNG.getRandomInt(from.getArmies(),1);
        p.moveArmies(from, to, number);


        LoggerController.log(String.format("Fortification Finished", p.getName()));
    }

    /**
     * Reinforcement phase:
     * Give Player the armies according the rules
     * Allocate armies as player wants to their territories only
     * @param Player who wants to reinforce
     */
    public void reinforcement(PlayerInterface p)
    {
        LoggerController.log(String.format("Starting Reinforcement Phase...", p.getName()));


        int newArmies = calculateReinforcementArmies(p);
        p.setUnusedArmies(newArmies);


        this.placeArmies(p);
        LoggerController.log(String.format("Reinforcement Finished", p.getName()));
    }


    public int calculateReinforcementArmies(PlayerInterface p)
    {
        int result = 0;


        result += p.getTerritories().size() / 3;
        if(result<3)
            result = 3; 

        return result;
    }


    /**
     * Method to Move players from one territory to another
     * @param Player that wants to move armies
     */
    public void placeArmies(PlayerInterface p)
    {
        int armiesToPlace = p.getUnusedArmies();
        int i = 0;
        while(i<armiesToPlace )
        {
            LoggerController.log(p.getState());
            TerritoryInterface playerRandomTerritory = p.getRandomTerritory();
            int randomArmy = utility.DiceRNG.getRandomInt(p.getUnusedArmies(),1);

            p.placeArmy(randomArmy, playerRandomTerritory);
            i += randomArmy;

            LoggerController.log(p.getState());
        }

    }

    /**
     * This method automatically place initial armies into territories one by one
     * Method to Initially assign armies to territories of each players
     */
    public void placeInitialArmies()
    {

        int armiesToPlace = 0;
        for(PlayerInterface x:this.playerlist)
            armiesToPlace+=x.getUnusedArmies();

        int i = 0;
        while(i<armiesToPlace )
        {
            PlayerInterface p = nextPlayer();
            if(p.getUnusedArmies()==0)
                continue;

            LoggerController.log(p.getState());

            TerritoryInterface playerRandomTerritory  = p.getRandomTerritory();
            int randomArmy = 1;

            p.placeArmy(randomArmy, playerRandomTerritory  );
            i += randomArmy;

            LoggerController.log(p.getState());
        }

    }

    /**
     * Method to populate players in the game
     */
    public void addPlayers() //throws InvalidNumOfPlayersException 
    {

        //if (this.numberOfPlayers > MAXIMUM_PLAYERS || this.numberOfPlayers < MINIMUM_PLAYERS)
        //    throw new InvalidNumOfPlayersException();


    	Gradient Gradientcolor = new Gradient();
        for (int i=1; i<=this.numberOfPlayers; i++) {
            PlayerInterface p = new Players("Player " + Integer.toString(i), Gradientcolor.getRandomColor());
            this.playerlist.add(p);
            LoggerController.log(p.toString() + " was added to the game.");
        }
        Gradientcolor = null;
    }

    /**
     * To determine initial Armies based on the number of players according the rules
     * @return Number of Armies that needs to be assigned
     */
    public int calculateInitialArmies()
    {
        int result = 0;

        switch (this.numberOfPlayers)
        {
            case 2:
                result = 40;
                break;
            case 3:
                result = 35;
                break;
            case 4:
                result = 30;
                break;
            case 5:
                result = 25;
                break;
            case 6:
                result = 20;
                break;
        }

        return result;
    }


    public void allocateInitialArmies()
    {
        int initialArmies = calculateInitialArmies();
        for(PlayerInterface p : this.playerlist)
        {
            p.setUnusedArmies(initialArmies);
        }
        LoggerController.log(String.format("Number of armies Allocated to each player: %s", initialArmies));
    }


    /**
     * Allocating random countries to players
     */
    public void allocateTerritories()
    {

        for(ContinentInterface c:this.map.getContinents())
        {
            for (TerritoryInterface t: c.getTerritories())
            {
                PlayerInterface p = this.nextPlayer();
                p.ownTerritory(t);
            }
        }
    }

    /**
     * Determine Who is the next player
     * @return object that returns next player
     */
    public PlayerInterface nextPlayer()
    {
        if(this.turn == this.numberOfPlayers-1)
            turn = -1;
        turn++;
        return this.playerlist.get(turn);

    }

    /**
     * Setting turn to default once all players get their chance
     */
    private void resetTurn() { 
    this.turn = -1; 
    }

}
