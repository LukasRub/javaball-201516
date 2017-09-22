package referee;

import referee.qualifications.QualBody;
import referee.qualifications.QualLevel;
import general.Area;
import match.Match;

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
    private TreeMap<Integer, Match> assignedMatches;

    public Referee(String firstName, String lastName, String qualification, String area, String areaAvailability,
                   int matchesAllocated) {

        this.areaAvailability = new ArrayList<Area>(Area.values().length);
        assignedMatches = new TreeMap<Integer, Match>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.matchesAllocated = matchesAllocated;

        parseArea(area);
        this.areaAvailability.add(this.area);

        parseInitials(firstName, lastName);
        parseQualificationString(qualification);
        parseAreaAvailability(areaAvailability);

    }

    public Referee(String firstName, String lastName, QualBody qualificationBody, QualLevel qualificationLevel,
                   Area area, ArrayList<Area> areaAvailability, int matchesAllocated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.qualificationBody = qualificationBody;
        this.qualificationLevel = qualificationLevel;
        this.area = area;
        this.areaAvailability = new ArrayList<Area>(areaAvailability);
        this.matchesAllocated = matchesAllocated;

        assignedMatches = new TreeMap<Integer, Match>();
        parseInitials(firstName, lastName);
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

    public void assignMatch(Map.Entry<Integer, Match> entry) {
        assignedMatches.put(entry.getKey(), entry.getValue());
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

    public QualLevel getQualificationLevel() {
        return qualificationLevel;
    }

    public String getInitials() {
        return initials;
    }

    public boolean hasID() {
        return hasID;
    }

    public boolean wouldTravelTo(Area area) {
        boolean  wouldTravelTo = false;
        if (this.areaAvailability.indexOf(area) > -1)
            wouldTravelTo = true;
        return wouldTravelTo;
    }

    public String getID() {
        return id;
    }

    public int getMatchesAllocated() {
        return matchesAllocated;
    }

    private String assignedMatchesToString() {
        String toString = "";
        if (!assignedMatches.isEmpty()) {
//            String labels = String.format("->| Week | Level  | Region  | %-20s | %-20s |", "Ref. no. 1", "Ref. no. 2");
//            String dashLine = "  " + String.join("", Collections.nCopies(labels.length()-2, "-"));
//            toString += "\n" + dashLine + "\n" + labels + "\n" + dashLine;
            for (Map.Entry<Integer, Match> entry : assignedMatches.entrySet()) {
                toString += String.format("\n--->|%3d | %s", entry.getKey(), entry.getValue().toString());
                toString += String.join("", Collections.nCopies(17, "-"));
            }
        }
        return toString;
    }


    @Override
    public String toString() {
//        return String.format("%-16s | %-13s | %-11s | %-25s | %14d | %s",
//                getFullName(),
//                qualificationBody.getQualBodyTitle() + qualificationLevel.getLevel(),
//                area.toString(),
//                areaAvailability.toString(),
//                matchesAllocated,
//                assignedMatchesToString()
        return String.format("%-18s %-15s %-12s %-25s %17d %s",
                getFullName(),
                getQualificationString(),
                area.toString(),
                areaAvailability.toString(),
                matchesAllocated,
                assignedMatchesToString()
        );
    }

}
