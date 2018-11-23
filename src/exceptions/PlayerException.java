package exceptions;

/**
 * 
 * Exception class for handling invalid number of players 
 * @author Shah Mohammad Mostakim 
 * @version 3.0 
 *
 */

public class PlayerException extends MainException{
	
	  public PlayerException(String message) {
	        super(message);
	    }

	    public PlayerException()
	    {
	        this("There could be at minimum 2 and at maximum 6 players");
	    }

}
