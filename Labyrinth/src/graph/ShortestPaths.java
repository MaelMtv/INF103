package graph;
import java.util.List;

public interface ShortestPaths {
    
    /**
     * Sets vertex2 as a predecessor for vertex1
     * @param vertex1 succesor of vertex2
     * @param vertex2 predecessor of vertex1
     */
    public void setPrevious(Vertex vertex1, Vertex vertex2);
    
    /**
     * Returns the predecessor of vertex
     * @param vertex the vertex that we want to know the predecessor of
     * @return the predecesoor of vertex
     */
    public Vertex getPrevious(Vertex vertex);
    
    /**
     * Returns the list of the shortest path from the start of the graph to vertex
     * @param vertex the end of the path thatwe return 
     * @return the list of the shortest path from the start of the graph to vertex
     */
    public List<Vertex> getShortestPath(Vertex vertex);
}
