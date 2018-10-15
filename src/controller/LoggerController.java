package controller;

import utility.MessageEnum;

/**
 * TO send notify View and send messages to the view
 * @author Muhammad_Hamza_Noor
 * @version 1.0.0
 */
public class LoggerController {


	/**
	 * @param Message Enumerator
	 * @param Content of message
	 */
	public static void log(MessageEnum tag, String message){
		view.Message.log(tag, message);
	}

	/**
	 * TO log the message to View when called
	 * @param Content of message
	 */
	public static void log(String message){
		view.Message.log(message);
	}

}
