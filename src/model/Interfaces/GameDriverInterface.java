package model.Interfaces;

import model.Players;

import java.util.ArrayList;

/**
 * Set Number of Armies that are needed to be given based on player count and based on rules
 * @author Meet_Patel
 * @version 1.0.0
 */
public interface GameDriverInterface {

    void allocateTerritories(ArrayList<Players> players);

    void allocateArmies(ArrayList<Players> players);

}
