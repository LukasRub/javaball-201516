import general.Area;
import match.*;
import referee.*;
import referee.qualifications.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by lukas on 17.9.19.
 */
public class Main {

    public static void main(String [] args) {

        RefereeContainer referees = RefereeContainer.getInstance();

        referees.addReferee(new Referee("David", "Guerrera", "NJB4", "North", "YNN", 0));
        referees.addReferee(new Referee("DG2", "Donald", "Grayson", "NJB2", "Central", "YYN", 16));
        referees.addReferee(new Referee("Daniel", "Garland", "IJB1", "North", "YYY", 5));
        referees.addReferee(new Referee("Lukas", "Rubikas", QualBody.NJB, QualLevel.FOUR, Area.CENTRAL,
                new ArrayList<Area>(Arrays.asList(Area.NORTH, Area.CENTRAL)), 5));
        referees.addReferee(new Referee("Paul", "Stevenson", "IJB4", "South", "YNY", 4));
        referees.addReferee(new Referee("Connor", "McTavish", "NJB4", "South", "YYY", 3));

        try {
            FileHandler.readRefereeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MatchAllocator.findAssignSuitableReferees(14, new Match(QualLevel.FOUR, Area.NORTH));
        MatchAllocator.findAssignSuitableReferees(15, new Match(QualLevel.ONE, Area.SOUTH));
        MatchAllocator.findAssignSuitableReferees(28, new Match(QualLevel.FOUR, Area.SOUTH));
        MatchAllocator.findAssignSuitableReferees(52, new Match(QualLevel.THREE, Area.NORTH));
        MatchAllocator.findAssignSuitableReferees(13, new Match(QualLevel.FOUR, Area.CENTRAL));
        MatchAllocator.findAssignSuitableReferees(51, new Match(QualLevel.ONE, Area.NORTH));
        MatchAllocator.findAssignSuitableReferees(3, new Match(QualLevel.ONE, Area.NORTH));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.TWO, Area.CENTRAL));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.FOUR, Area.SOUTH));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.TWO, Area.NORTH));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.ONE, Area.SOUTH));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.THREE, Area.NORTH));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.ONE, Area.SOUTH));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.FOUR, Area.NORTH));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.THREE, Area.CENTRAL));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.ONE, Area.NORTH));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.FOUR, Area.CENTRAL));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.ONE, Area.CENTRAL));
        MatchAllocator.findAssignSuitableReferees(new Match(QualLevel.ONE, Area.NORTH));

//        Match match = new Match(QualLevel.FOUR, Area.NORTH);
//        match.assignReferee(referees.getRefereeList().get("DG1"));
//        match.assignReferee(referees.getRefereeList().get("DG2"));
//        MatchContainer.getInstance().addMatch(match);

        try {
            FileHandler.writeMatchFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(referees.toString());

    }
}
