package model.Interfaces;

import model.AttackPlan;
import model.Interfaces.PlayerInterface;
import model.Interfaces.TerritoryInterface;

public interface StrategyInterface {

    int getAttackAttempts();
    String getName();


    TerritoryInterface getInforcementTerritory(PlayerInterface p);
    int getReinforcementArmies(PlayerInterface p);

    AttackPlan getAttackPlan(PlayerInterface p);
    int diceToAttack(PlayerInterface p);
    int diceToDefend(PlayerInterface p);
    int getMovingArmiesToNewTerritory(PlayerInterface p);

    TerritoryInterface getFortificationFromTerritory(PlayerInterface p);
    TerritoryInterface getFortificationToTerritory(PlayerInterface p, TerritoryInterface from);
    int getFortificationArmies(PlayerInterface p, TerritoryInterface from);

}
