package maze;

import java.awt.Color;

public class WallBox extends MazeBox {

    public WallBox(int x, int y, Maze maze){
        super(x, y, maze, "W", false);
    }

    /**
     * Return the color of this box
     * @return the color of this box
     */
    public Color getColor(){
        return Color.BLUE;
    }
}
