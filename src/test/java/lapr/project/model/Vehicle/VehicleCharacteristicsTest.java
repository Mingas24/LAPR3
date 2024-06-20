package lapr.project.model.Vehicle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Nuno Capela
 */
public class VehicleCharacteristicsTest {
    
    VehicleCharacteristics instance;

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        instance = new VehicleCharacteristics(10, 3.14, 2.4);
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of getWeight method, of class VehicleCharacteristics.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        
        int expResult = 10;
        int result = instance.getWeight();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getAerodynamicCoefficient method, of class VehicleCharacteristics.
     */
    @Test
    public void testGetAerodynamicCoefficient() {
        System.out.println("getAerodynamicCoefficient");
       
        double expResult = 3.14;
        double result = instance.getAerodynamicCoefficient();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getFrontalArea method, of class VehicleCharacteristics.
     */
    @Test
    public void testGetFrontalArea() {
        System.out.println("getFrontalArea");
        
        double expResult = 2.4;
        double result = instance.getFrontalArea();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setWeight method, of class VehicleCharacteristics.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        int weight = 20;
        
        instance.setWeight(weight);
        assertEquals(weight, instance.getWeight());
    }

    /**
     * Test of setAerodynamicCoefficient method, of class VehicleCharacteristics.
     */
    @Test
    public void testSetAerodynamicCoefficient() {
        System.out.println("setAerodynamicCoefficient");
        double aerodynamicCoefficient = 5.5;
       
        instance.setAerodynamicCoefficient(aerodynamicCoefficient);
        assertEquals(aerodynamicCoefficient, instance.getAerodynamicCoefficient());
    }

    /**
     * Test of setFrontalArea method, of class VehicleCharacteristics.
     */
    @Test
    public void testSetFrontalArea() {
        System.out.println("setFrontalArea");
        double frontalArea = 1.20;
        
        instance.setFrontalArea(frontalArea);
        assertEquals(frontalArea, instance.getFrontalArea());
    }

}
