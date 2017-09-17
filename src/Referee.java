import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;

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

    private Referee(){
        this.areaAvailability.add(this.area);
    }

    public Referee(String id, String firstName, String lastName, QualBody qualificationBody, QualLevel qualificationLevel,
            Area area, String areaAvailability, int matchesAllocated) {
        this();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.qualificationBody = qualificationBody;
        this.qualificationLevel = qualificationLevel;
        this.area = area;
        this.areaAvailability = this.parseAreaAvailability(areaAvailability);
        this.matchesAllocated = matchesAllocated;
    }

    private ArrayList<Area> parseAreaAvailability(String areaAvailability) {
//        Set<Area> areas = EnumSet.allOf(Area.class);
//        if ((areaAvailability.charAt(0) == 'Y') && (!this.areaAvailability.contains(Area.NORTH))) {
//
//        }
        return null;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }









}
