import java.util.*;

/**
 * Created by lukas on 17.9.19.
 */
public class RefereeContainer {

    public static final int MAX_REFEREES = 12;
    private static RefereeContainer ourInstance;
    private TreeMap<String, Referee> referees = new TreeMap<String, Referee>();

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
            String newKey;
            if (!newReferee.hasID() || (newReferee.hasID() && ourInstance.referees.containsKey(newReferee.getID())))
                newKey = findUniqueKey(newReferee.getInitials());
            else
                newKey = newReferee.getID();
            ourInstance.referees.put(newKey, newReferee);
        }
        else throw new RuntimeException("No more referees can be added.");
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

    @Override
    public String toString(){
        String labels = String.format("| %6s | %-16s | Qualification | Home region | %-25s | No. of matches |",  "ID", "Name", "Available regions");
        String dashLine = String.join("", Collections.nCopies(labels.length(), "-"));
        String toString = dashLine + "\n" + labels + "\n" + dashLine;
        for (Map.Entry<String, Referee> entry : ourInstance.referees.entrySet()) {
            toString += String.format("\n|%7s | %s", entry.getKey(), entry.getValue().toString());
            toString += "\n" + dashLine;
        }
        return toString;
    }

}
