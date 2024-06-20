/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Toshiba
 */
public class RegisteredUserTest {

    RegisteredUser instance = new RegisteredUser("Napoleao", "franca@franca.fr", "male", 1, 40, 44.7, 123456789, "nana", 14);

    public RegisteredUserTest() {
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
     * Test of getUsername method, of class RegisteredUser.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        RegisteredUser instance = this.instance;
        String expResult = "Napoleao";
        String result = instance.getUsername();
        assertEquals(expResult, result);

    }

    /**
     * Test of getEmail method, of class RegisteredUser.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "franca@franca.fr";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGender method, of class RegisteredUser.
     */
    @Test
    public void testGetGender() {
        System.out.println("getGender");
        String expResult = "male";
        String result = instance.getGender();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAge method, of class RegisteredUser.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String expResult = "nana";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeight method, of class RegisteredUser.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        int expResult = 1;
        int result = instance.getHeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeight method, of class RegisteredUser.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");

        int expResult = 40;
        int result = instance.getWeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCyclingAverageSpeed method, of class RegisteredUser.
     */
    @Test
    public void testGetCyclingAverageSpeed() {
        System.out.println("getCyclingAverageSpeed");
        double expResult = 44.7;
        double result = instance.getCyclingAverageSpeed();
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getVisa method, of class RegisteredUser.
     */
    @Test
    public void testGetVisa() {
        System.out.println("getVisa");
        long expResult = 123456789;
        long result = instance.getVisa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPoints method, of class RegisteredUser.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        int expResult = 14;
        int result = instance.getPoints();
        assertEquals(expResult, result);
    }
    @Test
    public void testSetPoints() {
        System.out.println("testSetPoints");
        int points = 20;
        instance.setPoints(points);
        assertEquals(points, instance.getPoints());
    }
}
