package lapr.project.model.Vehicle;

import java.time.LocalDateTime;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Nuno Capela
 */
public class ScooterTest {

    VehicleInfos vinfo;
    VehicleCharacteristics vc;
    Battery bat;
    Scooter instance;

    public ScooterTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        vinfo = new VehicleInfos(12.5, 5.8, "Available",LocalDateTime.of(2019, 12, 12, 12, 0));
        vc = new VehicleCharacteristics(11, 3.15, 2.5);
        bat = new Battery(100.00, 90.00);
        instance = new Scooter("testID", "city", vinfo, vc, bat, 50);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getMaxBattery method, of class Scooter.
     */
    @Test
    public void testGetMaxBattery() {
        System.out.println("getMaxBattery");

        double expResult = 100.00;
        double result = instance.getMaxBattery();
        assertEquals(expResult, result);

    }

    /**
     * Test of getActualBattery method, of class Scooter.
     */
    @Test
    public void testGetActualBattery() {
        System.out.println("getActualBattery");

        double expResult = 90.00;
        double result = instance.getActualBattery();
        assertEquals(expResult, result);

    }

    /**
     * Test of testGetMotor method, of class Scooter.
     */
    @Test
    public void testGetMotor() {
        System.out.println("getActualBattery");

        int expResult = 50;
        int result = instance.getMotor();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBattery() {
        System.out.println("getBattery");
        Battery expResult = bat;
        Battery result = instance.getBattery();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooterType method, of class Scooter.
     */
    @Test
    public void testGetScooterType() {
        System.out.println("getScooterType");

        String expResult = "city";
        String result = instance.getTypeOfScooter();
        assertEquals(expResult, result);

    }

    /**
     * Test of getLastUpdateTime method, of class Scooter.
     */
    @Test
    public void testGetLockTime() {
        System.out.println("getLockTime");

        LocalDateTime expResult = LocalDateTime.of(2019, 12, 12, 12, 0);
        LocalDateTime result = instance.getLastUpdateTime();
        assertEquals(expResult, result);

    }

    @Test
    public void testSetMaxBattery() {
        System.out.println("testSetMaxBattery");
        double batt = 100.00;
        instance.setMaxBattery(batt);
        assertEquals(100.00, instance.getMaxBattery());

    }

    @Test
    public void testSetActualBattery() {
        System.out.println("testSetActualBattery");
        double batt = 20.00;
        instance.setActualBattery(batt);
        assertEquals(20.00, instance.getActualBattery());
    }

    @Test
    public void testSetScooterType() {
        System.out.println("setScooterType");
        String type = "off-road";
        instance.setTypeOfScooter(type);
        assertEquals("off-road", instance.getTypeOfScooter());
    }

    @Test
    public void testSetMotor() {
        System.out.println("setMotor");
        int type = 150;
        instance.setMotor(type);
        assertEquals(150, instance.getMotor());
    }

    @Test
    public void testSetBattery() {
        System.out.println("setBattery");
        Battery batt = new Battery(80.0, 40.0);
        instance.setBattery(batt);
        assertEquals(batt, instance.getBattery());
    }

    /**
     * Test of setLastUpdateTime method, of class Scooter.
     */
    @Test
    public void testSetLockTime() {
        System.out.println("setLockTime");
        LocalDateTime lockTime = LocalDateTime.of(2019, 12, 25, 15, 0);
        instance.setLastUpdateTime(lockTime);
        assertEquals(lockTime, instance.getLastUpdateTime());
    }
}
