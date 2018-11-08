package view;

import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

 
/**
 * To connect Multiple views
 * @author WaleedAhmad
 * @author Muhammad_Hamza_Noor
 * @author Meet_Patel
 * @version 1.0.0
 */
public class GUIManager extends Application {
	
	
    static WelcomeGUI welcomeView  = null;
    static MapEditorGUI mapEditorView = null;
    public static PhaseView phaseView = null;

    
    static RWMapFileController rwMapFileController;
	static ReadController readController;
	static GameController gameController;
	static WriteController writeController;   
    
    /**
     * Starts the main UI window
     * and initializes the Editor and Welcome View
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage window) {    
    	window.setTitle("Risk Game");
    	
    	mapEditorView = new MapEditorGUI(readController,writeController);
		welcomeView = new WelcomeGUI(window, rwMapFileController, mapEditorView); 
        mapEditorView.getStartGameButton().setOnAction(new EventHandler<ActionEvent>() {            
        	@Override
            public void handle(ActionEvent event){
        		 int numberOfPlayers = mapEditorView.getNumberOfPlayers();
        		 phaseView.setNumberOfPlayers(numberOfPlayers);
         		window.setScene(phaseView.getView());
            	gameController.startGame(numberOfPlayers);
            }
    	});   
    	
        window.setScene(welcomeView.getView());
        window.show();
    }


	/**
	 * @param new_rwMapFileController
	 * @param new_readController
	 * @param new_writeController
	 */
	public static void addControllers(RWMapFileController new_rwMapFileController, ReadController new_readController,
			WriteController new_writeController,GameController new_gameController) {		
		rwMapFileController = new_rwMapFileController;
		readController = new_readController;
		writeController = new_writeController;
		gameController = new_gameController; 
	}	

	/**		
	75		 * Sets required View		
	76		 * @param view which implements {@link IView}		
	77		 * @param typeOfView defines type of view to be set		
	78		 */		
	public static void setView(ViewInterface view, String typeOfView) {		
					
	switch(typeOfView){		
	case "phaseview":		
		phaseView= (PhaseView)view;		
						break;		
					default:		
					break;	
	
	}
	}
}
					
       

