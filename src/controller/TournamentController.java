package controller;
import model.ManageTournament;

/**
 * Tournament Controller
 * @author  Meet Patel
 * @version 3.0.0
 */
public class TournamentController 
{
	/**
	 * Manages the tournament like adding Maps and Players
	 */
	ManageTournament tournamentManager = null; 
	
	/**
	 * @param new_tournamentManager is the ManageTournament such that
	 * this controller manages the tournament through it
	 */
	public TournamentController(ManageTournament new_tournamentManager){
		tournamentManager = new_tournamentManager;
	}
	
	/**
	 * @param Number of Players in the Tournament
	 * @param Comma Separated String of Strategies of Players
	 * @param Tells Number of turns
	 * @param Number of games to play in Tournament
	 */
	public void start(int numberOfPlayers, String playerStrategies,String turns, String noOfGames){
		tournamentManager.start(numberOfPlayers, playerStrategies,
				Integer.parseInt(turns), Integer.parseInt(noOfGames));
	}
	
	/**
	 * Adds a map to the Tournament
	 */
	public void addAMap(){
		tournamentManager.createMap();
	}
}
