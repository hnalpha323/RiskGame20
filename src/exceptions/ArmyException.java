package exceptions;

/**
 * Exception class for handling insufficient number of armies 
 * @author Shah Mohammad Mostakim 
 * @version 3.0 
 *
 */

public class ArmyException extends MainException{
	  public ArmyException(String message) {
	        super(message);
	    }

	    public ArmyException() {
	        this("There are no sufficient armies.");
	    }

}
