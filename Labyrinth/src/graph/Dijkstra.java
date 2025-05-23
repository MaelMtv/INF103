package graph;
import java.util.List;
import java.lang.Integer;

public class Dijkstra{
    /**
     * Returns the shortest path from startVertex to endVertex of there is one
     * @param graph the graph on which we apply the algorithm
     * @param startVertex the start of the path
     * @param endVertex the end of the path
     * @return shortest path from startVertex to endVertex of there is one
     */
    public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex){
        ProcessedVertexesImpl processedVertexes = new ProcessedVertexesImpl();//graph.getProcessedVertexes();
        MinDistanceImpl minD = new MinDistanceImpl();
        ShortestPathsImpl shortestPath = new ShortestPathsImpl();
        List<Vertex> allVertexes = graph.getAllVertexes();
        processedVertexes.add(startVertex);
        minD.setDistance(startVertex, 0);
        Vertex pivot = startVertex;

        for(Vertex vertex: allVertexes){
            if(vertex != startVertex){
                minD.setDistance(vertex, (Integer.MAX_VALUE-3));
            }
        }
        for(@SuppressWarnings("unused") Vertex v : allVertexes){
            List<Vertex> successeurs = graph.successors(pivot);
            for(Vertex vertex : successeurs){
                if(!processedVertexes.contains(vertex)){
                    int newDistance = minD.get(pivot)+graph.getDistance(pivot,vertex);
                    if( newDistance < minD.getDistance(vertex)){
                        minD.setDistance(vertex, newDistance);
                        shortestPath.setPrevious(vertex, pivot);
                    }
                }
            }
            int min = Integer.MAX_VALUE;
            Vertex minVertex = null;
            for(Vertex vertex : allVertexes){
                if(!processedVertexes.contains(vertex) && minD.getDistance(vertex) < min){
                    min = minD.getDistance(vertex);
                    minVertex = vertex;
                }
            }
            pivot = minVertex;
            processedVertexes.add(pivot);
        }
        return shortestPath;
    }
}