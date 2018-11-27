package model;


import model.Interfaces.*;
import model.strategy.*;
import utility.Gradient;
import exceptions.PlayerException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;



/**
 * This is the main game manager that controls the game
 * @author Muhammad_Hamza_Noor
 * @author Waleed Ahmad
 * @author Meet Patel
 * @version 2.0.0
 */
public class GameDriver extends Observable implements Serializable 
{

	private static final long serialVersionUID = -6921437067469919760L;
	int playerCursor = 0;
	PlayerInterface temporarayPlayerholder;

    /** Holds the current turn of play */
    private int turn = -1;
    private int strategyTurn = -1;
    
    /** Holds name of the game */
    private String name = "Game";
    
    /** Holds a string of all player names and there strategies  */
    private String playersText = "";
    private String strategyString = "";
    
    
    /** Holds the current Phase name */ 
    private String currentPhase = "";
    
    /** Holds ArrayList of Strategies*/
    private ArrayList<StrategyInterface> strategies = new ArrayList<>();
    /** Holds ArrayList of players */
    private ArrayList<Players> playerlist = new ArrayList<>();
    
    /** Holds the map on which the game is going to be played */
    private Map map;
    
    /** Holds a deck of cards so players will get whenever then win on a territory */
    Deck cardDeck = new Deck();
    
    private int totalTurnsinGame = 500;
	private static int MIN_PLAYERS = 2;
    private static int MAX_PLAYERS = 6;

    private int numberOfPlayers = 0;
    private boolean isGameOn=false;

    /**
     * Sets the map and players field
     * Allocate default armies to each player
     * Allocate countries to players
     * Starts the game
     * @param Number of players
     * @param Strategies
     * @throws PlayersException
     */
    public void startGame(int players, String strategies) throws PlayerException 
    {
        this.numberOfPlayers = players;     
        this.map = new Map();
        start();
    }
    
    public GameDriver(int players, String strategies, int totalTurns) 
    {
        this.numberOfPlayers = players;
        this.map = new Map();
        this.strategyString = strategies;
        this.setStrategies();
        this.totalTurnsinGame = totalTurns;
    }

    public GameDriver(Map m,int players, String strategies, int totalTurns) 
    {
        this.numberOfPlayers = players;
        this.map = m;
        this.strategyString = strategies;
        this.setStrategies();
        this.totalTurnsinGame = totalTurns;
    }
    
    public GameDriver() 
    {
	}
    
    /**
     * Sets strategies
     * Sample for 3 players with Human, Random and Aggressive strategies is h,r,a
     */
	public void setStrategies()
    {
        String[] stra = this.strategyString.split(",");
        for(String s:stra)
        {
            switch (s)
            {
                case "h":
                    this.strategies.add(new Human());
                    break;
                case "a":
                    this.strategies.add(new Aggressive());
                    break;
                case "b":
                    this.strategies.add(new Benevolent());
                    break;
                case "r":
                    this.strategies.add(new Random());
                    break;
                case "c":
                    this.strategies.add(new Cheater());
                    break;
            }
        }
    }


    /**
     * Start the game
     * @throws PlayerException
     */
	public void start() throws PlayerException
    {
    	sendNotification("Game Change: StartUp");
    	this.setPhase("Startup");
        this.initGame();
        sendStartupEnd();
        this.isGameOn = true;
        this.setPhase("GamePlay");
        sendNotification("Game Change: StartUp phase finished \n Game Play is about to start");
        this.resetTurn();
    }


    /**
     * To start the game
     * @param Should they start the game
     * @throws PlayerException
     */
    public void start(boolean play) throws PlayerException
    {
        this.setPhase("Startup");
        this.initGame();
        this.isGameOn = true;
        this.setPhase("GamePlay");
        if (play)
            this.play(true);
    }
    
    /**
	 * Notifies all the player object observers when startUp phase is completed
	 */
	private void sendStartupEnd() 
	{
		for(PlayerInterface p : playerlist)
		{
			p.sendNotify(p.getState());
		}		
	}

    /**
     * Initialize the game steps
     * Step 1: Add players and give each them armies according to the rules
     * Step 2: Randomly allocate the countries in the map
     * Step 3: Allocate initial armies according to the rules
     * Step 4: Place armies onto territories in turn
     * @throws PlayerException be careful
     */
    public void initGame() throws PlayerException
    {
        //Step 1: Add players and give each them armies according to the rules
        Log.log("---------Adding Players---------");
        initPlayers();

        //Step 2: Allocate initial armies according to the rules
        Log.log("---------Allocating Initial Armies---------");
        allocateInitialArmies();

        //Step 3: Randomly allocate the countries in the map
        Log.log("---------Allocating Territories---------");
        allocateTerritories();

        //Step 4: Place armies into territories in turn
        Log.log("---------Placing armies one by one into territories---------");
        placeInitialArmies();

    }
    
