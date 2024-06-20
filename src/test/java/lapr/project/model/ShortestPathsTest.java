/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.time.LocalDateTime;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.Location;
import lapr.project.model.Location.PointOfInterest;
import lapr.project.model.Location.ParkCharger;
import lapr.project.model.Location.Capacity;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import lapr.project.model.Vehicle.Battery;
import lapr.project.model.Vehicle.Bicycle;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import lapr.project.utils.graphbase.Graph;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author Nuno Capela
 */
public class ShortestPathsTest {

    @SuppressWarnings("unchecked")
    private final Graph<Location, Double> testGraph;
    private final Graph<Location, Double> testGraphCal;
    private final Graph<Location, Double> testGraphEnergy;
    private final LinkedList<Location> parkList = new LinkedList<>();
    private final List<Location> locationList = new ArrayList<>();

    private final Capacity capacity = new Capacity(20, 10);
    private final ParkCharger charger = new ParkCharger(120, 120);

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
    //Creating Bike
    private final VehicleInfos viB = new VehicleInfos("001", "AVAILABLE");
    private final VehicleCharacteristics vcB = new VehicleCharacteristics(8, 1, 1.5);
    private final Bicycle bike = new Bicycle("b000", viB, vcB, 10);
    //Creating Scooter
    private final VehicleInfos viS = new VehicleInfos("002", "AVAILABLE", LocalDateTime.of(2019, 12, 13, 12, 0));
    private final VehicleCharacteristics vcS = new VehicleCharacteristics(20, 1, 2);
    private final Battery bS = new Battery(1000, 500);
    private final Scooter scooter = new Scooter("s000", "city", viS, vcS, bS, 50);

