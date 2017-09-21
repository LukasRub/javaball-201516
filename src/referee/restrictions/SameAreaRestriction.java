package referee.restrictions;

import match.Match;
import referee.Referee;

import java.util.Map;
import java.util.TreeMap;


/**
 * Created by lukas on 17.9.21.
 */
public class SameAreaRestriction extends RestrictionDecorator {


    public SameAreaRestriction(Restriction restriction) {
        super(restriction);
    }

    @Override
    public TreeMap<String, Referee> getRefereeList() {
        TreeMap<String, Referee> referees = new TreeMap<String, Referee>(restriction.getRefereeList());
        TreeMap<String, Referee> candidates = new TreeMap<String, Referee>();
        for(Map.Entry<String, Referee> entry : referees.entrySet()) {
            if (entry.getValue().getHomeArea() == restriction.getMatchDetails().getMatchLocation()) {
                candidates.put(entry.getKey(), entry.getValue());
            }
        }
        return candidates;
    }

}
