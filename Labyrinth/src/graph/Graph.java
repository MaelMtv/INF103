package graph;
import java.util.List;

public interface Graph {
     /**
      * Returns the list of neighbors of a vertex
      * @param vertex the vertex whose neighbors we want to find
      * @return the list of neighbors that are not walls
      */
     public List<Vertex> successors(Vertex vertex);

     /**
      * Returns the list of all the vertexes in the graph
      * @return the list of all the vertexes in the graph
      */
     public List<Vertex> getAllVertexes();

     /**
     * Returns the distance between vertex1 and vertex2
     * @param src first vertex
     * @param dst second vertex
     * @return 1 if vertex1 and vertex2 are next to each other and both are not walls, +infinity otherwise
     */
     public int getDistance(Vertex src,Vertex dst) ;
}
