package referee.restrictions;

import general.Area;
import match.Match;
import referee.Referee;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lukas on 17.9.21.
 */
public class TravelRestriction extends RestrictionDecorator {

    private int distance;

    public TravelRestriction(Restriction restriction, int distance) {
        super(restriction);
        this.distance = distance;
    }


    @Override
    public TreeMap<String, Referee> getRefereeList() {

        TreeMap<String, Referee> referees = new TreeMap<String, Referee>(restriction.getRefereeList());
        TreeMap<String, Referee> candidates = new TreeMap<String, Referee>();

        Area matchLocation = restriction.getMatchDetails().getMatchLocation();

        for(Map.Entry<String, Referee> entry : referees.entrySet()) {

            if (entry.getValue().wouldTravelTo(matchLocation)
                    && entry.getValue().getHomeArea().calculateDistance(matchLocation) == distance) {

               candidates.put(entry.getKey(), entry.getValue());

            }

        }
        return candidates;
    }

}
