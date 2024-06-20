package lapr.project.model.Vehicle;

import java.time.LocalDateTime;

/**
 *
 * @author Francisco
 */
public abstract class Vehicle {

    private String vehicleID;
    private VehicleInfos vehicleInfos;
    private VehicleCharacteristics vehicleCharacteristics;

    /**
     *
     * @param vehicleID
     * @param vehicleInfos
     * @param vehicleCharacteristics
     */
    public Vehicle(String vehicleID, VehicleInfos vehicleInfos, VehicleCharacteristics vehicleCharacteristics) {

        this.vehicleID = vehicleID;
        this.vehicleInfos = vehicleInfos;
        this.vehicleCharacteristics = vehicleCharacteristics;
    }

    /**
     * EMPTY CONSTRUCTOR
     */
    public Vehicle() {
        this.vehicleID = "";
        this.vehicleInfos = new VehicleInfos();
        this.vehicleCharacteristics = new VehicleCharacteristics();
    }

    /**
     *
     * @return the id of a vehicle
     */
    public String getVehicleID() {
        return vehicleID;
    }

    /**
     * Sets the id of a vehicle to the given parameter
     *
     * @param vehicleID
     */
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     *
     * @return the id of a park
     */
    public String getParkID() {
        return vehicleInfos.getParkID();
    }

    /**
     *
     * @return the status of a vehicle
     */
    public String getVehicleStatus() {
        return vehicleInfos.getVehicleStatus();
    }

    /**
     * Sets the vehicle status to the given parameter
     *
     * @param vehicleStatus
     */
    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleInfos.setVehicleStatus(vehicleStatus);
    }

    /**
     *
     * @return the weight of a vehicle
     */
    public int getWeight() {
        return vehicleCharacteristics.getWeight();
    }

    /**
     * Sets the weight of a vehicle to the given parameter
     *
     * @param weight
     */
    public void setWeight(int weight) {
        this.vehicleCharacteristics.setWeight(weight);
    }

    /**
     *
     * @return the aerodynamic coefficient of a vehicle
     */
    public double getAerodynamicCoefficient() {
        return vehicleCharacteristics.getAerodynamicCoefficient();
    }

    /**
     * Sets the aerodynamic coefficient of a vehicle to the given parameter
     *
     * @param aerodynamicCoefficient
     */
    public void setAerodynamicCoefficient(double aerodynamicCoefficient) {
        this.vehicleCharacteristics.setAerodynamicCoefficient(aerodynamicCoefficient);
    }

    /**
     *
     * @return the frontal area of a vehicle
     */
    public double getFrontalArea() {
        return vehicleCharacteristics.getFrontalArea();
    }

    /**
     * Sets the frontal area of a vehicle to the given parameter
     *
     * @param frontal_area
     */
    public void setFrontalArea(double frontal_area) {
        this.vehicleCharacteristics.setFrontalArea(frontal_area);
    }

    /**
     *
     * @return the latitude of a park
     */
    public double getLatPark() {
        return vehicleInfos.getLatPark();
    }

    /**
     * Changes the latitude of a park to the given parameter
     *
     * @param latPark
     */
    public void setLatPark(double latPark) {
        this.vehicleInfos.setLatPark(latPark);
    }

    /**
     *
     * @return the longitude of a park
     */
    public double getLongPark() {
        return vehicleInfos.getLongPark();
    }

    /**
     * Changes the longitude of a park to the given parameter
     *
     * @param longPark
     */
    public void setLongPark(double longPark) {
        this.vehicleInfos.setLongPark(longPark);
    }

    /**
     *
     * @return the infos of a vehicle
     */
    public VehicleInfos getVehicleInfos() {
        return vehicleInfos;
    }

    /**
     *
     * @return the characteristics of a vehicle
     */
    public VehicleCharacteristics getVehicleCharacteristics() {
        return vehicleCharacteristics;
    }

    /**
     * Changes the infos of a vehicle to the given parameter
     *
     * @param VehicleInfos
     */
    public void setVehicleInfos(VehicleInfos VehicleInfos) {
        this.vehicleInfos = VehicleInfos;
    }

    /**
     * Changes the characteristics of a vehicle to the given parameter
     *
     * @param VehicleCharacteristics
     */
    public void setVehicleCharacteristics(VehicleCharacteristics VehicleCharacteristics) {
        this.vehicleCharacteristics = VehicleCharacteristics;
    }

    public LocalDateTime getLastUpdateTime() {
        return this.vehicleInfos.getLastUpdateTime();
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.vehicleInfos.setLastUpdateTime(lastUpdateTime);
    }

}
