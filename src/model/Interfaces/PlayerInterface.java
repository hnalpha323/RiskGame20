package model.Interfaces;

import util.ActionResponse;

import java.util.ArrayList;

public interface PlayerInterface {

	ActionResponse ownTerritory(TerritoryInterface territory);
    ArrayList<TerritoryInterface> getTerritories();


    String getName();
    void setName(String newName);

    void setUnusedArmies(int armies);
    int getUnusedArmies();

    void setUsedArmies(int armies);
    int getUsedArmies();

    String toString();

    ActionResponse placeArmy(int count, TerritoryInterface territory);

    String getState();

    TerritoryInterface getTerritoryByName(String territoryName);

    TerritoryInterface getRandomTerritory();

    ActionResponse moveArmies(TerritoryInterface from, TerritoryInterface to, int number);
}
