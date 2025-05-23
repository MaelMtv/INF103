package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.event.*;
import graph.Dijkstra;
import maze.DepartureArrivalException;
import maze.Maze;
import maze.MazeReadingException;
import graph.ShortestPaths;
import graph.Vertex;
import java.awt.Color;

public class DrawingAppModel {

    private int rows;
    private int columns;
    private List<Hexagon> hexagons;
    private List<ChangeListener> listeners = new ArrayList<ChangeListener>();
    private boolean modified = false;
    private String filePath = "init.maze";
    private String newHexagon = "Empty";
    private static final Color brown = new Color(88,41,0);

    public DrawingAppModel(int a, int b){
        rows = a;
        columns = b;
        hexagons = new ArrayList<Hexagon>();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                hexagons.add(new Hexagon(i, j, "E", Color.LIGHT_GRAY));
            }
        }
    }

    /**
     * Checks whether or not the model is modified
     * @return true if it is, false if it is not
     */
    public boolean isModified() {
        return modified;
    }

    /**
     * Adds a component as a listener on this model
     * @param listener the component that we want to listen to this
     */
    public void addObserver(ChangeListener listener){
        listeners.add(listener);
    }

    /**
     * Tells all of its listeners to update themselves
     */
    public void stateChanged(){
        ChangeEvent evt = new ChangeEvent(this);
        for(ChangeListener listener : listeners){
            listener.stateChanged(evt);
        }
    }

    /**
     * Returns the number of rows of the maze
     * @return the number of rows of the maze
     */
    public int getRows(){
        return rows;
    }

    /**
     * Returns the number of columns of the maze
     * @return the number of columns of the maze
     */
    public int getColumns(){
        return columns;
    }

    /**
     * Returns the list of all the hexagons of the maze
     * @return the list of all the hexagons of the maze
     */
    public List<Hexagon> getHexagons(){
        return hexagons;
    }
    /**
     * Sets the list of hexagons to a new one
     * @param hexagons the new list of hexagons
     */
    public void setMaze(List<Hexagon> hexagons){
        this.hexagons = hexagons;
        modified = true;
        stateChanged();
        
    }

    /**
     * Transforms the given fileName into a path used when saving the maze and sets a new value for the attribute filePath
     * Doesn't modify fileName If it is already a path leading to a .maze file in the data folder
     * @param fileName the name of the file in which the maze will now be saved
     */
    public void setFilePath(String fileName){
        if(fileName.startsWith("data/") && fileName.endsWith(".maze")){
            this.filePath = fileName;
        }
        else{
            this.filePath = "data/"+fileName;
        }
        modified = true;
        stateChanged();
    }

    /**
     * Returns the type of mazebox that we set on mouse click
     * @return a String representing a type of mazebox
     */
    public String getNewHexagon(){
        return newHexagon;
    }
    /**
     * Sets a new value for the type of mazebox that we set on mouse click
     * @param newHexagon the new type String
     */
    public void setNewHexagon(String newHexagon){
        if(this.newHexagon != newHexagon){
            this.newHexagon = newHexagon;
            modified =true;
            stateChanged();
        }
    }

    /**
     * Replaces the hexagon on coordinates (i,j) to a new hexagon whose parameters are given as input for this method
     * Prevents multiple starts/ends by removing every start/end when you set a new start/end
     * @param i the abscissa of the hexagon to change
     * @param j the ordinate of the hexagon to change
     * @param label the new label of the hexagon
     * @param color the new color of the hexagon
     */
    public void setHexagon(int i, int j, String label, Color color){
        if(label == "A"){
            for(Hexagon hexagon : hexagons){
                if(hexagon.getLabel()== "A"){
                    hexagon.setLabel("E");
                    hexagon.setColor(Color.LIGHT_GRAY);
                }
            }
        }
        else{
            if(label == "D"){
                for(Hexagon hexagon : hexagons){
                    if(hexagon.getLabel()== "D"){
                        hexagon.setLabel("E");
                        hexagon.setColor(Color.LIGHT_GRAY);
                    }
                }
            }
        }
        hexagons.set(i*columns+j, new Hexagon(i,j, label, color));
        modified = true;
        stateChanged();
    }

    /**
     * Saves the maze in the file found with the filePath attribute and creates a file if it does not exist yet
     * @throws FileNotFoundException if the file is not found or the creation failed
     * @throws IOException
     */
    public void saveToFile() throws FileNotFoundException,IOException{
        File file = new File(filePath);
        file.createNewFile();
        PrintWriter writer = new PrintWriter(filePath);
        int n = rows;
        int m = columns;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                writer.print(hexagons.get(j+i*m).getLabel());
            }
            writer.println();
        }
        writer.close();
        modified = false;
    }

    /**
     * Solves the maze by changing the color of the shortest path from the starting tile to the finishing tile if there is such a path
     * @throws FileNotFoundException if saveToFile() throws it
     * @throws IOException if saveToFile() throws it
     * @throws MazeReadingException if there are more than one start or finish in the maze (which should be impossible)
     * @throws DepartureArrivalException if the maze does not have a start or a finish (thrown by getStart() or getEnd())
     * @throws NoPathException if there is no path from start to finish
     */
    public final void solveMaze() throws FileNotFoundException,IOException, MazeReadingException,DepartureArrivalException,NoPathException{
        clearPath();
        String filePath2 = this.filePath;
        setFilePath("solveFile.maze");
        saveToFile();
        Maze maze = new Maze(0,0);
        maze.initFromTextFile(filePath);
        ShortestPaths path = Dijkstra.dijkstra(maze, maze.getStart(), maze.getEnd());
        List<Vertex> path2 = path.getShortestPath(maze.getEnd());
        if(path2.contains(maze.getStart()) && path2.contains(maze.getEnd())){
            for(Vertex vertex : path2){
                String label = vertex.getLabel();
                if(label != "A" && label != "D"){
                    int i = vertex.getx();
                    int j = vertex.gety();
                    Color color = brown;
                    setHexagon(i, j, label, color);
                }
            }
        }
        else{
            throw new NoPathException();
        }
        setFilePath(filePath2);
        modified = false;
    }

    /**
     * Opens a maze stored in a file that the user chose and displays it
     * @param fileName name of the file used for initialization
     * @throws MazeReadingException if there are more than one start or finish in the file
     */
    public void open(String fileName) throws MazeReadingException{
        setFilePath(fileName);
        List<Hexagon> hexlist = new ArrayList<Hexagon>();        
        Maze maze = new Maze(0,0);
        maze.initFromTextFile(filePath);
        List<Vertex> vertexes = maze.getAllVertexes();
        for(Vertex vertex : vertexes){
            int i = vertex.getx();
            int j = vertex.gety();
            String label = vertex.getLabel();
            Color color = vertex.getColor();
            hexlist.add(new Hexagon(i,j, label, color));
        }
        rows = maze.getRows();
        columns = maze.getColumns(); 
        setMaze(hexlist);
        modified = false;
    }

    /**
     * If the maze has been solved, changes the path tiles' color back to light gray
     */
    public void clearPath(){
        for(Hexagon hexagon : hexagons){
            Color color = hexagon.getColor();
            if(brown.equals(color)){
                int i = hexagon.getI();
                int j = hexagon.getJ();
                setHexagon(i, j, "E", Color.LIGHT_GRAY);
            }
        }
    }

    /**
     * Creates a new maze of dimension rows x columns named fileName
     * @param rows the number of rows of the new maze
     * @param columns the number of columns of the new maze
     * @param fileName the name of the new maze
     */
    public void createNewMaze(int rows, int columns, String fileName){
        this.rows = rows;
        this.columns = columns;
        List<Hexagon> hexlist = new ArrayList<Hexagon>();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                hexlist.add(new Hexagon(i,j, "E", Color.LIGHT_GRAY));
            }
        }
        setMaze(hexlist);
        setFilePath(fileName+".maze");
    }
}
