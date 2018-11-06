package model.Interfaces;

import utility.Results;

import java.util.ArrayList;

import model.AttackPlan;
import model.Card;
import model.GameDriver;
import model.Interfaces.StrategyInterface;

/**
 * @author Meet_Patel
 * @version 1.0.0
 */
public interface PlayerInterface extends Comparable <PlayerInterface> {

	Results ownTerritory(TerritoryInterface territory);
    Results lostTerritory(TerritoryInterface territory);

    ArrayList<TerritoryInterface> getTerritories();


    String getName();
    void setName(String newName);

    void setUnusedArmies(int armies);
    int getUnusedArmies();

    void setUsedArmies(int armies);
    int getUsedArmies();

    String toString();

    Results placeArmy(int count, TerritoryInterface territory);

    String getState();

    TerritoryInterface getTerritoryByName(String territoryName);

    TerritoryInterface getRandomTerritory();

    Results moveArmies(TerritoryInterface from, TerritoryInterface to, int number);
    
    AttackPlan getTerritoryToAttack();

    void setGameDriver(GameDriver gm);
    GameDriver getGameDriver();

    void reinforcement();
    void attack(int attempts);
    void attack();
    void fortification();

    int compareTo(PlayerInterface o);
    
    StrategyInterface getStrategy();
    void setStrategy(StrategyInterface strategy);
    void setStatus(boolean status);
    boolean getStatus();
    
    
    void addCard(Card crd);
    ArrayList<Card> getCardSet();
    int getTrades();
    void increaseTrades();
    int getCardsSize();
}
