package gameLauncher;

import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;
import model.DataReader;
import model.DataWriter;
import view.GUIManager;

/** 
 * Class that contains Main function which will launch the Game
 * This class initializes controllers,View and connects them.
 * 
 * @author Meet_Patel
 * @version 1.0.0
 */

public class Launcher {

	public static void main(String[] args) {
		 RWMapFileController rwMapFileController = new RWMapFileController();
		 GameController gameController = new GameController();
		 ReadController readController = new ReadController(new DataReader());
		 WriteController writeController = new WriteController(new DataWriter());
		 GUIManager.addRWControllers(rwMapFileController,readController,writeController,gameController);
		 javafx.application.Application.launch(GUIManager.class);
	}
}
