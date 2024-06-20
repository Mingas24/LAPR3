package lapr.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import lapr.project.data.*;
import lapr.project.model.Location.*;
import lapr.project.model.*;
import lapr.project.model.Vehicle.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockBicycleControllerTest {

    TripDB tripDB;
    VehicleDB vehicleDB;
    LocationDB locationDB;
    RegisteredUserDB registeredUserDB;

    Park startPark1, endingPark1, startPark2, endingPark2;
    Bicycle bicycle1, bicycle2, bicycle3;
    ParkCharger parkCharger;
    Capacity capacity1, capacity2;
    RegisteredUser registeredUser;
    Trip trip;

    AddParkController apc;
    AddRegisteredUserController aruc;
    AddBicycleController abc;
    UnlockBicycleController ubc;
    LockBicycleController lbc;

    public LockBicycleControllerTest() {
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
        tripDB = new TripDB();
        vehicleDB = new VehicleDB();
        locationDB = new LocationDB();
        registeredUserDB = new RegisteredUserDB();
    }

    @BeforeAll
    public static void setUpClass() throws IOException, SQLException {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        capacity1 = new Capacity(40, 10);
        parkCharger = new ParkCharger(220, 130);
        startPark1 = new Park("147", "Fonte da Lua", 41.148519, -8.644335, capacity1, 0, parkCharger);
        endingPark1 = new Park("150", "Quatro Fontes", 41.148119, -8.644336, capacity1, 30, parkCharger);

        registeredUser = new RegisteredUser("accm", "1180914@isep.ipp.pt", "female", 147, 57, 4.7, 123654987, "password1234", 50);

        VehicleInfos vehicleInfos1 = new VehicleInfos(41.148519, -8.644335, "UNAVAILABLE");
        VehicleCharacteristics vehicleCharacteristics1 = new VehicleCharacteristics(7, 0.95, 4.5);
        bicycle1 = new Bicycle("b01", vehicleInfos1, vehicleCharacteristics1, 17);

        capacity2 = new Capacity(1, 10);
        startPark2 = new Park("198", "Fonte de Zeus", 42.148519, -8.644345, capacity1, 0, parkCharger);
        endingPark2 = new Park("199", "Fonte de Afrodite", 41.248119, -8.644332, capacity2, 30, parkCharger);

        VehicleInfos vehicleInfos2 = new VehicleInfos(42.148519, -8.644345, "UNAVAILABLE");
        VehicleCharacteristics vehicleCharacteristics2 = new VehicleCharacteristics(7, 0.95, 4.5);
        bicycle2 = new Bicycle("b02", vehicleInfos2, vehicleCharacteristics2, 17);

        VehicleInfos vehicleInfos3 = new VehicleInfos(41.248119, -8.644332, "AVAILABLE");
        VehicleCharacteristics vehicleCharacteristics3 = new VehicleCharacteristics(7, 0.95, 4.5);
        bicycle3 = new Bicycle("b03", vehicleInfos3, vehicleCharacteristics3, 17);

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testEndBicycleTrip() throws IOException, SQLException {
        System.out.println("testEndScooterTrip");
        try {
            apc = new AddParkController();
            aruc = new AddRegisteredUserController();
            abc = new AddBicycleController();
            ubc = new UnlockBicycleController();
            lbc = new LockBicycleController();

            locationDB.ClearDatabase();

            apc.addPark(startPark1);
            apc.addPark(endingPark1);
            apc.addPark(startPark2);
            apc.addPark(endingPark2);

            aruc.addRegisteredUser(registeredUser);

            abc.addBicycle(bicycle1);
            abc.addBicycle(bicycle2);
            abc.addBicycle(bicycle3);

            ubc.addAndCreateTrip(registeredUser.getUsername(), bicycle1.getVehicleID());
            System.out.println("criou a trip");
            boolean expResult = true;
            boolean result = lbc.endBikeTrip(bicycle1.getVehicleID(), endingPark1.getLatitude(), endingPark1.getLongitude(), registeredUser.getUsername());
            assertEquals(expResult, result);

            ubc.addAndCreateTrip(registeredUser.getUsername(), bicycle2.getVehicleID());
            expResult = false;
            result = lbc.endBikeTrip(bicycle2.getVehicleID(), endingPark2.getLatitude(), endingPark2.getLongitude(), registeredUser.getUsername());
            assertEquals(expResult, result);
            
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            locationDB.ClearDatabase();
        }
    }
}
