package model.Interfaces;

import utility.Results;

import java.util.ArrayList;

import model.Interfaces.TerritoryInterface;

/**
 * @author Meet_Patel
 * @version 1.0.0
 */
public interface TerritoryInterface {

	void setOwner(PlayerInterface player);
    PlayerInterface getOwner();
    String getName();

    int getArmies();
    void placeArmies(int count);
    Results removeArmies(int count);
    Results killArmies(int count);

    boolean hasAdjacencyWith(TerritoryInterface t);
    ArrayList<TerritoryInterface> getAdjacentTerritoryObjects();
    ArrayList<TerritoryInterface> getAdjacentNeighbours();

}
