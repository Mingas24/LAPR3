package lapr.project.utils.graphbase;

import java.util.Objects;

/**
 *
 * @author DEI-ESINF
 * @param <V>
 * @param <E>
 */
public class Edge<V, E> implements Comparable<E> {

    private E element;           // Edge information
    private double weight;       // Edge weight
    private Vertex<V, E> vOrig;  // vertex origin
    private Vertex<V, E> vDest;  // vertex destination

    public Edge() {
        element = null;
        weight = 0.0;
        vOrig = null;
        vDest = null;
    }

    public Edge(E eInf, double ew, Vertex<V, E> vo, Vertex<V, E> vd) {
        element = eInf;
        weight = ew;
        vOrig = vo;
        vDest = vd;
    }

    /**
     *
     * @param edge
     */
    public Edge(Edge<V, E> edge) {
        element = edge.element;
        weight = edge.weight;
        vOrig = edge.vOrig;
        vDest = edge.vDest;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E eInf) {
        element = eInf;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double ew) {
        weight = ew;
    }

    public V getVOrig() {
        if (this.vOrig != null) {
            return vOrig.getElement();
        }
        return null;
    }

    public void setVOrig(Vertex<V, E> vo) {
        vOrig = vo;
    }

    public V getVDest() {
        if (this.vDest != null) {
            return vDest.getElement();
        }
        return null;
    }

    public void setVDest(Vertex<V, E> vd) {
        vDest = vd;
    }

    @Override

    @SuppressWarnings(value = "unchecked")
    public boolean equals(Object otherObj) {

        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        Edge<V, E> otherEdge = (Edge<V, E>) otherObj;

        // if endpoints vertices are not equal
        if ((this.vOrig == null && otherEdge.vOrig != null)
                || (this.vOrig != null && otherEdge.vOrig == null)) {
            return false;
        }

        if ((this.vDest == null && otherEdge.vDest != null)
                || (this.vDest != null && otherEdge.vDest == null)) {
            return false;
        }

        if (this.vOrig != null && otherEdge.vOrig != null
                && !this.vOrig.equals(otherEdge.vOrig)) {
            return false;
        }

        if (this.vDest != null && otherEdge.vDest != null
                && !this.vDest.equals(otherEdge.vDest)) {
            return false;
        }

        if (Double.compare(this.weight, otherEdge.weight) != 0) {
            return false;
        }

        if (this.element != null && otherEdge.element != null) {
            return this.element.equals(otherEdge.element);
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, weight, vOrig, vDest);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public int compareTo(Object otherObject) {

        Edge<V, E> other = (Edge<V, E>) otherObject;
        if (this.weight < other.weight) {
            return -1;
        }
        if (Double.compare(this.weight, other.weight) == 0) {
            return 0;
        }
        return 1;
    }

}
