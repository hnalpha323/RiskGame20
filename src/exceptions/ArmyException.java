package exceptions;

public class ArmyException extends MainException{
	  public ArmyException(String message) {
	        super(message);
	    }

	    public ArmyException() {
	        this("There are no sufficient armies.");
	    }

}
