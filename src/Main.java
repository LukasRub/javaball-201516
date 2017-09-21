import general.Area;
import match.Match;
import referee.Referee;
import referee.RefereeContainer;
import referee.qualifications.QualLevel;
import referee.restrictions.LevelRestriction;
import referee.restrictions.Restriction;
import referee.restrictions.SameAreaRestriction;

import java.io.IOException;

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

        Match match = new Match(QualLevel.TWO, Area.CENTRAL);
        Restriction restriction = new SameAreaRestriction(new LevelRestriction(referees.getRefereeList(), match));
//        restriction.

        match.assignReferee(referees.getRefereeList().get("DG1"));
        match.assignReferee(referees.getRefereeList().get("DG2"));
        System.out.println(restriction.getRefereeList());

//        MatchContainer matches = MatchContainer.getInstance();
//        matches.addMatch(2, match1);
//        matches.addMatch(match2);
//
        System.out.println(referees.toString());
//        matches.printMatches();



    }

}
