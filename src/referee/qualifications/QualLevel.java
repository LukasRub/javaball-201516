package referee.qualifications;

/**
 * Created by lukas on 17.9.17.
 */
public enum QualLevel {
    ONE (1, "Junior"),
    TWO (2, "Senior"),
    THREE (3, "Senior"),
    FOUR (4, "Senior");

    private final int level;
    private final String eligibility;

    private QualLevel(int level, String eligibility) {
        this.level = level;
        this.eligibility = eligibility;
    }

    public int getLevel() {
        return this.level;
    }

    public String getEligibility() {
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
