package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Vehicle.Battery;
import lapr.project.model.Vehicle.Bicycle;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import oracle.jdbc.OracleTypes;

public class VehicleDB extends DataHandler {

    public boolean addBicycle(Bicycle bike) {
        return addBicycle(bike.getVehicleID(), bike.getLatPark(), bike.getLongPark(), bike.getVehicleStatus(), bike.getWeight(),
                bike.getAerodynamicCoefficient(), bike.getFrontalArea(), bike.getWheelSize());
    }

    private boolean addBicycle(String VehicleID, double LatPark, double LongPark, String VehicleStatus, int Weight,
            double AerodynamicCoefficient, double FrontalArea, int WheelSize) {
        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{call addBicycle(?,?,?,?,?,?,?,?) }");
            callStmt.setString(1, VehicleID);
            callStmt.setDouble(2, LatPark);
            callStmt.setDouble(3, LongPark);
            callStmt.setString(4, VehicleStatus);
            callStmt.setInt(5, Weight);
            callStmt.setDouble(6, AerodynamicCoefficient);
            callStmt.setDouble(7, FrontalArea);
            callStmt.setInt(8, WheelSize);
            callStmt.execute();
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
            return false;
        }
    }

    public Bicycle getBycicle(String identification) {
        CallableStatement callStmt = null;

        try {

            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getBike(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, identification);

            callStmt.execute();

            ResultSet result = (ResultSet) callStmt.getObject(1);

            if (result.next()) {

                String VehicleID = result.getString(1);

                VehicleInfos vi = new VehicleInfos(result.getString(2), result.getString(3));
                VehicleCharacteristics vc = new VehicleCharacteristics(result.getInt(4), result.getDouble(5), result.getDouble(6));

                int ws = result.getInt(7);
                return new Bicycle(VehicleID, vi, vc, ws);
            }
            closeAll();

        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the bike");
        }
        return null;
    }

    public boolean addScooter(Scooter scoot) {
        return addScooter(scoot.getVehicleID(), scoot.getLatPark(), scoot.getLongPark(), scoot.getVehicleStatus(), scoot.getWeight(),
                scoot.getAerodynamicCoefficient(), scoot.getFrontalArea(), scoot.getTypeOfScooter(), scoot.getMaxBattery(),
                scoot.getActualBattery(), scoot.getMotor(), scoot.getLastUpdateTime());
    }

    private boolean addScooter(String VehicleID, double LatPark, double LongPark, String VehicleStatus, int Weight,
            double AerodynamicCoefficient, double FrontalArea, String TypeOfScooter, double MaxBattery,
            double ActualBattery, int Motor, LocalDateTime lockTime) {
        Timestamp lockTimeSQL = Timestamp.valueOf(lockTime);

        try {
            openConnection();

            CallableStatement callStmt = getConnection().prepareCall("{call addScooter(?,?,?,?,?,?,?,?,?,?,?,?) }");
            callStmt.setString(1, VehicleID);
            callStmt.setDouble(2, LatPark);
            callStmt.setDouble(3, LongPark);
            callStmt.setString(4, VehicleStatus);
            callStmt.setInt(5, Weight);
            callStmt.setDouble(6, AerodynamicCoefficient);
            callStmt.setDouble(7, FrontalArea);
            callStmt.setString(8, TypeOfScooter);
            callStmt.setDouble(9, MaxBattery);
            callStmt.setDouble(10, ActualBattery);
            callStmt.setInt(11, Motor);
            callStmt.setTimestamp(12, lockTimeSQL);
            callStmt.execute();
            closeAll();
            System.out.println("ADDED SCOOTER TO DB");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
            return false;
        }
    }

    public Scooter getScooter(String identification) {
        CallableStatement callStmt = null;

        try {

            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getScooter(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, identification);

            callStmt.execute();

            ResultSet result = (ResultSet) callStmt.getObject(1);

            if (result.next()) {
                String VehicleID = result.getString(1);

                String parkID = result.getString(2);

                String vehicleStatus = result.getString(3);

                int weight = result.getInt(4);

                double aerocoef = result.getDouble(5);

                double frontalArea = result.getDouble(6);

                String ScooterType = result.getString(7);

                double maxBatt = result.getDouble(8);

                double actualBatt = result.getDouble(9);

                int motor = result.getInt(10);

                LocalDateTime lockTime = result.getTimestamp(11).toLocalDateTime();

                VehicleInfos vi = new VehicleInfos(parkID, vehicleStatus, lockTime);

                VehicleCharacteristics vc = new VehicleCharacteristics(weight, aerocoef, frontalArea);

                Battery bat = new Battery(maxBatt, actualBatt);

                return new Scooter(VehicleID, ScooterType, vi, vc, bat, motor);
            }
            closeAll();

        } catch (SQLException e) {
            closeAll();
            System.out.println("It WAS NOT possible to get the SCOOTER");
        }
        return null;
    }

    public boolean updateScooter(Scooter scooter) {
        return updateScooter(scooter.getVehicleID(), scooter.getTypeOfScooter(),
                scooter.getLatPark(), scooter.getLongPark(), scooter.getVehicleStatus(),
                scooter.getMaxBattery(), scooter.getActualBattery(),
                scooter.getWeight(), scooter.getAerodynamicCoefficient(),
                scooter.getFrontalArea(), scooter.getMotor(),
                scooter.getLastUpdateTime());
    }

    private boolean updateScooter(String vehicleID, String typeOfScooter,
            double latPark, double longPark, String vehicleStatus,
            double maxBattery, double actualBattery, int weight,
            double aerodynamicCoefficient, double frontalArea, int motor,
            LocalDateTime lockTime) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updateScooter(?,?,?,?,?,?,?,?,?,?,?,?)}");
            callStmt.setString(1, vehicleID);
            callStmt.setString(2, typeOfScooter);
            callStmt.setDouble(3, latPark);
            callStmt.setDouble(4, longPark);
            callStmt.setString(5, vehicleStatus);
            callStmt.setDouble(6, maxBattery);
            callStmt.setDouble(7, actualBattery);
            callStmt.setInt(8, weight);
            callStmt.setDouble(9, aerodynamicCoefficient);
            callStmt.setDouble(10, frontalArea);
            callStmt.setInt(11, motor);
            callStmt.setTimestamp(12, Timestamp.valueOf(lockTime));
            callStmt.execute();
            closeAll();
               System.out.println("update scooter");
            return true;

        } catch (SQLException e) {
            closeAll();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBicycle(Bicycle bicycle) {
        return updateBicycle(bicycle.getVehicleID(), bicycle.getLatPark(),
                bicycle.getLongPark(), bicycle.getVehicleStatus(),
                bicycle.getWeight(), bicycle.getAerodynamicCoefficient(),
                bicycle.getFrontalArea(), bicycle.getWheelSize());
    }

    private boolean updateBicycle(String vehicleID, double lat, double lon,
            String vehicleStatus, int weight, double aerodynamicCoefficient,
            double frontalArea, int wheelSize) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{call updateBike(?,?,?,?,?,?,?,?) }");
            callStmt.setString(1, vehicleID);
            callStmt.setDouble(2, lat);
            callStmt.setDouble(3, lon);
            callStmt.setString(4, vehicleStatus);
            callStmt.setInt(5, weight);
            callStmt.setDouble(6, aerodynamicCoefficient);
            callStmt.setDouble(7, frontalArea);
            callStmt.setInt(8, wheelSize);
            callStmt.execute();
            closeAll();

            return true;
        } catch (SQLException e) {
            closeAll();

            e.printStackTrace();
            return false;
        }
    }

    public boolean removeScooter(String vehicleID) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call removeScooter(?) }");
            callStmt.setString(1, vehicleID);
            callStmt.execute();
            System.out.println("REMOVED FROM DB THE SCOOTER");
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
            return false;
        }
    }

    public boolean removeBycicle(String vehicleID) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call removeBicycle(?) }");
            callStmt.setString(1, vehicleID);
            callStmt.execute();
            System.out.println("REMOVED From DB the BYCICLE");
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
            return false;
        }
    }

    public List<Scooter> getAllScooterInPark(String parkID) {
        List<Scooter> scooterList = new ArrayList<>();
        CallableStatement callStmt = null;
        int count = 0;

        try {
            openConnection();
            callStmt = getConnection().prepareCall("{?=call getAllScooterInPark(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, parkID);

            callStmt.execute();
            ResultSet result = (ResultSet) callStmt.getObject(1);
            while (result.next()) {
                String vehicleID = result.getString(1);

                String typeOfScooter = result.getString(2);

                String park = result.getString(3);

                String vehicleStatus = result.getString(4);

                int weight = result.getInt(5);

                double aerodynamicCoefficient = result.getDouble(6);

                double frontalArea = result.getDouble(7);

                double maxBatt = result.getDouble(8);

                double actualBatt = result.getDouble(9);

                int motor = result.getInt(10);

                LocalDateTime lockTime = result.getTimestamp(11).toLocalDateTime();

                VehicleInfos vi = new VehicleInfos(park, vehicleStatus, lockTime);

                VehicleCharacteristics vc = new VehicleCharacteristics(weight, aerodynamicCoefficient, frontalArea);

                Battery b = new Battery(maxBatt, actualBatt);

                Scooter s = new Scooter(vehicleID, typeOfScooter, vi, vc, b, motor);

                count++;

                scooterList.add(s);
            }
            closeAll();
            return scooterList;
        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the Scooters");
        }
        return scooterList;
    }

    public List<Scooter> getAllAvailableScooterInPark(String parkID) {
        List<Scooter> scooterAvailableList = new ArrayList<>();

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{?=call getAllAvailableScooterInPark(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, parkID);
            callStmt.execute();
            ResultSet result = (ResultSet) callStmt.getObject(1);
            while (result.next()) {

                String vehicleID = result.getString(1);
                String typeOfScooter = result.getString(2);
                String park = result.getString(3);
                String vehicleStatus = result.getString(4);
                int weight = result.getInt(5);
                double aerodynamicCoefficient = result.getDouble(6);
                double frontalArea = result.getDouble(7);
                double maxBatt = result.getDouble(8);
                double actualBatt = result.getDouble(9);
                int motor = result.getInt(10);
                LocalDateTime lockTime = result.getTimestamp(11).toLocalDateTime();

                VehicleInfos vi = new VehicleInfos(park, vehicleStatus, lockTime);
                VehicleCharacteristics vc = new VehicleCharacteristics(weight, aerodynamicCoefficient, frontalArea);
                Battery b = new Battery(maxBatt, actualBatt);

                Scooter s = new Scooter(vehicleID, typeOfScooter, vi, vc, b, motor);
                scooterAvailableList.add(s);

            }
            closeAll();
            return scooterAvailableList;
        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the Scooters");
        }
        return scooterAvailableList;
    }

    public List<Bicycle> getAllBikeInPark(String parkID) {
        List<Bicycle> bikeList = new ArrayList<>();

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{?=call getAllBikeInPark(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, parkID);
            callStmt.execute();
            ResultSet result = (ResultSet) callStmt.getObject(1);
            while (result.next()) {
                String vehicleID = result.getString(1);
                String park = result.getString(2);
                String vehicleStatus = result.getString(3);
                int weight = result.getInt(4);
                double aerodynamicCoefficient = result.getDouble(5);
                double frontalArea = result.getDouble(6);
                int wheel = result.getInt(7);
                VehicleInfos vi = new VehicleInfos(park, vehicleStatus);
                VehicleCharacteristics vc = new VehicleCharacteristics(weight, aerodynamicCoefficient, frontalArea);

                Bicycle b = new Bicycle(vehicleID, vi, vc, wheel);
                bikeList.add(b);
            }
            closeAll();
            return bikeList;
        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the bikes");
        }
        return bikeList;
    }

    public List<Bicycle> getAllAvailableBikeInPark(String parkID) {
        List<Bicycle> avaiBikeList = new ArrayList<>();

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{?=call getAllAvailableBikeInPark(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, parkID);
            callStmt.execute();
            ResultSet result = (ResultSet) callStmt.getObject(1);
            while (result.next()) {
                String vehicleID = result.getString(1);
                String park = result.getString(2);
                String vehicleStatus = result.getString(3);
                int weight = result.getInt(4);
                double aerodynamicCoefficient = result.getDouble(5);
                double frontalArea = result.getDouble(6);
                int wheel = result.getInt(7);
                VehicleInfos vi = new VehicleInfos(park, vehicleStatus);
                VehicleCharacteristics vc = new VehicleCharacteristics(weight, aerodynamicCoefficient, frontalArea);

                Bicycle b = new Bicycle(vehicleID, vi, vc, wheel);
                avaiBikeList.add(b);
            }
            closeAll();
            return avaiBikeList;
        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the bikes");
        }
        return avaiBikeList;
    }
    public List<Scooter> getAllScooter() {
        List<Scooter> allScooterList = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{?=call getAllScooters() }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet result = (ResultSet) callStmt.getObject(1);
            String vehicleID = result.getString(1);
            String typeOfScooter = result.getString(2);
            String park = result.getString(3);
            String vehicleStatus = result.getString(4);
            int weight = result.getInt(5);
            double aerodynamicCoefficient = result.getDouble(6);
            double frontalArea = result.getDouble(7);
            double maxBatt = result.getDouble(8);
            double actualBatt = result.getDouble(9);
            int motor = result.getInt(10);

            VehicleInfos vi = new VehicleInfos(park, vehicleStatus);
            VehicleCharacteristics vc = new VehicleCharacteristics(weight, aerodynamicCoefficient, frontalArea);
            Battery b = new Battery(maxBatt, actualBatt);
            Scooter s = new Scooter(vehicleID, typeOfScooter, vi, vc, b, motor);

            allScooterList.add(s);
            
        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the Scooters");
        }
        return allScooterList;
    }
}
