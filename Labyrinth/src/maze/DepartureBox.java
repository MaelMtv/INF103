package maze;

import java.awt.Color;

public class DepartureBox extends MazeBox {

    public DepartureBox(int x, int y, Maze maze){
        super(x, y, maze, "D", true);
    }

    /**
     * Return the color of this box
     * @return the color of this box
     */
    public Color getColor(){
        return Color.GREEN;
    }
}
