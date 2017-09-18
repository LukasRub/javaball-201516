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
    public static final int maxLevel = 4;

    private QualLevel(int level, String eligibility) {
        this.level = level;
        this.eligibility = eligibility;
    }

    public int getLevel() {
        return this.level;
    }

    public String eligibility() {
        return this.eligibility;
    }

    public static QualLevel parseFromInt(int qualificationLevel) {
        QualLevel qualLevel = null;
        switch(qualificationLevel) {
            case 1: qualLevel = QualLevel.ONE;
                    break;
            case 2: qualLevel = QualLevel.TWO;
                    break;
            case 3: qualLevel = QualLevel.THREE;
                    break;
            case 4: qualLevel = QualLevel.FOUR;
                    break;
            default: throw new IllegalArgumentException("Such qualification level does not exits.");
        }
        return  qualLevel;
    }
}
