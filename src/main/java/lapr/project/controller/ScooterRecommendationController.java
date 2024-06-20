/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import lapr.project.model.Location.Location;
import lapr.project.model.ScooterRecommendation;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.utils.graphbase.Graph;

/**
 *
 * @author Utilizador
 */
public class ScooterRecommendationController {

    public ScooterRecommendationController () throws IOException, SQLException {
    }

    public Scooter recommendateScooter(Location source, Location dest, Graph<Location, Double> mainGraph, List<Location> poiList) {
        ScooterRecommendation sr = new ScooterRecommendation();
        Scooter s = sr.recommendateScooter(source, dest, mainGraph, poiList);
        if (s == null) {
            return new Scooter();
        }
        return s;
    }
}
