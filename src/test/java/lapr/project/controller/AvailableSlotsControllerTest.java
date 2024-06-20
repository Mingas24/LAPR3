package lapr.project.controller;

import java.io.FileInputStream;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;

import java.util.Properties;
import lapr.project.data.LocationDB;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author diogo
 */
public class AvailableSlotsControllerTest {

    LocationDB locationDB;
    Park park1;
    AvailableSlotsController asp;
    AddParkController apc;

    public AvailableSlotsControllerTest() {

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
        locationDB = new LocationDB();
    }

    @BeforeAll
    public static void setUpClass() throws IOException, SQLException {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        park1 = new Park("P01", "Fonte da Luz", 21.2, 22.4, new Capacity(12, 12), 30, new ParkCharger(30, 20));
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testavailableSlotsBike() throws IOException, SQLException {
        System.out.println("AvailableSlotsBike");
        try {
            asp = new AvailableSlotsController();
            apc = new AddParkController();
            locationDB.ClearDatabase();
            apc.addPark(park1);
            int expResult = park1.getBikeCapacity();
            int result = asp.availableSlotsBike(park1.getLocationId());
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            locationDB.ClearDatabase();
        }
    }

    @Test
    public void testavailableSlotsScooter() throws IOException, SQLException {
        System.out.println("AvailableSlotsScooter");
        try {
            asp = new AvailableSlotsController();
            apc = new AddParkController();
            locationDB.ClearDatabase();
            apc.addPark(park1);
            int expResult = park1.getScooterCapacity();
            int result = asp.availableSlotsScooter(park1.getLocationId());
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            locationDB.ClearDatabase();
        }
    }

}
