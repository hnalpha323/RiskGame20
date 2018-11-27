/**
 * 
 */
package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Players;


/**
 * /**
 * Phase view contains not only the current Phase of the game but also has
 * <ul>
 * <li>DominationView</li>
 * <li>HumanPlayerView</li>
 * <li>PlayerStatisticsView</li>
 * <li>CardView</li>
 * </ul>
 * @author Waleed
 */

public class PhaseView implements ViewInterface,Observer
{
	Button saveStateOfGame = null;
	HashMap<String,PlayerInfoView> playersStatistics= new HashMap<String,PlayerInfoView>();
	DominationView dominationView = null;
	ManualPlayerView humanPlayerView = null;
	CardView cardView = null;
	Label phase;
	GameController gameController = null;

	int numberOfPlayers;
String previousPlayer = "";
	
	/** button to take next turn input from user */
	Button nextTurn= null;
	
	/** to store reference to parent window */
	
	Stage windowStage = null; 	
	Stage dialog = null;
	
	/**
	 * Constructor that initializes the {@link DominationView} 
	 */
	public PhaseView(DominationView new_dominationView,GameController new_gameController,CardView new_cardView,ManualPlayerView new_humanPlayerView) {
		this.dominationView = new_dominationView;
		this.gameController = new_gameController;
		this.cardView = new_cardView;
		this.humanPlayerView = new_humanPlayerView;	
	}
	
	/** 
	 * @return {@link Scene} which contains UI elements 
	 */
	
	public Scene getView(boolean isResume) 
	{		
		cardView.inti();
		BorderPane borderPane = new BorderPane();
		phase = new Label();
		phase.setTextFill(Color.GREEN);
		phase.setPadding(new Insets(5,5,5,5));
		
		
		// create header
				BorderPane header = new BorderPane();
				header.setPadding(new Insets(10,5,0,0));
				HBox phaseHolder = new HBox( phase );
				phaseHolder.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
			            + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
			            + "-fx-border-radius: 5;" + "-fx-border-color: blue;");		
				header.setLeft(phaseHolder);	    
				header.setRight(dominationView.getView());
				
				// create dialog to show card view
				dialog = new Stage();
		        dialog.initModality(Modality.APPLICATION_MODAL);
		        dialog.initOwner(windowStage);  
		        // adding card view to dialog
		        Scene dialogScene = new Scene(cardView.getCardholder(), 300, 200);
		        dialog.setScene(dialogScene);
				nextTurn = new Button("Next turn");
				
				//create footer
				BorderPane footer = new BorderPane();
				footer.setPadding(new Insets(5));				
				saveStateOfGame = new Button("Save game");
				saveStateOfGame.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						HashMap<String,String> uiState = new HashMap<>();
						uiState.put("PHASE_STATE", phase.getText());
						uiState.put("DOMIVATION_STATE", dominationView.getText());
						for(String playerName : playersStatistics.keySet()){
							uiState.put(playerName,
									playersStatistics.get(playerName).getContriesWon());
		        		}
						gameController.saveGame(uiState);
					}
				});
				nextTurn.setOnAction(new EventHandler<ActionEvent>() {            
		        	@Override
		            public void handle(ActionEvent event){
		        		for(String playerName : playersStatistics.keySet()){
		        			playersStatistics.get(playerName).clearStatus();
		        		}        		
		        		gameController.askNextTurn();	
		            }
		    	});   
				
				
				footer.setLeft(saveStateOfGame);
				footer.setCenter(humanPlayerView.getView());
				footer.setRight(nextTurn);
				
				// add footer and header to borderPane
				borderPane.setBottom(footer);
				borderPane.setTop(header);
				
				
				// create center container which holds PlayerStatisticsView
				HBox playerHbox = new HBox();		
				for(int i=1;i<=numberOfPlayers;i++){
			    	VBox vbox= new VBox();
			    	PlayerInfoView pview = new PlayerInfoView();
			    	pview.setActorName("Player "+i);
			    	vbox.setStyle( 
							"-fx-border-style: solid inside;" + 
									"-fx-border-width: 0 1 0 0;" +  
							"-fx-border-color: black;");
					vbox.getChildren().add(pview.getPlayerBox());
					playerHbox.getChildren().add(vbox);
			    	playersStatistics.put("Player "+i, pview);
			    }
				// add center container to borderPane
				borderPane.setCenter(playerHbox);
				borderPane.setStyle("-fx-font-family: 'Saira Semi Condensed', sans-serif;");
			    
				// if game is resumed then get the state from previously saved state
			    if(isResume){
			    	HashMap<String,String> newUIState = gameController.getUIState();
			    	phase.setText(newUIState.get("PHASE_STATE"));
			    	dominationView.dominationLabel.setText(newUIState.get("DOMIVATION_STATE"));
			    	for(int i=1;i<=numberOfPlayers;i++){
			    		playersStatistics.get("Player "+i).getContriesWonLabel().setText(
			    				newUIState.get("Player "+i) );
			    	}
			    	
			    }
			    
			    Scene scene = new Scene(borderPane);
			    //adding google fonts to beautify font style
				scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Saira+Semi+Condensed");
				return scene;
			}

			/**
			 * Get called whenever model pushes a change
			 * @param model the model that pushed the update 
			 * @param object optional Object that is passed to notifyObservers
			 */
			@Override
			public void update(Observable model, Object object) {
				   
				  // if instance of Player else by GameManager
		          if(model instanceof Players){
		           		Players tmp = (Players) model;
		           		previousPlayer = tmp.getName();
		           		playersStatistics.get(tmp.getName()).setCurrentStatus(object.toString());
		           		playersStatistics.get(tmp.getName()).setCountriesWon(trim(tmp.getState()));
		           		    String s = (String)object;
		           		    if(s.split(":")[0].equals("CardView")){
		           		    	dialog.show();
		           			}
		           	}else{
		           		String s = (String)object;
		           		if(s.split(":").length > 0){
		           		    if(s.split(":")[0].equals("CardView")){

		           			} else if(!s.split(":")[0].equals("DominationView")){
		           				phase.setText(s);
		           			}
		           		} else {
		           			phase.setText(s);
		           		}
		           	}

			}
			
			
			/**
			 * @param string is the string we want to trim
			 * @return the trimmed string by delimiter (: 
			 */
			private String trim(String string){
				return string.substring(string.indexOf("(:")+3);
			}

			/**
			 * Sets number Of players
			 * @param new_numberofPlayers number of players going to play
			 */
			public void setNumberOfPlayers(int new_numberofPlayers){
				this.numberOfPlayers = new_numberofPlayers;
			}
			
			
			/**
			 * @return {@link #windowStage}
			 */
			public Stage getWindowStage() {
				return windowStage;
			}

			
			/**
			 * @param windowStage to set {@link #windowStage}
			 */
			public void setWindowStage(Stage windowStage) {
				this.windowStage = windowStage;
			}
			}

		