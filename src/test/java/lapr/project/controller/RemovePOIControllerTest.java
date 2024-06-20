package lapr.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import lapr.project.data.LocationDB;
import lapr.project.model.Location.PointOfInterest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Toshiba
 */
public class RemovePOIControllerTest {

    LocationDB poiDB;
    PointOfInterest poi1;
    RemovePOIController rpc;
    AddPOIController apc;

    public RemovePOIControllerTest() throws IOException, SQLException {
        try {

            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.poiDB = new LocationDB();
    }

    @BeforeAll
    public static void setUpClass() throws IOException, SQLException {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException, SQLException {

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testRemovePOI() throws IOException, SQLException {
        System.out.println("testRemovePOI");
        try {
            apc = new AddPOIController();
            rpc = new RemovePOIController();

            boolean expResult = true;
            apc.addPoint(new PointOfInterest("7", 45.148538, -24.188518, 5, "Ponte D. Luis"));

            boolean result = rpc.removePointOfInterest("7");
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            poiDB.ClearDatabase();
        }
    }
}
