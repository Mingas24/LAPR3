package lapr.project.controller;

import java.io.FileInputStream;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import lapr.project.data.LocationDB;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author diogo
 */
public class RemoveParkControllerTest {

    LocationDB ParkDb;
    Park park1, park2, park;
    Capacity capacity;
    ParkCharger charger;
    int ParkID;
    AddParkController apk;
    RemoveParkController rpk;

    public RemoveParkControllerTest() {

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
        ParkDb = new LocationDB();
    }

    @BeforeAll
    public static void setUpClass() throws IOException, SQLException {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        capacity = new Capacity(20, 10);
        charger = new ParkCharger(120, 130);
        park1 = new Park("127", "Fonte da Luz", 41.148518, -8.644335, capacity, 30, charger);
        apk = new AddParkController();
        apk.addPark(park1);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testRemovePark() throws IOException, SQLException {
        System.out.println("testRemovePark");
        try {
            rpk = new RemoveParkController();

            boolean expResult = true;
            boolean result = rpk.removePark("127");

            assertEquals(expResult, result);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ParkDb.ClearDatabase();
        }

    }
}
