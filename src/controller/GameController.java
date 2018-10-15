package controller;

import model.GameDriver;
//import utility.exception.InvalidNumOfPlayersException;

/**
 * @author Muhammad_Hamza_Noor
 * Controls the Game Play using GameDriver Model 
 */
public class GameController {	

	/**
	 * Initializes the Game
	 */
	public void startGame(int numberOfPlayers) {
		// try
         ///{
             new GameDriver(numberOfPlayers).start();
       //  }
       // catch (InvalidNumOfPlayersException e)
        // {
         //   e.printStackTrace();
        // }
		
	}
	
}
