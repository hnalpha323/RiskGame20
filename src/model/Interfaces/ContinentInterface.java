package model.Interfaces;

import java.util.ArrayList;

public interface ContinentInterface {
	
	ArrayList<TerritoryInterface> getTerritories();
	String getName();
	
	int getContinentValue();
	void setContinentValue(int value);
	
    void addTerritory(TerritoryInterface t);
    void removeTerritory(TerritoryInterface t);
    
    boolean controlByPlayer(PlayerInterface p);

}
