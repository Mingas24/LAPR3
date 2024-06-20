/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Location.Location;

/**
 *
 * @author Utilizador
 */
public class Route {

    private Location source;
    private Location dest;
    private List<Location> locations;
    private double cost;

    /**
     * Constructor with parameters 
     * @param source
     * @param dest
     * @param locations
     * @param cost
     */
    public Route(Location source, Location dest, List<Location> locations, double cost) {
        this.source = source;
        this.dest = dest;
        this.locations = locations;
        this.cost = cost;
    }

    /**
     * Empty constructor
     */
    public Route() {
        this.source = new Location() {};
        this.dest = new Location() {};
        this.locations = new LinkedList<>();
        this.cost = 0;
    }

    /**
     *
     * @return
     */
    public Location getSource() {
        return source;
    }

    /**
     * Method that updates the source 
     * @param source new source 
     */
    public void setSource(Location source) {
        this.source = source;
    }

    /**
     * Method that returns the destination location
     * @return destination
     */
    public Location getDest() {
        return dest;
    }

    /**
     * Method that updated the destination location 
     * @param dest new location to be updated 
     */
    public void setDest(Location dest) {
        this.dest = dest;
    }

    /**
     * Method that returns the list of locations 
     * @return list of locations 
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * Method that updated a new list of locations 
     * @param locations list of locations to be updated 
     */
    public void setLocations(LinkedList<Location> locations) {
        this.locations = locations;
    }

    /**
     * Method that returns the cost 
     * @return the cost 
     */
    public double getCost() {
        return cost;
    }

    /**
     * Method that updates the cost 
     * @param cost to be updated 
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return  locations.toString();
    }

}
