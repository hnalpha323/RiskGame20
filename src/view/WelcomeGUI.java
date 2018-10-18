/**
 * @Version 1.0.0
 */
package view;

import java.awt.SystemColor;
import java.io.File;
import controller.RWMapFileController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;


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
			chooseMapButton.setText("Load Map");
	        chooseMapButton.setMinWidth(140);
   	        chooseMapButton.setMinHeight(50);
   	        chooseMapButton.setStyle(" -fx-background-color: \r\n" + 
   	        		"        linear-gradient(#ffd65b, #e68400),\r\n" + 
   	        		"        linear-gradient(#ffef84, #f2ba44),\r\n" + 
   	        		"        linear-gradient(#ffea6a, #efaa22),\r\n" + 
   	        		"        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\r\n" + 
   	        		"        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\r\n" + 
   	        		"    -fx-background-radius: 30;\r\n" + 
   	        		"    -fx-background-insets: 0,1,2,3,0;\r\n" + 
   	        		"    -fx-text-fill: #654b00;\r\n" + 
   	        		"    -fx-font-weight: bold;\r\n" + 
   	        		"    -fx-font-size: 14px;\r\n" + 
   	        		"    -fx-padding: 10 20 10 20;");
   	      
		
	    
	        Button saveMapButton = new Button();
	        saveMapButton.setMinWidth(140);
	        saveMapButton.setMinHeight(50);
	        saveMapButton.setText("Save Map");
	        saveMapButton.setStyle(" -fx-background-color: \r\n" + 
	        		"        linear-gradient(#ffd65b, #e68400),\r\n" + 
	        		"        linear-gradient(#ffef84, #f2ba44),\r\n" + 
	        		"        linear-gradient(#ffea6a, #efaa22),\r\n" + 
	        		"        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\r\n" + 
	        		"        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\r\n" + 
	        		"    -fx-background-radius: 30;\r\n" + 
	        		"    -fx-background-insets: 0,1,2,3,0;\r\n" + 
	        		"    -fx-text-fill: #654b00;\r\n" + 
	        		"    -fx-font-weight: bold;\r\n" + 
	        		"    -fx-font-size: 14px;\r\n" + 
	        		"    -fx-padding: 10 20 10 20;");
	        
	        
	        Button createMapButton = new Button();
	        createMapButton.setMinWidth(140);
	        createMapButton.setMinHeight(50);
	        createMapButton.getGraphic();
	        createMapButton.setText("Create Map");
	        createMapButton.setStyle(" -fx-background-color: \r\n" + 
	        		"        linear-gradient(#ffd65b, #e68400),\r\n" + 
	        		"        linear-gradient(#ffef84, #f2ba44),\r\n" + 
	        		"        linear-gradient(#ffea6a, #efaa22),\r\n" + 
	        		"        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\r\n" + 
	        		"        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\r\n" + 
	        		"    -fx-background-radius: 30;\r\n" + 
	        		"    -fx-background-insets: 0,1,2,3,0;\r\n" + 
	        		"    -fx-text-fill: #654b00;\r\n" + 
	        		"    -fx-font-weight: bold;\r\n" + 
	        		"    -fx-font-size: 14px;\r\n" + 
	        		"    -fx-padding: 10 20 10 20;");
	        
	        
	        
	        Button gobackButton = new Button();
	        gobackButton.setMinWidth(140);
	        gobackButton.setMinHeight(50);
	        gobackButton.setText("Previous Map");
	        gobackButton.setStyle("  -fx-background-color: \r\n" + 
	        		"        linear-gradient(#ffd65b, #e68400),\r\n" + 
	        		"        linear-gradient(#ffef84, #f2ba44),\r\n" + 
	        		"        linear-gradient(#ffea6a, #efaa22),\r\n" + 
	        		"        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\r\n" + 
	        		"        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\r\n" + 
	        		"    -fx-background-radius: 30;\r\n" + 
	        		"    -fx-background-insets: 0,1,2,3,0;\r\n" + 
	        		"    -fx-text-fill: #654b00;\r\n" + 
	        		"    -fx-font-weight: bold;\r\n" + 
	        		"    -fx-font-size: 14px;\r\n" + 
	        		"    -fx-padding: 10 20 10 20;");
	     
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