package model.Interfaces;

import java.util.ArrayList;

import model.Interfaces.PlayerInterface;
import model.Interfaces.TerritoryInterface;

/**
 * @author Meet_Patel
 * @version 1.0.0
 */
public interface ContinentInterface {
	
	ArrayList<TerritoryInterface> getTerritories();
    void addTerritory(TerritoryInterface t);
    void removeTerritory(TerritoryInterface t);
    String getName();
    int getContinentValue();
    void setContinentValue(int value);
    boolean controlByPlayer(PlayerInterface p);

}
