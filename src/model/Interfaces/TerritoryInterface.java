package model.Interfaces;

import util.ActionResponse;

import java.util.ArrayList;

/**
 * @author Meet_Patel
 * @version 1.0.0
 *
 */
public interface TerritoryInterface {

	void setOwner(PlayerInterface player);
    PlayerInterface getOwner();
    String getName();

    int getArmies();
    void placeArmies(int count);
    ActionResponse removeArmies(int count);
    boolean hasAdjacencyWith(TerritoryInterface t);
    ArrayList<TerritoryInterface> getAdjacentTerritoryObjects();
}
