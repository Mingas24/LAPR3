package lapr.project.controller;

import java.io.FileInputStream;
import lapr.project.model.Location.PointOfInterest;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import lapr.project.data.LocationDB;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author diogo
 */
public class AddPOIControllerTest {

    LocationDB PoiDb;
    PointOfInterest point1, point2;
    AddPOIController apk;

    public AddPOIControllerTest() {

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
        PoiDb = new LocationDB();
    }

    @BeforeAll
    public static void setUpClass() throws IOException, SQLException {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        point1 = new PointOfInterest("85", 44.148538, -23.188518, 58, "IT is cool");
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddPoint() throws IOException, SQLException {
        System.out.println("testAddPointOfInterest");
        try {

            apk = new AddPOIController();
            boolean expResult = true;
            boolean result = apk.addPoint(point1);
            assertEquals(expResult, result);

            point2 = PoiDb.getPoint(point1.getLocationId());

            assertEquals(point1.getLocationId(), point2.getLocationId());
            assertEquals(point1.getLatitude(), point2.getLatitude());
            assertEquals(point1.getLongitude(), point2.getLongitude());
            assertEquals(point1.getElevation(), point2.getElevation());
            assertEquals(point1.getDescription(), point2.getDescription());
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            PoiDb.ClearDatabase();
        }

    }

}
