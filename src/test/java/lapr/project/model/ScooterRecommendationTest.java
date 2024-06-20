/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import lapr.project.controller.AddParkController;
import lapr.project.controller.AddScooterController;
import lapr.project.data.LocationDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Location;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import lapr.project.model.Location.PointOfInterest;
import lapr.project.model.Vehicle.Battery;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import lapr.project.utils.graphbase.Graph;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Utilizador
 */
public class ScooterRecommendationTest {

    private final Graph<Location, Double> testGraphEnergy;
    private final List<Location> locationList = new ArrayList<>();
    private final ScooterRecommendation instance = new ScooterRecommendation();
    Capacity capacity = new Capacity(20, 10);
    ParkCharger charger = new ParkCharger(120, 120);
    AddScooterController asc;
    VehicleDB sdb;
    LocationDB ldb;
    AddParkController apc;

    //Creating POIs
    private final PointOfInterest poi1 = new PointOfInterest("1", 41.148518, -8.644335, 4, "testRibeira");
    private final PointOfInterest poi2 = new PointOfInterest("2", 41.14788, -8.61112, 5, "testAliados");
    private final PointOfInterest poi3 = new PointOfInterest("3", 41.14555, -8.61062, 5, "testSãoBento");

    //Creating Parks
    private final Park park1 = new Park("001", "Sé do Porto", 41.14278, -8.60928, capacity, 100, charger);
    private final Park park3 = new Park("003", "Trindade", 41.15068, -8.61053, capacity, 50, charger);
    private final Park park4 = new Park("004", "Mercado do Bolhão", 41.15005, -8.60678, capacity, 50, charger);
    private final Park park5 = new Park("005", "Torre dos Clérigos", 41.14563, -8.61483, capacity, 70, charger);
    private final Park nullPark = null;

    //Creating User
    private final RegisteredUser user = new RegisteredUser("Napoleão", "franca@franca.fr", "male", 150, 60, 10, 123456789, "nana", 14);

    //Creating Paths
    private final Path path1 = new Path(poi2.getLatitude(), poi2.getLongitude(), poi1.getLatitude(), poi1.getLongitude(), 0.004, 10, 15);
    private final Path path2 = new Path(poi2.getLatitude(), poi2.getLongitude(), park4.getLatitude(), park4.getLongitude(), 0.004, 8, 12);
    private final Path path3 = new Path(poi2.getLatitude(), poi2.getLongitude(), park3.getLatitude(), park3.getLongitude(), 0.004, 16, 5);
    private final Path path4 = new Path(poi2.getLatitude(), poi2.getLongitude(), poi3.getLatitude(), poi3.getLongitude(), 0.004, 9, 14);
    private final Path path5 = new Path(poi3.getLatitude(), poi3.getLongitude(), poi2.getLatitude(), poi2.getLongitude(), 0.004, 5, 76);
    private final Path path6 = new Path(poi2.getLatitude(), poi2.getLongitude(), park5.getLatitude(), park5.getLongitude(), 0.004, 20, 32);
    private final Path path7 = new Path(park1.getLatitude(), park1.getLongitude(), poi3.getLatitude(), poi3.getLongitude(), 0.004, 13, 24);
    private final Path path8 = new Path(poi3.getLatitude(), poi3.getLongitude(), park4.getLatitude(), park4.getLongitude(), 0.004, 12, 19);
    private final Path path9 = new Path(park4.getLatitude(), park4.getLongitude(), park3.getLatitude(), park3.getLongitude(), 0.004, 17, 51);
    private final Path path10 = new Path(park5.getLatitude(), park5.getLongitude(), park1.getLatitude(), park1.getLongitude(), 0.004, 2, 42);
    private final Path path11 = new Path(poi3.getLatitude(), poi3.getLongitude(), poi1.getLatitude(), poi1.getLongitude(), 0.004, 6, 31);
    private final Path path12 = new Path(poi1.getLatitude(), poi1.getLongitude(), park4.getLatitude(), park4.getLongitude(), 0.004, 15, 21);
    private final Path path13 = new Path(poi1.getLatitude(), poi1.getLongitude(), poi3.getLatitude(), poi3.getLongitude(), 0.004, 6, 7);
    private final Path path14 = new Path(park5.getLatitude(), park5.getLongitude(), poi2.getLatitude(), poi2.getLongitude(), 0.004, 18, 65);

    //Creating Scooter
    private final VehicleInfos viS = new VehicleInfos(park1.getLatitude(), park1.getLongitude(), "AVAILABLE", LocalDateTime.of(2019, 12, 12, 12, 0));

    private final VehicleCharacteristics vcS = new VehicleCharacteristics(12, 1, 0.0043);

