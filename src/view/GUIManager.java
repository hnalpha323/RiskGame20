package view;

import gameLauncher.Calls;
import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.TournamentController;
import controller.WriteController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.GameDriver;

 
/**
 * @author Risk Team 20
 * Is the glue between the views
 */
public class GUIManager extends Application {
	
	
    static WelcomeGUI welcomeView  = null;
    static MapEditorGUI mapEditorView = null;
    public static PhaseView phaseView = null;
    
    
    static RWMapFileController rwMapFileController;
    static TournamentController tournamentController;
	static ReadController readController;
	static GameController gameController;
	static WriteController writeController;
	static Calls[] calls = new Calls[3];
    
    /**
     * Starts the main UI window
     * and initializes the Editor and Welcome View
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage window) {
    	
    	window.setTitle("Game");
    	
    	mapEditorView = new MapEditorGUI(readController,writeController);
		welcomeView = new WelcomeView(gameController,window, rwMapFileController, mapEditorView);
		
		//listener to check game start button event in Map Editor view
		mapEditorView.getStartGameButton().setOnAction(new EventHandler<ActionEvent>() {            
        	@Override
            public void handle(ActionEvent event){
        		int numberOfPlayers = mapEditorView.getNumberOfPlayers();
        		String strategies = mapEditorView.getPlayersStrategies();
        		boolean isGameAutomated = mapEditorView.getIsGameAutomatecheckBox().isSelected();
        		phaseView.setNumberOfPlayers(numberOfPlayers);
        		phaseView.setWindowStage(window);
        		window.setScene(phaseView.getView(false));
        		calls[0].called(numberOfPlayers,strategies);
        		gameController.startGame(numberOfPlayers,strategies,isGameAutomated);
        		
            }
    	});   
       
			
		window.setScene(welcomeView.getView(false));
        
		welcomeGUI.getResumeButton().setOnAction(new EventHandler<ActionEvent>() {            
        	@Override
            public void handle(ActionEvent event){        		
        		GameDriver gm = gameController.resumeGame();
        		calls[2].called(gm);
        		calls[1].called(0,"");
        		phaseView.setNumberOfPlayers(2);
        		phaseView.setWindowStage(window);
        		window.setScene(phaseView.getView(true));        		
        	}
		 });
		
		welcomeView.getTournamentButton().setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			   TournamentView tournamentView = new TournamentView(window, rwMapFileController, tournamentController);	
			   window.setScene(tournamentView.getView(false));		        
			}
			
		});
		
		window.show();
    }


	/**
	 * This method is used to initialize all the controllers
	 * @param new_rwMapFileController  is the .map IO controller 
	 * @param new_readController is map reader controller
	 * @param new_writeController is the map write controller
	 * @param new_gameController controlls the game start
	 * @param new_tournamentController to start the tournament if in tournament mode
	 */
	public static void addControllers(RWMapFileController new_rwMapFileController, ReadController new_readController,
			WriteController new_writeController,GameController new_gameController, TournamentController new_tournamentController) {		
		rwMapFileController = new_rwMapFileController;
		readController = new_readController;
		writeController = new_writeController;
		gameController = new_gameController;
		tournamentController = new_tournamentController;
	}


	/**
	 * Sets required View
	 * @param view which implements {@link IView}
	 * @param typeOfView defines type of view to be set
	 */
	public static void setView(ViewInterface view, String typeOfView) {
		
		switch(typeOfView){
			case "phaseview":
				phaseView = (PhaseView)view;
				break;
			default:
				break;
		}
		
	}


	/**
	 * @param new_callBack to initialize the callback functionality
	 */
	static int i =0;
	public static void addCallBack(Calls new_callBack) {
		calls[i++] = new_callBack;
	}
    
	
       
}
