import java.util.*;

/**
 * Created by lukas on 17.9.20.
 */
public class MatchContainer {

    public static final int NUMBER_OF_WEEKS = 52;

    private static MatchContainer ourInstance;
    private TreeMap<Integer, Match> matches = new TreeMap<Integer, Match>();
    private ArrayList<Integer> availableWeeks = new ArrayList<Integer>(NUMBER_OF_WEEKS);

    public static MatchContainer getInstance() {
        if (ourInstance == null) {
            synchronized (RefereeContainer.class) {
                ourInstance = new MatchContainer();
                for (int i = 1; i <= NUMBER_OF_WEEKS; ++i) {
                    ourInstance.availableWeeks.add(i);
                }
            }
        }
        return ourInstance;
    }

    public void addMatch(Match match) {
        addMatch(-1, match, true);
    }

    public void addMatch(int weekNumber, Match match) {
        addMatch(weekNumber, match, false);
    }

    public void addMatch(int weekNumber, Match match, boolean randomWeek) {
        if (ourInstance.matches.size() < NUMBER_OF_WEEKS) {
            if (randomWeek)
                weekNumber = getRandomWeekNumber();
            if (randomWeek || isUnassignedWeek(weekNumber)) {
                Map.Entry<Integer, Match> entry = new AbstractMap.SimpleEntry<Integer, Match>(weekNumber, match);
                ourInstance.matches.put(entry.getKey(), entry.getValue());
                ourInstance.availableWeeks.remove(ourInstance.availableWeeks.indexOf(entry.getKey()));
                match.appendMatchInfoToReferees(entry);
            } else throw new RuntimeException("Week no. " + String.valueOf(weekNumber) + " is already assigned.");
        }
        else throw new RuntimeException("Maximum number of matches already reached.");
    }

    private int getRandomWeekNumber() {
        int weekNumberIndex = ourInstance.availableWeeks.get(0);
        int availableWeeks = ourInstance.availableWeeks.size();
        if (availableWeeks > 1) {
            Random rand = new Random();
            weekNumberIndex = rand.nextInt(availableWeeks);
        }
        return ourInstance.availableWeeks.get(weekNumberIndex);
    }

    public void printMatches() {
        System.out.println("<===================== Matches =====================>");
        for (Map.Entry<Integer, Match> entry : ourInstance.matches.entrySet()) {
            System.out.println(String.format("Key: %2d | Value: %s", entry.getKey(), entry.getValue().toString()));
        }
        System.out.println();
    }

    private boolean isUnassignedWeek(int weekNumber) {
        return !ourInstance.matches.containsKey(weekNumber);
    }

    public int size() {
        return ourInstance.matches.size();
    }

}
