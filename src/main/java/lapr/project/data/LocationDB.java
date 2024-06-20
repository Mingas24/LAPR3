package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Location;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import lapr.project.model.Location.PointOfInterest;
import oracle.jdbc.OracleTypes;

/**
 * @author diogo
 */
public class LocationDB extends DataHandler {

    public boolean addPark(Park park1) {
        return addPark(park1.getLocationId(), park1.getDescription(), park1.getLatitude(), park1.getLongitude(), park1.getCapacity(), park1.getElevation(), park1.getParkInputVoltage(), park1.getParkInputCurrent());
    }

    private boolean addPark(String parkId, String parkDescription, double lat, double lon, Capacity cap, int elevation, int parkInputVoltage, int parkInputCurrent) {
//
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call addPark(?,?,?,?,?,?,?,?,?) }");
            callStmt.setString(1, parkId);
            callStmt.setString(2, parkDescription);
            callStmt.setDouble(3, lat);
            callStmt.setDouble(4, lon);
            callStmt.setInt(5, cap.getBicycleCapacity());
            callStmt.setInt(6, cap.getScooterCapacity());
            callStmt.setInt(7, elevation);
            callStmt.setInt(8, parkInputVoltage);
            callStmt.setInt(9, parkInputCurrent);
            callStmt.execute();
            closeAll();
            System.out.println("Added Park to DB");
            return true;

        } catch (SQLException e) {
            closeAll();
            e.printStackTrace();
            return false;
        }
    }

    public boolean removePark(String parkId) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call removePark(?) }");
            callStmt.setString(1, parkId);
            callStmt.execute();
            System.out.println("Removed DB OF PARK");
            closeAll();
            return true;
        } catch (SQLException e) {
            System.out.println("Failed O REMOVE");
            e.printStackTrace();
            closeAll();
            return false;
        }
    }

    public boolean updatePark(Park park) {
        return updatePark(park.getLocationId(), park.getDescription(), park.getLatitude(), park.getLongitude(), park.getCapacity(), park.getElevation(), park.getParkInputVoltage(), park.getParkInputCurrent());
    }

    private boolean updatePark(String parkId, String parkDescription, double lat, double lon, Capacity cap, int elevation, int parkInputVoltage, int parkInputCurrent) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{call updatePark(?,?,?,?,?,?,?,?,?) }");
            callStmt.setString(1, parkId);
            callStmt.setString(2, parkDescription);
            callStmt.setDouble(3, lat);
            callStmt.setDouble(4, lon);
            callStmt.setInt(5, cap.getBicycleCapacity());
            callStmt.setInt(6, cap.getScooterCapacity());
            callStmt.setInt(7, elevation);
            callStmt.setInt(8, parkInputVoltage);
            callStmt.setInt(9, parkInputCurrent);
            callStmt.execute();
            closeAll();
            System.out.println("Update park of DB");
            return true;

        } catch (SQLException e) {
            System.out.println("Did not UPDATE");
            closeAll();
            e.printStackTrace();
            return false;
        }
    }

    public Park getPark(String identification) {
        CallableStatement callStmt = null;
        Park park = new Park();
        String parkID;
        String parkDescription;
        double latPark;
        double lonPark;
        int bikeCapacity;
        int scooterCapacity;
        int elevation;
        int parkInputVoltage;
        int parkInputCurrent;
        Capacity c;
        ParkCharger pc;
        try {

            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getPark(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, identification);

            callStmt.execute();

            ResultSet result = (ResultSet) callStmt.getObject(1);

            if (result.next()) {
                parkID = result.getString(1);
                parkDescription = result.getString(2);
                latPark = result.getDouble(3);
                lonPark = result.getDouble(4);
                bikeCapacity = result.getInt(5);
                scooterCapacity = result.getInt(6);
                elevation = result.getInt(7);
                parkInputVoltage = result.getInt(8);
                parkInputCurrent = result.getInt(9);
                c = new Capacity(bikeCapacity, scooterCapacity);
                pc = new ParkCharger(parkInputVoltage, parkInputCurrent);
                park = new Park(parkID, parkDescription, latPark, lonPark, c, elevation, pc);
                return park;
            }
            closeAll();

        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the park");
        }
        return null;
    }

    public int parkDisponibilityBike(String parkID) {

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{?=call parkDisponibilityBike(?) }");
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, parkID);
            callStmt.execute();
            int result = callStmt.getInt(1);
            closeAll();
            return result;
        } catch (SQLException e) {
            closeAll();
        }

        return -1;
    }

    public int parkDisponibilityScooter(String parkID) {

        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{?=call parkDisponibilityScooter(?) }");
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, parkID);
            callStmt.execute();
            int result = callStmt.getInt(1);
            closeAll();
            return result;
        } catch (SQLException e) {
            closeAll();
        }

        return -1;
    }

    public ArrayList<Park> getAllParks() {

        ArrayList<Park> parkList = new ArrayList<>();

        CallableStatement callStmt = null;

        try {

            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getAllParks }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();

            ResultSet result = (ResultSet) callStmt.getObject(1);

            while (result.next()) {

                String parkID = result.getString(1);

                String parkDescription = result.getString(2);

                double latPark = result.getDouble(3);

                double lonPark = result.getDouble(4);

                int bikeCapacity = result.getInt(5);

                int scooterCapacity = result.getInt(6);

                int elevation = result.getInt(7);

                int parkInputVoltage = result.getInt(8);

                int parkInputCurrent = result.getInt(9);

                Capacity c = new Capacity(bikeCapacity, scooterCapacity);
                ParkCharger pc = new ParkCharger(parkInputVoltage, parkInputCurrent);

                parkList.add(new Park(parkID, parkDescription, latPark, lonPark, c, elevation, pc));
                System.out.println("adicinou um park Á LISTA DE PARKES");
//                parks.put(parkID, new Park(parkID, parkDescription, latPark, lonPark, c, elevation, pc));
            }

            closeAll();
//            return parks;
            return parkList;
        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the PARKS");

        }
