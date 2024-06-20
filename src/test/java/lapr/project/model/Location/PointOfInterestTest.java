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
public class PointOfInterestTest {

    private String locationId;
    private double latitude;
    private double longitude;
    private int elevation;
    private String poiDescription;

    private PointOfInterest poi;

    public PointOfInterestTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        locationId = "1";
        latitude = 41.148518;
        longitude = -8.644335;
        elevation = 4;
        poiDescription = "Ribeira";

        poi = new PointOfInterest(locationId, latitude, longitude, elevation, poiDescription);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetLocationId() {
        System.out.println("testGetLocationID");
        String expResult = "1";
        String result = poi.getLocationId();

        assertEquals(expResult, result);

    }

    @Test
    public void testSetLocationId() {
        System.out.println("testSetLocationID");
        String loc = "4";
        poi.setLocationId(loc);
        String result = poi.getLocationId();

        assertEquals(loc, result);
    }

    @Test
    public void testGetLatitude() {
        System.out.println("testGetLatitude");
        double expResult = 41.148518;
        double result = poi.getLatitude();

        assertEquals(expResult, result, 000001);
    }

    @Test
    public void testSetLatitude() {
        System.out.println("testSetLatitude");
        double lat = 41.148518;
        poi.setLatitude(lat);
        double result = poi.getLatitude();

        assertEquals(lat, result, 000001);
    }

    @Test
    public void testGetLongitude() {
        System.out.println("testGetLongitude");
        double expResult = -8.644335;
        double result = poi.getLongitude();

        assertEquals(expResult, result, 000001);
    }

    @Test
    public void testSetLongitude() {
        System.out.println("testSetLongitude");
        double lon = 8.644335;
        poi.setLongitude(lon);
        double result = poi.getLongitude();

        assertEquals(lon, result, 000001);
    }

    @Test
    public void testGetElevation() {
        System.out.println("testGetElevation");
        double expResult = 4;
        double result = poi.getElevation();

        assertEquals(expResult, result, 000001);
    }

    @Test
    public void testSetElevation() {
        System.out.println("testSetElevation");
        int elev = 85;
        poi.setElevation(elev);
        int result = poi.getElevation();

        assertEquals(elev, result);
    }

    @Test
    public void testGetPoiDescription() {
        System.out.println("testGetPoiDescription");
        String expResult = "Ribeira";
        String result = poi.getDescription();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetPoiDescription() {
        System.out.println("testSetPoiDescription");
        String descrip = "Clerigos";
        poi.setDescription(descrip);
        String result = poi.getDescription();
        assertEquals(descrip, result);
    }

}
