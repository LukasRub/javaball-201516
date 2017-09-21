package referee.restrictions;

import match.Match;
import referee.Referee;

import java.util.TreeMap;

/**
 * Created by lukas on 17.9.21.
 */
public abstract class RestrictionDecorator extends LevelRestriction {

    protected Restriction restriction;

    public RestrictionDecorator(Restriction restriction) {
        this.restriction = restriction;
    }

}
