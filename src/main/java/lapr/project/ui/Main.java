package lapr.project.ui;


import lapr.project.data.DataHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import lapr.project.model.Location.PointOfInterest;
import java.util.*;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
//        CalculatorExample calculatorExample = new CalculatorExample();
//        int value = calculatorExample.sum(3, 5);
//
//        if (LOGGER.isLoggable(Level.INFO))
//            LOGGER.log(Level.INFO, String.valueOf(value));

        //load database properties
        try {
            Properties properties
                    = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Initial Database Setup
        DataHandler dh = new DataHandler();
        dh.scriptRunner("sqlScript.sql");

//        System.out.println("\nVerificar se existe Sailor 100...");
//        try {
//            Sailor.getSailor(100);
//            System.out.println("Nunca deve aparecer esta mensagem");
//        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//        }
//        System.out.println("\nAdicionar Sailor ...");
//        long sailorID = 100;
//        String sailorName = "Popeye";
//        long sailorRating = 11;
//        int sailorAge = 85;
//
//        Sailor sailor = new Sailor(sailorID, sailorName);
//        sailor.setAge(sailorAge);
//        sailor.setRating(sailorRating);
//        sailor.save();
//        RegisteredUser userTeste;
//        userTeste = new RegisteredUser("TestinhoUser", "JorisBohnson@gov.uk", "Male", 1, 180, 100, 25.2, 11189346, 20);
//        userTeste.saveRegisteredUser();
//        


//        System.out.println("\t... Sailor Adicionado.");
//
//        System.out.println("\nVerificar se existe Sailor 100...");
//        try {
//            sailor = Sailor.getSailor(100);
//            System.out.println("\nSailor 100 existe...: " + sailor.getName());
//        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//        }
    }
}
