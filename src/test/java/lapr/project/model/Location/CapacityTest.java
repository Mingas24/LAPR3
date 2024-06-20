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
public class CapacityTest {

    Capacity instance = new Capacity(20, 40);

    public CapacityTest() {
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
     * Test of getBicycleCapacity method, of class Capacity.
     */
    @Test
    public void testGetBicycleCapacity() {
        System.out.println("getBicycles");
        int expResult = 20;
        int result = instance.getBicycleCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooterCapacity method, of class Capacity.
     */
    @Test
    public void testGetScooterCapacity() {
        System.out.println("getScooters");
        int expResult = 40;
        int result = instance.getScooterCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBicycleCapacity method, of class Capacity.
     */
    @Test
    public void testSetBicycleCapacity() {
        System.out.println("setBicycles");
        int bicycles = 100;
        instance.setBicycleCapacity(bicycles);
        int result = instance.getBicycleCapacity();
        assertEquals(bicycles, result);
    }

    /**
     * Test of setScooterCapacity method, of class Capacity.
     */
    @Test
    public void testSetScooterCapacity() {
        System.out.println("setScooters");
        int scooters = 80;
        instance.setScooterCapacity(scooters);
        int result = instance.getScooterCapacity();
        assertEquals(scooters, result);

    }

}
