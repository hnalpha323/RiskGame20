package controller;

import utility.MessageEnum;

/**
 * Controller sends print messages to View(View here is just a console)
 * @author Muhammad_Hamza_Noor
 * @version 1.0.0
 */
public class LoggerController {


	/**
	 * @param tag log message type
	 * @param message message content
	 */
	public static void log(MessageEnum tag, String message){
		view.Logger.log(tag, message);
	}

	/**
	 * place a new log with INFO tag
	 * @param message message content
	 */
	public static void log(String message){
		view.Logger.log(message);
	}

}
