package lapr.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 */
public class InputCSVReader {


    private static final Logger LOGGER = Logger.getLogger(InputCSVReader.class.getName());

    /**
     *
     * @param inputFile
     * @return
     */
    public static List<String> inputCSVReader(String inputFile) {
        ArrayList<String> linesList = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(inputFile))) {
            boolean flag = true;
            while (sc.hasNext()) {
                String str = sc.nextLine();
                if (!str.startsWith("#")) {
                    if (flag) {
                        str=sc.nextLine();
                        linesList.add(str);
                        flag = false;
                    } else {
                        linesList.add(str);
                    }

                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.warning("ERROR WHILE INPUTING CSV");

        }
        return linesList;
    }

    /**
     *
     * @param line
     * @return
     */
    public static List<String> lineReader(String line) {
        String[] lol = line.split(";");
        ArrayList<String> args = new ArrayList<>();
        args.addAll(Arrays.asList(lol));

        return args;
    }

}
