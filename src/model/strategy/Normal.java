package model.strategy;

import model.Interfaces.StrategyInterface;

public class Normal implements StrategyInterface {

    public Normal()
    {

    }

    @Override
    public int getAttackAttempts() {
        return 1;
    }

    @Override
    public String getName() {
        return "Normal";
    }
}
