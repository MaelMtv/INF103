package maze;

import java.awt.Color;

public class EmptyBox extends MazeBox {

    public EmptyBox(int x, int y, Maze maze){
        super(x, y, maze, "E", true);
    }

    /**
     * Return the color of this box
     * @return the color of this box
     */
    public Color getColor(){
        return Color.LIGHT_GRAY;
    }
}
