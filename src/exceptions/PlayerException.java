package exceptions;

public class PlayerException extends MainException{
	
	  public PlayerException(String message) {
	        super(message);
	    }

	    public PlayerException()
	    {
	        this("There could be at minimum 2 and at maximum 6 players");
	    }

}
