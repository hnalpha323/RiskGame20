package gameLauncher;

import java.util.ArrayList;

import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.TournamentController;	
import controller.WriteController;
import model.DataReader;
import model.DataWriter;

import model.GameDriver;
import model.Log;
import model.Players;
import model.SaveMethod;
import model.ManageTournament;
import model.Interfaces.StrategyInterface;
import model.strategy.Aggressive;		
import model.strategy.Benevolent;		
import model.strategy.Cheater;		
import model.strategy.Human;		
import model.strategy.Random;
import view.CardView;
import view.DominationView;
import view.Message;
import view.PhaseView;
import view.GUIManager;

import view.ManualPlayerView;		
	



/** 
 * Class that contains Main function which will launch the Game
 * This class initializes controllers,View and connects them.
 * 
 * @author Meet_Patel
 * @version 3.0.0
 */

public class Launcher
{
	
	static GameDriver gameDriver;
	
	/**		
	* Tells if a human is playing or not		
	*/		
	static boolean isHumanPlaying = false;		
					
	/**		
	* Takes care about the UI interface of Human player		
	*/		
	static ManualPlayerView manualPlayerView = null;		
				
	/** 				
	* Initialize the controllers		
	* Create the Views
	* Inject controllers into Views through GUIManager		
	* Add Observers to players whenever users gives number of players input				
	*/
		
	public static void main(String[] args) 
	{
		//Creates Controller to controls read and write the .map file
		 RWMapFileController rwMapFileController = new RWMapFileController();
		 
		 //Creates controller, which is responsible to redirect Read operations
		 ReadController readController = new ReadController(new DataReader());
		 
		 //Creates controller, which is responsible to redirect Write operations
		 WriteController writeController = new WriteController(new DataWriter());
		 
		 //Creates controller and manger who are responsible to play the tournament
		 ManageTournament manageTournament = new ManageTournament();	
		 TournamentController tournamentController = new TournamentController(manageTournament);
		 
		 //Creates Game Manager and sends it to GameController	
		 gameDriver = new GameDriver();	
		 
		 //To save the state of the game	
		 SaveMethod saveMethod = new SaveMethod();		
		 GameController gameController = new GameController(gameDriver,saveMethod);
		 
		 //Creates Domination View and to make it Observer
		 DominationView dominationView = new DominationView();
		 		 
		 //Instantiate the human player view	
		 manualPlayerView = new ManualPlayerView(gameController);
				 
		 //Creates phaseView make it Observer
		 CardView  cardView= new CardView();	
		 PhaseView phaseView = new PhaseView(dominationView,gameController,cardView,manualPlayerView);		 			
		 
		 //Creates Log model and adds view as Observer	
		 Log log = new Log();		
		 log.addObserver(new Message());		
		 Log.setLogger(log);
		 		 
		 //Sends all controllers to view manager, such that views can contact
		 GUIManager.addControllers(rwMapFileController,readController,writeController,gameController,tournamentController);
		 GUIManager.setView(phaseView,"phaseview");
		 
		 //Create Players objects and add observers only when users gives number of player inputs
		 GUIManager.addCallBack(new Calls(){
			    public void called(int numberOfPlayers, String strategies)
			    {
			    	gameDriver.addObserver(dominationView);
			    	gameDriver.addObserver(phaseView);
					setStrategies(strategies);
			    	for(int i=1;i<=numberOfPlayers;i++)
			    	{
			    		Players p = new Players("Player " + Integer.toString(i));
			    		p.addObserver(dominationView);
			    		p.addObserver(phaseView);
			    		p.addObserver(cardView);
			    		gameDriver.addPlayer(p);
			    	}			    	
			    }
		 });
		 
		 //this Calls is called whenever user want to resume the previous game
		 GUIManager.addCallBack(new Calls()
		 {
			    public void called(int numberOfPlayers, String strategies)
			    {
			    	ArrayList<Players> playerList = gameDriver.getPlayers();			    	
			        for(Players p : playerList)
			        {
			        	p.addObserver(dominationView);
			        	p.addObserver(cardView);
			    		p.addObserver(phaseView);	
			        }
			        gameDriver.addObserver(dominationView);
					gameDriver.addObserver(phaseView);
					gameDriver.addObserver(cardView);
					ArrayList<StrategyInterface> playerStrategies = gameDriver.getStrategies();
					for(int i = 0;i<playerStrategies.size();i++){
						if(playerStrategies.get(i).getName().equals("Human"))
						{
							Human h = (Human)playerStrategies.get(i);
							h.addObserver(manualPlayerView);
							playerStrategies.set(i, h);
						}
					}
			    }
		 });
		
		//this Calls is called whenever user want to resume the previous game
		 GUIManager.addCallBack(new Calls()
		 {
			    public <T> void called(T object)
			    {
			    	gameDriver = (GameDriver) object;
			    	gameController.setGameDriver(gameDriver);
			    }
		 });	    
		 
		 javafx.application.Application.launch(GUIManager.class);
	}
	
	 /**
     * set strategies according to strategies string
     * @param strategyString contains strategies of players going to play the game 
     * sample for 3 players with Human, Random and Aggressive strategies is h,r,a
     */
	public static void setStrategies(String strategyString)
    {
        String[] stra = strategyString.split(",");
        
        for(String s:stra)
        {
        	switch (s)
            {
                case "h":
                	isHumanPlaying = true;
                	Human humanStratergy = new Human();
                	humanStratergy.addObserver(manualPlayerView);
                	gameDriver.addStrategies(humanStratergy);
                	break;
                case "a":
                	StrategyInterface agressive = new Aggressive();
                	gameDriver.addStrategies(agressive);
                	break;
                case "b":
                	gameDriver.addStrategies(new Benevolent());
                	break;
                case "r":
                	gameDriver.addStrategies(new Random());
                	break;
                case "c":
                	gameDriver.addStrategies(new Cheater());
                	break;
            }
        }
    }
}

