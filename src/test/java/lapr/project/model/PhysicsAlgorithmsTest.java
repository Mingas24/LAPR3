package lapr.project.model;

import java.time.LocalDateTime;
import static lapr.project.model.PhysicsAlgorithms.distanceBetweenPoints;
import lapr.project.model.Vehicle.Battery;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhysicsAlgorithmsTest {

    public PhysicsAlgorithmsTest() {

    }

    private final VehicleInfos viS = new VehicleInfos(21.2, 22.4, "AVAILABLE", LocalDateTime.now());

    private final VehicleCharacteristics vcS = new VehicleCharacteristics(12, 1, 0.0043);
    private final Battery bS1 = new Battery(1000, 50);

    private final Scooter scooter1 = new Scooter("s000", "CITY", viS, vcS, bS1, 50);

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
     * Test of distanceBetweenParks, of class PhysicsAlgorithms.
     */
    @Test
    public void testDistanceBetweenPoints() {
        System.out.println("testDistanceBetweenPoints");

        double result = distanceBetweenPoints(41.471196, -8.247938, 41.17878, -8.60622, 20, 10);
        double expResult = 44.19;
        assertEquals(expResult, result, 0.01);

        
    }

    @Test
    public void testCalculateBearingAngle() {

        double result = PhysicsAlgorithms.calculateBearingAngle(39.099912, 38.627089, -94.581213, -90.200203);
        double expResult = 96.5126242349995;
        assertEquals(result, expResult);
    }

    @Test
    public void testcalculateRelsultantAngle() {  //double bearingAngle, double windDirection

        double result = PhysicsAlgorithms.calculateResultantAngle(30.2, 220.2);
        double expResult = 3.316;
        assertEquals(result, expResult, 0.001);
    }

    @Test
    public void testdiagonalDistance() {

        double result = PhysicsAlgorithms.diagonalDistance(16.1, 20, 10); //double distance, double elevation
        double expResult = 18.95;
        assertEquals(result, expResult, 0.01);
    }
    //------------

    @Test
    public void testfrictionEnergy() {

        double result = PhysicsAlgorithms.frictionEnergy(16.25, 1000, 16.1, 0.02);
        double expResult = 3155.6;
        assertEquals(result, expResult, 0.1);
    }

    @Test
    public void testdWindEnergy() {

        double result = PhysicsAlgorithms.WindEnergy(40, 0.2, 20, 16.1, 3.316, 20);//double avgSpeed, double coef, double frontalArea, double distance, double relativeWindAngle, double windSpeed);
        double expResult = 140569.4;
        assertEquals(result, expResult, 0.1);
    }

    @Test
    public void testgraviticPotentialEnergy() {  //int mass, double elevation)

        double result = PhysicsAlgorithms.graviticPotentialEnergy(1000, 3.2);
        double expResult = 31360;
        assertEquals(result, expResult, 0.1);
    }

    @Test
    public void testconversionToCalories() {

        double result = PhysicsAlgorithms.conversionToCalories(1000);
        double expResult = 239.234;
        assertEquals(result, expResult, 0.1);
    }

    @Test
    public void testcalculateMissingEnergy() {
        double result = PhysicsAlgorithms.calculateMissingEnergy(scooter1);
        double expResult = 500000;

        assertEquals(result, expResult);
    }

    @Test
    public void testcalcuteTimeDifference() {

        int result = PhysicsAlgorithms.calcuteTimeDifference(scooter1);
        int expResult = 0;
        assertEquals(result, expResult);
    }

}
