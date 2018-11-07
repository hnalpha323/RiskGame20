/**
 * 
 */
package model;

import java.util.ArrayList;
import model.Interfaces.PlayerInterface;

/**
 * @author WaleedAhmad
 *
 */
public class Model{

    public static Notifier notifier = null;
    

	public void setNotifier(Notifier new_notifier){
			notifier  = new_notifier;
	}
	
	public void sendNotification(String type,Object object){
		if(type.equals("GamePlay")){
			ArrayList<PlayerInterface> players = (ArrayList)object;			
			notifier.notifyListners(type,"PhaseChange:Set Up");			
			for(PlayerInterface player: players){
		           notifier.notifyListners(type,player);
			}
		}else if(type.equals("PhaseChange")){
			notifier.notifyListners(type,object);
		}		
		else{
			if(notifier != null)
				notifier.notifyListners(type,object);
		}

	}

	public void sendNotification(NotificationType type,Object object){
		   
           notifier.notifyListners("",object);
	}
	
}
