///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package lapr.project.model;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
//import lapr.project.data.VehicleDB;
//import lapr.project.model.Location.Location;
//import lapr.project.model.Location.PointOfInterest;
//import lapr.project.model.Vehicle.Scooter;
//import lapr.project.utils.graphbase.Graph;
//
///**
// *
// * @author Utilizador
// */
//public class ScootersCapableMakingJourney {
//
//    public ScootersCapableMakingJourney() {
//    }
//
//    public List<Scooter> capableScooters(Location source, Location dest, Graph<Location, Double> mainGraph, List<Location> poiList) {
//
//        if (source == null || source instanceof PointOfInterest || dest instanceof PointOfInterest) {
//            return null;
//        }
//        VehicleDB vdb = new VehicleDB();
//        ShortestPaths sp = new ShortestPaths();
//        List<Scooter> scooterList = vdb.getAllScooter();
//        List<Scooter> capableScooter = new ArrayList<>();
//
//        if (poiList.isEmpty()) {
//            LinkedList parkList = new LinkedList<>();
//            Route route = sp.shortestPath2Parks(source, dest, parkList, mainGraph);
//            scooterList.stream().filter((s) -> (s.getBattery().getActualBatt() * s.getBattery().getMaxBatt() / 100 > route.getCost() * 1.1)).forEachOrdered((s) -> {
//                capableScooter.add(s);
//            });
//            return capableScooter;
//        }
//        Route route = sp.shortestPathPOI(source, dest, mainGraph, poiList);
//        scooterList.stream().filter((s) -> (s.getBattery().getActualBatt() * s.getBattery().getMaxBatt() / 100 > route.getCost() * 1.1)).forEachOrdered((s) -> {
//            capableScooter.add(s);
//        });
//        return capableScooter;
//    }
//}
