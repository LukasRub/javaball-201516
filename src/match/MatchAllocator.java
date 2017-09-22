package match;

import general.Area;
import referee.Referee;
import referee.RefereeContainer;
import referee.restrictions.LevelRestriction;
import referee.restrictions.Restriction;
import referee.restrictions.TravelRestriction;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lukas on 17.9.22.
 */
public class MatchAllocator {

    private static RefereeContainer refereeContainer = RefereeContainer.getInstance();

    public static void findAssignSuitableReferees(int weekNumber, Match match) {
        LinkedHashMap<String, Referee> selectedReferees = findSuitableReferees(match);
        for (Map.Entry<String, Referee> entry : selectedReferees.entrySet()) {
            match.assignReferee(refereeContainer.getRefereeList().get(entry.getKey()));
        }
        MatchContainer.getInstance().addMatch(weekNumber, match);
    }

    public static void findAssignSuitableReferees(Match match) {
        LinkedHashMap<String, Referee> selectedReferees = findSuitableReferees(match);
        for (Map.Entry<String, Referee> entry : selectedReferees.entrySet()) {
            match.assignReferee(refereeContainer.getRefereeList().get(entry.getKey()));
        }
        MatchContainer.getInstance().addMatch(match);
    }

    private static LinkedHashMap<String, Referee> findSuitableReferees(Match match) {
        TreeMap<String, Referee> initialList = new TreeMap<String, Referee>(refereeContainer.getRefereeList());
        return recursiveSearch(new LinkedHashMap<String, Referee>(), initialList, match, 0);
    }

    private static LinkedHashMap<String, Referee> recursiveSearch(LinkedHashMap<String, Referee> selected,
                                                            TreeMap<String, Referee> candidates,
                                                            final Match match, int travelDistance) {
        if ((selected.size() == Match.REFEREES_PER_MATCH) || (travelDistance > Area.values().length)) {
            return selected;
        }
        TreeMap<String, Referee> list = applyRestrictions(candidates, match, travelDistance);
        if ((list.size() + selected.size()) <= Match.REFEREES_PER_MATCH) {
            copyAndDeleteEntries(list, selected, candidates);
        }
        else  {
            int numberOfRefsRequired = Match.REFEREES_PER_MATCH - selected.size();
            copyAndDeleteEntries(findRefsWithLeastAllocs(list, numberOfRefsRequired), selected, candidates);
        }
        return recursiveSearch(selected, candidates, match, ++travelDistance);
    }

    private static TreeMap<String, Referee> applyRestrictions(TreeMap<String, Referee> refereeList,
                                                              Match match, int distance) {
        Restriction restriction = new TravelRestriction(new LevelRestriction(refereeList, match), distance);
        return restriction.getRefereeList();
    }

    private static void copyAndDeleteEntries(TreeMap<String, Referee> from, LinkedHashMap<String, Referee> to,
                                      TreeMap<String, Referee> other) {
        for(Map.Entry<String, Referee> entry : from.entrySet()) {
            to.put(entry.getKey(), entry.getValue());
            other.remove(entry.getKey());
        }
    }

    private static TreeMap<String, Referee> findRefsWithLeastAllocs(TreeMap<String, Referee> refereeList, int number) {
        TreeMap<String, Referee> leastAllocs = new TreeMap<String, Referee>();
        for (int i=0; i < number; i++) {
            Map.Entry<String, Referee> minEntry = findMinEntry(refereeList);
            leastAllocs.put(minEntry.getKey(), minEntry.getValue());
            refereeList.remove(minEntry.getKey());
        }
        return leastAllocs;
    }

    private static Map.Entry<String, Referee> findMinEntry(TreeMap<String, Referee> refereeList) {
        Map.Entry<String, Referee> minEntry = refereeList.firstEntry();
        if (refereeList.size() > 1) {
            for(Map.Entry<String, Referee> entry : refereeList.entrySet()) {
                if (entry.getValue().getMatchesAllocated() < minEntry.getValue().getMatchesAllocated()) {
                    minEntry = entry;
                }
            }
        }
        return minEntry;
    }

}