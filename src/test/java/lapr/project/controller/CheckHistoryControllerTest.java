package lapr.project.controller;

import java.io.FileInputStream;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.util.converter.LocalDateTimeStringConverter;
import lapr.project.data.RegisteredUserDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import lapr.project.model.RegisteredUser;

import lapr.project.model.Trip;
import lapr.project.model.Vehicle.Bicycle;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Francisco
 */
public class CheckHistoryControllerTest {

    RegisteredUserDB registertedUserDb;

    String username;
    RegisteredUser registeredUser;
    Bicycle bicycle;
    ParkCharger parkCharger;
    Capacity capacity;
    Park park1, endingPark;
    Trip trip, trip2;
    LocalDateTime start, end;
    VehicleInfos vehicleInfos;
    VehicleCharacteristics vehicleCharacteristics;

    AddBicycleController abc;
    CheckHistoryController chc;
    UnlockBicycleController ubc;
    AddParkController apk;
    AddRegisteredUserController aruc;
    LockBicycleController lbc;

    public CheckHistoryControllerTest() {

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
        registertedUserDb = new RegisteredUserDB();

    }

    @BeforeAll
    public static void setUpClass() throws IOException, SQLException {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        capacity = new Capacity(40, 10);
        parkCharger = new ParkCharger(220, 130);
        park1 = new Park("190", "Fonte da Luz", 21.2, 22.4, new Capacity(12, 12), 30, new ParkCharger(30, 20));
        endingPark = new Park("400", "Quatro Fontes", 41.148119, -8.644336, capacity, 30, parkCharger);

        registeredUser = new RegisteredUser("dt", "1180990@isep.ipp.pt", "male", 1, 40, 1.1, 123456777, "fifi", 0);

        vehicleInfos = new VehicleInfos(21.2, 22.4, "AVAILABLE");
        vehicleCharacteristics = new VehicleCharacteristics(7, 0.95, 4.5);
        bicycle = new Bicycle("bike09", vehicleInfos, vehicleCharacteristics, 4);
        start = LocalDateTime.of(2020, Month.JANUARY, 2, 10, 10);
        end = LocalDateTime.of(2020, Month.JANUARY, 3, 10, 10);

        username = registeredUser.getUsername();
        trip = new Trip(1, username, "bike09", park1.getLocationId(), endingPark.getLocationId(), start, end);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkHistory method, of class CheckHistoryController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCheckHistory() throws Exception {
        System.out.println("checkHistory");
        try {
            chc = new CheckHistoryController();
            ubc = new UnlockBicycleController();
            apk = new AddParkController();
            abc = new AddBicycleController();
            aruc = new AddRegisteredUserController();
            lbc = new LockBicycleController();

            registertedUserDb.ClearDatabase();
            apk.addPark(park1);
            apk.addPark(endingPark);
            abc.addBicycle(bicycle);
            aruc.addRegisteredUser(registeredUser);

            ubc.addAndCreateTrip(username, bicycle.getVehicleID());
            lbc.endBikeTrip(bicycle.getVehicleID(), endingPark.getLatitude(), endingPark.getLongitude(), username);

            List<Trip> expResult = new ArrayList<>();
            expResult.add(trip);
            List<Trip> result = chc.checkHistory(username);
            assertEquals(expResult.size(), result.size());
            for (int i = 0; i < result.size(); i++) {
                assertEquals(expResult.get(i).getTripId(), result.get(i).getTripId());
                assertEquals(expResult.get(i).getEndingPark(), result.get(i).getEndingPark());
                assertEquals(expResult.get(i).getStartPark(), result.get(i).getStartPark());
                assertEquals(expResult.get(i).getVehicleId(), result.get(i).getVehicleId());
                assertEquals(expResult.get(i).getUsername(), result.get(i).getUsername());

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            registertedUserDb.ClearDatabase();
        }
    }

}
