package View;

import utility.MessageEnum;

/**
 * @author WaleedAhmad
 * @version 1.0.0
 * Used to Log the messages on Command Prompt
 */
public class Message {
	 /**
     * place a new log
     * @param tag log message type
     * @param message message content
     */
    public static void log(MessageEnum tag, String message){
        String logTag = "";
        switch (tag)
        {
            case INFORMATION:
                logTag = "[:)] ";
                break;
            case WARNING:
                logTag = "[:|] ";
                break;
            case ERROR:
                logTag = "[:(] ";
                break;
        }
        System.out.println("");
        System.out.println(logTag + message);
    }

    /**
     * place a new log with INFO tag
     * @param message message content
     */
    public static void log(String message)
    {
        log(MessageEnum.INFORMATION, message);
    }

}
