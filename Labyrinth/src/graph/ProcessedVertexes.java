package graph;

public interface ProcessedVertexes {

    /**
     * Checks if this contains vertex
     * @param vertex the vertex that we want to know whether it is in this or not
     * @return true if this contains vertex, false otherwise
     */
    public boolean contains2(Vertex vertex); //Vérifie si ProcessedVertexes contient un certain élément

    /**
     * Adds vertex to this
     * @param vertex the vertex we want to add
     */
    public void add2(Vertex vertex);// Ajoute un élément à ProcessedVertexes
}
