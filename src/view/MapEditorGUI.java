package view;


import java.util.ArrayList;
import java.util.Optional;

import nullrestrictor.NullHandler;
import controller.ReadController;
import controller.WriteController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * @author Risk Team 20
 * @version 3.0.0
 * Responsible for generating Map editor view
 */
public class MapEditorGUI implements ViewInterface
{		

	private static Scene editorScene = null;
	private static String country,continent = null;

	private ReadController readController = null;
	private WriteController writeController = null;
	private Button startGameButton = new Button();
	private Button closeButton = new Button();
	private TextField numberOfPlayerInput = new TextField();

	TextInputDialog dialog = new TextInputDialog();
	boolean isDeletedContinent= false,isDeletedContry=false;



	/**Constructor used to inject dependencies
	 * @param new_readController
	 * @param new_writeController
	 */
	public MapEditorGUI(ReadController new_readController,WriteController new_writeController) {
		readController = new_readController;
		writeController = new_writeController;
	}


	/**
	 * Returns container having Map Editor UI elements
	 * @return {@link Scene} instance
	 */
	public Scene getView(boolean isResume)
	{
		ObservableList<String> continents = FXCollections.observableArrayList();		 
		ObservableList<String> contries = FXCollections.observableArrayList();

		continents.addAll(model.MapDatabase.continentValues.keySet());
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		ChoiceBox<String> continentChoiceBox = new ChoiceBox<String>();
		ChoiceBox<String> countriesChoiceBox = new ChoiceBox<String>();
		
		Button addContent = new Button("Add Continent");	
		addContent.setStyle(" -fx-background-color: \r\n" + 
				"        #000000,\r\n" + 
				"        linear-gradient(#ffd65b, #e68400),\r\n" + 
				"        linear-gradient(#ffef84, #f2ba44),\r\n" + 
				"        linear-gradient(#ffea6a, #efaa22);\r\n" + 
				"    -fx-background-insets: 0,1,2,3;\r\n" + 
				"    -fx-background-radius: 3,2,2,2;\r\n" + 
				"    -fx-padding: 12 30 12 30;\r\n" + 
				"    -fx-text-fill: 654b00;\r\n" + 
				"    -fx-font-size: 12px;\r\n");
        
		
		Button addCountry = new Button("Add Country");	     
		addCountry.setStyle(" -fx-background-color: \r\n" + 
				"        #000000,\r\n" + 
				"        linear-gradient(#ffd65b, #e68400),\r\n" + 
				"        linear-gradient(#ffef84, #f2ba44),\r\n" + 
				"        linear-gradient(#ffea6a, #efaa22);\r\n" + 
				"    -fx-background-insets: 0,1,2,3;\r\n" + 
				"    -fx-background-radius: 3,2,2,2;\r\n" + 
				"    -fx-padding: 12 30 12 30;\r\n" + 
				"    -fx-text-fill: 654b00;\r\n" + 
				"    -fx-font-size: 12px;\r\n");
				
		Button deleteContent = new Button("Delete Continent");	     
		deleteContent.setStyle(" -fx-background-color: \r\n" + 
				"        #000000,\r\n" + 
				"        linear-gradient(#ffd65b, #e68400),\r\n" + 
				"        linear-gradient(#ffef84, #f2ba44),\r\n" + 
				"        linear-gradient(#ffea6a, #efaa22);\r\n" + 
				"    -fx-background-insets: 0,1,2,3;\r\n" + 
				"    -fx-background-radius: 3,2,2,2;\r\n" + 
				"    -fx-padding: 12 30 12 30;\r\n" + 
				"    -fx-text-fill: 654b00;\r\n" + 
				"    -fx-font-size: 12px;\r\n");
				
		Button deleteCountry = new Button("Delete Country");
		deleteCountry.setStyle(" -fx-background-color: \r\n" + 
				"        #000000,\r\n" + 
				"        linear-gradient(#ffd65b, #e68400),\r\n" + 
				"        linear-gradient(#ffef84, #f2ba44),\r\n" + 
				"        linear-gradient(#ffea6a, #efaa22);\r\n" + 
				"    -fx-background-insets: 0,1,2,3;\r\n" + 
				"    -fx-background-radius: 3,2,2,2;\r\n" + 
				"    -fx-padding: 12 30 12 30;\r\n" + 
				"    -fx-text-fill: 654b00;\r\n" + 
				"    -fx-font-size: 12px;\r\n");
	
		Button saveChanges = new Button("Save Changes");
	
		TextField editadjacentContries = new TextField ();
		editadjacentContries.setPrefWidth(800);
		editadjacentContries.setPromptText("Adjacent Countries");
		TextField editContinentValue = new TextField ();
		editContinentValue.setPromptText("Continent Value");
		numberOfPlayerInput = new TextField ();
		numberOfPlayerInput.setPromptText("Number Of Players");

		addContent.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				String tmp = getUserInput("Enter continent name");
				continentChoiceBox.getItems().add(tmp);
				continentChoiceBox.setValue(tmp);
			}
		});

		deleteContent.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				isDeletedContinent = true;
			}
		}); 


		deleteCountry.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				isDeletedContry = true;
			}
		}); 

		addCountry.setOnAction(new EventHandler<ActionEvent>() {				
			@Override
			public void handle(ActionEvent event) {
				String tmp = getUserInput("Enter contry name");
				countriesChoiceBox.getItems().add(tmp);
				countriesChoiceBox.setValue(tmp);
			}
		});



		saveChanges.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(continentChoiceBox.getValue().equals("Continents") && countriesChoiceBox.getValue().equals("Countries")){
					return ;
				}
				writeController.addData(editadjacentContries.getText(),continent,country,editContinentValue.getText(),isDeletedContinent,isDeletedContry);
				if(isDeletedContinent){    	  
					continentChoiceBox.getItems().remove(continent);
					continentChoiceBox.setValue("Continents");
				}
				if(isDeletedContry){
					countriesChoiceBox.getItems().remove(country);
					countriesChoiceBox.setValue("Countries");
				}
				isDeletedContinent = false; isDeletedContry = false;
			}
		});

		continentChoiceBox.setTooltip(new Tooltip("Select the Continent"));
		continentChoiceBox.getItems().add("Continents");
		continentChoiceBox.setValue("Continents");
		continentChoiceBox.getItems().addAll(continents);


		countriesChoiceBox.getItems().add("Countries");
		countriesChoiceBox.setValue("Countries");


		continentChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue observable, String oldValue, String newValue){
				continent = newValue;
				ArrayList<String> territories = readController.getTerritoriesNames(continent);
				contries.clear();
				countriesChoiceBox.getItems().clear();
				if(territories != null){
					contries.addAll(territories);
				}
				country = "";
				editContinentValue.setText(""+readController.getContinentValue(continent));
				editadjacentContries.setText("");
				countriesChoiceBox.getItems().add("Countries");			    
				countriesChoiceBox.getItems().addAll(contries);
				countriesChoiceBox.setValue("Countries");
			}
		});


		countriesChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue observable,String oldValue, String newValue) {
				if(newValue != null && !newValue.equals("Countries")){
					country = newValue;
					editadjacentContries.setText(readController.getAdjacentTerritories(continent, country).toString().replaceAll(" ", ""));
				}
			}
		});


		BorderPane borderPane = new BorderPane();

		Image imageOk = new Image(getClass().getResourceAsStream("/Arrow.png")); 
		getCloseButton().setGraphic(new ImageView(imageOk));
		//Adding to row 1 to UI grid
		gridPane.add(continentChoiceBox,0,0);
		gridPane.add(countriesChoiceBox,1,0);
		gridPane.add(editContinentValue, 2, 0);
		//Adding to row 2 to UI grid
		gridPane.add(editadjacentContries, 0, 1,3,1);
		//Adding to row 3 to UI grid
		gridPane.add(addContent, 0,2);
		gridPane.add(addCountry, 2,2);
		gridPane.add(deleteContent, 0,3);
		
		gridPane.add(deleteCountry, 2,3);
		//Adding to row 4 to UI grid
		gridPane.add(new Label("Number of Players"), 1, 4);
		gridPane.add(numberOfPlayerInput, 2,4);

		ToolBar header = new ToolBar(closeButton);
		header.setStyle( 
				"-fx-border-style: solid inside;" + 
						"-fx-border-width: 0 0 1 0;" +  
				"-fx-border-color: black;");
		gridPane.setStyle("-fx-padding:10");
		borderPane.setTop(header);
		HBox footer = new HBox();

		footer.setStyle( 
				"-fx-border-style: solid inside;" + 
						"-fx-border-width: 1 0 0 0;" +
						"-fx-padding:10;"+
				"-fx-border-color: red;");
		gridPane.setStyle("-fx-padding:10");
		startGameButton.setText("Start Game");
		footer.setAlignment(Pos.CENTER);
		footer.setPadding(new Insets(15, 12, 15, 12));
		footer.getChildren().addAll(startGameButton,saveChanges);

		borderPane.setBottom(footer);
		borderPane.setCenter(gridPane);
		editorScene = new Scene(borderPane, 500, 500);
		return editorScene;		
	}



	/**
	 * @return the closeButton
	 */
	public Button getCloseButton() {
		return closeButton;
	}


	/**
	 * @return the startGameButton
	 */
	public Button getStartGameButton() {
		return startGameButton;
	}



	/**
	 * @return the numberOfPlayers given by user
	 */
	public int getNumberOfPlayers() {
		return Integer.parseInt(numberOfPlayerInput.getText());
	}


	/**
	 * @param question displays the question
	 * @return the input text given in dialog
	 */

	public String getUserInput(String question){
		dialog.setHeaderText(question);
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			return result.get();
		}
		return null;
	}


}
