/**
 * Created by lukas on 17.9.19.
 */
public class Main {

    public static void main(String [] args) {

        RefereeContainer referees = RefereeContainer.getInstance();
        referees.addReferee(new Referee("Dave", "Gray", "NJB2", "Central", "NYY", 3));


    }

}