    /**
     * This method moves the player to the next phase whenever it get called
     * If a player finished his 3 phases(reinforcement,attack,fortification) then selects next player 
     */
    public void takeNextTurn() 
    {
    	if(playerCursor ==0)
    	{
    		temporarayPlayerholder = nextPlayer();
    	}
    	else if(playerCursor == 1)
    	{
    		temporarayPlayerholder.sendNotify("CardView: Start showing");
    		temporarayPlayerholder.reinforcement();
    	}
    	else if(playerCursor == 2)
    	{
    		 temporarayPlayerholder.attack();
    	}
    	else if(playerCursor == 3)
    	{
    		temporarayPlayerholder.fortification();
    	}
    	
    	playerCursor++; 
    	if(playerCursor == 4)
    	{
    		playerCursor = 0;
    	}
    	this.domitantionResult();
	}
    
    
    /**
     * This method calculates the domination of each player and 
     * sends it to the Observers
     */
    public void domitantionResult()
    {
    	 String tmp = "";
    	 int totalNoOfTerritories = 0;
    	 //to get total number of territories in the map
    	 for(PlayerInterface p:this.playerlist)
         {
    		 totalNoOfTerritories += p.getTerritories().size();
         }
    	 //to get percentage of map occupied by each player
    	 for(PlayerInterface p:this.playerlist)
         {
    		 double control_percent = Math.round(((double) p.getTerritories().size() / totalNoOfTerritories) * 100);
             tmp += "\n"+ p.getName()+"="+control_percent;
         }
    	 
    	 sendNotification("DominationView: "+tmp);
    }
    

    /**
     * this is the method that handles the Gameplay
     */
    public FinalResult play(boolean verbos)
    {
        PlayerInterface winner = null;
        this.resetTurn();
        int i = 1;
        if(verbos)
            Log.log("-------PLAYING-------");
        while(this.isGameOn)
        {
            if(verbos)
                Log.log(String.format("-------Turn %s-------", i));
            
            //Step1:Selects a player in round robin 
            PlayerInterface p = nextPlayer();
            //Step2: Gives him reinforcement
            p.reinforcement();
            //Step3: And the attack
            p.attack();
            //Step4: And then fortification
            p.fortification();
            //Step5: Check if player own entire map, if not continue
            winner = getWinner();
            
            if (winner == null)
            {
                Log.log("No winner, so next turn will start.");
            }

            i++;
            if (winner!= null || i == totalTurnsinGame)
                this.isGameOn=false;
        }

        if (verbos)
        {
            String dominationView = this.dominationResult(true, i);
            Log.log(dominationView);
        }
        
        String winnerName = "Draw";
        if (winner != null)
            winnerName = winner.getStrategy().getName();
        return new FinalResult(map.getName(), winnerName);
 
    }


    /**
     * Calculates Reinforcement Armies for each player
     * @param player
     * @return Number of Armies the player should get
     */
    public int calculateReinforcementArmies(PlayerInterface p)
    {
        int result = 0;

        //Step 1: calculate based on occupied territories
        result += p.getTerritories().size() / 3;
        if(result<3)
            result = 3; // Since the minimum is 3 armies.

        //Step 2: if player has occupied all the continent
        for(ContinentInterface c: this.map.getContinents() )
        {
            boolean isKing = true;
            for(TerritoryInterface t : c.getTerritories())
            {
                if (t.getOwner() != this)
                    isKing = false;
            }

            if (isKing)
                result += c.getContinentValue();
        }

        //Step 3: card exchanging
        result += exchangeCard(p);

        return result;
    }



    /**
     * This method moves armies from a territory to another
     * @param Player who wants to place armies
     */
    public void placeArmies(PlayerInterface p)
    {
        int armiesToPlace = p.getUnusedArmies();
        int i = 0;
        while(i<armiesToPlace )
        {
            Log.log(p.getState());
            TerritoryInterface playerRandomTerritory = p.getStrategy().getInforcementTerritory(p);
            int randomArmy = p.getStrategy().getReinforcementArmies(p);
            p.placeArmy(randomArmy, playerRandomTerritory);
            i += randomArmy;
            Log.log(p.getState());
        }

    }

