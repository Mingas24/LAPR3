package lapr.project.assessment;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.*;
import lapr.project.data.LocationDB;
import lapr.project.data.RegisteredUserDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Location.Capacity;
import lapr.project.model.Location.Location;
import lapr.project.model.Location.Park;
import lapr.project.model.Location.ParkCharger;
import lapr.project.model.Location.PointOfInterest;
import lapr.project.model.Path;
import lapr.project.model.PhysicsAlgorithms;
import lapr.project.model.RegisteredUser;
import lapr.project.model.Route;
import lapr.project.model.Vehicle.Battery;
import lapr.project.model.Vehicle.Bicycle;
import lapr.project.model.Vehicle.Scooter;
import lapr.project.model.Vehicle.VehicleCharacteristics;
import lapr.project.model.Vehicle.VehicleInfos;
import lapr.project.utils.CreateGraphs;
import lapr.project.utils.InputCSVReader;
import lapr.project.utils.graphbase.Graph;

public class Facade implements Serviceable {

    int countPOI = 0;

    /**
     * Add Bicycles to the system.
     *
     * Basic: Add one bicycle to one park. Intermediate: Add several bicycles to
     * one park. Advanced: Add several bicycles to several parks
     * transactionally.
     *
     * @param inputFile Path to file with bicycles to add, according to
     * input/bicycles.csv.
     * @return Number of added bicycles.
     */
    @Override
    public int addBicycles(String inputFile) {

        List<String> listBikesToAdd = new ArrayList<>();
        listBikesToAdd = InputCSVReader.inputCSVReader(inputFile);

        List<String> listBikesToRemoveIfNecessary = new ArrayList<>();

        int count = 0;

        try {
            AddBicycleController abc = new AddBicycleController();

            for (String string : listBikesToAdd) {
                List<String> fill = new ArrayList<>();
                fill = InputCSVReader.lineReader(string);

                VehicleInfos vi = new VehicleInfos(Double.parseDouble(fill.get(3)), Double.parseDouble(fill.get(4)), "AVAILABLE");
                VehicleCharacteristics vc = new VehicleCharacteristics(Integer.parseInt(fill.get(1)), Double.parseDouble(fill.get(4)), Double.parseDouble(fill.get(5)));

                Bicycle bike = new Bicycle(fill.get(0), vi, vc, Integer.parseInt(fill.get(6)));
                listBikesToRemoveIfNecessary.add(fill.get(0));
                abc.addBicycle(bike);
                count++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException ex) {
            try {
                RemoveBicycleController rbc = new RemoveBicycleController();

                for (int i = 0; i < count; i++) {
                    rbc.removeBicycle(listBikesToRemoveIfNecessary.get(i));
                }
                Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
                return -1;

            } catch (IOException | SQLException ex1) {
                Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex1);
                return -1;
            }

        }
        return count;
    }

    /**
     * Add Escooters to the system.
     *
     * Basic: Add one Escooter to one park. Intermediate: Add several Escooters
     * to one park. Advanced: Add several Escooters to several parks
     * transactionally.
     *
     * @param inputFile Path to file with Escooters to add, according to
     * input/escooters.csv.
     * @return Number of added escooters.
     */
    @Override
    public int addEscooters(String inputFile) {

        List<String> listScooterToAdd = new ArrayList<>();
        listScooterToAdd = InputCSVReader.inputCSVReader(inputFile);

        List<String> listScooterToRemoveIfNecessary = new ArrayList<>();

        int count = 0;

        try {
            AddScooterController abc = new AddScooterController();

            for (String string : listScooterToAdd) {
                List<String> fill = new ArrayList<>();
                fill = InputCSVReader.lineReader(string);

                VehicleInfos vi = new VehicleInfos(Double.parseDouble(fill.get(3)), Double.parseDouble(fill.get(4)), "AVAILABLE", LocalDateTime.now());
                VehicleCharacteristics vc = new VehicleCharacteristics(Integer.parseInt(fill.get(1)), Double.parseDouble(fill.get(7)), Double.parseDouble(fill.get(8)));
                Battery bat = new Battery(count, count);

                Scooter scooter = new Scooter(fill.get(0), fill.get(2), vi, vc, bat, Integer.parseInt(fill.get(9)));
                listScooterToRemoveIfNecessary.add(fill.get(0));
                abc.addScooter(scooter);
                count++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException ex) {
            try {
                RemoveScooterController rsc = new RemoveScooterController();

                for (int i = 0; i < count; i++) {
                    rsc.removeScooter(listScooterToRemoveIfNecessary.get(i));
                }

            } catch (IOException | SQLException ex1) {
                Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex1);
                return -1;
            }
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;

        }
        return count;
    }

