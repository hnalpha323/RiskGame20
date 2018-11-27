package model;

import model.GameDriver;
import org.junit.Test;
import exceptions.PlayerException;

/**
 * Start game without a map
 */
public class NoMapTest {

    @Test(expected = Exception.class)
    public void test() throws PlayerException
    {
        GameDriver gm = new GameDriver(3, "r,r,r", 500);
        gm.start(false);
    }

}