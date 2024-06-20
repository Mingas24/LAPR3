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
import java.util.Properties;
import lapr.project.data.LocationDB;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Asus
 */
public class UpdateParkControllerTest {

    LocationDB ParkDb;
    Park park1, park2, test;
    Capacity capacity;
    ParkCharger charger;
    AddParkController apc;
    UpdateParkController upc;

    public UpdateParkControllerTest() {
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ParkDb = new LocationDB();
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {

    }

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        capacity = new Capacity(20, 10);
        charger = new ParkCharger(120, 130);
        park1 = new Park("128", "Aliados", 41.148519, -9.64433, capacity, 30, charger);
        park2 = new Park("128", "4Fontes", 41.148519, -9.64433, capacity, 40, charger);

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testUpdateParkController() throws SQLException, IOException {
        System.out.println("Update Park");
        ParkDb.ClearDatabase();
        try {

            upc = new UpdateParkController();
            apc = new AddParkController();
            apc.addPark(park1);

            boolean expResult = true;
            boolean result = upc.updatePark(park2);

            assertEquals(expResult, result);
            test = ParkDb.getPark(park2.getLocationId());

            System.out.println(test);
            assertEquals(park2.getLocationId(), test.getLocationId());
            assertEquals(park2.getDescription(), test.getDescription());
            assertEquals(park2.getLatitude(), test.getLatitude());
            assertEquals(park2.getLongitude(), test.getLongitude());
            assertEquals(park2.getCapacity().getBicycleCapacity(), test.getCapacity().getBicycleCapacity());
            assertEquals(park2.getCapacity().getScooterCapacity(), test.getCapacity().getScooterCapacity());
            assertEquals(park2.getElevation(), test.getElevation());
            assertEquals(park2.getParkCharger().getParkInputCurrent(), test.getParkCharger().getParkInputCurrent());
            assertEquals(park2.getParkCharger().getParkInputVoltage(), test.getParkCharger().getParkInputVoltage());

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ParkDb.ClearDatabase();
        }

    }
}