    /**
     * Add Parks to the system.
     *
     * Basic: Add one Park. Intermediate: Add several Parks. Advanced: Add
     * several Parks transactionally.
     *
     * @param inputFile Path to file that contains the parks, according to file
     * input/parks.csv.
     * @return The number of added parks.
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @Override
    public int addParks(String inputFile) {

        List<String> listParksToAdd = new ArrayList<>();
        listParksToAdd = InputCSVReader.inputCSVReader(inputFile);

        List<String> listParksToRemoveIfNecessary = new ArrayList<>();

        int count = 0;

        try {
            AddParkController apc = new AddParkController();

            for (String string : listParksToAdd) {
                List<String> fill = new ArrayList<>();
                fill = InputCSVReader.lineReader(string);

                Capacity cap = new Capacity(Integer.parseInt(fill.get(5)), Integer.parseInt(fill.get(6)));

                ParkCharger charger = new ParkCharger(Integer.parseInt(fill.get(7)), Integer.parseInt(fill.get(8)));

                Park park = new Park(fill.get(0), fill.get(4), Double.parseDouble(fill.get(1)),
                        Double.parseDouble(fill.get(2)), cap, Integer.parseInt(fill.get(3)), charger);
                listParksToRemoveIfNecessary.add(fill.get(0));
                apc.addPark(park);
                count++;

            }
        } catch (IOException ex) {
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException ex) {
            try {
                RemoveParkController rpc = new RemoveParkController();

                for (int i = 0; i < count; i++) {
                    rpc.removePark(listParksToRemoveIfNecessary.get(i));

                }

            } catch (IOException | SQLException ex1) {
                Logger.getLogger(Facade.class
                        .getName()).log(Level.SEVERE, null, ex1);
                return -1;

            }
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);
            return -1;

        }
        return count;
    }

    /**
     * Remove a park from the system.
     *
     * @param parkIdentification Park to be removed from the system.
     * @return The number of removed parks.
     */
    @Override
    public int removePark(String parkIdentification) {
        int count = 0;
        try {
            RemoveParkController rpc = new RemoveParkController();
            rpc.removePark(parkIdentification);
            count++;
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return count;
    }

    /**
     * Add POIs to the system.
     *
     * Basic: Add one POI. Intermediate: Add several POIs. Advanced: Add several
     * POIs transactionally.
     *
     * @param inputFile Path to file that contains the POIs, according to file
     * input/pois.csv.
     * @return The number of added POIs.
     */
    @Override
    public int addPOIs(String inputFile) {

        List<String> listPOIToAdd = new ArrayList<>();
        listPOIToAdd = InputCSVReader.inputCSVReader(inputFile);

        List<String> listPOIToRemoveIfNecessary = new ArrayList<>();

        int count = 0;

        try {
            AddPOIController apoc = new AddPOIController();

            for (String string : listPOIToAdd) {
                List<String> fill = new ArrayList<>();
                fill = InputCSVReader.lineReader(string);

                String POIid = "POID" + countPOI;
                PointOfInterest poi = new PointOfInterest(POIid, Double.parseDouble(fill.get(0)), Double.parseDouble(fill.get(1)), Integer.parseInt(fill.get(2)), fill.get(3));
                listPOIToRemoveIfNecessary.add(POIid);
                apoc.addPoint(poi);
                countPOI++;
                count++;

            }
        } catch (IOException ex) {
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException ex) {
            try {
                RemoveParkController rpc = new RemoveParkController();

                for (int i = 0; i < count; i++) {
                    rpc.removePark(listPOIToRemoveIfNecessary.get(i));
                    countPOI--;
                }

            } catch (IOException | SQLException ex1) {
                Logger.getLogger(Facade.class
                        .getName()).log(Level.SEVERE, null, ex1);
                return -1;

            }
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);
            return -1;

        }
        return count;
    }

