package lapr.project.model.Vehicle;

import java.time.LocalDateTime;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author diogo
 */
public class VehicleInfosTest {

    VehicleInfos instance;

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        instance = new VehicleInfos(12.4, 5.7, "available", LocalDateTime.of(2019, 12, 12, 12, 0));
        instance.setParkID("testID");
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of getLatPark method, of class VehicleInfos.
     */
    @Test
    public void testGetLatPark() {
        System.out.println("getLatPark");

        double expResult = 12.4;
        double result = instance.getLatPark();
        assertEquals(expResult, result);

    }

    /**
     * Test of getLongPark method, of class VehicleInfos.
     */
    @Test
    public void testGetLongPark() {
        System.out.println("getLongPark");
        double expResult = 5.7;
        double result = instance.getLongPark();
        assertEquals(expResult, result);

    }

    /**
     * Test of getParkID method, of class VehicleInfos.
     */
    @Test
    public void testGetParkID() {
        System.out.println("getParkID");
        String expResult = "testID";
        String result = instance.getParkID();
        assertEquals(expResult, result);

    }

    /**
     * Test of getVehicleStatus method, of class VehicleInfos.
     */
    @Test
    public void testGetVehicleStatus() {
        System.out.println("getVehicleStatus");
        String expResult = "available";
        String result = instance.getVehicleStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLockTime method, of class VehicleInfos.
     */
    @Test
    public void testGetLastUpdateTime() {
        System.out.println("getLockTime");
        LocalDateTime expResult = LocalDateTime.of(2019, 12, 12, 12, 0);
        LocalDateTime result = instance.getLastUpdateTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLatPark method, of class VehicleInfos.
     */
    @Test
    public void testSetLatPark() {
        System.out.println("setLatPark");
        double latPark = 10.0;
        instance.setLatPark(latPark);
        assertEquals(latPark, instance.getLatPark());
    }

    /**
     * Test of setLongPark method, of class VehicleInfos.
     */
    @Test
    public void testSetLongPark() {
        System.out.println("setLongPark");
        double longPark = 5.0;
        instance.setLongPark(longPark);
        assertEquals(longPark, instance.getLongPark());
    }

    /**
     * Test of setParkID method, of class VehicleInfos.
     */
    @Test
    public void testSetParkID() {
        System.out.println("setParkID");
        String parkID = "testSetID";
        instance.setParkID(parkID);
        assertEquals(parkID, instance.getParkID());
    }

    /**
     * Test of setVehicleStatus method, of class VehicleInfos.
     */
    @Test
    public void testSetVehicleStatus() {
        System.out.println("setVehicleStatus");
        String vehicleStatus = "testStatus";
        instance.setVehicleStatus(vehicleStatus);
        assertEquals(vehicleStatus, instance.getVehicleStatus());
    }

    /**
     * Test of setLockTime method, of class VehicleInfos.
     */
    @Test
    public void testSetLastUpdateTime() {
        System.out.println("setLockTime");
        LocalDateTime lockTime = LocalDateTime.of(2019, 12, 12, 12, 0);
        instance.setLastUpdateTime(lockTime);
        assertEquals(lockTime, instance.getLastUpdateTime());
    }
}
