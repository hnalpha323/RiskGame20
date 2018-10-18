package view;

import utility.MessageEnum;

/**
 * Send specific messages on specific situations
 * @author WaleedAhmad
 * @version 1.0.0
 */
public class Message {

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


    public static void log(String message)
    {
        log(MessageEnum.INFORMATION, message);
    }
    
}
