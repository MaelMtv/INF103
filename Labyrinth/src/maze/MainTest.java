package maze;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import graph.Dijkstra;
import graph.ShortestPaths;
import graph.Vertex;

public class MainTest {
    public static final String path1 = "D:/JavaVSC/Dijkstra/src/data/labyrinth.maze";
    public static final String path2 = "D:/JavaVSC/Dijkstra/src/data/labyrinth2.maze";
    public static void main(String[] args) throws FileNotFoundException, IOException, DepartureArrivalException, MazeReadingException{
        Maze maze = new Maze(10,10);
        maze.initFromTextFile(path1);
        System.out.println();
        maze.saveToTextFile(path2);
        ShortestPaths path = Dijkstra.dijkstra(maze, maze.getStart(), maze.getEnd());
        List<Vertex> path3 = path.getShortestPath(maze.getEnd());
        maze.showPath(path3);
    }
}