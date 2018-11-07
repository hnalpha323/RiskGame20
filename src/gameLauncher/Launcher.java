package gameLauncher;

import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;
import model.DataReader;
import model.DataWriter;
import model.Model;
import model.Notifier;
import view.GUIManager;
import view.DominationView;
import view.PhaseView;



/** 
 * Class that contains Main function which will launch the Game
 * This class initializes controllers,View and connects them.
 * 
 * @author Meet_Patel
 * @version 1.0.0
 */

public class Launcher {

	public static void main(String[] args) 
	{
		//Creates Controller to controls read and write the .map file
		 RWMapFileController rwMapFileController = new RWMapFileController();
		 
		 //Creates controller, which is responsible to redirect Read operations
		 ReadController readController = new ReadController(new DataReader());
		 
		 //Creates controller, which is responsible to redirect Write operations
		 WriteController writeController = new WriteController(new DataWriter());
		 
		 //Create Notifier: A model class which sends updates to Views
		 Model model = new Model();
		 Notifier notifier = new Notifier();		 
		 model.setNotifier(notifier);
		 
		 //Creates Domination View and make it Observer
		 DominationView dominationView = new DominationView();
		 notifier.addObserver(dominationView);
		 
		 //Creates phaseView make it Observer
		 PhaseView phaseView = new PhaseView(dominationView);
		 notifier.addObserver(phaseView);
		 
		 GameController gameController = new GameController();
		 
		 //Sends all controllers to view manager, such that views can contact
		 GUIManager.addControllers(rwMapFileController,readController,writeController,gameController);
		 GUIManager.setView(phaseView,"phaseview");
		 
		 javafx.application.Application.launch(GUIManager.class);
	}
}
