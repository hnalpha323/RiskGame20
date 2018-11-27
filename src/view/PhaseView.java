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
		
		BorderPane hbox = new BorderPane();
		Label label = new Label("Phase:");
		label.setPadding(new Insets(5,5,5,5));
		label.setTextFill(Color.RED);
		hbox.setPadding(new Insets(10,5,0,0));
		hbox.setLeft(new HBox(label, phase));
		hbox.setRight(dominationView.getView());
		
		borderPane.setTop(hbox);

		HBox playerHbox = new HBox();
		hbox.setStyle("-fx-font-family: 'Saira Semi Condensed', sans-serif;");
	    for(int i=1;i<=numberOfPlayers;i++){
	    	VBox vbox= new VBox();
	    	TextArea tmp = new TextArea();
	    	tmp.setMinHeight(500);
	    	vbox.getChildren().add(tmp);
	    	PlayerInfoView pview = new PlayerInfoView();
	    	pview.setActorName("Player "+i);
	    	vbox.setStyle( 
					"-fx-border-style: solid inside;" + 
							"-fx-border-width: 0 1 0 0;" +  
					"-fx-border-color: black;");
			tmp.setStyle("-fx-padding:10");
	    	
			vbox.getChildren().add(pview.getPlayerBox());
			
			
			playerHbox.getChildren().add(vbox);
	    
	    	playersStatistics.put("Player "+i, pview);
	    	playersViews.put("Player "+i, tmp);
	    }
	    borderPane.setCenter(playerHbox);
		Scene scene = new Scene(borderPane);
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
		
	   if(object instanceof String){
		   String sentString = (String)object;
		   String string = sentString.split(":")[0];
		if ("PhaseChange".equals(string)) {
			phase.setText(sentString.split(":")[1]);
		}else if(playersViews.containsKey(string)){
			playersViews.get(string).appendText("\n"+sentString);			
		}
	   }
		else if(object instanceof Players){
			Players tmp = (Players) object;
			playersViews.get(tmp.getName()).appendText(tmp.getState());
			
		}
	}
	
	
	/**
	 * Sets number Of players
	 * @param new_numberofPlayers
	 */
	public void setNumberOfPlayers(int new_numberofPlayers){
		this.numberOfPlayers = new_numberofPlayers;
	}
	
}
