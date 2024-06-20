package lapr.project.controller;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Location.Location;
import lapr.project.model.Route;
import lapr.project.model.ShortestPaths;
import lapr.project.utils.graphbase.Graph;

/**
 *
 * @author Nuno Capela
 */
public class ShortestPathsController {

    /**
     *
     */
    public ShortestPathsController() {
    }

    /**
     * Method that calculates the shortest path between two different parks
     *
     * @param source vertex 
     * @param dest vertex
     * @param parkList list of parks that contain the shortest path
     * @param Graph for which we want to calculate the shortest path
     * @return result of the shortest path calculation
     */
    public Route shortestPath2Parks(Location source, Location dest, LinkedList<Location> parkList, Graph<Location, Double> Graph) throws RuntimeException,NullPointerException {
        ShortestPaths sp = new ShortestPaths();
        Route result = sp.shortestPath2Parks(source, dest, parkList, Graph);
        return result;
    }

    /**
     * Method that calculates the shortest route between two parks that goes by
     * at least a certain number of interest points, which can be specified by
     * the user.
     *
     * @param source vertex
     * @param dest vertex
     * @param poiList list of points of interest that we want to visit 
     * @param Graph for which we want to calculate the shortest path
     * @return result of the calculation
     */
    public Route shortestPath2ParksPOIS(Location source, Location dest, List<Location> poiList, Graph<Location, Double> Graph) throws RuntimeException, NullPointerException {
        ShortestPaths sp = new ShortestPaths();
        Route result = sp.shortestPathPOI(source, dest, Graph, poiList);
        return result;
    }

}
