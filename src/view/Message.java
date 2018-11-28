package view;

import java.util.Observable;
import java.util.Observer;

import utility.MessageEnum;

/**
 * Send specific messages on specific situations
 * @author WaleedAhmad
 * @version 1.0.0
 */
public class Message implements Observer
{

    public static void log(MessageEnum tag, String message){
        String logTag = "";
        switch (tag)
        {
            case INFORMATION:
                logTag = "--> ";
                break;
            case WARNING:
                logTag = "--> ";
                break;
            case ERROR:
                logTag = "--> ";
                break;
        }
        System.out.println("");
        System.out.println(logTag + message);
    }
    
    /**
     * @param Message content
     */
    public static void log(String message)
    {
        log(MessageEnum.INFORMATION, message);
    }

	/**
	 * Called whenever model pushes a message
	 */
	@Override
	public void update(Observable o, Object arg) 
	{		
	     System.out.println((String) arg);		
	}
    
}
