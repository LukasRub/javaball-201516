/**
 * Created by lukas on 17.9.17.
 */
public enum QualLevel {
    ONE (1, "junior"),
    TWO (2, "senior"),
    THREE (3, "senior"),
    FOUR (4, "senior");

    private final int level;
    private final String eligibility;

    private QualLevel(int level, String eligibility) {
        this.level = level;
        this.eligibility = eligibility;
    }

    public int level() {
        return this.level;
    }

    public String eligibility() {
        return this.eligibility;
    }

}
