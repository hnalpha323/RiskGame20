package model.Interfaces;
import model.FinalResult;
import model.Map;
import java.util.ArrayList;

/**
 * @author Meet_Patel
 * @version 3.0.0
 */

public interface TournamentInterface {
    void start(ArrayList<Map> maps, int p, String playerStrategies, int g, int d);

    String getResult(ArrayList<FinalResult> results, int mapCount, int gameCount);

}

