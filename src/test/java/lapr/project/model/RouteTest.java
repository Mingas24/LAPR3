/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Location;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Utilizador
 */
public class RouteTest {

    public RouteTest() {
    }
    private final Capacity capacity = new Capacity(20, 10);
    private final ParkCharger charger = new ParkCharger(120, 120);
    private final Park park1 = new Park("001", "Sé do Porto", 41.14278, -8.60928, capacity, 100, charger);
    private final Park park3 = new Park("003", "Trindade", 41.15068, -8.61053, capacity, 50, charger);
    LinkedList<Location> routeList = new LinkedList<>();

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        routeList.add(park1);
        routeList.add(park3);
    }

    @AfterEach
    public void tearDown() {
    }

    Route instance = new Route(park1, park3, routeList, 800.31);

    @Test
    public void testGetSource() {
        Location result = instance.getSource();
        Location expResult = park1;
        assertEquals(result.getDescription(), expResult.getDescription());
        assertEquals(result.getElevation(), expResult.getElevation());
        assertEquals(result.getLatitude(), expResult.getLatitude());
        assertEquals(result.getLocationId(), expResult.getLocationId());
        assertEquals(result.getLongitude(), expResult.getLongitude());
        assertNotNull(result);
        assertNotNull(expResult);
        
    }

    @Test
    public void testGetDest() {
        Location result = instance.getDest();
        Location expResult = park3;
        assertEquals(result.getDescription(), expResult.getDescription());
        assertEquals(result.getElevation(), expResult.getElevation());
        assertEquals(result.getLatitude(), expResult.getLatitude());
        assertEquals(result.getLocationId(), expResult.getLocationId());
        assertEquals(result.getLongitude(), expResult.getLongitude());
        assertNotNull(result);
        assertNotNull(expResult);
    }

    @Test
    public void testGetLocations() {
        List<Location> result = instance.getLocations();
        LinkedList<Location> expResult = new LinkedList<>();
        expResult.add(park1);
        expResult.add(park3);
        assertEquals(result, expResult);
    }

    @Test
    public void testGetCost() {
        double result = instance.getCost();
        double expResult = 800.31;
        assertEquals(result, expResult);
    }

    @Test
    public void testSetSource() {
        instance.setSource(park3);
        Location result = instance.getSource();
        Location expResult = park3;
        assertEquals(result.getDescription(), expResult.getDescription());
        assertEquals(result.getElevation(), expResult.getElevation());
        assertEquals(result.getLatitude(), expResult.getLatitude());
        assertEquals(result.getLocationId(), expResult.getLocationId());
        assertEquals(result.getLongitude(), expResult.getLongitude());
        assertNotNull(result);
        assertNotNull(expResult);
    }

    @Test
    public void testSetDest() {
        instance.setDest(park1);
        Location result = instance.getDest();
        Location expResult = park1;
        assertEquals(result.getDescription(), expResult.getDescription());
        assertEquals(result.getElevation(), expResult.getElevation());
        assertEquals(result.getLatitude(), expResult.getLatitude());
        assertEquals(result.getLocationId(), expResult.getLocationId());
        assertEquals(result.getLongitude(), expResult.getLongitude());
        assertNotNull(result);
        assertNotNull(expResult);
    }

    @Test
    public void testSetLocations() {
        LinkedList<Location> set = new LinkedList<>();
        set.add(park3);
        set.add(park1);
        instance.setLocations(set);
        List<Location> result = instance.getLocations();
        LinkedList<Location> expResult = new LinkedList<>();
        expResult.add(park3);
        expResult.add(park1);
        assertEquals(result, expResult);
    }

    @Test
    public void testSetCost() {
        instance.setCost(700.1);
        double result = instance.getCost();
        double expResult = 700.1;
        assertEquals(result, expResult);
    }

    @Test
    public void testToString() {
        String result = instance.toString();
        String expResult = "Route:\n" + "source-" + instance.getSource()
                + ", dest-" + instance.getDest() + ", locations-" + instance.getLocations()
                + ", cost-" + instance.getCost() + '}';
    }
}
