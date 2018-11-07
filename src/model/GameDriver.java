package model;


import model.Interfaces.*;
import model.strategy.Aggressive;
import model.strategy.Defensive;
import model.strategy.Normal;
import utility.Gradient;
import exceptions.PlayerException;

import java.util.ArrayList;
import java.util.Collections;


import controller.LoggerController;


/**
 * This is the main game manager that controls the game
 * @author Muhammad_Hamza_Noor
 * @author Waleed Ahmad
 * @author Meet Patel
 * @version 2.0.0
 */
public class GameDriver extends Model {

    private static int MIN_PLAYERS = 2;
    private static int MAX_PLAYERS = 6;

    private int numberOfPlayers = 0;
    private int turn = -1;

    private int strategyTurn = -1;
    ArrayList<StrategyInterface> strategies = new ArrayList<>();

    public Deck cardDeck = new Deck();

    private boolean isGameOn=false;

    private MapInterface map;
    private ArrayList<PlayerInterface> playerlist = new ArrayList<>();
    private String currentPhase = "";


    /**
     * Class constructor.
     * It sets the map and players field then
     * calls initGame that to add Players then
     * allocate default armies to each player then
     * allocate countries randomly to players
     * finally game is started
     * @param players number of players
     * @throws PlayerException be careful
     */
    public GameDriver(int players) {

        this.numberOfPlayers = players;
        this.map = new Map();
        strategies.add(new Normal());
        strategies.add(new Defensive());
        strategies.add(new Aggressive());
    }


    /**
     * constructor
     * @param m selected map
     * @param players number of players
     */
    public GameDriver(MapInterface m,int players) {

        this.numberOfPlayers = players;
        this.map = m;
        strategies.add(new Normal());
        strategies.add(new Defensive());
        strategies.add(new Aggressive());
    }

    /**
     * Start the game
     * @throws PlayerException be careful
     */
    public void start() throws PlayerException
    {
        this.setPhase("Startup");
        this.initGame();
        sendNotification("GamePlay", playerlist);
        this.isGameOn = true;
        this.setPhase("GamePlay");
        sendNotification("PhaseChange", "PhaseChange:Game Play");
        this.play();
    }


    /**
     * to start the game
     * @param play should they start the game
     * @throws PlayerException
     */
    public void start(boolean play) throws PlayerException
    {
        this.setPhase("Startup");
        this.initGame();
        this.isGameOn = true;
        this.setPhase("GamePlay");
        if (play)
            this.play();
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
        LoggerController.log("---------Adding Players---------");
        addPlayers();

        //Step 2: Allocate initial armies according to the rules
        LoggerController.log("---------Allocating Initial Armies---------");
        allocateInitialArmies();

        //Step 3: Randomly allocate the countries in the map
        LoggerController.log("---------Allocating Territories---------");
        allocateTerritories();

        //Step 4: Place armies into territories in turn
        LoggerController.log("---------Placing armies one by one into territories---------");
        placeInitialArmies();

    }

    /**
     * this is the method that handles the Gameplay
     */
    public void play()
    {
        this.resetTurn();
        int i = 1;
        LoggerController.log("---------Starting The Game---------");
        while(this.isGameOn)
        {
            LoggerController.log(String.format("---------Turn %s---------", i));
            PlayerInterface p = nextPlayer();
            
            p.reinforcement();
            
            p.attack();

            p.fortification();

            PlayerInterface winner = getWinner();
            if (winner == null)
            {
                LoggerController.log("Next turn");
            }

            i++;
            if (winner!= null ) //|| i == 3)
                this.isGameOn=false;
        }

        String dominationView = this.domitantionResult(true, i);
        LoggerController.log(dominationView);
        this.sendNotification(NotificationType.DominationView, dominationView);
        //LoggerController.log(this.getWinner().getName());

    }


    /**
     * return list of continents controlled by the player
     * @param p player
     * @return list of continents
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
     * calculates reinforcement armies for each player
     * @param p player
     * @return number or armies the player should get
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
     * card exchange logic
     * @param p player
     * @return number of armies player gets in exchange
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

            LoggerController.log(String.format("Player %s received %s Armies via card exchange(exchange no %s)", p.getName(),
                    exchangeValue, p.getTrades()));
        }
        return exchangeValue;
    }


    /**
     * this method helps players to move armies from a territory to another
     * @param p player
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
     * according to the game rules
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
     * this method add players to the game
     * it uses the number which is given while creating game instance.
     * @throws PlayerException be careful
     */
    public void addPlayers() throws PlayerException {

        if (this.numberOfPlayers > MAX_PLAYERS || this.numberOfPlayers < MIN_PLAYERS)
            throw new PlayerException();


        Gradient colorManager = new Gradient();
        for (int i=1; i<=this.numberOfPlayers; i++) {
            StrategyInterface strategy = getRandomStrategy();
            PlayerInterface p = new Players("Player " + Integer.toString(i), colorManager.getRandomColor(), strategy);
            p.setGameDriver(this);
            this.playerlist.add(p);
            LoggerController.log(p.toString() + " was added to the game.");
        }
        colorManager = null;
    }

    /**
     * calculates initial armies according to the game rules
     * @return number of armies
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
        LoggerController.log(String.format("%s armies allocated to each player.", initialArmies));
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
     * returns next player based on turns
     * @return player object
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
        	sendNotification("GameChange", result.getName()+": Phase started");
        
       
        
        return result;
    }

    /**
     * reset the turn
     * @see GameDriver#nextPlayer()
     */
    private void resetTurn() { this.turn = -1; }


    /**
     * what phase is the game in now
     * @return phase name
     */
    public String getPhase() { return this.currentPhase; }
    public void setPhase(String value) {
        this.currentPhase = value;
    }


    /**
     * Generate domination view string
     * @param verbos to generate texts or just calculate winner
     * @return domination view
     */
    public String domitantionResult(boolean verbos, int trn)
    {
        StringBuilder sb = new StringBuilder();
        if(verbos)
            sb.append(String.format("===DOMINATION VIEW AT TURN %s===\n", trn));

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

        if(verbos) {
        	for(PlayerInterface p:tmp)
        		sb.append(String.format("%s(%s) controls %s of the map.\n", p.getName(), p.getStrategy().getName(), p.getDomination()));
        }

        sendNotification("DominationView", "DominationView: "+sb.toString());
        
        if(verbos)
            sb.append("---------------------");

        return sb.toString();

    }


    /**
     * Determine the winner
     * @return player who won the game
     */
    public PlayerInterface getWinner()
    {
        PlayerInterface winner = null;
        this.domitantionResult(false,0);

        for(PlayerInterface p : this.playerlist)
            //if(p.getDomination()>85.0)
            if(p.getDomination()>45.0)
            {
                winner = p;
            }

        return winner;

    }
    /**
     * get a random playing strategy
     * @return strategy to play the game
     */
    public StrategyInterface getRandomStrategy()
    {
        if(this.strategyTurn==this.strategies.size()-1)
            this.strategyTurn = -1;
        this.strategyTurn++;

        return this.strategies.get(strategyTurn);

    }

}
