/**
 * Created by lukas on 17.9.17.
 */
import java.lang.Math;

public enum Area {
    NORTH ("North", 0),
    CENTRAL ("Central", 1),
    SOUTH ("South", 2);

    private final String areaTitle;
    private final int distance;

    private Area(String areaTitle, int distance) {
        this.areaTitle = areaTitle;
        this.distance = distance;
    }

    public int calculateDistance(Area other) {
        return Math.abs(this.distance - other.distance);
    }

    @Override
    public String toString() {
        return String.format("%s", this.areaTitle);
    }

    public static Area parseFromString(String areaTitle) {
        Area area = null;
        if (areaTitle.equalsIgnoreCase(Area.NORTH.areaTitle)) area = Area.NORTH;
        if (areaTitle.equalsIgnoreCase(Area.CENTRAL.areaTitle)) area = Area.CENTRAL;
        if (areaTitle.equalsIgnoreCase(Area.SOUTH.areaTitle)) area = Area.SOUTH;
        if (area == null) throw new IllegalArgumentException("Such region does not exist.");
        return area;
    }
}
