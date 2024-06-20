package lapr.project.model.Vehicle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Nuno Capela
 */
public class BatteryTest {

    Battery instance;

    public BatteryTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Battery(100.00, 90.00);

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getMaxBatt method, of class Battery.
     */
    @Test
    public void testGetMaxBatt() {
        System.out.println("getMaxBatt");

        double expResult = 100.00;
        double result = instance.getMaxBatt();
        assertEquals(expResult, result);

    }

    /**
     * Test of getActualBatt method, of class Battery.
     */
    @Test
    public void testGetActualBatt() {
        System.out.println("getActualBatt");

        double expResult = 90.00;
        double result = instance.getActualBatt();
        assertEquals(expResult, result);

    }

    /**
     * Test of setMaxBatt method, of class Battery.
     */
    @Test
    public void testSetMaxBatt() {
        System.out.println("setMaxBatt");
        double maxBatt = 100.00;
        instance.setMaxBatt(maxBatt);
        assertEquals(maxBatt, instance.getMaxBatt());
    }

    /**
     * Test of setActualBatt method, of class Battery.
     */
    @Test
    public void testSetActualBatt() {
        System.out.println("setActualBatt");
        double actualBatt = 90.00;
        instance.setActualBatt(actualBatt);
        assertEquals(actualBatt, instance.getActualBatt());
    }

}
