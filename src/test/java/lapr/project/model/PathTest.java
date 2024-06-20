package lapr.project.model;

import lapr.project.model.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * @author Asus
 */
public class PathTest {

    Path instance, instance2;

    public PathTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

        instance = new Path(41.148518, -8.644335, 38.148518, -9.644335, 0.002, 1.2, 220);
        instance2 = new Path("Trindade", "ISEP", 0.002, 1.2, 220);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void getlatitudeATest() {
        System.out.println("getLatitudeA");
        double expResult = 41.148518;
        double result = instance.getlatitudeA();
        assertEquals(expResult, result);
    }

    @Test
    public void setlatitudeATest() {
        System.out.println("setlatitudeA Test");

        instance.setlatitudeA(42.148518);
        assertEquals(42.148518, instance.getlatitudeA());

    }

    @Test
    public void getlatitudeBTest() {
        System.out.println("getLatitudeB");
        double expResult = 38.148518;
        double result = instance.getlatitudeB();
        assertEquals(expResult, result);

    }

    @Test
    public void setlatitudeBTest() {
        System.out.println("setlatitudeB Test");

        instance.setlatitudeB(35.148518);
        assertEquals(35.148518, instance.getlatitudeB());
    }

    @Test
    public void getlongitudeATest() {
        System.out.println("getLongitudeA");
        double expResult = -8.644335;
        double result = instance.getlongitudeA();
        assertEquals(expResult, result);
    }

    @Test
    public void setlongitudeATest() {

        System.out.println("setlongitudeA Test");
        instance.setlongitudeA(-10.644435);
        assertEquals(-10.644435, instance.getlongitudeA());
    }

    @Test
    public void getlongitudeBTest() {
        System.out.println("getLongitudeB");
        double expResult = -9.644335;
        double result = instance.getlongitudeB();
        assertEquals(expResult, result);
    }

    @Test
    public void setlongitudeBTest() {
        System.out.println("setlongitudeB Test");
        instance.setlongitudeB(-11.644435);
        assertEquals(-11.644435, instance.getlongitudeB());
    }

    @Test
    public void getkinetic_coefficientTest() {
        System.out.println("Get Kinetic_coefficient Test");
        double expResult = 0.002;
        double result = instance.getkinetic_coefficient();
        assertEquals(expResult, result);
    }

    @Test
    public void setkinetic_coefficientTest() {
        System.out.println("setkinetic_coefficient Test");
        instance.setkinetic_coefficient(0.7);
        assertEquals(0.7, instance.getkinetic_coefficient());

    }

    @Test
    public void getWindSpeedTest() {
        System.out.println("Get WindSpeed Test");
        double expResult = 1.2;
        double result = instance.getWindSpeed();
        assertEquals(expResult, result);
    }

    @Test
    public void setWindSpeedTest() {
        System.out.println("setWindSpeed Test");
        instance.setWindSpeed(2.1);
        assertEquals(2.1, instance.getWindSpeed());
    }

    @Test
    public void getWindDirectionTest() {
        System.out.println("Get Direction Test");
        double expResult = 220;
        double result = instance.getWindDirection();
        assertEquals(expResult, result);
    }

    @Test
    public void setWindDirectionTest() {

        System.out.println("Set Wind Direction Test");
        instance.setWindDirection(100);
        assertEquals(100, instance.getWindDirection());
    }

    @Test
    public void getLocationATest() {
        System.out.println("getLocationATest");
        String expResult = "Trindade";
        String result = instance2.getLocationA();
        assertEquals(expResult, result);
    }
    
    @Test
    public void getLocationBTest() {
        System.out.println("getLocationBTest");
        String expResult = "ISEP";
        String result = instance2.getLocationB();
        assertEquals(expResult, result);
    }
    
    @Test
    public void setLocationATest() {
        System.out.println("setLocationATest");
        instance2.setLocationA("Ola");
        assertEquals("Ola", instance2.getLocationA());
    }
    
    @Test
    public void setLocationBTest() {
        System.out.println("setLocationBTest");
        instance2.setLocationB("TUDO");
        assertEquals("TUDO", instance2.getLocationB());
    }
}
