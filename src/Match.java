import java.util.LinkedList;

/**
 * Created by lukas on 17.9.20.
 */
public class Match {
    public static final int REFEREES_PER_MATCH = 2;

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

}
