package maze;

import java.awt.Color;

public class ArrivalBox extends MazeBox  {
    
    public ArrivalBox(int x, int y, Maze maze){
        super(x, y, maze, "A", true);
    }

    /**
     * Return the color of this box
     * @return the color of this box
     */
    public Color getColor(){
        return Color.RED;
    }
}
