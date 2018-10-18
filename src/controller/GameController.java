package controller;

import model.GameDriver;
import exceptions.PlayerException;

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
		try
         {
             new GameDriver(numberOfPlayers).start();
        }
       catch (PlayerException e)
         {
            e.printStackTrace();
         }
		
	}
	
}
