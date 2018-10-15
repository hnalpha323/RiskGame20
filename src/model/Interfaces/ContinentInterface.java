package model.Interfaces;

import java.util.ArrayList;

/**
 * @author Meet_Patel
 * @version 1.0.0
 *
 */
public interface ContinentInterface {
	
	ArrayList<TerritoryInterface> getTerritories();
	String getName();
	
	int getContinentValue();
	void setContinentValue(int value);
	
    void addTerritory(TerritoryInterface t);
    void removeTerritory(TerritoryInterface t);
    
    boolean controlByPlayer(PlayerInterface p);

}
