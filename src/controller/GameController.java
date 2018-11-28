
package controller;

import java.io.IOException;
import java.util.HashMap;

import model.GameDriver;
import model.SaveMethod;
import exceptions.*;

/**
 * @author Muhammad_Hamza_Noor
 * Controls the whole Game Play through GameDriver
 * @see GameDriver 
 */
public class GameController {	

	/**
	 * GameDriver takes care about phases of game 
	 */
	GameDriver GameDriver = null;
	
	/**
	 * {@link #SaveMethod} to save the state of game
	 */
	SaveMethod SaveMethod = null;
	
	
	boolean isResumedGame = false;
	
	/**
	 * Constructor that initializes the GameDriver
	 * @param new_GameDriver is the reference of GameDriver created in Driver class 
	 * @param new_savePreocess to save the game through game controller
	 */
	public GameController(GameDriver new_GameDriver,SaveMethod new_savePreocess){
		SaveMethod = new_savePreocess;
		GameDriver = new_GameDriver;
	}
	
	/**
	 * Initializes the Game Manager with number of players
	 * @param numberOfPlayers tells numbers of players going to play the game
	 * @param strategies is the comma separated string example b,r,c 
	 * @param isGameAutomated to check if the game should be automated
	 * Have to catch the <code>InvalidNumOfPlayersException</code> exception
	 */
	public void startGame(int numberOfPlayers,String strategies,boolean isGameAutomated) {		
			isResumedGame = false;
		    try {
		    	if(isGameAutomated)
		    		GameDriver.startGame(numberOfPlayers,strategies);
		    	else
		    		new GameDriver(numberOfPlayers,strategies,800000).start(true);
			} catch (PlayerException e) {
				e.printStackTrace();
			}	
	}


	/**
	 * This method tells the GameDriver to start the next round
	 */
	public void askNextTurn() {
		GameDriver.takeNextTurn();
	}

	/**
	 * @param text the input given by the HumanPlayer
	 */
	public void submitAnswer(String text) {		
		GameDriver.setAnswerForHuman(text);		
	}

	/**
	 * This method redirects save game request to {@link #SaveMethod} model
	 * @param uiState is state of UI in {@link HashMap} to save
	 */
	public void saveGame(HashMap<String,String> uiState) {
		try {
			SaveMethod.saveState(GameDriver);
			SaveMethod.saveUIState(uiState);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the GameDriver State from previously saved state
	 */
	public GameDriver resumeGame() {	
	   isResumedGame = true;
       return SaveMethod.getPreviousState();
	}

	/**
	 * @param GameDriver the GameDriver that will be set only in case of resume game
	 */
	public void setGameDriver(GameDriver GameDriver) {
		this.GameDriver = GameDriver;
	}

	/**
	 * @return the previously saved UI state of the game such that it can be used in 
	 * resume game process
	 */
	public HashMap<String,String> getUIState() {		
		return SaveMethod.getUIState();
		
	}

}