    @SuppressWarnings("unchecked")
    public ShortestPathsTest() {
        this.testGraph = new Graph<>(true);
        this.testGraphCal = new Graph<>(true);
        this.testGraphEnergy = new Graph<>(true);
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
            testGraph.insertVertex(local);
            testGraphCal.insertVertex(local);
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
        testGraph.insertEdge(poi2, poi1, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat6, long6, lat5, long5, poi2.getElevation(), poi1.getElevation()));
        testGraph.insertEdge(poi2, park4, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat6, long6, lat4, long4, poi2.getElevation(), park4.getElevation()));
        testGraph.insertEdge(poi2, park3, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat6, long6, lat3, long3, poi2.getElevation(), park3.getElevation()));
        testGraph.insertEdge(poi2, poi3, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat6, long6, lat2, long2, poi2.getElevation(), poi3.getElevation()));
        testGraph.insertEdge(poi3, poi2, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat2, long2, lat6, long6, poi3.getElevation(), poi2.getElevation()));
        testGraph.insertEdge(poi2, park5, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat6, long6, lat7, long7, poi2.getElevation(), park5.getElevation()));
        testGraph.insertEdge(park1, poi3, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat1, long1, lat2, long2, park1.getElevation(), poi3.getElevation()));
        testGraph.insertEdge(poi3, park4, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat2, long2, lat4, long4, poi3.getElevation(), park4.getElevation()));
        testGraph.insertEdge(park4, park3, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat4, long4, lat3, long3, park4.getElevation(), park3.getElevation()));
        testGraph.insertEdge(park5, park1, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat7, long7, lat1, long1, park5.getElevation(), park1.getElevation()));
        testGraph.insertEdge(poi3, poi1, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat2, long2, lat5, long5, poi3.getElevation(), poi1.getElevation()));
        testGraph.insertEdge(poi1, park4, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat5, long5, lat4, long4, poi1.getElevation(), park4.getElevation()));
        testGraph.insertEdge(poi1, poi3, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat5, long5, lat2, long2, poi1.getElevation(), poi3.getElevation()));
        testGraph.insertEdge(park5, poi2, 1.0, PhysicsAlgorithms.distanceBetweenPoints(lat7, long7, lat6, long6, park5.getElevation(), poi2.getElevation()));
        //--------------------------------------------------Graph CAL---------------------------------------------------------------//
        testGraphCal.insertEdge(poi2, poi1, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat5, long6, long5, poi2.getElevation(), poi1.getElevation(), bike.getFrontalArea(),
                        path1.getWindSpeed(), path1.getWindDirection(), path1.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(poi2, park4, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat4, long6, long4, poi2.getElevation(), park4.getElevation(), bike.getFrontalArea(),
                        path2.getWindSpeed(), path2.getWindDirection(), path2.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(poi2, park3, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat3, long6, long3, poi2.getElevation(), park3.getElevation(), bike.getFrontalArea(),
                        path3.getWindSpeed(), path3.getWindDirection(), path3.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(poi2, poi3, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat2, long6, long2, poi2.getElevation(), poi3.getElevation(), bike.getFrontalArea(),
                        path4.getWindSpeed(), path4.getWindDirection(), path4.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(poi3, poi2, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat2, lat6, long2, long6, poi3.getElevation(), poi2.getElevation(), bike.getFrontalArea(),
                        path5.getWindSpeed(), path5.getWindDirection(), path5.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(poi2, park5, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat7, long6, long7, poi2.getElevation(), park5.getElevation(), bike.getFrontalArea(),
                        path6.getWindSpeed(), path6.getWindDirection(), path6.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(park1, poi3, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat1, lat2, long1, long2, park1.getElevation(), poi3.getElevation(), bike.getFrontalArea(),
                        path7.getWindSpeed(), path7.getWindDirection(), path7.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(poi3, park4, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat2, lat4, long2, long4, poi3.getElevation(), park4.getElevation(), bike.getFrontalArea(),
                        path8.getWindSpeed(), path8.getWindDirection(), path8.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(park4, park3, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat4, lat3, long4, long3, park4.getElevation(), park3.getElevation(), bike.getFrontalArea(),
                        path9.getWindSpeed(), path9.getWindDirection(), path9.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(park5, park1, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat7, lat1, long7, long1, park5.getElevation(), park1.getElevation(), bike.getFrontalArea(),
                        path10.getWindSpeed(), path10.getWindDirection(), path10.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(poi3, poi1, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat2, lat5, long2, long5, poi3.getElevation(), poi1.getElevation(), bike.getFrontalArea(),
                        path11.getWindSpeed(), path11.getWindDirection(), path12.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(park5, poi2, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat7, lat6, long7, long6, park5.getElevation(), poi2.getElevation(), bike.getFrontalArea(),
                        path12.getWindSpeed(), path12.getWindDirection(), path12.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(poi1, park4, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat5, lat4, long5, long4, poi1.getElevation(), park4.getElevation(), bike.getFrontalArea(),
                        path13.getWindSpeed(), path13.getWindDirection(), path13.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));

        testGraphCal.insertEdge(poi1, poi3, 1.0,
                PhysicsAlgorithms.conversionToCalories(PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat5, lat2, long5, long2, poi1.getElevation(), poi3.getElevation(), bike.getFrontalArea(),
                        path14.getWindSpeed(), path14.getWindDirection(), path14.getkinetic_coefficient(),
                        bike.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), bike.getWeight())*0.7));
        //--------------------------------------------------Graph J-----------------------------------------------------------------//
        testGraphEnergy.insertEdge(poi2, poi1, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat5, long6, long5, poi2.getElevation(), poi1.getElevation(), scooter.getFrontalArea(),
                        path1.getWindSpeed(), path1.getWindDirection(), path1.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(poi2, park4, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat4, long6, long4, poi2.getElevation(), park4.getElevation(), scooter.getFrontalArea(),
                        path2.getWindSpeed(), path2.getWindDirection(), path2.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(poi2, park3, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat3, long6, long3, poi2.getElevation(), park3.getElevation(), scooter.getFrontalArea(),
                        path3.getWindSpeed(), path3.getWindDirection(), path3.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(poi2, poi3, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat2, long6, long2, poi2.getElevation(), poi3.getElevation(), scooter.getFrontalArea(),
                        path4.getWindSpeed(), path4.getWindDirection(), path4.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(poi3, poi2, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat2, lat6, long2, long6, poi3.getElevation(), poi2.getElevation(), scooter.getFrontalArea(),
                        path5.getWindSpeed(), path5.getWindDirection(), path5.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(poi2, park5, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat6, lat7, long6, long7, poi2.getElevation(), park5.getElevation(), scooter.getFrontalArea(),
                        path6.getWindSpeed(), path6.getWindDirection(), path6.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(park1, poi3, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat1, lat2, long1, long2, park1.getElevation(), poi3.getElevation(), scooter.getFrontalArea(),
                        path7.getWindSpeed(), path7.getWindDirection(), path7.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(poi3, park4, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat2, lat4, long2, long4, poi3.getElevation(), park4.getElevation(), scooter.getFrontalArea(),
                        path8.getWindSpeed(), path8.getWindDirection(), path8.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(park4, park3, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat4, lat3, long4, long3, park4.getElevation(), park3.getElevation(), scooter.getFrontalArea(),
                        path9.getWindSpeed(), path9.getWindDirection(), path9.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(park5, park1, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat7, lat1, long7, long1, park5.getElevation(), park1.getElevation(), scooter.getFrontalArea(),
                        path10.getWindSpeed(), path10.getWindDirection(), path10.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(poi3, poi1, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat2, lat5, long2, long5, poi3.getElevation(), poi1.getElevation(), scooter.getFrontalArea(),
                        path11.getWindSpeed(), path11.getWindDirection(), path12.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(park5, poi2, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat7, lat6, long7, long6, park5.getElevation(), poi2.getElevation(), scooter.getFrontalArea(),
                        path12.getWindSpeed(), path12.getWindDirection(), path12.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(poi1, park4, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat5, lat4, long5, long4, poi1.getElevation(), park4.getElevation(), scooter.getFrontalArea(),
                        path13.getWindSpeed(), path13.getWindDirection(), path13.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));

        testGraphEnergy.insertEdge(poi1, poi3, 1.0,
                (PhysicsAlgorithms.calculateEnergyBetweenPoints(
                        lat5, lat2, long5, long2, poi1.getElevation(), poi3.getElevation(), scooter.getFrontalArea(),
                        path14.getWindSpeed(), path14.getWindDirection(), path14.getkinetic_coefficient(),
                        scooter.getAerodynamicCoefficient(), user.getCyclingAverageSpeed(), user.getWeight(), scooter.getWeight())));
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of shortestPath2Parks method, of class ShortestPaths.
     */
    @Test
    public void testShortestPath2Parks() {
        //----------------------------- PATH KM -----------------------------//
        System.out.println("shortestPath2Parks");

        Location source = park1;
        Location dest = park4;

        ShortestPaths instance = new ShortestPaths();

        LinkedList<Location> expResult = new LinkedList<>();
        Route result;

        expResult.add(park1);
        expResult.add(poi3);
        expResult.add(park4);

        result = instance.shortestPath2Parks(source, dest, parkList, testGraph);
        Route expResult1 = new Route(park1, park4, expResult, 0.94);

        assertEquals(expResult1.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult1.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult1.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult1.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult1.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult1.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult1.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult1.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult1.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult1.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult1.getCost(), result.getCost(), 0.01);
        assertEquals(expResult1.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult1);

        //Testing if source is not a park
        source = poi1;
        dest = park5;

        expResult.clear();
        parkList.clear();

        result = instance.shortestPath2Parks(source, dest, parkList, testGraph);
        Route expResult2 = new Route();
        assertEquals(expResult2.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult2.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult2.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult2.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult2.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult2.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult2.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult2.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult2.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult2.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult2.getCost(), result.getCost());
        assertEquals(expResult2.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult2);

        //Testing if dest is not a park
        source = park5;
        dest = poi1;

        result = instance.shortestPath2Parks(source, dest, parkList, testGraph);
        assertEquals(expResult2.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult2.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult2.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult2.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult2.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult2.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult2.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult2.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult2.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult2.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult2.getCost(), result.getCost());
        assertEquals(expResult2.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult2);

        //Testing if source is equal to dest
        source = locationList.get(0);
        dest = locationList.get(0);

        expResult.add(park1);
        result = instance.shortestPath2Parks(source, dest, parkList, testGraph);
        Route expResult3 = new Route(source, source, expResult, 0);

        assertEquals(expResult3.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult3.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult3.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult3.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult3.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult3.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult3.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult3.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult3.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult3.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult3.getCost(), result.getCost());
        assertEquals(expResult3.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult3);

        //Testing if source is null
        expResult.clear();
        parkList.clear();

        source = nullPark;
        dest = park1;

        result = instance.shortestPath2Parks(source, dest, parkList, testGraph);
        assertEquals(expResult2.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult2.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult2.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult2.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult2.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult2.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult2.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult2.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult2.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult2.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult2.getCost(), result.getCost());
        assertEquals(expResult2.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult2);

        //Testing if dest is null
        expResult.clear();
        parkList.clear();

        source = park1;
        dest = nullPark;

        result = instance.shortestPath2Parks(source, dest, parkList, testGraph);

        assertEquals(expResult2.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult2.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult2.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult2.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult2.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult2.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult2.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult2.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult2.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult2.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult2.getCost(), result.getCost());
        assertEquals(expResult2.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult2);

        //----------------------------- PATH CAL -----------------------------//
        source = park1;
        dest = park4;

        result = instance.shortestPath2Parks(source, dest, parkList, testGraphCal);

        expResult.add(park1);
        expResult.add(poi3);
        expResult.add(park4);
        Route expResult4 = new Route(source, dest, expResult, 22320.61);

        assertEquals(expResult4.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult4.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult4.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult4.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult4.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult4.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult4.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult4.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult4.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult4.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult4.getCost(), result.getCost(), 0.01);
        assertEquals(expResult4.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult4);

        //------------------------------ PATH J ------------------------------//
        expResult.clear();
        parkList.clear();

        result = instance.shortestPath2Parks(source, dest, parkList, testGraphEnergy);

        expResult.add(park1);
        expResult.add(poi3);
        expResult.add(park4);

        Route expResult5 = new Route(source, dest, expResult, 156807.32);

        assertEquals(expResult5.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult5.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult5.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult5.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult5.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult5.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult5.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult5.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult5.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult5.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult5.getCost(), result.getCost(), 0.01);
        assertEquals(expResult5.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult5);

    }

    /**
     * Test of shortestPathPOI method, of class ShortestPaths.
     */
    @Test
    public void testShortestPathPOI() {
        System.out.println("shortestPathPOI");
        //Testing shortestPathPOI with one POI
        Location source = park1;
        Location dest = park4;
        LinkedList<Location> poiList = new LinkedList<>();
        poiList.add(poi2);

        ShortestPaths instance = new ShortestPaths();
        LinkedList<Location> expResult = new LinkedList<>();
        expResult.add(park1);
        expResult.add(poi3);
        expResult.add(poi2);
        expResult.add(park4);

        Route result = instance.shortestPathPOI(source, dest, testGraph, poiList);
        Route expResult1 = new Route(source, dest, expResult, 1.04);

        assertEquals(expResult1.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult1.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult1.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult1.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult1.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult1.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult1.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult1.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult1.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult1.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult1.getCost(), result.getCost(), 0.01);
        assertEquals(expResult1.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult1);

        //Testing shortestPathPOI with two POI
        expResult.clear();
        poiList.clear();

        source = park5;
        dest = park3;

        poiList.add(poi1);
        poiList.add(poi3);

        expResult.add(park5);
        expResult.add(poi2);
        expResult.add(poi1);
        expResult.add(poi3);
        expResult.add(poi2);
        expResult.add(park3);

        result = instance.shortestPathPOI(source, dest, testGraph, poiList);
        Route expResult2 = new Route(source, dest, expResult, 6.60);

        assertEquals(expResult2.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult2.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult2.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult2.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult2.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult2.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult2.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult2.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult2.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult2.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult2.getCost(), result.getCost(), 0.01);
        assertEquals(expResult2.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult2);

        //Testing if source is not a Park
        expResult.clear();

        source = poi1;

        result = instance.shortestPathPOI(source, dest, testGraph, poiList);
        Route expResult3 = new Route();

        assertEquals(expResult3.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult3.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult3.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult3.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult3.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult3.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult3.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult3.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult3.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult3.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult3.getCost(), result.getCost(), 0.01);
        assertEquals(expResult3.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult3);

        //Testing if dest is not a Park
        source = park1;
        dest = poi1;

        result = instance.shortestPathPOI(source, dest, testGraph, poiList);

        assertEquals(expResult3.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult3.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult3.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult3.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult3.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult3.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult3.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult3.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult3.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult3.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult3.getCost(), result.getCost(), 0.01);
        assertEquals(expResult3.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult3);

        //Testing if source is null   
        source = nullPark;
        dest = park1;

        result = instance.shortestPathPOI(source, dest, testGraph, poiList);

        assertEquals(expResult3.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult3.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult3.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult3.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult3.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult3.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult3.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult3.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult3.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult3.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult3.getCost(), result.getCost(), 0.01);
        assertEquals(expResult3.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult3);

        //Testing if dest is null
        source = park1;
        dest = nullPark;

        result = instance.shortestPathPOI(source, dest, testGraph, poiList);

        assertEquals(expResult3.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult3.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult3.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult3.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult3.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult3.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult3.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult3.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult3.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult3.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult3.getCost(), result.getCost(), 0.01);
        assertEquals(expResult3.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult3);

        //Testing if dest is the same of source
        dest = park1;

        result = instance.shortestPathPOI(source, dest, testGraph, poiList);

        expResult.add(park1);
        Route expResult4 = new Route(source, dest, expResult, 0);

        assertEquals(expResult4.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult4.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult4.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult4.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult4.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult4.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult4.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult4.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult4.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult4.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult4.getCost(), result.getCost(), 0.01);
        assertEquals(expResult4.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult4);

        //Testing if poiList is empty
        expResult.clear();
        poiList.clear();

        dest = park4;

        result = instance.shortestPathPOI(source, dest, testGraph, poiList);

        assertEquals(expResult3.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult3.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult3.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult3.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult3.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult3.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult3.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult3.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult3.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult3.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult3.getCost(), result.getCost(), 0.01);
        assertEquals(expResult3.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult3);

        //----------------------------- PATH CAL -----------------------------//
        source = park5;
        dest = park3;

        poiList.add(poi1);
        poiList.add(poi3);

        expResult.add(park5);
        expResult.add(poi2);
        expResult.add(poi1);
        expResult.add(poi3);
        expResult.add(poi2);
        expResult.add(park3);

        result = instance.shortestPathPOI(source, dest, testGraphCal, poiList);
        Route expResult5 = new Route(source, dest, expResult, 17979.27);

        assertEquals(expResult5.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult5.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult5.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult5.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult5.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult5.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult5.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult5.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult5.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult5.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult5.getCost(), result.getCost(), 0.01);
        assertEquals(expResult5.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult5);

        //------------------------------ PATH J ------------------------------//
        expResult.clear();

        expResult.add(park5);
        expResult.add(poi2);
        expResult.add(poi1);
        expResult.add(poi3);
        expResult.add(poi2);
        expResult.add(park3);

        result = instance.shortestPathPOI(source, dest, testGraphEnergy, poiList);
        Route expResult6 = new Route(source, dest, expResult, 126419.93);

        assertEquals(expResult6.getSource().getDescription(), result.getSource().getDescription());
        assertEquals(expResult6.getSource().getElevation(), result.getSource().getElevation());
        assertEquals(expResult6.getSource().getLatitude(), result.getSource().getLatitude());
        assertEquals(expResult6.getSource().getLocationId(), result.getSource().getLocationId());
        assertEquals(expResult6.getSource().getLongitude(), result.getSource().getLongitude());
        assertEquals(expResult6.getDest().getDescription(), result.getDest().getDescription());
        assertEquals(expResult6.getDest().getElevation(), result.getDest().getElevation());
        assertEquals(expResult6.getDest().getLatitude(), result.getDest().getLatitude());
        assertEquals(expResult6.getDest().getLocationId(), result.getDest().getLocationId());
        assertEquals(expResult6.getDest().getLongitude(), result.getDest().getLongitude());
        assertEquals(expResult6.getCost(), result.getCost(), 0.01);
        assertEquals(expResult6.getLocations(), result.getLocations());
        assertNotNull(result);
        assertNotNull(expResult6);
    }
}
