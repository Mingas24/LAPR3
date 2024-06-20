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
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle.Bicycle;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateBicycleControllerTest {

    LocationDB locationDB;
    VehicleDB vehicleDB;
    Park park1;
    Capacity capacity;
    ParkCharger charger;
    Bicycle bicycle1, bicycle2, test;

    UpdateBicycleController ubc;
    AddParkController apc;
    AddBicycleController abc;

    public UpdateBicycleControllerTest() {
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        locationDB = new LocationDB();
        vehicleDB = new VehicleDB();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        park1 = new Park("129", "Fonte da Luz", 21.2, 22.4, new Capacity(12, 12), 30, new ParkCharger(30, 20));

        VehicleInfos vi = new VehicleInfos(21.2, 22.4, "UNAVAILABLE");
        VehicleInfos vi2 = new VehicleInfos(21.2, 22.4, "AVAILABLE");

        VehicleCharacteristics vc = new VehicleCharacteristics(4, 0.95, 4.5);

        bicycle1 = new Bicycle("B01", vi, vc, 4);
        bicycle2 = new Bicycle("B01", vi2, vc, 2);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testUpdateBicycleController() throws SQLException, IOException {
        System.out.println("testUpdateBicycleController");
        vehicleDB.ClearDatabase();
        try {
            ubc = new UpdateBicycleController();
            apc = new AddParkController();
            abc = new AddBicycleController();

            apc.addPark(park1);
            abc.addBicycle(bicycle1);

            boolean expResult = true;
            boolean result = ubc.updateBicycle(bicycle2);
            assertEquals(expResult, result);

            test = vehicleDB.getBycicle(bicycle2.getVehicleID()); 
            assertEquals(bicycle2.getVehicleID(), test.getVehicleID());
            assertEquals(bicycle2.getVehicleStatus(), test.getVehicleStatus());
            assertEquals(bicycle2.getWeight(), test.getWeight());
            assertEquals(bicycle2.getAerodynamicCoefficient(), test.getAerodynamicCoefficient());
            assertEquals(bicycle2.getFrontalArea(), test.getFrontalArea());
            assertEquals(bicycle2.getWheelSize(), test.getWheelSize());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
        }
    }

}
