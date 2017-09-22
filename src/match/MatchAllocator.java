package match;

import general.Area;
import referee.Referee;
import referee.RefereeContainer;
import referee.restrictions.LevelRestriction;
import referee.restrictions.Restriction;
import referee.restrictions.TravelRestriction;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lukas on 17.9.22.
 */
public class MatchAllocator {

    public static void findSuitableReferees(Match match) {

        RefereeContainer refereeContainer = RefereeContainer.getInstance();
        TreeMap<String, Referee> initialList = new TreeMap<String, Referee>(refereeContainer.getRefereeList());

        TreeMap<String, Referee> selectedReferees =
                recursiveShit(new TreeMap<String, Referee>(), initialList, match, 0);

        System.out.println(selectedReferees);

    }

    private static TreeMap<String, Referee> recursiveShit(TreeMap<String, Referee> selected,
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
            copyAndDeleteEntries(findRefsWithLeastAllocs(list, Match.REFEREES_PER_MATCH - selected.size()),
                    selected, candidates);
        }

        return recursiveShit(selected, candidates, match, ++travelDistance);
    }

    private static TreeMap<String, Referee> applyRestrictions(TreeMap<String, Referee> refereeList,
                                                              Match match, int distance) {
        Restriction restriction = new TravelRestriction(new LevelRestriction(refereeList, match), distance);
        return restriction.getRefereeList();
    }

    private static void copyAndDeleteEntries(TreeMap<String, Referee> from, TreeMap<String, Referee> to,
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
