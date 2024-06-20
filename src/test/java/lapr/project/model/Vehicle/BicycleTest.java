/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.Vehicle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Nuno Capela
 */
public class BicycleTest {

    Bicycle instance, instance2;
    VehicleInfos vinfo, vinfo2;
    VehicleCharacteristics vc;
    Vehicle vv;

    public BicycleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        vinfo = new VehicleInfos(12.4, 5.7, "Available");
        vinfo2 = new VehicleInfos("testSetID", "Available");
        vc = new VehicleCharacteristics(10, 3.14, 2.4);
        instance = new Bicycle("testID", vinfo, vc, 19);
        instance2 = new Bicycle("testID", vinfo2, vc, 19);

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void getVehicleIDTest() {
        System.out.println("getVehicleIDTest");
        String expResult = "testID";
        String result = instance.getVehicleID();
        assertEquals(expResult, result);
    }

    @Test
    public void setVehicleIDTest() {
        System.out.println("setVehicleIDTest");
        String bicycleID = "testSetBikeID";
        instance.setVehicleID(bicycleID);
        assertEquals(bicycleID, instance.getVehicleID());
    }

    @Test
    public void getParkIDTest() {
        System.out.println("getParkIDTest");
        String result = instance2.getParkID();
        assertEquals("testSetID", result);

    }

    @Test
    public void setParkIDTest() {
        System.out.println("setParkIDTest");
        String parkID = "testSetID";

        vinfo.setParkID(parkID);
        assertEquals("testSetID", instance.getParkID());
    }

    @Test
    public void getVehicleStatusTest() {
        System.out.println("getVehicleStatusTest");
        String result = instance.getVehicleStatus();
        assertEquals("Available", result);
    }

    @Test
    public void setVehicleStatusTest() {
        String VehicleStatus = "testSetStatus";
        instance.setVehicleStatus(VehicleStatus);
        assertEquals("testSetStatus", instance.getVehicleStatus());
    }

    @Test
    public void getWeightTest() {
        System.out.println("getWeightTest");
        int expResult = 10;
        int result = instance.getWeight();
        assertEquals(expResult, result);
    }

    @Test
    public void setWeightTest() {
        System.out.println("setWeightTest");
        int weight = 11;
        instance.setWeight(weight);
        assertEquals(11, instance.getWeight());
    }

    @Test
    public void getAerodynamicCoefficientTest() {
        System.out.println("getAerodynamicCoefficientTest");
        double expResult = 3.14;
        double result = instance.getAerodynamicCoefficient();
        assertEquals(expResult, result);
    }

    @Test
    public void setAerodynamicCoefficientTest() {
        System.out.println("setAerodynamicCoefficientTest");
        double aerodynamicCoefficient = 11;
        instance.setAerodynamicCoefficient(aerodynamicCoefficient);
        assertEquals(11, instance.getAerodynamicCoefficient());
    }

    @Test
    public void getFrontalAreaTest() {
        System.out.println("getFrontalAreaTest");
        double expResult = 2.4;
        double result = instance.getFrontalArea();
        assertEquals(expResult, result);
    }

    @Test
    public void setFrontalAreaTest() {
        System.out.println("setFrontalAreaTest");
        double frontal = 1.1;
        instance.setFrontalArea(frontal);
        assertEquals(1.1, instance.getFrontalArea());
    }

    @Test
    public void getWheelSizeTest() {
        System.out.println("getWheelSizeTest");
        int result = instance.getWheelSize();
        assertEquals(19, result);
    }

    @Test
    public void setWheelSizeTest() {
        System.out.println("setWheelSizeTest");
        int wheelSize = 12;
        instance.setWheelSize(wheelSize);
        assertEquals(12, instance.getWheelSize());
    }

    @Test
    public void getLatTest() {
        System.out.println("getLatTest");
        double result = instance.getLatPark();
        assertEquals(12.4, result);
    }

    @Test
    public void setLatTest() {
        System.out.println("setLatTest");
        double lat = 123.213;
        instance.setLatPark(lat);
        assertEquals(lat, instance.getLatPark());
    }

    @Test
    public void getLongTest() {
        System.out.println("getLongTest");
        double result = instance.getLongPark();
        assertEquals(5.7, result);
    }

    @Test
    public void setLongTest() {
        System.out.println("setLongTest");
        double lat = 1.12313;
        instance.setLongPark(lat);
        assertEquals(lat, instance.getLongPark());
    }

    @Test
    public void getVehicleInfos() {
        System.out.println("getVehicleInfos");
        VehicleInfos expResult = vinfo;
        VehicleInfos result = instance.getVehicleInfos();
        assertEquals(expResult, result);
    }

    @Test
    public void getVehicleCharacteristics() {
        System.out.println("getVehicleCharacteristics");
        VehicleCharacteristics expResult = vc;
        VehicleCharacteristics result = instance.getVehicleCharacteristics();
        assertEquals(expResult, result);
    }

    @Test
    public void setVehicleInfo() {
        System.out.println("setVehicleInfos");
        VehicleInfos vf = new VehicleInfos("nonono", "Available");
        instance.setVehicleInfos(vf);
        assertEquals(vf, instance.getVehicleInfos());
    }

    @Test
    public void setVehicleCharacteristics() {
        System.out.println("setVehicleCharacteristics");
        VehicleCharacteristics vhc = new VehicleCharacteristics(19, 0.98, 0.9);
        instance.setVehicleCharacteristics(vhc);
        assertEquals(vhc, instance.getVehicleCharacteristics());
    }
}
