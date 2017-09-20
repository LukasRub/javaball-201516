import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lukas on 17.9.17.
 */
public class Referee {

    private String firstName;
    private String lastName;
    private String id = null;
    private boolean hasID = false;
    private String initials;
    private QualBody qualificationBody;
    private QualLevel qualificationLevel;
    private Area area;
    private ArrayList<Area> areaAvailability;
    private int matchesAllocated;
    private LinkedList<Integer> assignedMatches;

    public Referee(String firstName, String lastName, String qualification, String area, String areaAvailability,
                   int matchesAllocated) {

        this.areaAvailability = new ArrayList<Area>(Area.values().length);
        assignedMatches = new LinkedList<Integer>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.matchesAllocated = matchesAllocated;

        parseArea(area);
        this.areaAvailability.add(this.area);

        parseInitials(firstName, lastName);
        parseQualificationString(qualification);
        parseAreaAvailability(areaAvailability);

    }

    public Referee(String id, String firstName, String lastName, String qualification,
                   String area, String areaAvailability, int matchesAllocated) {
        this(firstName, lastName, qualification, area, areaAvailability, matchesAllocated);
        this.id = id;
        hasID = true;
    }

    public Referee(String id, String firstName, String lastName, String qualification, String matchesAllocated,
                   String area, String areaAvailability) {
        this(id, firstName, lastName, qualification, area, areaAvailability, Integer.parseInt(matchesAllocated));
    }

    public Referee(String[] inputArray) {
        this(inputArray[0], inputArray[1], inputArray[2], inputArray[3], inputArray[4], inputArray[5], inputArray[6]);
    }

    private void parseQualificationString(String qualification) throws IllegalArgumentException {
        String pattern = "([A-Z]+)(?=(\\d+))";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(qualification);
        if (matcher.find()) {
            qualificationBody = QualBody.parseFromString(matcher.group(1));
            qualificationLevel = QualLevel.parseFromInt(Integer.parseInt(matcher.group(2)));
        }
        else throw new IllegalArgumentException("Invalid qualification string.");
    }

    private void parseAreaAvailability(String areaAvailability) {
        Set<Area> areas = EnumSet.allOf(Area.class);
        Iterator<Area> iterator = areas.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Area currentArea = iterator.next();
            if ((areaAvailability.charAt(index++) == 'Y') && (!this.areaAvailability.contains(currentArea))) {
                this.areaAvailability.add(currentArea);
            }
        }
    }

    private void parseInitials(String firstName, String lastName) {
        char[] initials = new char[2];
        initials[0] = firstName.charAt(0);
        initials[1] = lastName.charAt(0);
        this.initials = new String(initials);
    }

    private void parseArea(String area) {
        this.area = Area.parseFromString(area);
    }

    public void assignMatch(int weekNumber) {
        assignedMatches.addLast(weekNumber);
        ++matchesAllocated;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getQualificationString() {
        return qualificationBody.getQualBodyTitle() + this.qualificationLevel.getLevel();
    }

    public Area getHomeArea() {
        return area;
    }

    public ArrayList<Area> getAreaAvailability() {
        return new ArrayList<Area>(areaAvailability);
    }

    public String getInitials() {
        return initials;
    }

    public boolean hasID() {
        return hasID;
    }

    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s%d from the %s, available areas: %s, matches allocated: %d",
                firstName,
                lastName,
                qualificationBody.getQualBodyTitle(),
                qualificationLevel.getLevel(),
                area.toString(),
                areaAvailability.toString(),
                matchesAllocated
        );
    }

}
