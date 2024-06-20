/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Location;
import lapr.project.model.Location.PointOfInterest;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.utils.graphbase.Graph;

/**
 *
 * @author Utilizador
 */
public class ScooterRecommendation {



    public static class compareBattery implements Comparator<Scooter> {

        @Override
        public int compare(Scooter s1, Scooter s2) {
            if (s1.getActualBattery() > s2.getActualBattery()) {
                return 1;
            }
            if (s1.getActualBattery() == s2.getActualBattery()) {
                return 0;
            }
            return -1;
        }
    }

    public static class compareBatteryWithoutDest implements Comparator<Scooter> {

        @Override
        public int compare(Scooter s1, Scooter s2) {
            if (s1.getActualBattery() > s2.getActualBattery()) {
                return -1;
            }
            if (s1.getActualBattery() == s2.getActualBattery()) {
                return 0;
            }
            return 1;
        }
    }

    /**
     * method to recommend a scooter
     *
     * @param source
     * @param dest
     * @param mainGraph
     * @param poiList
     * @return recommendatedScooters.get(0)
     */
    public Scooter recommendateScooter(Location source, Location dest, Graph<Location, Double> mainGraph, List<Location> poiList) {

        if (source == null || source instanceof PointOfInterest || dest instanceof PointOfInterest) {
            return null;
        }
        VehicleDB vb = new VehicleDB();
        List<Scooter> scooterList = vb.getAllScooterInPark(source.getLocationId());
        List<Scooter> recommendatedScooters = new ArrayList<>();
        
        if (poiList.isEmpty()) {
            LinkedList<Location> parkList = new LinkedList<>();
            ShortestPaths sp = new ShortestPaths();
            Route route = sp.shortestPath2Parks(source, dest, parkList, mainGraph);
            scooterList.stream().filter((s) -> ((s.getBattery().getActualBatt() * s.getBattery().getMaxBatt()) / 100 > route.getCost() * 1.1)).forEachOrdered((s) -> {
                recommendatedScooters.add(s);
            });
            if (recommendatedScooters.isEmpty()) {
                return null;
            }
            Collections.sort(recommendatedScooters, new ScooterRecommendation.compareBattery());
            return recommendatedScooters.get(0);
        }
        
        if (dest == null) {
            if (scooterList.isEmpty()) {
                return null;
            }
            Collections.sort(scooterList, new ScooterRecommendation.compareBatteryWithoutDest());
            return scooterList.get(0);
        }
        
        ShortestPaths sp = new ShortestPaths();
        Route route = sp.shortestPathPOI(source, dest, mainGraph, poiList);
        scooterList.stream().filter((s) -> ((s.getBattery().getActualBatt() * s.getBattery().getMaxBatt()) / 100 > route.getCost() * 1.1)).forEachOrdered((s) -> {
            recommendatedScooters.add(s);
        });
        if (recommendatedScooters.isEmpty()) {
            return null;
        }
        Collections.sort(recommendatedScooters, new ScooterRecommendation.compareBattery());
        return recommendatedScooters.get(0);
    }
}