    private final Battery bS1 = new Battery(200000, 50);
    private final Battery bS2 = new Battery(200000, 90);
    private final Battery bS3 = new Battery(200000, 10);
    private final Battery bS4 = new Battery(200000, 80);

    private final Scooter scooter1 = new Scooter("s000", "CITY", viS, vcS, bS1, 50);
    private final Scooter scooter2 = new Scooter("s001", "city", viS, vcS, bS2, 50);
    private final Scooter scooter3 = new Scooter("s002", "city", viS, vcS, bS3, 50);
    private final Scooter scooter4 = new Scooter("s003", "city", viS, vcS, bS4, 50);
    private final Scooter scooter5 = new Scooter("s004", "city", viS, vcS, bS4, 50);

    @SuppressWarnings("unchecked")
    public ScooterRecommendationTest() {
        this.testGraphEnergy = new Graph<>(true);

        try {

            Properties properties
                    = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }
        sdb = new VehicleDB();
        ldb = new LocationDB();
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
        locationList.add(park1);
        locationList.add(poi3);
        locationList.add(park3);
        locationList.add(park4);
        locationList.add(poi1);
        locationList.add(poi2);
        locationList.add(park5);

        //Adds locations to vertex
        locationList.forEach((local) -> {
            testGraphEnergy.insertVertex(local);
        });

        //Adds paths to edge
        double lat1 = park1.getLatitude();
        double long1 = park1.getLongitude();
        double lat2 = poi3.getLatitude();
        double long2 = poi3.getLongitude();
        double lat3 = park3.getLatitude();
        double long3 = park3.getLongitude();
        double lat4 = park4.getLatitude();
        double long4 = park4.getLongitude();
        double lat5 = poi1.getLatitude();
        double long5 = poi1.getLongitude();
        double lat6 = poi2.getLatitude();
        double long6 = poi2.getLongitude();
        double lat7 = park5.getLatitude();
        double long7 = park5.getLongitude();

        testGraphEnergy.insertEdge(poi2, poi1, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat5, long6, long5, poi2.getElevation(), poi1.getElevation(), scooter1.getFrontalArea(),
                        path1.getWindSpeed(), path1.getWindDirection(), path1.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(poi2, park4, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat4, long6, long4, poi2.getElevation(), park4.getElevation(), scooter1.getFrontalArea(),
                        path2.getWindSpeed(), path2.getWindDirection(), path2.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(poi2, park3, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat3, long6, long3,poi2.getElevation(), park3.getElevation(), scooter1.getFrontalArea(),
                        path3.getWindSpeed(), path3.getWindDirection(), path3.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(poi2, poi3, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat2, long6, long2,poi2.getElevation(), poi3.getElevation(), scooter1.getFrontalArea(),
                        path4.getWindSpeed(), path4.getWindDirection(), path4.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(poi3, poi2, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat2, lat6, long2, long6, poi3.getElevation(),poi2.getElevation(), scooter1.getFrontalArea(),
                        path5.getWindSpeed(), path5.getWindDirection(), path5.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(poi2, park5, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat7, long6, long7, poi2.getElevation(),park5.getElevation(), scooter1.getFrontalArea(),
                        path6.getWindSpeed(), path6.getWindDirection(), path6.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(park1, poi3, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat1, lat2, long1, long2,park1.getElevation(), poi3.getElevation(), scooter1.getFrontalArea(),
                        path7.getWindSpeed(), path7.getWindDirection(), path7.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(poi3, park4, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat2, lat4, long2, long4,poi3.getElevation(), park4.getElevation(), scooter1.getFrontalArea(),
                        path8.getWindSpeed(), path8.getWindDirection(), path8.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(park4, park3, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat4, lat3, long4, long3, park4.getElevation(),park3.getElevation(), scooter1.getFrontalArea(),
                        path9.getWindSpeed(), path9.getWindDirection(), path9.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(park5, park1, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat7, lat1, long7, long1,park5.getElevation(), park1.getElevation(), scooter1.getFrontalArea(),
                        path10.getWindSpeed(), path10.getWindDirection(), path10.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(poi3, poi1, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat2, lat5, long2, long5, poi3.getElevation(),poi1.getElevation(), scooter1.getFrontalArea(),
                        path11.getWindSpeed(), path11.getWindDirection(), path12.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(park5, poi2, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat7, lat6, long7, long6, park5.getElevation(),poi2.getElevation(), scooter1.getFrontalArea(),
                        path12.getWindSpeed(), path12.getWindDirection(), path12.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(poi1, park4, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat5, lat4, long5, long4,poi1.getElevation(), park4.getElevation(), scooter1.getFrontalArea(),
                        path13.getWindSpeed(), path13.getWindDirection(), path13.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        testGraphEnergy.insertEdge(poi1, poi3, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat5, lat2, long5, long2,poi1.getElevation(), poi3.getElevation(), scooter1.getFrontalArea(),
                        path14.getWindSpeed(), path14.getWindDirection(), path14.getkinetic_coefficient(),
                        scooter1.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter1.getWeight())));

        scooter1.getVehicleInfos().setParkID("001");
        scooter2.getVehicleInfos().setParkID("001");
        scooter3.getVehicleInfos().setParkID("001");
        scooter4.getVehicleInfos().setParkID("001");
        scooter5.getVehicleInfos().setParkID("001");
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Method to test the recommendateScooter
     *
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void recommendateScooterTest() throws IOException, SQLException {
        System.out.println("recommendateScooterTest");

        LinkedList<Location> poiList = new LinkedList<>();
        Location source = park1;
        Location dest = park4;
        poiList.add(poi2);
        sdb.ClearDatabase();

        try {
            sdb.ClearDatabase();
            apc = new AddParkController();
            asc = new AddScooterController();

            apc.addPark(park1);
            apc.addPark(park3);
            apc.addPark(park4);
            apc.addPark(park5);

            asc.addScooter(scooter1);
            asc.addScooter(scooter2);
            asc.addScooter(scooter3);
            asc.addScooter(scooter4);
            asc.addScooter(scooter5);

            Scooter result = instance.recommendateScooter(source, dest, testGraphEnergy, poiList);

            Scooter expResult = scooter4;
            assertEquals(result.getVehicleID(), expResult.getVehicleID());
            assertEquals(result.getTypeOfScooter(), expResult.getTypeOfScooter());
            assertEquals(result.getVehicleInfos().getParkID(), expResult.getVehicleInfos().getParkID());
            assertEquals(result.getVehicleInfos().getVehicleStatus(), expResult.getVehicleInfos().getVehicleStatus());
            assertEquals(result.getVehicleCharacteristics().getAerodynamicCoefficient(), expResult.getVehicleCharacteristics().getAerodynamicCoefficient());
            assertEquals(result.getVehicleCharacteristics().getFrontalArea(), expResult.getVehicleCharacteristics().getFrontalArea());
            assertEquals(result.getVehicleCharacteristics().getWeight(), expResult.getVehicleCharacteristics().getWeight());
            assertEquals(result.getBattery().getActualBatt(), expResult.getBattery().getActualBatt());
            assertEquals(result.getBattery().getMaxBatt(), expResult.getBattery().getMaxBatt());
            assertEquals(result.getMotor(), expResult.getMotor());
            assertNotNull(result);
            assertNotNull(expResult);

            //-------------Without PoiList-------------//
            
            poiList.clear();

            result = instance.recommendateScooter(source, dest, testGraphEnergy, poiList);

            assertEquals(expResult.getVehicleID(), result.getVehicleID());
            assertEquals(result.getTypeOfScooter(), expResult.getTypeOfScooter());
            assertEquals(result.getVehicleInfos().getParkID(), expResult.getVehicleInfos().getParkID());
            assertEquals(result.getVehicleInfos().getVehicleStatus(), expResult.getVehicleInfos().getVehicleStatus());
            assertEquals(result.getVehicleCharacteristics().getAerodynamicCoefficient(), expResult.getVehicleCharacteristics().getAerodynamicCoefficient());
            assertEquals(result.getVehicleCharacteristics().getFrontalArea(), expResult.getVehicleCharacteristics().getFrontalArea());
            assertEquals(result.getVehicleCharacteristics().getWeight(), expResult.getVehicleCharacteristics().getWeight());
            assertEquals(result.getBattery().getActualBatt(), expResult.getBattery().getActualBatt());
            assertEquals(result.getBattery().getMaxBatt(), expResult.getBattery().getMaxBatt());
            assertEquals(result.getMotor(), expResult.getMotor());
            assertNotNull(result);
            assertNotNull(expResult);

            //-------------Without Dest-------------//
            poiList.add(poi2);
            dest = nullPark;

            result = instance.recommendateScooter(source, dest, testGraphEnergy, poiList);
            expResult = scooter2;

            assertEquals(expResult.getVehicleID(), result.getVehicleID());
            assertEquals(result.getTypeOfScooter(), expResult.getTypeOfScooter());
            assertEquals(result.getVehicleInfos().getParkID(), expResult.getVehicleInfos().getParkID());
            assertEquals(result.getVehicleInfos().getVehicleStatus(), expResult.getVehicleInfos().getVehicleStatus());
            assertEquals(result.getVehicleCharacteristics().getAerodynamicCoefficient(), expResult.getVehicleCharacteristics().getAerodynamicCoefficient());
            assertEquals(result.getVehicleCharacteristics().getFrontalArea(), expResult.getVehicleCharacteristics().getFrontalArea());
            assertEquals(result.getVehicleCharacteristics().getWeight(), expResult.getVehicleCharacteristics().getWeight());
            assertEquals(result.getBattery().getActualBatt(), expResult.getBattery().getActualBatt());
            assertEquals(result.getBattery().getMaxBatt(), expResult.getBattery().getMaxBatt());
            assertEquals(result.getMotor(), expResult.getMotor());
            assertNotNull(result);
            assertNotNull(expResult);

            //-------------Without source-------------//
            source = nullPark;
            dest = park4;

            result = instance.recommendateScooter(source, dest, testGraphEnergy, poiList);

            assertNull(result);

            //-------------Source Poi-------------//
            source = poi1;

            result = instance.recommendateScooter(source, dest, testGraphEnergy, poiList);

            assertNull(result);

            //-------------Dest Poi-------------//
            source = park1;
            dest = poi1;

            result = instance.recommendateScooter(source, dest, testGraphEnergy, poiList);

            assertNull(result);

            //With poiList but scooterList empty//
            scooter1.getVehicleInfos().setLatPark(41.15068);
            scooter1.getVehicleInfos().setLongPark(-8.61053);
            scooter2.getVehicleInfos().setLatPark(41.15068);
            scooter2.getVehicleInfos().setLongPark(-8.61053);
            scooter3.getVehicleInfos().setLatPark(41.15068);
            scooter3.getVehicleInfos().setLongPark(-8.61053);
            scooter4.getVehicleInfos().setLatPark(41.15068);
            scooter4.getVehicleInfos().setLongPark(-8.61053);
            scooter5.getVehicleInfos().setLatPark(41.15068);
            scooter5.getVehicleInfos().setLongPark(-8.61053);

            sdb.updateScooter(scooter1);
            sdb.updateScooter(scooter2);
            sdb.updateScooter(scooter3);
            sdb.updateScooter(scooter4);
            sdb.updateScooter(scooter5);

            source = park1;
            dest = park4;

            result = instance.recommendateScooter(source, dest, testGraphEnergy, poiList);

            assertNull(result);

            //Without dest but scooterList empty//
            dest = nullPark;

            result = instance.recommendateScooter(source, dest, testGraphEnergy, poiList);

            assertNull(result);

            //Without poiList but scooterList empty//
            dest = park4;
            poiList.clear();

            result = instance.recommendateScooter(source, dest, testGraphEnergy, poiList);

            assertNull(result);

            sdb.ClearDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sdb.ClearDatabase();
        }
    }

    /**
     * Method to test the compareBattery
     */
    @Test

    public void compareBatteryTest() {
        List<Scooter> result = new ArrayList<>();
        result.add(scooter1);
        result.add(scooter2);
        result.add(scooter3);
        result.add(scooter4);
        result.add(scooter5);
        Collections.sort(result, new ScooterRecommendation.compareBattery());

        List<Scooter> expResult = new ArrayList<>();
        expResult.add(scooter3);
        expResult.add(scooter1);
        expResult.add(scooter4);
        expResult.add(scooter5);
        expResult.add(scooter2);

        assertEquals(result, expResult);
    }
    
    /**
     * Method to test the compareBattery
     */
    @Test

    public void compareBatteryTest2() {
        List<Scooter> result = new ArrayList<>();
        result.add(scooter1);
        result.add(scooter2);
        result.add(scooter3);
        Collections.sort(result, new ScooterRecommendation.compareBattery());

        List<Scooter> expResult = new ArrayList<>();
        expResult.add(scooter3);
        expResult.add(scooter1);
        expResult.add(scooter2);

        assertEquals(result, expResult);
    }

    /**
     * Method to test the compareBatteryWithoutDest
     */
    @Test

    public void compareBatteryWithoutDestTest() {
        List<Scooter> result = new ArrayList<>();
        result.add(scooter1);
        result.add(scooter2);
        result.add(scooter3);
        result.add(scooter4);
        result.add(scooter5);
        Collections.sort(result, new ScooterRecommendation.compareBatteryWithoutDest());

        List<Scooter> expResult = new ArrayList<>();
        expResult.add(scooter2);
        expResult.add(scooter4);
        expResult.add(scooter5);
        expResult.add(scooter1);
        expResult.add(scooter3);

        assertEquals(result, expResult);
    }
}
