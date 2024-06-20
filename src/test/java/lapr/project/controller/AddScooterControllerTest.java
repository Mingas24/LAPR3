package lapr.project.controller;

import java.io.FileInputStream;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;
import java.time.LocalDateTime;

import java.util.Properties;
import lapr.project.data.LocationDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import lapr.project.model.Vehicle.Battery;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author diogo
 */
public class AddScooterControllerTest {

    VehicleDB VehicleDB;
    LocationDB LocationDB;
    AddScooterController asc;
    Scooter Scooter1;
    Park park1;
    AddParkController apk;

    public AddScooterControllerTest() {

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

        VehicleInfos vi = new VehicleInfos(21.2, 22.4, "UNAVAILABLE", LocalDateTime.of(2019, 12, 12, 12, 0));

        VehicleCharacteristics vc = new VehicleCharacteristics(4, 0.95, 4.5);

        Battery bt = new Battery(21.1, 32.1);

        Scooter1 = new Scooter("Scooter01", "CITY", vi, vc, bt, 4);

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddScooter() throws IOException, SQLException {
        System.out.println("testAddScooter");
        try {
            apk = new AddParkController();
            asc = new AddScooterController();
            VehicleDB.ClearDatabase();
            apk.addPark(park1);
            boolean expResult = true;
            boolean result = asc.addScooter(Scooter1);

            assertEquals(expResult, result);

            Scooter sc = VehicleDB.getScooter("Scooter01");

            assertEquals(Scooter1.getVehicleID(), sc.getVehicleID());
            assertEquals(Scooter1.getTypeOfScooter(), sc.getTypeOfScooter());
            assertEquals(Scooter1.getFrontalArea(), sc.getFrontalArea());
            assertEquals(Scooter1.getMaxBattery(), sc.getMaxBattery());
            assertEquals(Scooter1.getActualBattery(), sc.getActualBattery());
            assertEquals(Scooter1.getAerodynamicCoefficient(), sc.getAerodynamicCoefficient());
            assertEquals(Scooter1.getVehicleStatus(), sc.getVehicleStatus());
            assertEquals(Scooter1.getMotor(), sc.getMotor());
            assertEquals(Scooter1.getLastUpdateTime(), sc.getLastUpdateTime());

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            VehicleDB.ClearDatabase();
        }

    }

}
