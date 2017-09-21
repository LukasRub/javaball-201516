package referee.restrictions;

import match.Match;
import referee.Referee;
import referee.RefereeContainer;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lukas on 17.9.21.
 */
public class LevelRestriction implements Restriction {

    private TreeMap<String, Referee> referees;
    private Match match;

    public LevelRestriction(TreeMap<String, Referee> referees, Match match) {
        this.referees = new TreeMap<String, Referee>(referees);
        this.match = match;
    }

    public LevelRestriction() {}

    @Override
    public TreeMap<String, Referee> getRefereeList() {
        TreeMap<String, Referee> candidates = new TreeMap<String, Referee>();
        for (Map.Entry<String, Referee> entry : referees.entrySet()) {
            if (entry.getValue().getQualificationLevel().getLevel() >= match.getRequiredLevel().getLevel()) {
                candidates.put(entry.getKey(), entry.getValue());
            }
        }
        return candidates;
    }

    @Override
    public Match getMatchDetails() {
        return match;
    }

}
