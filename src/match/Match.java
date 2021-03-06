package match;

import referee.Referee;
import referee.qualifications.QualLevel;
import general.Area;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by lukas on 17.9.20.
 */
public class Match {
    public static final int REFEREES_PER_MATCH = 3;

    private QualLevel requiredLevel;
    private Area matchLocation;
    private LinkedList<Referee> assignedReferees;

    public Match(QualLevel qualLevel, Area area) {
        assignedReferees = new LinkedList<Referee>();
        requiredLevel = qualLevel;
        matchLocation = area;
    }

    public void assignReferee(Referee referee) {
        if (assignedReferees.size() < REFEREES_PER_MATCH) {
            assignedReferees.addLast(referee);
        }
    }

    public void appendMatchInfoToReferees(Map.Entry<Integer, Match> entry) {
        Iterator<Referee> iterator = assignedReferees.iterator();
        while (iterator.hasNext()) {
            iterator.next().assignMatch(entry);
        }
    }

    public Area getMatchLocation() {
        return matchLocation;
    }

    public QualLevel getRequiredLevel() {
        return requiredLevel;
    }

    public String assignedRefereesToString() {
        String assignedRefereesToString = "";
        Iterator<Referee> iterator = assignedReferees.iterator();
        while (iterator.hasNext()) {
            assignedRefereesToString += String.format("%-25s", iterator.next().getFullName());
        }
        return assignedRefereesToString;
    }

    @Override
    public String toString() {
        return String.format("%-7s %-7s %s",
                requiredLevel.getEligibility(),
                matchLocation.toString(),
                assignedRefereesToString()
        );
    }

}
