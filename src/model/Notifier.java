
package model;

import java.util.Observable;


/**
 * @author Meet_Patel
 * @version 2.0.0
 */
public class Notifier extends Observable{

	public String notificationType = "null";
	
	public void notifyListners(String type,Object object){
		notificationType = type;
		setChanged();
		notifyObservers(object);		
	}
	
}
