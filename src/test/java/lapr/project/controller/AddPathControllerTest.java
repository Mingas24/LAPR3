package lapr.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import lapr.project.data.LocationDB;
import lapr.project.data.PathDB;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import lapr.project.model.Location.PointOfInterest;
import lapr.project.model.Path;
import lapr.project.model.RegisteredUser;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Nuno Capela
 */
public class AddPathControllerTest {

    PathDB pathDB;
    LocationDB parkDB;
    Path path;
    AddPathController apc;
    AddPOIController apkc;

    private final Capacity capacity = new Capacity(20, 10);
    private final ParkCharger charger = new ParkCharger(120, 120);

    //Creating POIs
    private final PointOfInterest poi1 = new PointOfInterest("1", 41.148518, -8.644335, 4, "testRibeira");
    private final PointOfInterest poi2 = new PointOfInterest("2", 41.14788, -8.61112, 5, "testAliados");
    private final PointOfInterest poi3 = new PointOfInterest("3", 41.14555, -8.61062, 5, "testSãoBento");

    //Creating Parks
    private final Park park1 = new Park("001", "Sé do Porto", 41.14278, -8.60928, capacity, 100, charger);
    private final Park park3 = new Park("003", "Trindade", 41.15068, -8.61053, capacity, 50, charger);
    private final Park park4 = new Park("004", "Mercado do Bolhão", 41.15005, -8.60678, capacity, 50, charger);
    private final Park park5 = new Park("005", "Torre dos Clérigos", 41.14563, -8.61483, capacity, 70, charger);
    private final Park nullPark = null;

    //Creating User
    private final RegisteredUser user = new RegisteredUser("Napoleão", "franca@franca.fr", "male", 150, 60, 10, 123456789, "nana", 14);
    //Creating Paths
    private final Path path1 = new Path(poi2.getLatitude(), poi2.getLongitude(), poi1.getLatitude(), poi1.getLongitude(), 0.004, 10, 15);
    private final Path path2 = new Path(poi2.getLatitude(), poi2.getLongitude(), park4.getLatitude(), park4.getLongitude(), 0.004, 8, 12);
    private final Path path3 = new Path(poi2.getLatitude(), poi2.getLongitude(), park3.getLatitude(), park3.getLongitude(), 0.004, 16, 5);
    private final Path path4 = new Path(poi2.getLatitude(), poi2.getLongitude(), poi3.getLatitude(), poi3.getLongitude(), 0.004, 9, 14);
    private final Path path5 = new Path(poi3.getLatitude(), poi3.getLongitude(), poi2.getLatitude(), poi2.getLongitude(), 0.004, 5, 76);
    private final Path path6 = new Path(poi2.getLatitude(), poi2.getLongitude(), park5.getLatitude(), park5.getLongitude(), 0.004, 20, 32);
    private final Path path7 = new Path(park1.getLatitude(), park1.getLongitude(), poi3.getLatitude(), poi3.getLongitude(), 0.004, 13, 24);
    private final Path path8 = new Path(poi3.getLatitude(), poi3.getLongitude(), park4.getLatitude(), park4.getLongitude(), 0.004, 12, 19);
    private final Path path9 = new Path(park4.getLatitude(), park4.getLongitude(), park3.getLatitude(), park3.getLongitude(), 0.004, 17, 51);
    private final Path path10 = new Path(park5.getLatitude(), park5.getLongitude(), park1.getLatitude(), park1.getLongitude(), 0.004, 2, 42);
    private final Path path11 = new Path(poi3.getLatitude(), poi3.getLongitude(), poi1.getLatitude(), poi1.getLongitude(), 0.004, 6, 31);
    private final Path path12 = new Path(poi1.getLatitude(), poi1.getLongitude(), park4.getLatitude(), park4.getLongitude(), 0.004, 15, 21);
    private final Path path13 = new Path(poi1.getLatitude(), poi1.getLongitude(), poi3.getLatitude(), poi3.getLongitude(), 0.004, 6, 7);
    private final Path path14 = new Path(park5.getLatitude(), park5.getLongitude(), poi2.getLatitude(), poi2.getLongitude(), 0.004, 18, 65);

    public AddPathControllerTest() {

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
        pathDB = new PathDB();
        parkDB = new LocationDB();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addPath method, of class AddPathController.
     */
    @Test
    public void testAddPath() throws Exception {
        System.out.println("addPath");
        try {
            apc = new AddPathController();
            apkc = new AddPOIController();
            pathDB.ClearDatabase();
            boolean expResult = true;

            apkc.addPoint(poi2);
            apkc.addPoint(poi1);

            boolean result = apc.addPath(path1);

            assertEquals(expResult, result);

            path = pathDB.getPath(poi2.getLocationId(), poi1.getLocationId());

            assertEquals(poi2.getLocationId(), path.getLocationA());
            assertEquals(poi1.getLocationId(), path.getLocationB());
            assertEquals(path1.getkinetic_coefficient(), path.getkinetic_coefficient());
            assertEquals(path1.getWindDirection(), path.getWindDirection());
            assertEquals(path1.getWindSpeed(), path.getWindSpeed());

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            pathDB.ClearDatabase();
        }
    }

}
