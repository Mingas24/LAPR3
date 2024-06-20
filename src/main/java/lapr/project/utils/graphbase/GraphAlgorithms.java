package lapr.project.utils.graphbase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithms {

    private GraphAlgorithms() {
        //Do nothing because it has no parameters
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights This implementation
     * uses Dijkstra's algorithm
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist minimum distances
     */
    protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, V[] vertices,
            boolean[] visited, int[] pathKeys, double[] dist) {

        int vOrigKey = g.getKey(vOrig);
        dist[vOrigKey] = 0;
        while (vOrigKey != -1) {
            vOrig = vertices[vOrigKey];
            visited[vOrigKey] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                Edge<V, E> edge = g.getEdge(vOrig, vAdj);
                int vAdjKey = g.getKey(vAdj);
                if (!visited[vAdjKey] && dist[vAdjKey] > dist[vOrigKey] + edge.getWeight()) {
                    dist[vAdjKey] = dist[vOrigKey] + edge.getWeight();
                    pathKeys[vAdjKey] = vOrigKey;
                }
            }
            vOrigKey = getVertMinDist(dist, visited);
        }
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vDest Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path stack with vertices of the current path (the path is in
     * reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
            LinkedList<V> path, ArrayList<LinkedList<V>> paths) {
        visited[g.getKey(vOrig)] = true;
        path.add(vOrig);
        for (V vAdj : g.adjVertices(vOrig)) {
            if (vAdj.equals(vDest)) {
                path.add(vDest);
                paths.add(revPath(path));
                path.pop();
            } else {
                if (!visited[g.getKey(vAdj)]) {
                    allPaths(g, vAdj, vDest, visited, path, paths);
                }
            }
        }
        visited[g.getKey(vOrig)] = false;
        path.pop();
    }

    /**
     * @param <V>
     * @param <E>
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from voInf to vdInf
     */
    public static <V, E> List<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {
        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return paths;
        }
        boolean[] visited = new boolean[g.numVertices()];
        LinkedList<V> path = new LinkedList<>();
        allPaths(g, vOrig, vDest, visited, path, paths);
        return paths;
    }

    /**
     * Reverses the path
     *
     * @param path stack with path
     */
    private static <V> LinkedList<V> revPath(LinkedList<V> path) {
        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty()) {
            pathrev.push(pathcopy.pop());
        }
        return pathrev;
    }

    /**
     *
     * @param dist list of distances
     * @param visited list of visited vertices
     * @return Index of the vertex with minimum distance.
     */
    public static int getVertMinDist(double[] dist, boolean[] visited) {
        double min = Double.MAX_VALUE;
        int minVertIdx = -1;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (min > dist[i]) {
                    min = dist[i];
                    minVertIdx = i;
                }
            }
        }
        return minVertIdx;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param <V>
     * @param <E>
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @param verts
     * @param pathKeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {
        path.push(vDest);
        int vKey = pathKeys[g.getKey(vDest)];
        if (vKey != -1) {
            vDest = verts[vKey];
            getPath(g, vOrig, vDest, verts, pathKeys, path);
        }

    }

    /**
     * shortest-path between vOrig and vDest
     */
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {
        shortPath.clear();
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }

        if (vOrig.equals(vDest)) {
            shortPath.add(vDest);
            return 0;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] verts = g.allkeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }
        shortestPathLength(g, vOrig, verts, visited, pathKeys, dist);
        if (pathKeys[g.getKey(vDest)] == -1) {
            return 0;
        }
        getPath(g, vOrig, vDest, verts, pathKeys, shortPath);
        return dist[g.getKey(vDest)];
    }

    /**
     * shortest-path between voInf and all other
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists) {
        dists.clear();
        paths.clear();
        if (!g.validVertex(vOrig)) {
            return false;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allkeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);

        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (int i = 0; i < nverts; i++) {
            LinkedList<V> shortPath = new LinkedList<>();

            if (Double.compare(dist[i], Double.MAX_VALUE) != 0) {
                getPath(g, vOrig, vertices[i], vertices, pathKeys, shortPath);
            }
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }
        return true;
    }

}
