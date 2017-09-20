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


        Match match1 = new Match(QualLevel.TWO, Area.CENTRAL);
        match1.assignReferee(referees.getRefereeList().get("DG1"));
        match1.assignReferee(referees.getRefereeList().get("DG2"));

        Match match2 = new Match(QualLevel.FOUR, Area.NORTH);
        match2.assignReferee(referees.getRefereeList().get("DG2"));
        match2.assignReferee(referees.getRefereeList().get("DG3"));

        MatchContainer matches = MatchContainer.getInstance();
        matches.addMatch(2, match2);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);
        matches.addMatch(match1);




        referees.printReferees();
        matches.printMatches();

        System.out.println(matches.size());

    }

}
