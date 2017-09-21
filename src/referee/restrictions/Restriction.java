package referee.restrictions;

import match.Match;
import referee.Referee;

import java.util.TreeMap;

/**
 * Created by lukas on 17.9.21.
 */
//public abstract class Restriction {
//
//    protected TreeMap<String, Referee> referees;
//    protected Match match;
//
//    protected Restriction(TreeMap<String, Referee> refereeCandidates, Match match) {
//        this.referees = new TreeMap<String, Referee>(refereeCandidates);
//        this.match = match;
//    }
//
//    public abstract TreeMap<String, Referee> getRefereeList();
//    public abstract Match getMatchDetails();
//
//}

public interface Restriction {

    public abstract TreeMap<String, Referee> getRefereeList();
    public abstract Match getMatchDetails();

}