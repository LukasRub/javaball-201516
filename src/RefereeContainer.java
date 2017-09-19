import java.util.*;

/**
 * Created by lukas on 17.9.19.
 */
public class RefereeContainer {

    public static final int MAX_REFEREES = 12;
    private static RefereeContainer ourInstance = null;
    private TreeMap<String, Referee> referees;

    private RefereeContainer() {
        ourInstance.referees = new TreeMap<String, Referee>();
    }

    public static RefereeContainer getInstance() {
        if (ourInstance == null) {
            synchronized (RefereeContainer.class) {
                ourInstance = new RefereeContainer();
            }
        }
        return ourInstance;
    }

    public void addReferee(Referee newReferee) {
        if (ourInstance.referees.size() < MAX_REFEREES) {
            String newKey = findUniqueKey(newReferee.getInitials());
            ourInstance.referees.put(newKey, newReferee);
        }
    }

    public TreeMap<String, Referee> getRefereeList() {
        return (TreeMap<String, Referee>) ourInstance.referees.clone();
    }

    private String findUniqueKey(String newRefereeInitials) {
        int index = 0;
        String potentialKey;
        do {
            potentialKey = newRefereeInitials + String.valueOf(++index);
        }
        while(ourInstance.referees.containsKey(potentialKey));
        return potentialKey;
    }

}
