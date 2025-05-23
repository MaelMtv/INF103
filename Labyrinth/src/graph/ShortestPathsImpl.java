package graph;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ShortestPathsImpl extends HashMap<Vertex, Vertex> implements ShortestPaths {

    /**
     * Returns the list of the shortest path from the start of the graph to vertex
     * @param vertex the end of the path thatwe return 
     * @return the list of the shortest path from the start of the graph to vertex
     */
    public List<Vertex> getShortestPath(Vertex vertex){
        List<Vertex> shortestPath = new ArrayList<Vertex>();
        while(vertex != null){
            shortestPath.add(vertex);
            vertex = getPrevious(vertex);
        }
        return shortestPath;
    }

    public ShortestPathsImpl(){
        super();
    }

    /**
     * Sets vertex2 as a predecessor for vertex1
     * @param vertex1 succesor of vertex2
     * @param vertex2 predecessor of vertex1
     */
    public void setPrevious(Vertex vertex1, Vertex vertex2) {
       this.put(vertex1, vertex2);
    }

    /**
     * Returns the predecessor of vertex, only used inside the class but is declared in an interface so cannot be declared private
     * @param vertex the vertex that we want to know the predecessor of
     * @return the predecesoor of vertex
     */
    public Vertex getPrevious(Vertex vertex) {
        return this.get(vertex);
    }
}
