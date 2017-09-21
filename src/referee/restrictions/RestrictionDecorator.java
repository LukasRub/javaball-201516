package referee.restrictions;

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

    public TreeMap<String, Referee> applyRestriction() {
        return restriction.getRefereeList();
    }

}
