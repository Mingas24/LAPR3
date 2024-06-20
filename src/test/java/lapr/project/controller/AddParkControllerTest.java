package lapr.project.controller;

import java.io.FileInputStream;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import lapr.project.data.LocationDB;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author diogo
 */
public class AddParkControllerTest {

    LocationDB ParkDb;
    Park park1, park2, park3, park;
    Capacity capacity;
    ParkCharger charger;
    AddParkController apk;

    public AddParkControllerTest() {

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
        park1 = new Park("129", "Fonte da Luz", 41.148518, -8.644335, capacity, 30, charger);
        park2 = new Park("130", "Parke do xeu", 42.148518, -9.644335, new Capacity(60, 80), 40, new ParkCharger(200, 300));
        park3 = new Park("131", "Casa do zezinho", 43.148518, -10.644335, new Capacity(70, 90), 50, new ParkCharger(400, 500));

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddPark() throws IOException, SQLException {
        System.out.println("testAddPark");
        try {

            apk = new AddParkController();
            boolean expResult = true;
            boolean result = apk.addPark(park1);

            assertEquals(expResult, result);

            park = ParkDb.getPark(park1.getLocationId());

            assertEquals(park1.getLocationId(), park.getLocationId());
            assertEquals(park1.getDescription(), park.getDescription());
            assertEquals(park1.getLatitude(), park.getLatitude());
            assertEquals(park1.getLongitude(), park.getLongitude());
            assertEquals(park1.getCapacity().getBicycleCapacity(), park.getCapacity().getBicycleCapacity());
            assertEquals(park1.getCapacity().getScooterCapacity(), park.getCapacity().getScooterCapacity());
            assertEquals(park1.getElevation(), park.getElevation());
            assertEquals(park1.getParkCharger().getParkInputCurrent(), park.getParkCharger().getParkInputCurrent());
            assertEquals(park1.getParkCharger().getParkInputVoltage(), park.getParkCharger().getParkInputVoltage());

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ParkDb.ClearDatabase();
        }

    }
//This test does not actualy test a function from addParkController, but from parkDB
    @Test
    public void testGetAllParks() throws IOException, SQLException {
        System.out.println("testGetAllParks");

//        HashMap parks = new HashMap<>();
        ArrayList<Park> parks;
        try {
            apk = new AddParkController();
            apk.addPark(park1);
            apk.addPark(park2);
            apk.addPark(park3);

            parks = ParkDb.getAllParks();
            System.out.println(parks.size());
//            for (Object o : parks.keySet()) {
            for (Object o : parks) {
                Park p = (Park) o;
                System.out.println(p.getLocationId());

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ParkDb.ClearDatabase();
        }

    }
}