    /**
     * This method automatically places initial armies into territories one by one
     * according to the game rules in the start-up phase
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

            Log.log(p.getState());

            TerritoryInterface playerRandomTerritory  = p.getRandomTerritory();
            int randomArmy = 1;

            p.placeArmy(randomArmy, playerRandomTerritory  );
            i += randomArmy;

            Log.log(p.getState());
        }

    }

    /**
     * this method add players to the game
     * it uses the number which is given while creating game instance.
     * @throws InvalidNumOfPlayersException be careful
     */
    public void initPlayers() throws PlayerException 
    {

        if (this.numberOfPlayers > MAX_PLAYERS || this.numberOfPlayers < MIN_PLAYERS)
            throw new PlayerException();
  
        //Give a random color to the players
        Gradient colorManager = new Gradient();
        //If player list is empty create players else just add strategies
        if(this.playerlist.size() == 0)
        {
        	for (int i=1; i<=this.numberOfPlayers; i++) 
        	{
                StrategyInterface strategy = strategies.get(i-1);
                Players p = new Players("Player " + Integer.toString(i), colorManager.getRandomColor(), strategy);
                this.playersText += p.getStrategy().getName() +", ";
                p.setGameDriver(this);
                this.playerlist.add(p);
                Log.log(p.toString() + " was added to the game.");
        	}	
        }
        else
        {
        	for (int i=0; i<this.playerlist.size(); i++) 
        	{
        		StrategyInterface strategy = strategies.get(i); 
        		playerlist.get(i).setStrategy(strategy);
        		playerlist.get(i).setGradient( colorManager.getRandomColor());
                this.playersText += playerlist.get(i).getStrategy().getName() +", ";
        		playerlist.get(i).setGameDriver(this);
        		Log.log(playerlist.get(i).toString() + " was added to the game.");
        	}
        }	
        colorManager = null;
    	
    }

    /**
     * Calculates initial armies according to the game rules
     * @return Number of armies
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

    /**
     * calculates initial armies by calling the appropriate method then
     * sets the return number for each player
     */
    public void allocateInitialArmies()
    {
        int initialArmies = calculateInitialArmies();
        for(PlayerInterface p : this.playerlist)
        {
            p.setUnusedArmies(initialArmies);
        }
        Log.log(String.format("%s Armies allocated to each player.", initialArmies));
    }


    /**
     * Randomly allocates territories to players
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
     * Returns next player based on turns
     */
    public PlayerInterface nextPlayer()
    {
        for(PlayerInterface p: this.playerlist)
        {
            if((p.getTerritories().size() == 0) && !this.getPhase().equals("Startup"))
            {
                p.setStatus(false);
            }
        }

        PlayerInterface result=null;
        while(result==null)
        {
            if(this.turn == this.numberOfPlayers-1)
                turn = -1;
            turn++;
            PlayerInterface tmp = this.playerlist.get(turn);
            if (tmp.getStatus())
                result = tmp;
        }

        if(!getPhase().equals("Startup"))
        	sendNotification("Game Change: "+result.getName()+" Turn started");
        
              
        return result;
    }

    /**
     * Resets the turn
     */
    private void resetTurn() 
    { 
    	this.turn = -1; 
    }


    /**
     * Returns what phase is the game is in now
     */
    public String getPhase() 
    {
    	return this.currentPhase; 
    }

    /**
     * Sets the phase
     */
    public void setPhase(String value) 
    {
        this.currentPhase = value;
    }


    /**
     * card exchange logic
     * @param p player who want to exchange the cards
     * @return number of armies a player got
     */
    public int exchangeCard(PlayerInterface p)
    {
        int exchangeValue = 0;

        if(p.getCardsSize() > 3)
        {
            ArrayList<Card> cards = p.getCardSet();
            for(Card c : cards)
                cardDeck.returnCard(c);
            switch (p.getTrades())
            {
                case 1:
                    exchangeValue = 4;
                    p.increaseTrades();
                    break;
                case 2:
                    exchangeValue = 6;
                    p.increaseTrades();
                    break;
                case 3:
                    exchangeValue = 8;
                    p.increaseTrades();
                    break;
                case 4:
                    exchangeValue = 10;
                    p.increaseTrades();
                    break;
                case 5:
                    exchangeValue = 12;
                    p.increaseTrades();
                    break;
                case 6:
                    exchangeValue = 15;
                    p.increaseTrades();
                    break;
                default:
                    exchangeValue = 15 + (p.getTrades() - 6) * 5;
                    p.increaseTrades();
                    break;
            }

            Log.log(String.format("%s Received %s Armies from Card Exchange(Exchange no %s)", p.getName(),
                    exchangeValue, p.getTrades()));
        }
        return exchangeValue;
    }


