/**
 * Created by lukas on 17.9.19.
 */
public class Main {

    public static void main(String [] args) {

        RefereeContainer referees = RefereeContainer.getInstance();
        referees.addReferee(new Referee("David", "G", "NJB2", "North", "YYY", 5));
        referees.addReferee(new Referee("DG2", "David", "Gerrard", "NJB2", "North", "YYY", 4));
        referees.addReferee(new Referee("Dick", "Gerrard", "NJB2", "North", "YYY", 5));

    }

    public static void something() {
        RefereeContainer r = RefereeContainer.getInstance();
        r.printReferees();
    }

}
