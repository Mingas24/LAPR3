package lapr.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import lapr.project.data.LocationDB;
import lapr.project.data.RegisteredUserDB;
import lapr.project.data.TripDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import lapr.project.model.RegisteredUser;
import lapr.project.model.Vehicle.Bicycle;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author diogo
 */
public class UnlockBicycleControllerTest {

    VehicleDB VehicleDB;
    LocationDB LocationDB;
    AddBicycleController abc;
    Bicycle Bicycle1;
    Park park1;
    AddParkController apk;
    TripDB TripDB;
    UnlockBicycleController ubc;
    RegisteredUser user1;
    RegisteredUserDB RegisteredUserDB;
    AddRegisteredUserController aruc;

    public UnlockBicycleControllerTest() {

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
        VehicleDB = new VehicleDB();
        LocationDB = new LocationDB();
        TripDB = new TripDB();
        RegisteredUserDB = new RegisteredUserDB();
    }

    @BeforeAll
    public static void setUpClass() throws IOException, SQLException {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException, SQLException {

        park1 = new Park("129", "Fonte da Luz", 21.2, 22.4, new Capacity(12, 12), 30, new ParkCharger(30, 20));
        VehicleInfos vi = new VehicleInfos(21.2, 22.4, "UNAVAILABLE");
        VehicleCharacteristics vc = new VehicleCharacteristics(4, 0.95, 4.5);
        Bicycle1 = new Bicycle("bike01", vi, vc, 0);
        user1 = new RegisteredUser("diogomt", "filipe123@gmail.com", "male", 1, 40, 1.1, 123456777, "fifi", 0);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testaddAndCreateTrip() throws IOException, SQLException {
        System.out.println("addAndCreateTrip");
        try {
            ubc = new UnlockBicycleController();
            apk = new AddParkController();
            abc = new AddBicycleController();
            aruc = new AddRegisteredUserController();
            VehicleDB.ClearDatabase();
            apk.addPark(park1);
            abc.addBicycle(Bicycle1);
            aruc.addRegisteredUser(user1);

            boolean expResult = true;
            boolean result = ubc.addAndCreateTrip("diogomt", "bike01");

            
            assertEquals(expResult, result);
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            VehicleDB.ClearDatabase();
        }

    }

//    @Disabled
//    public void testAddTrip() throws IOException, SQLException {
//        System.out.println("testAddTrip");
//        try {
//            apk = new AddParkController();
//            abc = new AddBicycleController();
//            VehicleDB.ClearDatabase();
//            apk.addPark(park1);
//            boolean expResult = true;
//            boolean result = abc.addBicycle(Bicycle1);
//
//            assertEquals(expResult, result);
//
////            park = ParkDb.getPark(park1.getLocationId());
////
////            assertEquals(park1.getLocationId(), park.getLocationId());
////            assertEquals(park1.getDescription(), park.getDescription());
////            assertEquals(park1.getLatitude(), park.getLatitude());
////            assertEquals(park1.getLongitude(), park.getLongitude());
////            assertEquals(park1.getCapacity().getBicycleCapacity(), park.getCapacity().getBicycleCapacity());
////            assertEquals(park1.getCapacity().getScooterCapacity(), park.getCapacity().getScooterCapacity());
////            assertEquals(park1.getElevation(), park.getElevation());
////            assertEquals(park1.getParkCharger().getParkInputCurrent(), park.getParkCharger().getParkInputCurrent());
////            assertEquals(park1.getParkCharger().getParkInputVoltage(), park.getParkCharger().getParkInputVoltage());
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            VehicleDB.ClearDatabase();
//        }
//
//    }

}
