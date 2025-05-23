package graph;

import java.awt.Color;

public interface Vertex {

    /**
     * Return the abscissa of this vertex
     * @return the abscissa of this vertex
     */
    public int getx();

    /**
     * Return the ordinate of this vertex
     * @return the ordinate of this vertex
     */
    public int gety();

    /**
     * Return the label of this vertex
     * @return the label of this vertex
     */
    public String getLabel();

    /**
     * Return the color of this vertex
     * @return the color of this vertex
     */
    public Color getColor();
    
    /**
     * Checks if this vertex is empty
     * @return true if this vertex is not a wall, false otherwise
     */
    public boolean isEmpty();
}
