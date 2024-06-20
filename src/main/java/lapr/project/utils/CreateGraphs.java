/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import lapr.project.data.*;
import lapr.project.model.Location.*;
import lapr.project.model.*;
import lapr.project.model.Vehicle.*;
import lapr.project.utils.graphbase.Graph;

/**
 *
 * @author Nuno Capela
 */
public class CreateGraphs {

    LocationDB ldb;
    PathDB pathDB;

    public CreateGraphs() throws IOException, SQLException {
        ldb = new LocationDB();
        pathDB = new PathDB();
    }

    /**
     * Method that creates a graph which weight is Km
     *
     * @return
     * @throws java.sql.SQLException
     */
    public Graph<Location, Double> createGraphDistance() throws SQLException {

        //Creates a new Graph object
        Graph<Location, Double> mainGraph = new Graph<>(true);

        //Access database and returns all parks and adds them to the arraylist
        ArrayList<Park> parkList = ldb.getAllParks();

        //Access database and returns all pois and adds them to the list
        List<PointOfInterest> poiList = ldb.getAllPois();

        //Creates a new list that stores the locations
        List<Location> locationList = new ArrayList<>();

        //adds all the parks to the list of locations
        for (Park p : parkList) {
            locationList.add(p);
        }
        //adds all the pois to the list of locations
        for (PointOfInterest p : poiList) {
            locationList.add(p);
        }

        //Create new vertices with every location on the list 
        for (Location l : locationList) {
            mainGraph.insertVertex(l);
        }

        //Creates a list of every paths 
        List<Path> pathList = pathDB.getAllPaths();

        Park park;
        PointOfInterest poi;

        for (Path p : pathList) {
            for (Location l1 : locationList) {
                for (Location l2 : locationList) {
                    if (p.getLocationA().equals(l1.getLocationId()) && p.getLocationB().equals(l2.getLocationId())) {
                        mainGraph.insertEdge(l1, l2, 1.0, PhysicsAlgorithms.distanceBetweenPoints(l1.getLatitude(), l1.getLongitude(), l2.getLatitude(), l2.getLongitude(), l1.getElevation(), l2.getElevation()));
                    }
                }

            }

        }
        return mainGraph;
    }

    /**
     * Method that creates a graph which weight is Calories
     *
     * @return
     * @throws java.sql.SQLException
     */
    public Graph<Location, Double> createGraphCalories() throws SQLException {
        LocationDB ldb = new LocationDB();
        PathDB pathDB = new PathDB();
        Graph<Location, Double> mainGraph = new Graph<>(true);

        RegisteredUser defaultRU = new RegisteredUser("default", "default", "default", 170, 65, 10, 123456789, "default", 0);
        VehicleInfos vib = new VehicleInfos("001", "AVAILABLE");
        VehicleCharacteristics vcb = new VehicleCharacteristics(14, 1, 0.047);
        Bicycle defaultBicycle = new Bicycle("b0000", vib, vcb, 22);

        ArrayList<Park> parkList = ldb.getAllParks();
        List<PointOfInterest> poiList = ldb.getAllPois();
        List<Location> locationList = new ArrayList<>();
        //Insert every park in the graph
        for (Park p : parkList) {
            locationList.add(p);
        }
        //Insert every POI in the graph
        for (PointOfInterest p : poiList) {
            locationList.add(p);
        }
        for (Location l : locationList) {
            mainGraph.insertVertex(l);
        }
        List<Path> pathList = pathDB.getAllPaths();

        Park park;
        PointOfInterest poi;
        for (Path p : pathList) {
            for (Location l1 : locationList) {
                for (Location l2 : locationList) {
                    if (p.getLocationA().equals(l1.getLocationId()) && p.getLocationB().equals(l2.getLocationId())) {
                        mainGraph.insertEdge(l1, l2, 1.0,
                                PhysicsAlgorithms.conversionToCalories(
                                        PhysicsAlgorithms.calculateEnergyBetweenPoints(
                                                l1.getLatitude(), l2.getLatitude(), l1.getLongitude(),
                                                l2.getLongitude(), l1.getElevation(), l2.getElevation(),
                                                defaultBicycle.getFrontalArea(), p.getWindSpeed(),
                                                p.getWindDirection(), p.getkinetic_coefficient(),
                                                defaultBicycle.getAerodynamicCoefficient(),
                                                defaultRU.getCyclingAverageSpeed(), defaultRU.getWeight(),
                                                defaultBicycle.getWeight()) * 0.7));
                    }
                }
            }
        }
        return mainGraph;
    }

    /**
     * Method that creates a graph which weight is Joules
     *
     * @return
     * @throws java.sql.SQLException
     */
    public Graph<Location, Double> createGraphEnergy() throws SQLException {
        Graph<Location, Double> mainGraph = new Graph<>(true);

        RegisteredUser defaultRU = new RegisteredUser("default", "default@test", "defaultGender", 170, 65, 10, 123456789, "defaultPWD", 0);
        VehicleInfos vis = new VehicleInfos("000", "AVAILABLE", LocalDateTime.now());
        VehicleCharacteristics vcs = new VehicleCharacteristics(12, 1, 0.047);
        Battery bat = new Battery(1000, 100);
        Scooter defaultScooter = new Scooter("s0000", "CITY", vis, vcs, bat, 250);
        ArrayList<Park> parkList = ldb.getAllParks();
        List<PointOfInterest> poiList = ldb.getAllPois();
        List<Location> locationList = new ArrayList<>();
        //Insert every park in the graph
        for (Park p : parkList) {
            locationList.add(p);
        }
        //Insert every POI in the graph
        for (PointOfInterest p : poiList) {
            locationList.add(p);
        }
        for (Location l : locationList) {
            mainGraph.insertVertex(l);
        }
        List<Path> pathList = pathDB.getAllPaths();

        Park park;
        PointOfInterest poi;
        for (Path p : pathList) {
            for (Location l1 : locationList) {
                for (Location l2 : locationList) {
                    if (p.getLocationA().equals(l1.getLocationId()) && p.getLocationB().equals(l2.getLocationId())) {
                        mainGraph.insertEdge(l1, l2, 1.0,
                                PhysicsAlgorithms.calculateEnergyBetweenPoints(
                                        l1.getLatitude(), l2.getLatitude(), l1.getLongitude(),
                                        l2.getLongitude(), l1.getElevation(), l2.getElevation(),
                                        defaultScooter.getFrontalArea(), p.getWindSpeed(),
                                        p.getWindDirection(), p.getkinetic_coefficient(),
                                        defaultScooter.getAerodynamicCoefficient(),
                                        defaultRU.getCyclingAverageSpeed(), defaultRU.getWeight(),
                                        defaultScooter.getWeight()));
                    }
                }
            }
        }
        return mainGraph;
    }
}