    /**
     * Generates Domination View String
     * @param Verbos to generate texts or just calculate winner
     * @param Turn tells the current turn of the game
     * @return String containing the Domination Result
     */
    public String dominationResult(boolean verbos, int turn)
    {
        StringBuilder sb = new StringBuilder();
        if(verbos)
            sb.append(String.format("-------Domination View at Turn %s-------\n", turn));

        int total_territories = 0;
        for(PlayerInterface p:this.playerlist)
            total_territories+=p.getTerritories().size();

        ArrayList<PlayerInterface> tmp = new ArrayList<>();
        for(PlayerInterface p:this.playerlist)
        {
            double control_percent = Math.round(((double) p.getTerritories().size() / (double) total_territories) * 100) ;
            p.setDomination(control_percent);
            tmp.add(p);
        }

        Collections.sort(tmp);
        Collections.reverse(tmp);

        if(verbos) 
        {
        	for(PlayerInterface p:tmp)
        		sb.append(String.format("%s(%s) Controls %s of the Map.\n", p.getName(), p.getStrategy().getName(), p.getDomination()));
        }

        sendNotification("Domination View: "+sb.toString());
        
        if(verbos)
            sb.append("---------------------");

        return sb.toString();

    }


    /**
     * Determine the winner
     * @return Player who won the game
     */
    public PlayerInterface getWinner()
    {
        PlayerInterface winner = null;
        this.dominationResult(false,0);

        for(PlayerInterface p : this.playerlist)
            if(p.getDomination()>90.0)
            {
                winner = p;
            }

        return winner;

    }

    /**
     * Get a Random playing strategy
     * @return Strategy to play the game
     */
    public StrategyInterface getRandomStrategy()
    {
        if(this.strategyTurn==this.getStrategies().size()-1)
            this.strategyTurn = -1;
        this.strategyTurn++;

        return this.getStrategies().get(strategyTurn);

    }



	/**
	 * Adds a player to the game
	 * @param newPlayer is a {@link Player}
	 */
	public void addPlayer(Players newPlayer) 
	{
		this.playerlist.add(newPlayer);
		
	}


	 /**
     * Return List of Continents controlled by the player
     * @param Player
     * @return List of Continents
     */
    public ArrayList<ContinentInterface> ContinentControlledBy(PlayerInterface p)
    {
        ArrayList<ContinentInterface> result = new ArrayList<>();
        boolean isKing = true;
        for(ContinentInterface c: this.map.getContinents() )
        {
            for(TerritoryInterface t : c.getTerritories())
            {
                if (t.getOwner() != p)
                    isKing = false;
            }
            if (isKing)
                result.add(c);
        }
        return result;
}
	
	/**
	 * This Method Notifies all Observer about the update
	 * @param type is the type of notification
	 */
	private void sendNotification(String type) 
	{
		setChanged();
		notifyObservers(type);				
	}



	public static void main(String[] args)
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        ArrayList<Map> maps = new ArrayList<>();
        maps.add((Map) m);


        GameDriver gd = new GameDriver(m, 3,"r,r,r", 5);
        try
        {
            gd.start(false);
            gd.play(true);
        }
        catch (Exception e)
        {

        }
    }

    /**
     * Find a Territory
     * @param Name of The Territory
     * @return Territory Object
     */
    public TerritoryInterface getTerritory(String name)
    {
        TerritoryInterface res = null;
        for (ContinentInterface c : map.getContinents())
        {
            for(TerritoryInterface t : c.getTerritories())
            {
                if(t.getName().equals(name))
                {
                    res = t;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Sets the Game Name
     * @param Name of the game
     */
    public void setName(String name) 
    { 
    	this.name = name; 
    }

    /**
     * Gets the Game Name
     * @return Name of the game
     */
    public String getName() 
    { 
    	return this.name; 
    }

    
    public String getPlayersText() 
    { 
    	return this.playersText; 
    }


	/**
	 * Adds Strategy to the strategies of the players
	 */
	public void addStrategies(StrategyInterface strategy) 
	{		
		this.getStrategies().add(strategy);
	}


	/**
	 * Sets Answer Given by user for Human player strategy
	 */
	public void setAnswerForHuman(String answerByHuman) 
	{
		Human.sharedTmp = answerByHuman;		
	}


	/**
	 * @return all Player objects in playerlist
	 */
	public ArrayList<Players> getPlayers() 
	{
		return playerlist;
	}


	/**
	 * @return the strategies in the current game
	 */
	public ArrayList<StrategyInterface> getStrategies() 
	{
		return strategies;
	}

	

}
