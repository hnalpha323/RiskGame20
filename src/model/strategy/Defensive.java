package model.strategy;

import model.Interfaces.StrategyInterface;

public class Defensive implements StrategyInterface {


    public Defensive()
    {

    }

    @Override
    public int getAttackAttempts() {
        return 0;
    }

    @Override
    public String getName() {
        return "Defensive";
    }
}
