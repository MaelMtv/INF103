package graph;

public interface MinDistance {

    /**
     * Sets value integer to vertex's mindistance to the start of the path
     * @param vertex the vertex that we want to change the mindistance of
     * @param integer new mindistance
     */
    public void setDistance(Vertex vertex, int integer);
    
    /**
     * Returns mindistance between vertex and the start of the path
     * @param vertex the vertex that we want to get the mindistance of
     * @return the minimum distance between vertex and the start of the path
     */
    public int getDistance(Vertex vertex);// Donne la valeur de MinDistance
}
