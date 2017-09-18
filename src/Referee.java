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

    private String id;
    private String firstName;
    private String lastName;
    private QualBody qualificationBody;
    private QualLevel qualificationLevel;
    private Area area;
    private ArrayList<Area> areaAvailability;
    private int matchesAllocated;

    private Referee() {
        this.areaAvailability = new ArrayList<Area>();
        this.areaAvailability.add(this.area);
    }

    public Referee(String id, String firstName, String lastName, String qualification,
            Area area, String areaAvailability, int matchesAllocated) {
        this();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.parseQualificationString(qualification);
        this.area = area;
        this.parseAreaAvailability(areaAvailability);
        this.matchesAllocated = matchesAllocated;
    }

    private void parseQualificationString(String qualification) throws IllegalArgumentException {
        String pattern = "([A-Z]+)(?=(\\d+))";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(qualification);
        if (matcher.find()) {
            this.qualificationBody = QualBody.parseFromString(matcher.group(0));
            this.qualificationLevel = QualLevel.parseFromInt(Integer.parseInt(matcher.group(1)));
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

    public String getFullName() {
        return firstName + " " + lastName;
    }









}
