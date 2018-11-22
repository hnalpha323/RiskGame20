package model.Interfaces;


import java.util.ArrayList;

/**
 * @author Meet_Patel
 * @version 3.0.0
 */
public interface MapInterface {

    ArrayList<ContinentInterface> getContinents();
    void clearData();
    void fakeData();
    void setName(String name);
    String getName();

}