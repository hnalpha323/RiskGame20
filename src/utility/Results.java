package utility;

/**
 * This Class returns results
 * @author Meet_Patel
 * @version 1.0.0
 */
public class Results {

	private String message;
	private boolean okay = false;

	/**
	 * Constructor with no parameter
	 */
    public Results(){
    }

    /**
     * Constructor with parameter
     * sets message of the object
     * @param message desired message to be delivered
     */

    public Results(String message){
        this.message = message;
    }

    /**
     * Constructor with parameter 
     * @param ok initial value of ok property
     * @param message initial value of message property
     */
    public Results(boolean okay, String message){
        this.okay = okay;
        this.message = message;
    }

    /**
     * @return To check if action is performed or not
     */
    public boolean getOk() {
    	return this.okay;
    }

    /**
     * @param Indication
     */
    public void setOk(boolean okay) {
    	this.okay=okay;
    }

    /**
     * @return get message
     */
    public String getMessage(){ 
    	return this.message; 
    }

    /**
     * @param sets message 
     */
    public void setMessage(String message){ 
    	this.message = message; 
    }
}
