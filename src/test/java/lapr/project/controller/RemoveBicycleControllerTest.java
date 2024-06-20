package lapr.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import lapr.project.data.*;
import lapr.project.model.Location.*;
import lapr.project.model.Vehicle.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

public class RemoveBicycleControllerTest {

    Park park1;
    Bicycle bicycle1;
    AddParkController apc;
    AddBicycleController abc;
    RemoveBicycleController rbc;
    VehicleDB vehicleDB;
    LocationDB locationDB;

    public RemoveBicycleControllerTest() throws IOException, SQLException {
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
        vehicleDB = new VehicleDB();
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
        park1 = new Park("129", "Fonte da Luz", 21.2, 22.4, new Capacity(12, 12), 30, new ParkCharger(30, 20));

        VehicleInfos vi = new VehicleInfos(21.2, 22.4, "UNAVAILABLE");
        VehicleCharacteristics vc = new VehicleCharacteristics(4, 0.95, 4.5);
        bicycle1 = new Bicycle("B01", vi, vc, 4);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testRemoveBicycle() throws IOException, SQLException {
        System.out.println("testRemoveBicycle");
        try {
            
            apc = new AddParkController();
            rbc = new RemoveBicycleController();
            abc = new AddBicycleController();

            apc.addPark(park1);
            abc.addBicycle(bicycle1);
            boolean expResult = true;
            boolean result = rbc.removeBicycle("B01");
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            vehicleDB.ClearDatabase();
        }
    }

}
