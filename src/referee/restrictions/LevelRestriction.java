package referee.restrictions;

import match.Match;
import referee.Referee;
import referee.RefereeContainer;
import referee.qualifications.QualLevel;

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
            QualLevel requiredLevel = match.getRequiredLevel();
            QualLevel currentRefereeLevel = entry.getValue().getQualificationLevel();

            if (((requiredLevel == QualLevel.ONE) && (currentRefereeLevel == requiredLevel )) || (currentRefereeLevel != QualLevel.ONE)) {
                candidates.put(entry.getKey(), entry.getValue());
            }
            if (entry.getValue().getQualificationLevel().getLevel() >= match.getRequiredLevel().getLevel()) {}
        }
        return candidates;
    }

    @Override
    public Match getMatchDetails() {
        return match;
    }

}
