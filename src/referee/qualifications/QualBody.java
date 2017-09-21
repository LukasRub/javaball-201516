package referee.qualifications;

/**
 * Created by lukas on 17.9.17.
 */
public enum QualBody {
    NJB ("NJB"),
    IJB ("IJB");

    private final String qualBodyTitle;

    private QualBody(String qualBodyTitle) {
        this.qualBodyTitle = qualBodyTitle;
    }

    public String getQualBodyTitle() {
        return this.qualBodyTitle;
    }

    public static QualBody parseFromString(String qualificationBody) {
        QualBody qualBody = null;
        if (qualificationBody.equals(QualBody.NJB.qualBodyTitle))
            qualBody = QualBody.NJB;
        if (qualificationBody.equals(QualBody.IJB.qualBodyTitle))
            qualBody = QualBody.IJB;
        if (qualBody == null)
            throw new IllegalArgumentException("Such qualification awarding body does not exist.");
        return qualBody;
    }
}
