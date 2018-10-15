package controller;

import model.GameDriver;
//import utility.exception.InvalidNumOfPlayersException;

/**
 * Initializes the Game Using GameDriver and number of players as input
 * Controls the Game Play using GameDriver Model 
 * @author Muhammad_Hamza_Noor
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
