package model.strategy;

import model.Interfaces.StrategyInterface;

public class Aggressive implements StrategyInterface {

    public Aggressive()
    {

    }

    @Override
    public int getAttackAttempts() {
        return 6;
    }

    @Override
    public String getName() {
        return "Aggressive";
    }
}
