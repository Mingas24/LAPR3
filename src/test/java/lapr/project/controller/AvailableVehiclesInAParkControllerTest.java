package lapr.project.controller;

import java.io.FileInputStream;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import lapr.project.data.LocationDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import lapr.project.model.Vehicle.Battery;
import lapr.project.model.Vehicle.Bicycle;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.model.Vehicle.Vehicle;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvailableVehiclesInAParkControllerTest {

    AvailableVehiclesInAParkController instance;
    VehicleDB sdb;
    LocationDB ldb;

    AddScooterController asc;
    AddParkController apc;
    AddBicycleController abc;

    List<Vehicle> avaiVehicles;
    List<Scooter> avaiScooters;
    List<Bicycle> avaiBikes;

    Bicycle BikeTest;
    Scooter ScooterTest;
    //Creating Park  
    Capacity capacity = new Capacity(20, 10);
    ParkCharger charger = new ParkCharger(120, 120);

    private final Park park1 = new Park("001", "Sé do Porto", 21.2, 22.4, capacity, 100, charger);

    //Creating Scooter
    private final VehicleInfos viS = new VehicleInfos(21.2, 22.4, "AVAILABLE", LocalDateTime.of(2019, 12, 12, 12, 0));
    private final VehicleInfos viS2 = new VehicleInfos(21.2, 22.4, "UNAVAILABLE", LocalDateTime.of(2019, 12, 13, 12, 0));

    private final VehicleCharacteristics vcS = new VehicleCharacteristics(12, 1, 0.0043);
    private final Battery bS1 = new Battery(200000, 50);
    private final Battery bS2 = new Battery(200000, 90);

    private final Scooter scooter1 = new Scooter("s000", "CITY", viS, vcS, bS1, 50);

    private final Scooter scooter2 = new Scooter("s001", "city", viS2, vcS, bS2, 50);

    //Creating Bicycle
    VehicleInfos viB = new VehicleInfos(21.2, 22.4, "AVAILABLE");
    VehicleInfos viB2 = new VehicleInfos(21.2, 22.4, "UNAVAILABLE");

    VehicleCharacteristics vcB = new VehicleCharacteristics(4, 0.95, 4.5);

    private final Bicycle bike1 = new Bicycle("bike02", new VehicleInfos(21.2, 22.4, "AVAILABLE"), new VehicleCharacteristics(4, 0.95, 4.5), 10);

    private final Bicycle bike2 = new Bicycle("bike03", new VehicleInfos(21.2, 22.4, "UNAVAILABLE"), new VehicleCharacteristics(4, 0.95, 4.5), 17);

    public AvailableVehiclesInAParkControllerTest() {
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sdb = new VehicleDB();
        ldb = new LocationDB();
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
     * Test of getAllAvailableScooterInPark method, of class
     * AvailableVehiclesInAParkController. //
     */
    @Test
    public void testGetAllAvailableScooterInPark() throws SQLException, IOException {
        System.out.println("getAllAvailableScooterInPark");

        avaiScooters = new ArrayList<>();

        try {
            sdb.ClearDatabase();

            instance = new AvailableVehiclesInAParkController();

            apc = new AddParkController();
            asc = new AddScooterController();
            abc = new AddBicycleController();

            apc.addPark(park1);

            asc.addScooter(scooter1);
            asc.addScooter(scooter2);

            abc.addBicycle(bike1);
            abc.addBicycle(bike2);

            String parkID = park1.getLocationId();

            List<Scooter> expResult = new ArrayList<>();

            ScooterTest = sdb.getScooter(scooter1.getVehicleID());

            List<Scooter> result = instance.getAllAvailableScooterInPark(parkID);

            expResult.add(ScooterTest);

            for (int i = 0; i < result.size(); i++) {
                assertEquals(expResult.get(i).getVehicleID(), result.get(i).getVehicleID());
            }

            sdb.ClearDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sdb.ClearDatabase();
        }

    }

    @Test
    //when there are no available vehicles
    public void testGetAllAvailableScooterInPark2() throws SQLException, IOException {
        System.out.println("getAllAvailableScooterInPark-NO AVAILABLE SCOOTERS");

        avaiScooters = new ArrayList<>();

        try {
            sdb.ClearDatabase();

            instance = new AvailableVehiclesInAParkController();

            apc = new AddParkController();
            asc = new AddScooterController();
            abc = new AddBicycleController();

            apc.addPark(park1);

            asc.addScooter(scooter2);

            abc.addBicycle(bike1);
            abc.addBicycle(bike2);

            String parkID = park1.getLocationId();

            List<Scooter> expResult = new ArrayList<>();

            List<Scooter> result = instance.getAllAvailableScooterInPark(parkID);

            for (int i = 0; i < result.size(); i++) {
                assertEquals(expResult.get(i).getVehicleID(), result.get(i).getVehicleID());
            }

            sdb.ClearDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sdb.ClearDatabase();
        }

    }

    /**
     * Test of getAllAvailableBikeInPark method, of class
     * AvailableVehiclesInAParkController.
     */
    @Test
    public void testGetAllAvailableBikeInPark() throws SQLException, IOException {

        System.out.println("getAllAvailableBikeInPark");
        avaiBikes = new ArrayList<>();

        try {

            sdb.ClearDatabase();
            instance = new AvailableVehiclesInAParkController();
            apc = new AddParkController();
            asc = new AddScooterController();
            abc = new AddBicycleController();

            apc.addPark(park1);

            asc.addScooter(scooter1);
            asc.addScooter(scooter2);

            abc.addBicycle(bike1);
            abc.addBicycle(bike2);

            String parkID = park1.getLocationId();

            List<Bicycle> expResult = new ArrayList<>();

            BikeTest = sdb.getBycicle(bike1.getVehicleID());

            List<Bicycle> result = instance.getAllAvailableBikeInPark(parkID);

            expResult.add(BikeTest);

            for (int i = 0; i < result.size(); i++) {
                assertEquals(expResult.get(i).getVehicleID(), result.get(i).getVehicleID());
            }

            sdb.ClearDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sdb.ClearDatabase();
        }

    }

    //when there are no available vehicles
    @Test

    public void testGetAllAvailableBikeInPark2() throws SQLException, IOException {

        System.out.println("getAllAvailableBikeInPark- NO AVAILABLE BIKE");
        avaiBikes = new ArrayList<>();

        try {
            sdb.ClearDatabase();
            instance = new AvailableVehiclesInAParkController();
            apc = new AddParkController();
            asc = new AddScooterController();
            abc = new AddBicycleController();

            apc.addPark(park1);

            asc.addScooter(scooter1);
            asc.addScooter(scooter2);

            abc.addBicycle(bike2);

            String parkID = park1.getLocationId();
            AvailableVehiclesInAParkController instance = new AvailableVehiclesInAParkController();

            List<Bicycle> expResult = new ArrayList<>();

            List<Bicycle> result = instance.getAllAvailableBikeInPark(parkID);

            for (int i = 0; i < result.size(); i++) {
                assertEquals(expResult.get(i).getVehicleID(), result.get(i).getVehicleID());
            }

            sdb.ClearDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sdb.ClearDatabase();
        }

    }

}
