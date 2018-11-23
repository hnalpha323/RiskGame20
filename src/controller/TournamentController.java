/**
 * 
 */
package controller;

import model.ManageTournament;

/**
 * <p> This class controls the Tournament 
 * through {@link #ManageTournament} </p>
 * @author Muhammad_Hamza_Noor
 */
public class TournamentController {

	
	/**
	 * {@link #ManageTournament} manages the tournament like adding maps and players
	 */
	ManageTournament ManageTournament = null; 
	
	/**
	 * Constructor to initialize the {@link #ManageTournament}
	 * @param new_ManageTournament is the {@link ManageTournament} such that
	 * this controller manages the tournament through it
	 */
	public TournamentController(ManageTournament new_ManageTournament){
		ManageTournament = new_ManageTournament;
	}
	
	/**
	 * @param numberOfPlayers are the number of players in the tournament
	 * @param playerStrategies is comma separated string of strategies of players
	 * @param turns tells number of turns
	 * @param noOfGames tells number of games to play in tournament
	 */
	public void start(int numberOfPlayers, String playerStrategies,String turns, String noOfGames){
		ManageTournament.start(numberOfPlayers, playerStrategies,
				Integer.parseInt(turns), Integer.parseInt(noOfGames));
	}
	
	/**
	 * Adds a map to the Tournament
	 */
	public void addAMap(){
		ManageTournament.createMap();
	}
	
}
