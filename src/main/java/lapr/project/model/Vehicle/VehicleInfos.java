package lapr.project.model.Vehicle;

import java.time.LocalDateTime;

/**
 * @author diogo
 */
public class VehicleInfos {

    private double latPark;
    private double longPark;
    private String parkID;
    private String vehicleStatus;
    private LocalDateTime lastUpdateTime;

    /**
     * Empty Constructor
     */
    public VehicleInfos() {

    }

    /**
     * constructor with lat, long and lastUpdateTime
     *
     * @param latPark
     * @param longPark
     * @param vehicleStatus
     * @param lockTime
     */
    public VehicleInfos(double latPark, double longPark, String vehicleStatus, LocalDateTime lockTime) {
        this.latPark = latPark;
        this.longPark = longPark;
        this.vehicleStatus = vehicleStatus;
        this.lastUpdateTime = lockTime;
    }

    /**
     * constructor with lat and long
     *
     * @param latPark
     * @param longPark
     * @param vehicleStatus
     */
    public VehicleInfos(double latPark, double longPark, String vehicleStatus) {
        this.latPark = latPark;
        this.longPark = longPark;
        this.vehicleStatus = vehicleStatus;
    }

    /**
     * constructor with parkID
     *
     * @param parkID
     * @param vehicleStatus
     */
    public VehicleInfos(String parkID, String vehicleStatus) {
        this.parkID = parkID;
        this.vehicleStatus = vehicleStatus;
    }

    /**
     * constructor with parkID
     *
     * @param parkID
     * @param vehicleStatus
     */
    public VehicleInfos(String parkID, String vehicleStatus, LocalDateTime lockTime) {
        this.parkID = parkID;
        this.vehicleStatus = vehicleStatus;
        this.lastUpdateTime = lockTime;
    }

    /**
     *
     * @return the latitude of a park
     */
    public double getLatPark() {
        return latPark;
    }

    /**
     *
     * @return the longitude of a park
     */
    public double getLongPark() {
        return longPark;
    }

    /**
     *
     * @return the id of a park
     */
    public String getParkID() {
        return parkID;
    }

    /**
     *
     * @return the status of a vehicle
     */
    public String getVehicleStatus() {
        return vehicleStatus;
    }

    /**
     *
     * @return the lock time of a vehicle
     */
    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * Changes the latitude of a park to the given parameter
     *
     * @param latPark
     */
    public void setLatPark(double latPark) {
        this.latPark = latPark;
    }

    /**
     * Changes the longitude of a park to the given parameter
     *
     * @param longPark
     */
    public void setLongPark(double longPark) {
        this.longPark = longPark;
    }

    /**
     * Changes the id of a park to the given parameter
     *
     * @param parkID
     */
    public void setParkID(String parkID) {
        this.parkID = parkID;
    }

    /**
     * Changes the status of a vehicle to the given parameter
     *
     * @param vehicleStatus
     */
    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    /**
     * Changes the lock time of a vehicle to the given parameter
     *
     * @param lastUpdateTime
     */
    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
