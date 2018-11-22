package model;

/**
 * 
 * @author Meet Patel
 * @version 3.0.0
 * This class is used to get the winner of the game
 *
 */

public class FinalResult {
	
	String map;
    String winner;
    String game;

    /**
     * @param Map name
     * @param Name of Wining player
     */
    public GameResult(String mapName, String winner)
    {
        this.map = mapName;
        this.winner = winner;
    }

    /**
     * @return Map Name
     */
    public String getMap() 
    { 
    	return this.map; 
    }

    /**
     * Gets the winner
     * @return winner
     */
    public String getWinner() 
    { 
    	return this.winner; 
    }

    /**
     * @return Name of The Game
     */
    public String getGame() 
    { 
    	return this.game; 
    }

    /**
     * @param Name of the game to set
     */
    public void setGame(String game) 
    { 
    	this.game = game; 
    }

}
