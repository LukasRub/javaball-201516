import general.Area;
import match.Match;
import referee.Referee;
import referee.RefereeContainer;
import referee.qualifications.QualLevel;
import referee.restrictions.*;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lukas on 17.9.19.
 */
public class Main {

    public static void main(String [] args) {

        RefereeContainer referees = RefereeContainer.getInstance();

        Referee ref1 = new Referee("David", "Guerrera", "NJB2", "North", "YYY", 0);
        Referee ref2 = new Referee("DG2", "Donald", "Grayson", "NJB2", "Central", "YYN", 16);
        Referee ref3 = new Referee("Daniel", "Garland", "IJB1", "North", "YYY", 5);

        referees.addReferee(ref1);
        referees.addReferee(ref2);
        referees.addReferee(ref3);

        try {
            RefereeFileHandler.readRefereeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Match match = new Match(QualLevel.TWO, Area.NORTH);
        TreeMap<String, Referee> initialRefereeList = new TreeMap<String, Referee>(referees.getRefereeList());
        TreeMap<String, Referee> refereeList = new TreeMap<String, Referee>(referees.getRefereeList());

        Restriction restriction = new SameAreaRestriction(new LevelRestriction(refereeList, match));
        if (restriction.getRefereeList().size() > 2) {
            restriction = new LessMatchesRestriction(restriction);
            refereeList = restriction.getRefereeList();
            match.assignReferee(refereeList.firstEntry().getValue());
            match.assignReferee(refereeList.lastEntry().getValue());
        }
        else {
            boolean firstRefereeAssigned = false;
            if (restriction.getRefereeList().size() == 1 ) {
                Map.Entry<String, Referee> entry = refereeList.firstEntry();
                match.assignReferee(entry.getValue());
                firstRefereeAssigned = true;
                refereeList = new TreeMap<String, Referee>(referees.getRefereeList());
                refereeList.remove(entry.getKey());
            }
            restriction = new TravelRestriction(new LevelRestriction(refereeList, match), 1);

        }
//        restriction.getRefereeList();

        System.out.println(match.toString());


//        restriction.

//        match.assignReferee(referees.getRefereeList().get("DG1"));
//        match.assignReferee(referees.getRefereeList().get("DG2"));
        System.out.println(restriction.getRefereeList());
        System.out.println(referees.toString());



    }

}
