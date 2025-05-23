package maze;
import graph.Vertex;

public abstract class MazeBox implements Vertex{
    private final int x;
    private final int y;
    private final Maze maze;
    private final String label;
    private final boolean empty;

    public MazeBox(int x, int y, Maze maze, String label, boolean empty){
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.label = label;
        this.empty = empty;
    }

    /**
     * Returns the maze in which this mazebox is located
     * @return the maze in which this mazebox is located
     */
    public Maze getMaze(){
        return maze;
    }

    /**
     * Return the abscissa of this mazebox
     * @return the abscissa of this mazebox
     */
    public int getx(){
        return this.x;
    }
    
    /**
     * Return the ordinate of this mazebox
     * @return the ordinate of this mazebox
     */
    public int gety(){
        return this.y;
    }

    /**
     * Return the label of this mazebox
     * @return the label of this mazebox
     */
    public String getLabel(){
        return this.label;
    }

    /**
     * Checks if this mazebox is empty
     * @return true if this mazebox is not a wall, false otherwise
     */
    public boolean isEmpty(){
        return this.empty;
    }
}
