/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.FileInputStream;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;
import lapr.project.data.LocationDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle.Battery;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateScooterControllerTest {

    LocationDB LocationDB;
    VehicleDB VehicleDB;
    Park park1;
    Capacity capacity;
    ParkCharger charger;
    AddParkController apc;
    UpdateParkController upc;
    AddScooterController asc;
    UpdateScooterController usc;
    Scooter Scooter1, Scooter2, test;

    public UpdateScooterControllerTest() {
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocationDB = new LocationDB();
        VehicleDB = new VehicleDB();

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

        VehicleInfos vi = new VehicleInfos(21.2, 22.4, "UNAVAILABLE", LocalDateTime.of(2019, 12, 12, 12, 0));

        VehicleCharacteristics vc = new VehicleCharacteristics(4, 0.95, 4.5);

        Battery bt = new Battery(21.1, 32.1);

        Scooter1 = new Scooter("Scooter01", "CITY", vi, vc, bt, 4);
        Scooter2 = new Scooter("Scooter01", "OFF-ROAD", vi, vc, bt, 2);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testUpdateScooterController() throws SQLException, IOException {
        System.out.println("testUpdateScooterController");
        VehicleDB.ClearDatabase();
        try {

            usc = new UpdateScooterController();
            apc = new AddParkController();
            asc = new AddScooterController();

            apc.addPark(park1);
            asc.addScooter(Scooter1);

            boolean expResult = true;
            boolean result = usc.updateScooter(Scooter2);

            assertEquals(expResult, result);
            test = VehicleDB.getScooter(Scooter2.getVehicleID());

            assertEquals(Scooter2.getVehicleID(), test.getVehicleID());
            assertEquals(Scooter2.getTypeOfScooter(), test.getTypeOfScooter());
            assertEquals(Scooter2.getFrontalArea(), test.getFrontalArea());
            assertEquals(Scooter2.getMaxBattery(), test.getMaxBattery());
            assertEquals(Scooter2.getActualBattery(), test.getActualBattery());
            assertEquals(Scooter2.getAerodynamicCoefficient(), test.getAerodynamicCoefficient());
            assertEquals(Scooter2.getVehicleStatus(), test.getVehicleStatus());
            assertEquals(Scooter2.getMotor(), test.getMotor());
            assertEquals(Scooter2.getLastUpdateTime(), test.getLastUpdateTime());

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            VehicleDB.ClearDatabase();
        }

    }
}
