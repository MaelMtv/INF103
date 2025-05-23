package graph;
import java.util.HashSet;

public class ProcessedVertexesImpl extends HashSet<Vertex> implements ProcessedVertexes {

    public ProcessedVertexesImpl(){
        super();
    }
    
    /**
     * Checks if this contains vertex, actually useless since HashSet already has a method contains
     * @param vertex the vertex that we want to know whether it is in this or not
     * @return true if this contains vertex, false otherwise
     */
    public boolean contains2(Vertex vertex){
        return contains(vertex);
    }

    /**
     * Adds vertex to this, actually useless since HashSet already has a method add
     * @param vertex the vertex we want to add
     */
    public void add2(Vertex vertex){
        add(vertex);
    }
}
