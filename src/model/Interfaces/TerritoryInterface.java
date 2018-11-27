package model.Interfaces;

import utility.Results;

import java.util.ArrayList;


/**
 * Business functions for Territory
 */
public interface TerritoryInterface {

    void setOwner(PlayerInterface player);
    PlayerInterface getOwner();
    String getName();
    ContinentInterface getContinent();
    void setContinent(ContinentInterface c);

    int getArmies();
    void placeArmies(int count);
    Results removeArmies(int count);
    Results killArmies(int count);
    boolean hasAdjacencyWith(TerritoryInterface t);
    ArrayList<TerritoryInterface> getAdjacentTerritoryObjects();
    ArrayList<TerritoryInterface> getAdjacentNeighbours();

}
