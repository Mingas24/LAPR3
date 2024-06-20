/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.Location;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Toshiba
 */
public class ParkTest {

    Park instance;
    Capacity capacity;
    ParkCharger parkCharger;

    public ParkTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        capacity = new Capacity(20, 10);
        parkCharger = new ParkCharger(120, 120);
        instance = new Park("147", "Fonte da Luz", 41.148518, -8.644335, capacity, 30, parkCharger);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getPark_id method, of class Park.
     */
    @Test
    public void ensureGetLocationId() {
        System.out.println("ensureGetLocationId");
        String expResult = "147";
        String result = instance.getLocationId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPark_description method, of class Park.
     */
    @Test
    public void ensureGetParkDescription() {
        System.out.println("getPark_description");
        String expResult = "Fonte da Luz";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLat_park method, of class Park.
     */
    @Test
    public void ensureGetLatPark() {
        System.out.println("getLat_park");
        double expResult = 41.148518;
        double result = instance.getLatitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLong_park method, of class Park.
     */
    @Test
    public void ensureGetLongPark() {
        System.out.println("getLong_park");
        double expResult = -8.644335;
        double result = instance.getLongitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBike_capacity method, of class Park.
     */
    @Test
    public void ensureGetBikePapacity() {
        System.out.println("getBike_capacity");
        int expResult = 20;
        int result = instance.getBikeCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooter_capacity method, of class Park.
     */
    @Test
    public void ensureGetScooterCapacity() {
        System.out.println("getScooter_capacity");
        int expResult = 10;
        int result = instance.getScooterCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getElevation method, of class Park.
     */
    @Test
    public void ensureGetElevation() {
        System.out.println("getElevation");
        int expResult = 30;
        int result = instance.getElevation();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPark_input_voltage method, of class Park.
     */
    @Test
    public void testGetParkInputCurrent() {
        System.out.println("getPark_input_voltage");
        int expResult = 120;
        int result = instance.getParkInputCurrent();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBikeCapacity method, of class Park.
     */
    @Test
    public void testGetCapacity() {
        System.out.println("getCapacity");
        Capacity result = instance.getCapacity();
        assertEquals(capacity, result);

    }

    /**
     * Test of setCapacity method, of class Park.
     */
    @Test
    public void testSetCapacity() {
        System.out.println("setCapacity");
        Capacity cap = new Capacity(120, 30);

        instance.setCapacity(cap);
        assertEquals(120, instance.getBikeCapacity());
        assertEquals(30, instance.getScooterCapacity());
    }

    @Test
    public void testGetParkCharger() {
        System.out.println("GetParkCharger");
        ParkCharger result = instance.getParkCharger();
        assertEquals(parkCharger, result);
    }

    @Test
    public void getParkInputVoltageTest() {
        System.out.println("getParkInputVoltage");
        int result = instance.getParkInputVoltage();
        assertEquals(120, result);
    }

    @Test
    public void setParkChargerTest() {
        System.out.println("setParkInputVoltageTest");
        ParkCharger pc = new ParkCharger(10, 20);

        instance.setParkCharger(pc);
        assertEquals(20, instance.getParkInputCurrent());
        assertEquals(10, instance.getParkInputVoltage());

    }
 
}
