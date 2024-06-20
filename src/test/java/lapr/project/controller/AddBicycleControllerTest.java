package lapr.project.controller;

import java.io.FileInputStream;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;

import java.util.Properties;
import lapr.project.data.LocationDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import lapr.project.model.Vehicle.Bicycle;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author diogo
 */
public class AddBicycleControllerTest {

    VehicleDB VehicleDB;
    LocationDB LocationDB;
    AddBicycleController abc;
    Bicycle Bicycle1,Bicycle2;
    Park park1;
    AddParkController apk;

    public AddBicycleControllerTest() {

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
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddBicycle() throws IOException, SQLException {
        System.out.println("testAddBicycle");
        try {
            VehicleDB.ClearDatabase();
             apk = new AddParkController();
            abc = new AddBicycleController();
            apk.addPark(park1);
            boolean expResult = true;
            boolean result = abc.addBicycle(Bicycle1);

            assertEquals(expResult, result);

            Bicycle2 = VehicleDB.getBycicle(Bicycle1.getVehicleID());
            
            assertEquals(Bicycle1.getVehicleID(), Bicycle2.getVehicleID());
            assertEquals(Bicycle1.getFrontalArea(), Bicycle2.getFrontalArea());
            assertEquals(Bicycle1.getAerodynamicCoefficient(), Bicycle2.getAerodynamicCoefficient());
            assertEquals(Bicycle1.getVehicleStatus(), Bicycle2.getVehicleStatus());
            assertEquals(Bicycle1.getWheelSize(), Bicycle2.getWheelSize());
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            VehicleDB.ClearDatabase();
        }

    }

}

