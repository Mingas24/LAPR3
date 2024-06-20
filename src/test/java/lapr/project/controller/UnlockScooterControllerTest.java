package lapr.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;
import lapr.project.data.*;
import lapr.project.model.Location.*;
import lapr.project.model.*;
import lapr.project.model.Vehicle.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

public class UnlockScooterControllerTest {

    VehicleDB vehicleDB;
    LocationDB locationDB;
    TripDB tripDB;

    AddParkController apc;
    AddScooterController asc;
    UnlockScooterController usc;
    AddRegisteredUserController aruc;

    Scooter scooter;
    Park park;
    RegisteredUser registeredUser;

    public UnlockScooterControllerTest() {
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        vehicleDB = new VehicleDB();
        locationDB = new LocationDB();
        tripDB = new TripDB();
    }

    @BeforeAll
    public static void setUpClass() throws IOException, SQLException {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        park = new Park("129", "Fonte da Luz", 21.2, 22.4, new Capacity(12, 12), 30, new ParkCharger(30, 20));
        VehicleInfos vi = new VehicleInfos(21.2, 22.4, "AVAILABLE", LocalDateTime.of(2019, 12, 12, 12, 0));
        VehicleCharacteristics vc = new VehicleCharacteristics(4, 0.95, 4.5);
        Battery battery = new Battery(150, 40);
        scooter = new Scooter("scooter01", "CITY", vi, vc, battery, 0);
        registeredUser = new RegisteredUser("luis1", "luis@gmail.com", "male", 125, 45, 4.5, 123456785, "luis1", 10);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndCreateTrip() throws IOException, SQLException {
        System.out.println("testAddTrip");
        try {
            apc = new AddParkController();
            asc = new AddScooterController();
            usc = new UnlockScooterController();
            aruc = new AddRegisteredUserController();

            vehicleDB.ClearDatabase();
            apc.addPark(park);
            asc.addScooter(scooter);
            aruc.addRegisteredUser(registeredUser);

            boolean expResult = true;
            boolean result = usc.addAndCreateTrip("luis1", "scooter01");

            assertEquals(expResult, result);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            vehicleDB.ClearDatabase();
        }
    }

}
