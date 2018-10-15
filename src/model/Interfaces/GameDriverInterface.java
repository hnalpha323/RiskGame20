package model.Interfaces;

import model.Players;

import java.util.ArrayList;

public interface GameDriverInterface {

    void allocateTerritories(ArrayList<Players> players);

    /**
     * According to the game rules, number of armies each player has is set by the number of players
     * @param players number of players
     */
    void allocateArmies(ArrayList<Players> players);

}
