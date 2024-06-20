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
import lapr.project.model.Vehicle.Bicycle;
import lapr.project.utils.SendEmail;

/**
 * @author diogo
 */
public class LockBicycleController {

    TripDB TripDB;

    LocationDB LocationDB;
    RegisteredUserDB RegisteredUserDB;
    VehicleDB VehicleDB;

    Bicycle bicycle;
    Trip trip;
    Park Park;
    RegisteredUser user;

    AvailableSlotsController asc;

    public LockBicycleController() throws IOException, SQLException {
        TripDB = new TripDB();
        VehicleDB = new VehicleDB();
        LocationDB = new LocationDB();
        RegisteredUserDB = new RegisteredUserDB();
        asc = new AvailableSlotsController();
    }

    /**
     * FALTA VERIFICAR SLOTS
     *
     *
     * @param vehicleId
     * @param lat
     * @param longi
     * @param username
     * @return
     */
    public boolean endBikeTrip(String vehicleId, double lat, double longi, String username) throws SQLException {
        String parkID = LocationDB.getParkIDbyCoordinates(lat, longi);
        int available = asc.availableSlotsBike(parkID);
        if (available == 0) {
            return false;
        }

        LocalDateTime endtime = LocalDateTime.now();

        user = RegisteredUserDB.getRegisteredUser(username);
        
        bicycle =VehicleDB.getBycicle(vehicleId);
        bicycle.setLatPark(lat);
        bicycle.setLongPark(longi);
        
        int tripToClose = TripDB.getTripId(vehicleId);
        TripDB.endTrip(tripToClose, lat, longi, endtime);
        trip = TripDB.getTrip(tripToClose);

        Park park1 = LocationDB.getPark(trip.getStartPark());
        Park park2 = LocationDB.getPark(trip.getEndingPark());
        int difOfElev = park2.getElevation() - park1.getElevation();

//adding points
        if (difOfElev > 25 && difOfElev <= 50) {
            user.setPoints(user.getPoints() + 5);
            RegisteredUserDB.updateUserPoints(user);
        }
        if (difOfElev > 50) {
            user.setPoints(user.getPoints() + 15);
            RegisteredUserDB.updateUserPoints(user);
        }
//sending email
        SendEmail email = new SendEmail();
        return email.sendLockDescription(user.getEmail(), "Locked Bicycle", "Trip Completed: " + tripToClose + "\nPoints: " + user.getPoints());
    }
}
