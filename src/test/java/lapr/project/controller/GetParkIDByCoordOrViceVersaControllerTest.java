package lapr.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import lapr.project.data.LocationDB;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Toshiba
 */
public class GetParkIDByCoordOrViceVersaControllerTest {

    LocationDB locationDB;

    Park park;
    ParkCharger parkCharger;
    Capacity capacity;

    AddParkController apc;
    GetParkIDByCoordOrViceVersaController gpidbcc;

    public GetParkIDByCoordOrViceVersaControllerTest() {
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        capacity = new Capacity(40, 10);
        parkCharger = new ParkCharger(220, 130);
        park = new Park("127", "Fonte da Lua", 41.148519, -8.644335, capacity, 30, parkCharger);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetParkIDByCoord() throws IOException, SQLException {
        System.out.println("testGetParkIDByCoord");
        try {
            apc = new AddParkController();
            gpidbcc = new GetParkIDByCoordOrViceVersaController();
            apc.addPark(park);
            String expResult = "127";
            String result = gpidbcc.getParkIDByCoord(41.148519, -8.644335);
            assertEquals(expResult, result);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            locationDB.ClearDatabase();
        }
    }

    @Test
    public void testGetCoodinatesByParkID() throws IOException, SQLException {
        System.out.println("testGetCoodsByParkID");
        try {
            apc = new AddParkController();
            gpidbcc = new GetParkIDByCoordOrViceVersaController();
            apc.addPark(park);
            Double expLatitude = 41.148519;
            Double expLongitude = -8.644335;
            List<Double> coords = gpidbcc.getCoordinatesByParkID("127");
            assertEquals(expLatitude, coords.get(0));
            assertEquals(expLongitude, coords.get(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            locationDB.ClearDatabase();
        }
    }
}
