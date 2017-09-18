import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by lukas on 17.9.17.
 */
public enum QualBody {
    NJB ("NJB"),
    IJB ("IJB");

    private final String qualificationBody;

    private QualBody(String qualificationBody) {
        this.qualificationBody = qualificationBody;
    }

    public String qualificationBody() {
        return this.qualificationBody;
    }

    public static QualBody parseFromString(String qualificationBody) {
        QualBody qualBody = null;
        if (qualificationBody == QualBody.NJB.qualificationBody) qualBody = QualBody.NJB;
        if (qualificationBody == QualBody.IJB.qualificationBody) qualBody = QualBody.IJB;
        if (qualBody == null) throw new IllegalArgumentException("Such qualification awarding body does not exist.");
        return qualBody;
    }
}
