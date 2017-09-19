import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lukas on 17.9.17.
 */
public class Referee {

    private String firstName;
    private String lastName;
    private String initials;
    private QualBody qualificationBody;
    private QualLevel qualificationLevel;
    private Area area;
    private ArrayList<Area> areaAvailability;
    private int matchesAllocated;

    private Referee() {
        areaAvailability = new ArrayList<Area>(Area.values().length);
        areaAvailability.add(area);
    }

    public Referee(String firstName, String lastName, String qualification,
            String area, String areaAvailability, int matchesAllocated) {

        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.matchesAllocated = matchesAllocated;

        parseArea(area);
        parseInitials(firstName, lastName);
        parseQualificationString(qualification);
        parseAreaAvailability(areaAvailability);

    }

    private void parseQualificationString(String qualification) throws IllegalArgumentException {
        String pattern = "([A-Z]+)(?=(\\d+))";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(qualification);
        if (matcher.find()) {
            qualificationBody = QualBody.parseFromString(matcher.group(0));
            qualificationLevel = QualLevel.parseFromInt(Integer.parseInt(matcher.group(1)));
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

}
