/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.time.LocalDateTime;

/**
 *
 * @author Nuno Capela
 */
public class Trip {
    private int tripId;
    private String username;
    private String vehicleId;
    private String startPark;
    private String endingPark;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor with parameters 
     * @param tripId
     * @param username
     * @param vehicleId
     * @param startParkId
     * @param endingPark
     * @param startTime
     * @param endTime
     */
    public Trip(int tripId, String username, String vehicleId, String startParkId, String endingPark, LocalDateTime startTime, LocalDateTime endTime) {
        this.tripId = tripId;
        this.username = username;
        this.vehicleId = vehicleId;
        this.startPark = startParkId;
        this.endingPark = endingPark;
        this.startTime = startTime;
        this.endTime = endTime;
    }
 
    /**
     * Method that returns the trip ID
     * @return the trip ID
     */
    public int getTripId() {
        return tripId;
    }

    /**
     * Method that updates the trip ID 
     * @param tripId new trip ID
     */
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    /**
     * Mehod that returns the username 
     * @return the username 
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method that returns the vehicle ID
     * @return the vehicle ID
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * Method that updates the vehicle ID
     * @param vehicleId new vehicle ID
     */
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Method that returns the start park
     * @return the start park 
     */
    public String getStartPark() {
        return startPark;
    }

    /**
     * Method that updates the start park 
     * @param startPark new start park 
     */
    public void setStartPark(String startPark) {
        this.startPark = startPark;
    }

    /**
     * Method that returns the ending park 
     * @return the ending park 
     */
    public String getEndingPark() {
        return endingPark;
    }

    /**
     * Method that updates the ending park
     * @param endingPark new ending park 
     */
    public void setEndingPark(String endingPark) {
        this.endingPark = endingPark;
    }

    /**
     * Method that returns the start time 
     * @return the start time 
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Method that updates the start time 
     * @param startTime new start time 
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Method that returns the time that the trip ends 
     * @return the end time 
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Method that updates the time that the trip ends
     * @param endTime new end time 
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Trip:" + "\ntripId=" + tripId + ", \nusername=" + username + ", \nvehicleId=" + vehicleId + 
                ", \nparkId=" + startPark + ", \nendingPark=" + endingPark + ", \nstartTime=" + 
                startTime + ", \nendTime=" + endTime ;
    }
    
}
