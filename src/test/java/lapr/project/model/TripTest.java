package lapr.project.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Utilizador
 */
public class TripTest {

    Trip instance;

    public TripTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        LocalDateTime startTime = LocalDateTime.of(2019, Month.NOVEMBER, 20, 12, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2019, Month.NOVEMBER, 20, 13, 0, 0);
        instance = new Trip(1, "Capela", "2", "3", "4", startTime, endTime);
    }

    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of Trip method, of class Trip.
     */
    @Test
    public void testTrip() {
        System.out.println("testTrip");
        int expResult = 1;
        int result = instance.getTripId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTripId method, of class Trip.
     */
    @Test
    public void testGetTripId() {
        System.out.println("testGetTripId");
        int expResult = 1;
        int result = instance.getTripId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class Trip.
     */
    @Test
    public void testGetUsername() {
        System.out.println("testGetTripId");
        String expResult = "Capela";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVehicleId method, of class Trip.
     */
    @Test
    public void testGetVehicleId() {
        System.out.println("testGetVehicleId");
        String expResult = "2";
        String result = instance.getVehicleId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParkId method, of class Trip.
     */
    @Test
    public void testGetParkId() {
        System.out.println("testGetParkId");
        String expResult = "3";
        String result = instance.getStartPark();
        assertEquals(expResult, result);
    }


    /**
     * Test of getEndingPark method, of class Trip.
     */
    @Test
    public void testGetEndingPark() {
        System.out.println("testGetEndingParkId");
        String expResult = "4";
        String result = instance.getEndingPark();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartTime method, of class Trip.
     */
    @Test
    public void testGetStartTime() {
        System.out.println("testGetEndingParkId");
        LocalDateTime expResult = LocalDateTime.of(2019, Month.NOVEMBER, 20, 12, 0, 0);
        LocalDateTime result = instance.getStartTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndTime method, of class Trip.
     */
    @Test
    public void testGetEndTime() {
        System.out.println("testGetEndingParkId");
        LocalDateTime expResult = LocalDateTime.of(2019, Month.NOVEMBER, 20, 13, 0, 0);
        LocalDateTime result = instance.getEndTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTripId method, of class Trip.
     */
    @Test
    public void testSetTripId() {
        System.out.println("testSetTripId");
        int expResult = 2;
        instance.setTripId(2);
        int result = instance.getTripId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class Trip.
     */
    @Test
    public void testSetUsername() {
        System.out.println("testSetUsername");
        String expResult = "Francisco";
        instance.setUsername("Francisco");
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVehicleId method, of class Trip.
     */
    @Test
    public void testSetVehicleId() {
        System.out.println("testSetVehicleId");
        String expResult = "1";
        instance.setVehicleId("1");
        String result = instance.getVehicleId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setParkId method, of class Trip.
     */
    @Test
    public void testSetParkId() {
        System.out.println("testSetParkId");
        String expResult = "2";
        instance.setStartPark("2");
        String result = instance.getStartPark();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setEndingPark method, of class Trip.
     */
    @Test
    public void testSetEndingPark() {
        System.out.println("testSetEndingPark");
        String expResult = "2";
        instance.setEndingPark("2");
        String result = instance.getEndingPark();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setStartTime method, of class Trip.
     */
    @Test
    public void testSetStartTime() {
        System.out.println("testSetStartTime");
        LocalDateTime expResult = LocalDateTime.of(2019, Month.NOVEMBER, 20, 12, 30, 0);
        LocalDateTime newStartTime = LocalDateTime.of(2019, Month.NOVEMBER, 20, 12, 30, 0);
        instance.setStartTime(newStartTime);
        LocalDateTime result = instance.getStartTime();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setEndTime method, of class Trip.
     */
    @Test
    public void testSetEndTime() {
        System.out.println("testSetEndTime");
        LocalDateTime expResult = LocalDateTime.of(2019, Month.NOVEMBER, 20, 12, 30, 0);
        LocalDateTime newEndTime = LocalDateTime.of(2019, Month.NOVEMBER, 20, 12, 30, 0);
        instance.setEndTime(newEndTime);
        LocalDateTime result = instance.getEndTime();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of setEndTime method, of class Trip.
     */
    @Test
    public void testToString() {
        System.out.println("testToString");
        
        LocalDateTime startTime = LocalDateTime.of(2019, Month.NOVEMBER, 20, 12, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2019, Month.NOVEMBER, 20, 13, 0, 0);
        String expResult = "Trip:" + "\ntripId=" + 1 + ", \nusername=" + "Capela" + ", \nvehicleId=" + 2 + 
                ", \nparkId=" + 3 + ", \nendingPark=" + 4 + ", \nstartTime=" + 
                startTime + ", \nendTime=" + endTime ;
        
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
