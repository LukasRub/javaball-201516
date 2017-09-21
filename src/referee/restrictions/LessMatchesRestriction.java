package referee.restrictions;

import referee.Referee;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lukas on 17.9.21.
 */
public class LessMatchesRestriction extends RestrictionDecorator {

    public LessMatchesRestriction(Restriction restriction) {
        super(restriction);
    }

    @Override
    public TreeMap<String, Referee> getRefereeList() {
        TreeMap<String, Referee> referees = new TreeMap<String, Referee>(restriction.getRefereeList());
        TreeMap<String, Referee> candidates = new TreeMap<String, Referee>();

        Map.Entry<String, Referee> min1 = findRefereeWithLeastMatches(referees); referees.remove(min1.getKey());
        Map.Entry<String, Referee> min2 = findRefereeWithLeastMatches(referees);

        candidates.put(min1.getKey(), min1.getValue());
        candidates.put(min2.getKey(), min2.getValue());

        return candidates;
    }

    private Map.Entry<String, Referee> findRefereeWithLeastMatches(TreeMap<String, Referee> referees) {
        Map.Entry<String, Referee> minEntry = referees.firstEntry();
        for(Map.Entry<String, Referee> entry : referees.entrySet()) {
            if (entry.getValue().getMatchesAllocated() < minEntry.getValue().getMatchesAllocated()) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

}