    /**
     * Add Users to the system.
     *
     * Basic: Add one User. Intermediate: Add several Users. Advanced: Add
     * several Users transactionally.
     *
     * @param inputFile Path to file that contains the Users, according to file
     * input/users.csv.
     * @return The number of added users.
     */
    @Override
    public int addUsers(String inputFile) {

        List<String> listUsersToAdd = new ArrayList<>();
        listUsersToAdd = InputCSVReader.inputCSVReader(inputFile);

        List<String> listUsersToRemoveIfNecessary = new ArrayList<>();

        int count = 0;

        try {
            AddRegisteredUserController aruc = new AddRegisteredUserController();

            for (String string : listUsersToAdd) {
                List<String> fill = new ArrayList<>();
                fill = InputCSVReader.lineReader(string);

                RegisteredUser user = new RegisteredUser(fill.get(0), fill.get(1), fill.get(6), Integer.getInteger(fill.get(2)), Integer.getInteger(fill.get(3)),
                        Double.parseDouble(fill.get(4)), Long.parseLong(fill.get(5)), fill.get(7), 0);

                listUsersToRemoveIfNecessary.add(fill.get(0));
                aruc.addRegisteredUser(user);
                count++;

            }
        } catch (IOException ex) {
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException ex) {
            try {
                RemoveRegisteredUserController rruc = new RemoveRegisteredUserController();

                for (int i = 0; i < count; i++) {
                    rruc.removeRegisteredUser(listUsersToRemoveIfNecessary.get(i));

                }

            } catch (IOException | SQLException ex1) {
                Logger.getLogger(Facade.class
                        .getName()).log(Level.SEVERE, null, ex1);
                return -1;

            }
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);
            return -1;

        }
        return count;
    }

    /**
     * Add Paths to the system transactionally.
     *
     * @param inputFile Path to file that contains the Paths, according to file
     * input/paths.csv.
     * @return The number of added Paths.
     */
    @Override
    public int addPaths(String inputFile) {

        List<String> listPathsToAdd = new ArrayList<>();
        listPathsToAdd = InputCSVReader.inputCSVReader(inputFile);
        int count = 0;

        try {
            AddPathController apathc = new AddPathController();

            for (String string : listPathsToAdd) {
                List<String> fill = new ArrayList<>();
                fill = InputCSVReader.lineReader(string);

                Path path = new Path(Double.parseDouble(fill.get(0)), Double.parseDouble(fill.get(1)), Double.parseDouble(fill.get(2)),
                        Double.parseDouble(fill.get(3)), Double.parseDouble(fill.get(4)), Double.parseDouble(fill.get(5)), Integer.parseInt(fill.get(6)));

                apathc.addPath(path);
                count++;

            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return count;
    }

    /**
     * Get the list of bicycles parked at a given park.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param outputFileName Path to file where output should be written,
     * according to file output/bicycles.csv. Sort in ascending order by bike
     * description.
     * @return The number of bicycles at a given park.
     */
    @Override
    public int getNumberOfBicyclesAtPark(double parkLatitudeInDegrees,
            double parkLongitudeInDegrees,
            String outputFileName) {

        try {
            Formatter output = new Formatter(new File(outputFileName));
            AvailableVehiclesInAParkController aviap = new AvailableVehiclesInAParkController();
            GetParkIDByCoordOrViceVersaController gid = new GetParkIDByCoordOrViceVersaController();

            List<Bicycle> listOfBikes = new ArrayList<>();
            String pID = gid.getParkIDByCoord(parkLatitudeInDegrees, parkLongitudeInDegrees);
            listOfBikes = aviap.getAllAvailableBikeInPark(pID);
            for (Bicycle b : listOfBikes) {
                output.format(b.getVehicleID());
                output.format("%n");

            }
            return listOfBikes.size();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    /**
     * Get the list of bicycles parked at a given park.
     *
     * @param parkIdentification The Park Identification.
     * @param outputFileName Path to file where output should be written,
     * according to file output/bicycles.csv. Sort in ascending order by bike
     * description.
     * @return The number of bicycles at a given park.
     */
    @Override
    public int getNumberOfBicyclesAtPark(String parkIdentification,
            String outputFileName
    ) {
        try {
            Formatter output = new Formatter(new File(outputFileName));
            AvailableVehiclesInAParkController aviap = new AvailableVehiclesInAParkController();
            GetParkIDByCoordOrViceVersaController gid = new GetParkIDByCoordOrViceVersaController();

            List<Bicycle> listOfBikes = new ArrayList<>();

            listOfBikes = aviap.getAllAvailableBikeInPark(parkIdentification);
            for (Bicycle b : listOfBikes) {
                output.format(b.getVehicleID());
                output.format("%n");

            }
            return listOfBikes.size();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    /**
     * Get the list of escooters parked at a given park.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param outputFileName Path to file where output should be written,
     * according to file output/escooters.csv. Sort in ascending order by bike
     * description.
     * @return The number of escooters at a given park.
     */
    @Override
    public int getNumberOfEscootersAtPark(double parkLatitudeInDegrees,
            double parkLongitudeInDegrees,
            String outputFileName
    ) {
        try {
            Formatter output = new Formatter(new File(outputFileName));
            AvailableVehiclesInAParkController aviap = new AvailableVehiclesInAParkController();
            GetParkIDByCoordOrViceVersaController gid = new GetParkIDByCoordOrViceVersaController();

            List<Scooter> listOfScooters = new ArrayList<>();
            String pID = gid.getParkIDByCoord(parkLatitudeInDegrees, parkLongitudeInDegrees);
            listOfScooters = aviap.getAllAvailableScooterInPark(pID);
            for (Scooter b : listOfScooters) {
                output.format(b.getVehicleID());
                output.format("%n");

            }
            return listOfScooters.size();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    /**
     * Get the list of escooters parked at a given park.
     *
     * @param parkIdentification The Park Identification.
     * @param outputFileName Path to file where output should be written,
     * according to file output/escooters.csv. Sort in ascending order by bike
     * description.
     * @return The number of escooters at a given park.
     */
    @Override
    public int getNumberOfEScootersAtPark(String parkIdentification,
            String outputFileName) {
        try {
            Formatter output = new Formatter(new File(outputFileName));
            AvailableVehiclesInAParkController aviap = new AvailableVehiclesInAParkController();
            GetParkIDByCoordOrViceVersaController gid = new GetParkIDByCoordOrViceVersaController();

            List<Scooter> listOfScooters = new ArrayList<>();
            listOfScooters = aviap.getAllAvailableScooterInPark(parkIdentification);
            for (Scooter b : listOfScooters) {
                output.format(b.getVehicleID());
                output.format("%n");

            }
            return listOfScooters.size();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    /**
     * Get a list of the nearest parks to the user.
     *
     * @param userLatitudeInDegrees User latitude in Decimal Degrees.
     * @param userLongitudeInDegrees User longitude in Decimal Degrees.
     * @param outputFileName Path to file where output should be written,
     * according to file output/locations.csv. Sort by distance in ascending
     * order.
     */
    @Override
    public void getNearestParks(double userLatitudeInDegrees,
            double userLongitudeInDegrees, String outputFileName) {
        Formatter output;
        try {
            output = new Formatter(new File(outputFileName));

            NearestParksController npc = new NearestParksController();
            List<Park> nearest = new ArrayList<>();
            nearest = npc.getNearestParks(userLatitudeInDegrees, userLongitudeInDegrees, 1);

            for (Park p : nearest) {
                output.format(p.getLocationId());
                output.format("%n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get a list of the nearest parks to the user.
     *
     * @param userLatitudeInDegrees User latitude in Decimal Degrees.
     * @param userLongitudeInDegrees User longitude in Decimal Degrees.
     * @param outputFileName Path to file where output should be written,
     * according to file output/locations.csv. Sort by distance in ascending
     * order.
     * @param radius The radius in meters to which extent the user desires the
     * results to be returned within.
     */
    @Override
    public void getNearestParks(double userLatitudeInDegrees,
            double userLongitudeInDegrees, String outputFileName,
            int radius) {
        Formatter output;
        try {
            output = new Formatter(new File(outputFileName));

            NearestParksController npc = new NearestParksController();
            List<Park> nearest = new ArrayList<>();
            nearest = npc.getNearestParks(userLatitudeInDegrees, userLongitudeInDegrees, radius);

            for (Park p : nearest) {
                output.format(p.getLocationId());
                output.format("%n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the number of free bicyle parking places at a given park for the
     * loaned bicycle.
     *
     * @param parkIdentification The Park Identification.
     * @param username The username that has an unlocked it.
     *
     * @return The number of free slots at a given park for the user's bicycle
     * type.
     */
    public int getFreeBicycleSlotsAtPark(String parkIdentification, String username) {
        try {
            AvailableSlotsController asct = new AvailableSlotsController();
            return asct.availableSlotsBike(parkIdentification);

        } catch (IOException ex) {
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Get the number of free escooters parking places at a given park for the
     * loaned scooter.
     *
     * @param parkIdentification The Park Identification.
     * @param username The username that has an unlocked it.
     *
     * @return The number of free slots at a given park for the user's vehicle.
     *
     */
    public int getFreeEscooterSlotsAtPark(String parkIdentification, String username) {
        try {
            AvailableSlotsController asct = new AvailableSlotsController();
            return asct.availableSlotsScooter(parkIdentification);

        } catch (IOException ex) {
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(Facade.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
//FALTA ISTO

    /**
     * Get the number of free parking places at a given park for the user's
     * loaned vehicle.
     *
     * @param username The username that has an unlocked it.
     * @param parkIdentification The Park Identification.
     *
     * @return The number of free slots at a given park for the user's vehicle.
     *
     */
    @Override
    public int getFreeSlotsAtParkForMyLoanedVehicle(String username,
            String parkIdentification) {

        return -1;
    }

    /**
     * Get the linear distance from one location to another.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal Degrees.
     * @param originLongitudeInDegrees Origin longitude in Decimal Degrees.
     * @param destinyLatitudeInDegrees Destiny latitude in Decimal Degrees.
     * @param destinyLongitudeInDegrees Destiny longitude in Decimal Degrees.
     * @return Returns the distance in meters from one location to another.
     */
    @Override
    public int linearDistanceTo(double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinyLatitudeInDegrees,
            double destinyLongitudeInDegrees) {
        double res = PhysicsAlgorithms.distanceBetweenPoints(originLatitudeInDegrees, originLongitudeInDegrees, destinyLatitudeInDegrees, destinyLongitudeInDegrees, 0, 0);
        return (int) res * 1000;
    }
//FALTA

    /**
     * Get the shortest path distance from one location to another.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal Degrees.
     * @param originLongitudeInDegrees Origin longitude in Decimal Degrees.
     * @param destinyLatitudeInDegrees Destiny latitude in Decimal Degrees.
     * @param destinyLongitudeInDegrees Destiny longitude in Decimal Degrees.
     * @return Returns the distance in meters from one location to another.
     */
    @Override
    public int pathDistanceTo(double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinyLatitudeInDegrees,
            double destinyLongitudeInDegrees
    ) {
        return -1;
    }

    /**
     * Unlocks a specific bicycle.
     *
     * @param username User that requested the unlock.
     * @param bicycleDescription Bicycle description to unlock.
     * @return The time in milliseconds at which it was unlocked.
     */
    @Override
    public long unlockBicycle(String bicycleDescription, String username
    ) {
        try {
            UnlockBicycleController ubc = new UnlockBicycleController();
            ubc.addAndCreateTrip(username, bicycleDescription);
            return System.currentTimeMillis();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Unlocks a specific escooter.
     *
     * @param username User that requested the unlock.
     * @param escooterDescription Escooter description to unlock.
     * @return The time in milliseconds at which it was unlocked.
     */
    @Override
    public long unlockEscooter(String escooterDescription, String username
    ) {
        try {
            UnlockScooterController usc = new UnlockScooterController();
            usc.addAndCreateTrip(username, escooterDescription);
            return System.currentTimeMillis();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Lock a specific bicycle at a park.
     *
     * Basic: Lock a specific bicycle at a park. Intermediate: Create an invoice
     * line for the loaned vehicle. Advanced: Add points to user.
     *
     * @param bicycleDescription Bicycle to lock.
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param username User that requested the unlock.
     * @return The time in milliseconds at which the bicycle was locked.
     */
    @Override
    public long lockBicycle(String bicycleDescription, double parkLatitudeInDegrees,
            double parkLongitudeInDegrees, String username
    ) {
        try {

            LockBicycleController lbc = new LockBicycleController();

            lbc.endBikeTrip(bicycleDescription, parkLatitudeInDegrees, parkLongitudeInDegrees, username);
            return System.currentTimeMillis();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Lock a specific bicycle at a park.
     *
     * Basic: Lock a specific bicycle at a park. Intermediate: Create an invoice
     * line for the loaned vehicle. Advanced: Add points to user.
     *
     * @param bicycleDescription Bicycle to lock.
     * @param parkIdentification The Park Identification.
     * @param username User that requested the unlock.
     * @return The time in milliseconds at which the bicycle was locked.
     */
    @Override
    public long lockBicycle(String bicycleDescription, String parkIdentification,
            String username
    ) {
        try {
            LockBicycleController lbc = new LockBicycleController();
            GetParkIDByCoordOrViceVersaController gpid = new GetParkIDByCoordOrViceVersaController();

            List<Double> coords = gpid.getCoordinatesByParkID(username);

            lbc.endBikeTrip(bicycleDescription, coords.get(0), coords.get(1), username);
            return System.currentTimeMillis();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Lock a specific escooter at a park.
     *
     * Basic: Lock a specific escooter at a park. Intermediate: Create an
     * invoice line for the loaned vehicle. Advanced: Add points to user.
     *
     * @param escooterDescription Escooter to lock.
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param username User that requested the unlock.
     * @return The time in milliseconds at which it was locked.
     */
    @Override
    public long lockEscooter(String escooterDescription, double parkLatitudeInDegrees,
            double parkLongitudeInDegrees, String username
    ) {
        try {
            LockScooterController lsc = new LockScooterController();
            lsc.endScooterTrip(escooterDescription, parkLatitudeInDegrees, parkLongitudeInDegrees, username);
            return System.currentTimeMillis();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Lock a specific escooter at a park.
     *
     * Basic: Lock a specific escooter at a park. Intermediate: Create an
     * invoice line for the loaned vehicle. Advanced: Add points to user.
     *
     * @param escooterDescription Escooter to lock.
     * @param parkIdentification The Park Identification.
     * @param username User that requested the unlock.
     * @return The time in milliseconds at which it was locked.
     */
    @Override
    public long lockEscooter(String escooterDescription, String parkIdentification,
            String username
    ) {
        try {
            LockScooterController lsc = new LockScooterController();
            GetParkIDByCoordOrViceVersaController gpid = new GetParkIDByCoordOrViceVersaController();

            List<Double> coords = gpid.getCoordinatesByParkID(username);

            lsc.endScooterTrip(escooterDescription, coords.get(0), coords.get(1), username);
            return System.currentTimeMillis();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Register a user on the system.
     *
     * @param username User's username.
     * @param email User's email.
     * @param password User's desired password.
     * @param visaCardNumber User's Visa Card number.
     * @param height User's height in cm.
     * @param weight User's weight in kg.
     * @param averageCyclingSpeed User's average speed in m/s with two decimal
     * places e.g 4.17.
     * @param gender User's gender in text.
     * @return Return 1 if a user is successfully registered.
     */
    @Override
    public int registerUser(String username, String email, String password,
            String visaCardNumber,
            int height, int weight,
            BigDecimal averageCyclingSpeed,
            String gender) {
        AddRegisteredUserController aruc;
        try {
            aruc = new AddRegisteredUserController();
            RegisteredUser user = new RegisteredUser(username, email, gender, height, weight, averageCyclingSpeed.doubleValue(), Long.parseLong(visaCardNumber), password, 0);

            aruc.addRegisteredUser(user);
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }
//FALTA

    /**
     * Unlocks any escooter at one park. It should unlock the one with higher
     * battery capacity.
     *
     * @param parkIdentification Park Identification where to unlock escooter.
     * @param username User that requested the unlock.
     * @param outputFileName Write the unlocked vehicle information to a file,
     * according to file output/escooters.csv.
     * @return The time in milliseconds at which it was unlocked.
     */
    @Override
    public long unlockAnyEscooterAtPark(String parkIdentification,
            String username,
            String outputFileName
    ) {
//        try {
//            UnlockScooterController ulc = new UnlockScooterController();
//            ulc.addAndCreateTrip(username, username);
//            return System.currentTimeMillis();
//        } catch (IOException | SQLException ex) {
//            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return -1;
    }

    //FALTA ISTO
    /**
     * Unlocks any escooter at one park that allows travelling to the
     * destination.
     *
     * @param parkIdentification Park Identification where to unlock escooter.
     * @param username User that requested the unlock.
     * @param destinyLatitudeInDegrees Destiny latitude in Decimal Degrees.
     * @param destinyLongitudeInDegrees Destiny longitude in Decimal Degrees.
     * @param outputFileName Write the unlocked vehicle information to a file,
     * according to file output/escooters.csv.
     * @return The time in milliseconds at which it was unlocked.
     */
    @Override
    public long unlockAnyEscooterAtParkForDestination(String parkIdentification,
            String username,
            double destinyLatitudeInDegrees,
            double destinyLongitudeInDegrees,
            String outputFileName
    ) {
        return -1;
    }

    /**
     * Suggest escooters with enough energy + 10% to go from one Park to
     * another.
     *
     * @param parkIdentification Park Identification where to unlock escooter.
     * @param username Username.
     * @param destinationParkLatitudeInDegrees Destination Park latitude in
     * Decimal degrees.
     * @param destinationParkLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     *
     * @param outputFileName Write the escooters information to a file,
     * according to file output/escooters.csv.
     * @return The number of suggested vehicles.
     */
    @Override
    public int suggestEscootersToGoFromOneParkToAnother(
            String parkIdentification,
            String username,
            double destinationParkLatitudeInDegrees,
            double destinationParkLongitudeInDegrees,
            String outputFileName
    ) {
        try {
            Formatter output = new Formatter(new File(outputFileName));
            LocationDB LocationDB = new LocationDB();

            GetParkIDByCoordOrViceVersaController gpid = new GetParkIDByCoordOrViceVersaController();
            ScooterRecommendationController src = new ScooterRecommendationController();

            String dest = gpid.getParkIDByCoord(destinationParkLatitudeInDegrees, destinationParkLongitudeInDegrees);

            Park start = LocationDB.getPark(parkIdentification);
            Park end = LocationDB.getPark(dest);

            List<Location> empty = new ArrayList<>();

            CreateGraphs cg = new CreateGraphs();

            Graph<Location, Double> mainGraph = cg.createGraphEnergy();

            Scooter winner = src.recommendateScooter(start, end, mainGraph, empty);

            output.format(winner.getVehicleID());
            output.format("%n");
            return 1;

        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Calculate the most energy efficient route from one park to another.
     *
     * Basic: Does not consider wind. Intermediate: Considers wind. Advanced:
     * Considers the different mechanical and aerodynamic coefficients.
     *
     * @param originParkIdentification Origin Park Identification.
     * @param destinationParkIdentification Destination Park Identification.
     *
     * @param typeOfVehicle The type of vehicle required e.g. "bicycle" or
     * "escooter".
     * @param vehicleSpecs The specs for the vehicle e.g. "16", "19", "27" or
     * any other number for bicyles and "city" or "off-road" for any escooter.
     * @param username The username.
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist. If so,
     * sort routes by the ascending number of points between the parks and by
     * ascending order of elevation difference.
     * @return The distance in meters for the most energy efficient path.
     */
    @Override
    public long mostEnergyEfficientRouteBetweenTwoParks(
            String originParkIdentification,
            String destinationParkIdentification,
            String typeOfVehicle,
            String vehicleSpecs,
            String username,
            String outputFileName
    ) {
        CreateGraphs cg;
        try {

            cg = new CreateGraphs();
            Formatter output = new Formatter(new File(outputFileName));
            Graph<Location, Double> mainGraph = null;

            if (typeOfVehicle.equalsIgnoreCase("escooter")) {
                mainGraph = cg.createGraphEnergy();
            } else {
                mainGraph = cg.createGraphCalories();
            }
            ShortestPathsController spc = new ShortestPathsController();

            LocationDB locDB = new LocationDB();
            Park source = locDB.getPark(originParkIdentification);
            Park dest = locDB.getPark(destinationParkIdentification);

            LinkedList<Location> parkList = new LinkedList<>();

            Route r = spc.shortestPath2Parks(source, dest, parkList, mainGraph);

            output.format(r.toString());

            return 1;

        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
//FALTA

    /**
     * Return the current debt for the user.
     *
     * @param username The username.
     * @param outputFileName The path for the file to output the debt, according
     * to file output/balance.csv. Sort the information by unlock time in
     * ascending order (oldest to newest).
     * @return The User's current debt in euros, rounded to two decimal places.
     */
    @Override
    public double getUserCurrentDebt(String username, String outputFileName
    ) {
        return -1;
    }

    /**
     * Return the current points for the user.
     *
     * @param username The user to get the points report from.
     * @param outputFileName The path for the file to output the points,
     * according to file output/points.csv. Sort the information by unlock time
     * in ascenind order (oldest to newest).
     * @return The User's current points.
     */
    @Override
    public double getUserCurrentPoints(String username, String outputFileName
    ) {
        try {
            Formatter output = new Formatter(new File(outputFileName));
            RegisteredUserDB rudb = new RegisteredUserDB();
            RegisteredUser user = rudb.getRegisteredUser(username);
            int pontos = user.getPoints();
            output.format("%d", pontos);
            return user.getPoints();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Calculate the amount of electrical energy required to travel from one
     * park to another.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in Decimal
     * degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     * @param username Username.
     * @return The electrical energy required in kWh, rounded to two decimal
     * places.
     */
    @Override
    public double calculateElectricalEnergyToTravelFromOneLocationToAnother(
            double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinationLatitudeInDegrees,
            double destinationLongitudeInDegrees,
            String username
    ) {
        return -1;
    }
//falta

    /**
     * Get for how long has a vehicle has been unlocked.
     *
     * @param vehicleDescription Vehicle description.
     * @return The time in seconds since the vehicle was unlocked.
     */
    @Override
    public long forHowLongAVehicleIsUnlocked(String vehicleDescription
    ) {

        return -1;
    }

    /**
     * Calculate the shortest Route from one park to another.
     *
     * Basic: Only one shortest Route between two Parks is available. Advanced:
     * More than one Route between two parks are available with different number
     * of POIs (limit to a maximum of two) inbetween and different evelations
     * difference.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in Decimal
     * degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     * @param numberOfPOIs The number of POIs that should be included in the
     * path. Default can be 0.
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist. If so,
     * sort routes by the ascending number of points between the parks and by
     * ascending order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    public long shortestRouteBetweenTwoParks(
            double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinationLatitudeInDegrees,
            double destinationLongitudeInDegrees,
            int numberOfPOIs,
            String outputFileName) {

        try {
            Formatter output = new Formatter(new File(outputFileName));
            LocationDB ldb = new LocationDB();
            List<Location> poiList = ldb.randomNums(numberOfPOIs);

            ShortestPathsController spc = new ShortestPathsController();

            LocationDB locDB = new LocationDB();

            GetParkIDByCoordOrViceVersaController gpid = new GetParkIDByCoordOrViceVersaController();

            gpid.getParkIDByCoord(originLatitudeInDegrees, originLongitudeInDegrees);

            Park source = locDB.getPark(gpid.getParkIDByCoord(originLatitudeInDegrees, originLongitudeInDegrees));
            Park dest = locDB.getPark(gpid.getParkIDByCoord(destinationLatitudeInDegrees, destinationLongitudeInDegrees));

            Graph<Location, Double> mainGraph = null;

            CreateGraphs cg = new CreateGraphs();

            mainGraph = cg.createGraphDistance();

            Route r = spc.shortestPath2ParksPOIS(source, dest, poiList, mainGraph);

            output.format("%d", r.getCost() * 1000);

            return (long) r.getCost() * 1000;

        } catch (SQLException | IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Calculate the shortest Route from one park to another.
     *
     * Basic: Only one shortest Route between two Parks is available. Advanced:
     * More than one Route between two parks are available with different number
     * of POIs (limit to a maximum of two) inbetween and different evelations
     * difference.
     *
     * @param originParkIdentification Origin Park Identification.
     * @param destinationParkIdentification Destination Park Identification.
     * @param numberOfPOIs The number of POIs that should be included in the
     * path. Default can be 0.
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist. If so,
     * sort routes by the ascending number of points between the parks and by
     * ascending order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    @Override
    public long shortestRouteBetweenTwoParks(
            String originParkIdentification,
            String destinationParkIdentification,
            int numberOfPOIs,
            String outputFileName) {

        try {
            Formatter output = new Formatter(new File(outputFileName));
            LocationDB ldb = new LocationDB();
            List<Location> poiList = ldb.randomNums(numberOfPOIs);

            ShortestPathsController spc = new ShortestPathsController();

            LocationDB locDB = new LocationDB();

            GetParkIDByCoordOrViceVersaController gpid = new GetParkIDByCoordOrViceVersaController();

            Park source = locDB.getPark(originParkIdentification);
            Park dest = locDB.getPark(destinationParkIdentification);

            Graph<Location, Double> mainGraph = null;

            LinkedList<Location> parkList = new LinkedList<>();

            CreateGraphs cg = new CreateGraphs();

            mainGraph = cg.createGraphDistance();

            Route r = spc.shortestPath2ParksPOIS(source, dest, poiList, mainGraph);

            output.format(r.toString());

            return (long) r.getCost() * 1000;

        } catch (SQLException | IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Calculate the shortest Route from one park to another.
     *
     * Basic: Only one shortest Route between two Parks is available. Advanced:
     * More than one Route between two parks are available with different number
     * of points inbetween and different evelations difference.
     *
     * @param originParkIdentification Origin Park Identification.
     * @param destinationParkIdentification Destination Park Identification.
     * @param inputPOIs Path to file that contains the POIs that the route must
     * go through, according to file input/pois.csv.
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist. If so,
     * sort routes by the ascending number of points between the parks and by
     * ascending order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    @Override
    public long shortestRouteBetweenTwoParksForGivenPOIs(
            String originParkIdentification,
            String destinationParkIdentification,
            String inputPOIs,
            String outputFileName
    ) {
        try {
            Formatter output = new Formatter(new File(outputFileName));
            LocationDB ldb = new LocationDB();
//            List<Location> poiList = ldb.randomNums(numberOfPOIs);
            List<Location> poiList = new ArrayList<>();
            Scanner scan = new Scanner(inputPOIs);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                PointOfInterest poi = ldb.getPoint(line);
                poiList.add(poi);
            }

            ShortestPathsController spc = new ShortestPathsController();

            Park source = ldb.getPark(originParkIdentification);
            Park dest = ldb.getPark(destinationParkIdentification);

            Graph<Location, Double> mainGraph = null;

            CreateGraphs cg = new CreateGraphs();

            mainGraph = cg.createGraphDistance();

            Route r = spc.shortestPath2ParksPOIS(source, dest, poiList, mainGraph);

            output.format(r.toString());

            return (long) r.getCost() * 1000;

        } catch (SQLException | IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Calculate the shortest Route from one park to another.
     *
     * Basic: Only one shortest Route between two Parks is available. Advanced:
     * More than one Route between two parks are available with different number
     * of points inbetween and different evelations difference.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in Decimal
     * degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     * Decimal degrees.
     * @param inputPOIs Path to file that contains the POIs that the route must
     * go through, according to file input/pois.csv.
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist. If so,
     * sort routes by the ascending number of points between the parks and by
     * ascending order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    @Override
    public long shortestRouteBetweenTwoParksForGivenPOIs(
            double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinationLatitudeInDegrees,
            double destinationLongitudeInDegrees,
            String inputPOIs,
            String outputFileName
    ) {
        try {
            Formatter output = new Formatter(new File(outputFileName));
            LocationDB ldb = new LocationDB();
//            List<Location> poiList = ldb.randomNums(numberOfPOIs);
            List<Location> poiList = new ArrayList<>();
            Scanner scan = new Scanner(inputPOIs);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                PointOfInterest poi = ldb.getPoint(line);
                poiList.add(poi);
            }
            ShortestPathsController spc = new ShortestPathsController();

            GetParkIDByCoordOrViceVersaController gpid = new GetParkIDByCoordOrViceVersaController();

            Park source = ldb.getPark(gpid.getParkIDByCoord(originLatitudeInDegrees, originLongitudeInDegrees));
            Park dest = ldb.getPark(gpid.getParkIDByCoord(destinationLatitudeInDegrees, destinationLongitudeInDegrees));

            Graph<Location, Double> mainGraph = null;

            CreateGraphs cg = new CreateGraphs();

            mainGraph = cg.createGraphDistance();

            Route r = spc.shortestPath2ParksPOIS(source, dest, poiList, mainGraph);

            output.format(r.toString());

            return (long) r.getCost() * 1000;

        } catch (SQLException | IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
//Falta

    /**
     * Get a report for the escooter charging status at a given park.
     *
     * @param parkIdentification Park Identification.
     * @param outputFileName Path to file where vehicles information should be
     * written, according to file output/chargingReport .csv. Sort items by
     * descending order of time to finish charge in seconds and secondly by
     * ascending escooter description order.
     * @return The number of escooters charging at the moment that are not 100%
     * fully charged.
     */
    @Override
    public long getParkChargingReport(
            String parkIdentification,
            String outputFileName
    ) {

        return -1;
    }
//falta

    /**
     * Calculate the most energetically efficient route from one park to another
     * with sorting options.
     *
     * @param originParkIdentification Origin Park Identification.
     * @param destinationParkIdentification Destination Park Identification.
     *
     * @param typeOfVehicle The type of vehicle required e.g. "bicycle" or
     * "escooter".
     * @param vehicleSpecs The specs for the vehicle e.g. "16", "19", "27" or
     * any other number for bicyles and "city" or "off-road" for any escooter.
     * @param username The user that asked for the routes.
     * @param maxNumberOfSuggestions The maximum number of suggestions to
     * provide.
     * @param ascendingOrder If routes should be ordered by ascending or
     * descending order
     * @param sortingCriteria The criteria to use for ordering "energy",
     * "shortest_distance", "number_of_points".
     * @param inputPOIs Path to file that contains the POIs that the route must
     * go through, according to file input/pois.csv. By default, the file is
     * empty.
     * @param outputFileName Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist.
     * @return The number of suggestions
     */
    @Override
    public int suggestRoutesBetweenTwoLocations(
            String originParkIdentification,
            String destinationParkIdentification,
            String typeOfVehicle,
            String vehicleSpecs,
            String username,
            int maxNumberOfSuggestions,
            boolean ascendingOrder,
            String sortingCriteria,
            String inputPOIs,
            String outputFileName
    ) {
        return -1;
    }

    /**
     * Get the current invoice for the current month, for a specific user. This
     * should include all loans that were charged the user, the number of points
     * the user had before the actual month, the number of points earned during
     * the month, the number of points converted to euros.
     *
     * @param month The month of the invoice e.g. 1 for January.
     * @param username The user for which the invoice should be created.
     * @param outputPath Path to file where the invoice should be written,
     * according to file output/invoice.csv.
     * @return User debt in euros rounded to two decimal places.
     */
    @Override
    public double getInvoiceForMonth(int month, String username,
            String outputPath
    ) {
        return -1;
    }

}
