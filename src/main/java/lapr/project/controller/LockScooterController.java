package lapr.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import lapr.project.data.LocationDB;
import lapr.project.data.RegisteredUserDB;
import lapr.project.data.TripDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Park;
import lapr.project.model.RegisteredUser;
import lapr.project.model.Trip;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.utils.SendEmail;

/**
 * @author Toshiba
 */
public class LockScooterController {

    TripDB tripDB;
    VehicleDB vehicleDB;
    LocationDB locationDB;
    RegisteredUserDB registeredUserDB;

    Scooter scooter;
    Trip trip;
    Park park;
    RegisteredUser registeredUser;

    AvailableSlotsController asc;

    public LockScooterController() throws IOException, SQLException {
        tripDB = new TripDB();
        vehicleDB = new VehicleDB();
        locationDB = new LocationDB();
        registeredUserDB = new RegisteredUserDB();
        asc = new AvailableSlotsController();
    }

    public boolean endScooterTrip(String vehicleID, double latitude, double longitude, String username) throws SQLException {
        String parkID = locationDB.getParkIDbyCoordinates(latitude, longitude);
        int available = asc.availableSlotsScooter(parkID);
        if (available == 0) {
            return false;
        }
        LocalDateTime endTime = LocalDateTime.now();

        registeredUser = registeredUserDB.getRegisteredUser(username);

        scooter = vehicleDB.getScooter(vehicleID);
        scooter.setLatPark(latitude);
        scooter.setLongPark(longitude);
        scooter.setLastUpdateTime(endTime);
        vehicleDB.updateScooter(scooter);

        int tripToClose = tripDB.getTripId(vehicleID);
        tripDB.endTrip(tripToClose, latitude, longitude, endTime);
        trip = tripDB.getTrip(tripToClose);

        Park startPark = locationDB.getPark(trip.getStartPark());
        Park endingPark = locationDB.getPark(trip.getEndingPark());

        int difOfElevation = endingPark.getElevation() - startPark.getElevation();

        if (difOfElevation > 25 && difOfElevation <= 50) {
            registeredUser.setPoints(registeredUser.getPoints() + 5);
            registeredUserDB.updateUserPoints(registeredUser);
        }
        if (difOfElevation > 50) {
            registeredUser.setPoints(registeredUser.getPoints() + 15);
            registeredUserDB.updateUserPoints(registeredUser);
        }

        SendEmail sendEmail = new SendEmail();
        return sendEmail.sendLockDescription(registeredUser.getEmail(), "Locked Scooter", "Trip Completed: " + tripToClose + "\nPoints: " + registeredUser.getPoints());
    }
}
