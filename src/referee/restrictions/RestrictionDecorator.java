package referee.restrictions;

/**
 * Created by lukas on 17.9.21.
 */
public abstract class RestrictionDecorator extends LevelRestriction {

    protected Restriction restriction;

    public RestrictionDecorator(Restriction restriction) {
        this.restriction = restriction;
    }

}
