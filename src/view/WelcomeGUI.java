/**
 * @Version 1.0.0
 */
package view;

import java.io.File;

import controller.RWMapFileController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


/**
 * This is a UI driver that calls all the necessary view when a action is performed
 * @author WaleedAhmad
 * @author Muhammad_Hamza_Noor
 * @author Meet_Patel
 */

public class WelcomeGUI implements ViewInterface{

	RWMapFileController maprwController;
	static Scene welcomeScreen = null;
	final FileChooser fileChooser = new FileChooser();
	static MapEditorGUI MapEditorGUI = null;
	Stage window = null;
	

	public WelcomeGUI(Stage new_window,RWMapFileController new_maprwController, MapEditorGUI new_MapEditorGUI) {
		window  = new_window;
		maprwController = new_maprwController; 
		MapEditorGUI = new_MapEditorGUI;
	}
	
	
	public Scene getView(){
		    Button chooseMapButton = new Button();
	        chooseMapButton.setMinWidth(200);
	        chooseMapButton.setText("Load Map");
	        Button saveMapButton = new Button();
	        saveMapButton.setMinWidth(200);
	        saveMapButton.setText("Save Map");
	        Button createMapButton = new Button();
	        createMapButton.setMinWidth(200);
	        createMapButton.setText("Create A Map");
	        Button gobackButton = new Button();
	        gobackButton.setMinWidth(200);
	        gobackButton.setText("Previous Map");
	        
	        gobackButton.setVisible(false);
	        
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setHeaderText("Invalid Map file detected");

	        chooseMapButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	                File file = fileChooser.showOpenDialog(window);
	                if(file != null){
	                  boolean isValid = maprwController.loadMap(file); 
	                  if(isValid)
	                	  loadAnotherView(MapEditorGUI.getView());
	                  else
	                	 alert.showAndWait();	                  
	                }
	                
	            }
	        });
	        
	        saveMapButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	if(maprwController.validateMap()){
	            		fileChooser.setInitialFileName("NewMap.map");
		            	fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Map File", "map"));
		                File file = fileChooser.showSaveDialog(window);
		                if(file != null){
		                	maprwController.writeMap(file);
		                }	
	            	}else{
	            		 alert.showAndWait();
	            	}	            	
	            }
	        });
	        
	        MapEditorGUI.getCloseButton().setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	loadAnotherView(welcomeScreen);
	            	gobackButton.setVisible(true);
	            }
	    	});
	        
	        createMapButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	maprwController.clearData();
	            	loadAnotherView(MapEditorGUI.getView());
	            }
	        });
	        
	        
	        gobackButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	loadAnotherView(MapEditorGUI.getView());
	            }
	        });
	        
	       
	        
	        GridPane gridPane = new GridPane();
	        gridPane.add(chooseMapButton,0,0);
	        gridPane.add(saveMapButton,0,1);
	        gridPane.add(createMapButton,0,2);
	        gridPane.add(gobackButton,0,3);
	        gridPane.setAlignment(Pos.CENTER);
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        welcomeScreen = new Scene(gridPane, 300, 250);
		    return welcomeScreen;
	}
	
	
  void loadAnotherView(Scene scene){
	    	window.setScene(scene);	
	  } 
	
}