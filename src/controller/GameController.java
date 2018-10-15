package controller;

import model.GameManager;
import util.expetion.InvalidNumOfPlayersException;

/**
 * @author Muhammad_Hamza_Noor
 * Controls the Game Play using GameManager Model 
 */
public class GameController {	

	/**
	 * Initializes the Game
	 */
	public void startGame(int numberOfPlayers) {
		 try
         {
             new GameManager(numberOfPlayers).start();
         }
         catch (InvalidNumOfPlayersException e)
         {
            e.printStackTrace();
         }
		
	}
	
}
