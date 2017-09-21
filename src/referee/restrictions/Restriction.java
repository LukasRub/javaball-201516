package referee.restrictions;

import match.Match;
import referee.Referee;

import java.util.TreeMap;

/**
 * Created by lukas on 17.9.21.
 */

public interface Restriction {

    public TreeMap<String, Referee> getRefereeList();
    public Match getMatchDetails();

}