//        return parks;
        return parkList;
    }

    //COISAS DOS POIS
    public boolean addPointOfInterest(PointOfInterest poi) {
        return addPointOfInterest(poi.getLocationId(), poi.getLatitude(), poi.getLongitude(), poi.getElevation(), poi.getDescription());
    }

    private boolean addPointOfInterest(String locationID, double latitude, double longitude, int elevation, String description) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{call addPoi(?,?,?,?,?) }");
            callStmt.setString(1, locationID);
            callStmt.setDouble(2, latitude);
            callStmt.setDouble(3, longitude);
            callStmt.setInt(4, elevation);
            callStmt.setString(5, description);
            callStmt.execute();
            closeAll();
            return true;

        } catch (SQLException e) {
            closeAll();
            return false;
        }
    }

    public boolean removePointOfInterest(String poiID) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{call removePoi(?) }");
            callStmt.setString(1, poiID);
            callStmt.execute();
            closeAll();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
            return false;
        }
    }

    public boolean updatePointOfInterest(PointOfInterest poi) {
        return updatePointOfInterest(poi.getLocationId());
    }

    private boolean updatePointOfInterest(String locationId) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{call updatePoi(?) }");
            callStmt.setString(1, locationId);
            callStmt.execute();
            closeAll();
            return true;

        } catch (SQLException e) {
            closeAll();
            return false;
        }
    }

    public PointOfInterest getPoint(String identification) {
        CallableStatement callStmt = null;
        PointOfInterest point = new PointOfInterest();
        String pointId;
        double latPoint;
        double longPoint;
        int elevation;
        String pointDescrip;
        try {

            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getPoi(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, identification);
            callStmt.execute();
            ResultSet result = (ResultSet) callStmt.getObject(1);
            if (result.next()) {
                pointId = result.getString(1);
                latPoint = result.getDouble(2);
                longPoint = result.getDouble(3);
                elevation = result.getInt(4);
                pointDescrip = result.getString(5);

                point = new PointOfInterest(pointId, latPoint, longPoint, elevation, pointDescrip);
                return point;
            }
            closeAll();

        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the point of interest");

        }
        return null;
    }

    public String getParkIDbyCoordinates(double latitude, double longitude) {
        CallableStatement callStmt = null;
        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getParkbyCoord(?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.VARCHAR);
            callStmt.setDouble(2, latitude);
            callStmt.setDouble(3, longitude);
            callStmt.execute();
            String result = callStmt.getString(1);
            closeAll();
            return result;
        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the point of interest");
            return null;
        }
    }

    public List<Double> getCoordinatesByParkID(String parkID) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{?=call getCoordsbyPark(?)}");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, parkID);
            callStmt.execute();
            List<Double> coords = new ArrayList<>();
            ResultSet result = (ResultSet) callStmt.getObject(1);
            double lat = 0;
            double lon = 0;
            if (result.next()) {
                lat = result.getDouble(1);
                lon = result.getDouble(2);
            }
            coords.add(lat);
            coords.add(lon);
            return coords;
        } catch (SQLException e) {
            closeAll();
            System.out.println("It wasn't possible to get the coords by parkid");
            return null;
        }
    }

    public List<PointOfInterest> getAllPois() {
        List<PointOfInterest> pois = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getAllPois }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet result = (ResultSet) callStmt.getObject(1);
            while (result.next()) {
                String locationID = result.getString(1);
                String poiDescription = result.getString(2);
                double latitude = result.getDouble(3);
                double longitude = result.getDouble(4);
                int elevation = result.getInt(5);
                pois.add(new PointOfInterest(locationID, latitude, longitude, elevation, poiDescription));
            }
            closeAll();
            return pois;
        } catch (SQLException e) {
            closeAll();
        }
        return pois;
    }

    public List<Location> randomNums(int num) {
        
        if(num==0){
            return new ArrayList<>();
        }
        
        List<PointOfInterest> poiList = getAllPois();
        Collections.shuffle(Arrays.asList(poiList));
        List<Location> shuffleList = new ArrayList<>();
        for(int i=0;i<num;i++){
            shuffleList.add(poiList.get(i));
        }
        return shuffleList;
    }
}
