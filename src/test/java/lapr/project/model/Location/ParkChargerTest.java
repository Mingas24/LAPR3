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
public class ParkChargerTest {

    ParkCharger instance;

    public ParkChargerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new ParkCharger(120, 120);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getParkInputVoltage method, of class ParkCharger.
     */
    @Test
    public void testGetParkInputVoltage() {
        System.out.println("getParkInputVoltage");
        int expResult = 120;
        int result = instance.getParkInputVoltage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setParkInputVoltage method, of class ParkCharger.
     */
    @Test
    public void testSetParkInputVoltage() {
        System.out.println("setParkInputVoltage");
        int parkInputVoltage = 70;
        instance.setParkInputVoltage(parkInputVoltage);
        assertEquals(parkInputVoltage, instance.getParkInputVoltage());
    }

    /**
     * Test of getParkInputCurrent method, of class ParkCharger.
     */
    @Test
    public void testGetParkInputCurrent() {
        System.out.println("getParkInputCurrent");
        int expResult = 120;
        int result = instance.getParkInputCurrent();
        assertEquals(expResult, result);
    }

    /**
     * Test of setParkInputCurrent method, of class ParkCharger.
     */
    @Test
    public void testSetParkInputCurrent() {
        System.out.println("setParkInputCurrent");
        int parkInputCurrent = 40;
        instance.setParkInputCurrent(parkInputCurrent);
        assertEquals(parkInputCurrent, instance.getParkInputCurrent());
    }

}
