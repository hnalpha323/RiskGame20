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
 * @author Risk_Team_20
 * @version 1.0.0
 * Link between multiple views
 */
public class GUIManager extends Application {
	
	
    static WelcomeGUI welcomeView  = null;
    static MapEditorGUI mapEditorView = null;
    
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
	public static void addRWControllers(RWMapFileController new_rwMapFileController, ReadController new_readController,
			WriteController new_writeController,GameController new_gameController) {		
		rwMapFileController = new_rwMapFileController;
		readController = new_readController;
		writeController = new_writeController;
		gameController = new_gameController; 
	}
    
       
}
