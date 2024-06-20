/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lapr.project.model.Trip;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class TripDB extends DataHandler {

    public boolean addTrip(Trip trip1) {
        return addTrip(trip1.getUsername(), trip1.getVehicleId(),
                trip1.getStartPark(), trip1.getEndingPark(),
                trip1.getStartTime());
    }

    private boolean addTrip(String username, String vehicleId, String parkId,
            String endingPark, LocalDateTime startTime) {

        Timestamp startTime1 = Timestamp.valueOf(startTime);

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{call addTrip(?,?,?,?,?) }");

            callStmt.setString(1, username);

            callStmt.setString(2, vehicleId);
            callStmt.setString(3, parkId);
            callStmt.setString(4, endingPark);
            callStmt.setTimestamp(5, startTime1);

            callStmt.execute();
            closeAll();
            return true;

        } catch (SQLException e) {
            closeAll();
            e.printStackTrace();
            return false;
        }
    }

    private void removeTrip(int tripId) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeTrip(?) }");
            callStmt.setInt(1, tripId);
            callStmt.execute();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
        }
    }

    public Trip getTrip(int identification) {
        CallableStatement callStmt = null;

        try {

            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getTrip(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, identification);

            callStmt.execute();

            ResultSet result = (ResultSet) callStmt.getObject(1);

            if (result.next()) {

                int tripID = result.getInt(1);
                String username = result.getString(2);
                String vehicleID = result.getString(3);
                String startPark = result.getString(4);
                String endPark = result.getString(5);
                LocalDateTime startTime = result.getTimestamp(6).toLocalDateTime();
                LocalDateTime endTime = result.getTimestamp(7).toLocalDateTime();

                return new Trip(tripID, username, vehicleID, startPark, endPark, startTime, endTime);
            }
            closeAll();

        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the trip");
        }
        return null;
    }

    public int getTripId(String vehicleId) {

        CallableStatement callStmt = null;

        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getTripId(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, vehicleId);

            callStmt.execute();

            int tripID = callStmt.getInt(1);
            closeAll();
            return tripID;

        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
            return -1;
        }
    }

    public boolean endTrip(int tripId, double lat, double longi, LocalDateTime endtime) {

        Timestamp endT = Timestamp.valueOf(endtime);

        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call endTrip(?,?,?,?) }");
            callStmt.setInt(1, tripId);
            callStmt.setDouble(2, lat);
            callStmt.setDouble(3, longi);
            callStmt.setTimestamp(4, endT);
            callStmt.execute();
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
            return false;
        }
    }
}
