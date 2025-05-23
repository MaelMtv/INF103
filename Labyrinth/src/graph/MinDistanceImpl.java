package graph;
import java.lang.Integer;
import java.util.HashMap;

public class MinDistanceImpl extends HashMap<Vertex, Integer> implements MinDistance {

    public MinDistanceImpl(){
        super();
    }

    /**
     * Sets value integer to vertex's mindistance to the start of the path
     * @param vertex the vertex that we want to change the mindistance of
     * @param integer new mindistance
     */
    public void setDistance(Vertex vertex, int integer) {
        put(vertex, integer);
    }

    /**
     * Returns mindistance between vertex and the start of the path
     * @param vertex the vertex that we want to get the mindistance of
     * @return the minimum distance between vertex and the start of the path
     */
    public int getDistance(Vertex vertex) {
        return get(vertex);
    }  
}
