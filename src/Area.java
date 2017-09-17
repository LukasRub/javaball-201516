/**
 * Created by lukas on 17.9.17.
 */
import java.lang.Math;

public enum Area {
    NORTH (0),
    CENTRAL (1),
    SOUTH (2);

    private final int distance;

    private Area(int distance) {
        this.distance = distance;
    }

    public int calculateDistance(Area other) {
        return Math.abs(this.distance - other.distance);
    }
}
