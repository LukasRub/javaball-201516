import java.io.IOException;

/**
 * Created by lukas on 17.9.19.
 */
public class Main {

    public static void main(String [] args) {

        RefereeContainer referees = RefereeContainer.getInstance();

        referees.addReferee(new Referee("David", "Guerrera", "NJB2", "North", "YYY", 16));
        referees.addReferee(new Referee("DG2", "Donald", "Grayson", "NJB2", "Central", "YYN", 4));
        referees.addReferee(new Referee("Daniel", "Garland", "IJB1", "North", "YYY", 5));

        try {
            RefereeFileHandler.readRefereeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        referees.printReferees();

    }

}
