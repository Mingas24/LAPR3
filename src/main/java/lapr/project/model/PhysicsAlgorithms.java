package lapr.project.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lapr.project.model.Vehicle.Scooter;

public class PhysicsAlgorithms {

    private static final int EARTH_RADIUS = 6371; // Earth Radius in Km.
    private static final double GRAVITY = 9.8;      // m/s
    private static final double ATMOSPHERIC_DENSITY = 1.225; // Density of air in 15ºC on the sea level (Kg/m^3)
    private static final double EFFICIENCY=0.7;


    public static double distanceBetweenPoints(double lat1, double long1, double lat2, double long2, int elevation1, int elevation2) {

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(long2 - long1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS * c *1000;
        double distance1=diagonalDistance(distance, elevation1, elevation2);
        return distance1/1000;
    }

    public static double calculateEnergyBetweenPoints(double latA, double latB, double longA, double longB, int elevationA, int elevationB, double frontalArea, double windSpeed, double windDirection, double coef, double aeroCoef, double avgSpeed, int userWeight, int vehicleWeight) {

        double distance = distanceBetweenPoints(latA, longA, latB, longB, elevationA, elevationB);
        double potentialEnergy = 0;
        int mass = userWeight + vehicleWeight;
        int elevation = Math.abs(elevationA - elevationB);

        double bearingAngle = calculateBearingAngle(latA, latB, longA, longB);

        double resultantWindAngle = calculateResultantAngle(bearingAngle, windDirection);

        double windEnergy = WindEnergy(avgSpeed, aeroCoef, frontalArea, distance, resultantWindAngle, windSpeed);

        double realDistance = diagonalDistance(distance, elevationA, elevationB);

        double frictionEnergy = frictionEnergy(realDistance, mass, distance, coef);

        if (elevation > 0) {
            potentialEnergy = graviticPotentialEnergy(mass, elevation);
        }

        return (frictionEnergy + windEnergy + potentialEnergy)/EFFICIENCY;

    }

    public static double calculateBearingAngle(double latA, double latB, double longA, double longB) {

        double x = Math.cos(Math.toRadians(latB)) * Math.sin(Math.toRadians(longB - longA));

        double y = Math.cos(Math.toRadians(latA)) * Math.sin(Math.toRadians(latB)) - Math.sin(Math.toRadians(latA)) * Math.cos(Math.toRadians(latB)) * Math.cos(Math.toRadians(longB - longA));
        return Math.toDegrees(Math.atan2(x, y));

    }

    public static double calculateResultantAngle(double bearingAngle, double windDirection) {
        double relativeAngle = windDirection - bearingAngle;

        if (relativeAngle >= 0) {
            return Math.toRadians(relativeAngle);
        } else {
            return Math.toRadians(relativeAngle + 360);
        }

    }

    public static double diagonalDistance(double distance, int elevation1, int elevation2) {
        int elevation = Math.abs(elevation1 - elevation2);
        return Math.sqrt(distance * distance + elevation * elevation);
    }

    //disipated friction energy
    public static double frictionEnergy(double diagonalDistance, int massa, double distance, double coef) {
        return coef * massa * GRAVITY * (distance / diagonalDistance) * diagonalDistance;
    }

    public static double WindEnergy(double avgSpeed, double aerocoef, double frontalArea, double distance, double resultantWindAngle, double windSpeed) {
        double dragForce = 0.5 * ATMOSPHERIC_DENSITY * Math.pow(avgSpeed - windSpeed * Math.cos(resultantWindAngle), 2) * aerocoef * frontalArea;
        return dragForce * distance;
    }

    public static double graviticPotentialEnergy(int mass, double elevation) {
        return mass * GRAVITY * elevation;
    }

    public static double conversionToCalories(double totalEnergy) {
        return totalEnergy / 4.18;
    }
    
   public static double calculateMissingEnergy(Scooter s){
               double missingCharge = s.getMaxBattery() - (s.getMaxBattery() * (s.getActualBattery() / 100));
                missingCharge = missingCharge * 1000; //kwh to wh
                return missingCharge;     
   } 
    
 
public static int calcuteTimeDifference(Scooter s){
       long time = ChronoUnit.SECONDS.between(s.getLastUpdateTime(),LocalDateTime.now());

       return (int) time;
   }  
}
