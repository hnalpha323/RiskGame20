/**
 * 
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Gives a player statistic view
 * @author SA
 */
public class PlayerInfoView{

	
	VBox playerBox = null;
	Label actorName = null;
	Label currentContry = null;
	Label countriesWon = null;
	
	/**
	 * 
	 */
	public PlayerInfoView() {
		this.playerBox = new VBox();
		playerBox.setPadding(new Insets(5));
		this.actorName = new Label();
		setFontColor(actorName, "#0076a3");
		HBox profilepicContainer = new HBox(); 
<<<<<<< HEAD
		Image profileImage = new Image(getClass().getResourceAsStream("C:\\Users\\getwa\\Documents\\GitHub\\RiskGame20\\bin\\user.png"));
=======
		Image profileImage = new Image(getClass().getResourceAsStream("/user.png"));
>>>>>>> d07ed86c4f728103d5afd855fdd5410ee80e0a6f
		profilepicContainer.getChildren().add(new ImageView(profileImage));
		profilepicContainer.getChildren().add(actorName);
		this.currentContry = new Label();
		this.countriesWon = new Label();
		setFontColor(countriesWon, "#F44336");
		this.playerBox.getChildren().add(profilepicContainer);
		this.playerBox.getChildren().add(currentContry);
		this.playerBox.getChildren().add(countriesWon);
	}
	
	
	
	/**
	 * @param label the label you want to color
	 * @param color is a type of String example: "red" or "#333"
	 */
	public void setFontColor(Label label,String color){
		label.setFont(new Font("Cambria", 18));
		label.setTextFill(Color.web(color));
		label.setPadding(new Insets(10));
	}
	
	/**
	 * @return the playerBox
	 */
	public VBox getPlayerBox() {
		return playerBox;
	}
	/**
	 * @param playerBox the playerBox to set
	 */
	public void setPlayerBox(VBox playerBox) {
		this.playerBox = playerBox;
	}
	/**
	 * @return the actorName
	 */
	public Label getActorName() {
		return actorName;
	}
	/**
	 * @param actorName the actorName to set
	 */
	public void setActorName(String actorName) {
		this.actorName.setText(actorName);
	}
	/**
	 * @return the currentContry
	 */
	public Label getCurrentContry() {
		return currentContry;
	}
	/**
	 * @param currentContry the currentContry to set
	 */
	public void setCurrentContry(Label currentContry) {
		this.currentContry = currentContry;
	}
	/**
	 * @return the countriesWon
	 */
	public Label getCountriesWon() {
		return countriesWon;
	}
	/**
	 * @param countriesWon the countriesWon to set
	 */
	public void setCountriesWon(Label countriesWon) {
		this.countriesWon = countriesWon;
	}


	
	
}
