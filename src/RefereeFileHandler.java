import referee.Referee;
import referee.RefereeContainer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by lukas on 17.9.20.
 */
public class RefereeFileHandler {

    private static final String FILE_PATH = new File("").getAbsolutePath();
    private static final String REFEREE_IN_FILENAME = "RefereesIn.txt";
    private static final String INPUT_FILE_DELIM = " ";

    private static RefereeContainer refereeContainer = RefereeContainer.getInstance();

    private static FileReader fileReader;

    public static void readRefereeFile() throws IOException {

        fileReader = new FileReader(FILE_PATH + "/" + REFEREE_IN_FILENAME);
        Scanner scanner = new Scanner(fileReader);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            Referee newReferee = new Referee(line.split(INPUT_FILE_DELIM));
            refereeContainer.addReferee(newReferee);

        }

        fileReader.close();

    }



}
