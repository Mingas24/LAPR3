/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Utilizador
 */
public class DistanceBetweenParksControllerTest {

    private final Capacity capacity = new Capacity(20, 10);
    private final ParkCharger charger = new ParkCharger(120, 120);
    private final Park park1 = new Park("001", "Sé do Porto", 41.14278, -8.60928, capacity, 100, charger);
    private final Park park2 = new Park("002", "Trindade", 41.15068, -8.61053, capacity, 50, charger);
    private final DistanceBetweenParksController dbpc = new DistanceBetweenParksController();
    public DistanceBetweenParksControllerTest() {
    }

    @BeforeAll
    @SuppressWarnings("unchecked")
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

    @Test
    public void testDistanceBetweenParks() throws Exception {
        double result = dbpc.distanceBetweenParks(park1, park2);
        double expResult = 0.88;
        assertEquals(expResult, result, 0.01);
    }
}
