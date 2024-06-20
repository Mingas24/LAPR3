package lapr.project.model;

import lapr.project.model.Location.Location;
import lapr.project.model.Location.PointOfInterest;
import java.util.LinkedList;
import java.util.List;
import lapr.project.utils.Permutations;
import lapr.project.utils.graphbase.*;

/**
 *
 * @author Nuno Capela
 */
public class ShortestPaths {

    Graph<Location, Double> mainGraph;
    List<Location> parkList;
    double distance;

    /**
     * Method that calculates the shortest route between two parks
     *
     * @param source source park
     * @param dest destination park
     * @param parkList list of vertexes containing the shortest route
     * @param mainGraph graph for wich we want to calculate the shortest route
     * @return
     */
    public Route shortestPath2Parks(Location source, Location dest, LinkedList<Location> parkList, Graph<Location, Double> mainGraph) {

        if (source instanceof PointOfInterest || dest instanceof PointOfInterest || source == null || dest == null) {
            return new Route();
        }

        distance = GraphAlgorithms.shortestPath(mainGraph, source, dest, parkList);

        return new Route(source, dest, parkList, distance);

    }

    /**
     * Method that calculates the shortest route between two parks that goes by
     * at least a certain number of interest points, which can be specified by
     * the user.
     *
     * @param source source vertex
     * @param dest destination vertex
     * @param mainGraph graph for wich we are calculating the shortest route
     * @param poiList list containig the pois that should be visited
     * @return list containing the shortest route
     */
    public Route shortestPathPOI(Location source, Location dest, Graph<Location, Double> mainGraph, List<Location> poiList) {

        try {

            if (source instanceof PointOfInterest || dest instanceof PointOfInterest || source == null || dest == null || poiList.isEmpty()) {
                return new Route();
            }

            if (source.equals(dest)) {
                LinkedList<Location> singlePath = new LinkedList<>();
                singlePath.add(source);
                return new Route(source, source, singlePath, 0);
            }

            LinkedList<Location> shortestPath = new LinkedList<>();

            LinkedList<LinkedList<Location>> permutationList = new LinkedList<>();
            Location[] poiArray = new Location[poiList.size()];
            poiArray = poiList.toArray(poiArray);

            Permutations.heapPermutation(poiArray, poiList.size(), poiList.size(), permutationList);

            LinkedList<Location> tempPath = new LinkedList<>();
            LinkedList<Location> aux = new LinkedList<>();

            double minDistance = Double.MAX_VALUE;
            double tempDistance = 0;

            for (int i = 0;
                    i < permutationList.size();
                    i++) {
                permutationList.get(i).addFirst(source);
                permutationList.get(i).addLast(dest);
                tempPath.add(permutationList.get(i).get(0));

                for (int h = 0; h < permutationList.get(i).size() - 1; h++) {
                    tempDistance += GraphAlgorithms.
                            shortestPath(mainGraph, permutationList.get(i).get(h), permutationList.get(i).get(h + 1), aux);
                    aux.removeFirst();
                    tempPath.addAll(aux);
                    aux.clear();
                }

                if (tempDistance < minDistance) {
                    shortestPath.clear();
                    shortestPath.addAll(tempPath);
                    minDistance = tempDistance;

                }

                tempPath.clear();
                aux.clear();
                tempDistance = 0;

            }

            return new Route(source, dest, shortestPath, minDistance);
        } catch (Exception e) {
            return new Route();
        }

    }
}
