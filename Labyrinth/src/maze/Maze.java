package maze;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import graph.Graph;
import graph.Vertex;

public class Maze implements Graph{

    private List<Vertex> maze;
    private int rows;
    private int columns;


    public Maze(int rows, int columns){
        this.maze = new ArrayList<Vertex>();
        this.rows = rows;
        this.columns = columns;
    }

     /**
      * Returns the list of all the vertexes in the maze
      * @return the list of all the vertexes in the maze
      */
    public List<Vertex> getAllVertexes(){
        return this.maze;
    }

    /**
     * Returns this maze's number of rows
     * @return this maze's number of rows
     */
    public int getRows(){
        return this.rows;
    }

    /**
     * Returns this maze's number of columns
     * @return this maze's number of columns
     */
    public int getColumns(){
        return this.columns;
    }

    /**
     * Returns the start of this maze if there is one
     * @return the starting vertex of this maze
     * @throws DepartureArrivalException if there is no start in the maze
     */
    public Vertex getStart() throws DepartureArrivalException {
        for(Vertex vertex : maze ){
            if(vertex.getLabel() == "D"){
                return vertex;
            }
        }
        throw new DepartureArrivalException("start");
    }

    /**
     * Returns the end of this maze if there is one
     * @return the ending vertex of this maze
     * @throws DepartureArrivalException if there is no end in the maze
     */
    public Vertex getEnd() throws DepartureArrivalException{
        for(Vertex vertex : maze ){
            if(vertex.getLabel() == "A"){
                return vertex;
            }
        }
        throw new DepartureArrivalException("finish");
    }

    /**
     * Used solely when initializing a maze from a file, sets the vertex of coordinates (i,j) in the list of vertex of the maze
     * @param i the abscissa of the vertex we add
     * @param j the ordiante of the vertex we add
     * @param c the character read in the file named fileName used to know which type of mazebox the vertex we add will be
     * @param fileName name of the file used to initialize the maze, used in case of a mazereadingexception
     * @throws MazeReadingException if c does not represent a type of mazebox
     */
    private void setBox(int i, int j, char c, String fileName) throws MazeReadingException{
        Vertex vertex;
        if(i < rows && j < columns){
            if(c == 'A'){
                vertex = new ArrivalBox(i, j, this);
            }
            else{
                if(c == 'D'){
                    vertex = new DepartureBox(i, j, this);
                }
                else{
                    if(c == 'E'){
                        vertex = new EmptyBox(i, j, this);
                   }
                    else{
                        if(c == 'W'){
                            vertex = new WallBox(i, j, this);
                        }
                        else{
                            throw new MazeReadingException(fileName, i,"Type de case inexistant");
                        }
                    }
                }
            }
            this.maze.add(vertex);
        }
    }

    /**
      * Returns the list of neighbors of a vertex
      * @param vertex the vertex whose neighbors we want to find
      * @return the list of neighbors that are not walls
      */
    public List<Vertex> successors(Vertex vertex){
        int x = vertex.getx();
        int y = vertex.gety();
        int n = rows;
        int m = columns;
        List<Vertex> successors = new ArrayList<Vertex>();
        List<Integer> abscissae ;
        List<Integer> ordinates;
        if(x % 2 == 1){
            abscissae = new ArrayList<Integer>(Arrays.asList(x, x - 1, x - 1, x, x + 1, x + 1));
            ordinates = new ArrayList<Integer>(Arrays.asList(y - 1, y, y + 1, y + 1, y + 1, y));
        }
        else{
            abscissae = new ArrayList<Integer>(Arrays.asList(x, x - 1, x - 1, x, x + 1, x + 1));
            ordinates = new ArrayList<Integer>(Arrays.asList(y - 1, y - 1, y, y + 1, y, y - 1));
        }

        int k, i, j;
        for(k = 0; k < 6; k ++){
            i = abscissae.get(k);
            j = ordinates.get(k);
            if(i >= 0 && i < n && j >= 0 && j < m){
                Vertex vertex2 = maze.get(j+i*m);
                if(vertex2.isEmpty()){
                    successors.add(vertex2);
                }
            }
        }
        return successors;
    }

    /**
     * Returns the distance between vertex1 and vertex2
     * @param src first vertex
     * @param dst second vertex
     * @return 1 if vertex1 and vertex2 are next to each other and both are not walls, +infinity otherwise
     */
    public int getDistance(Vertex vertex1, Vertex vertex2){
        MazeBox box1 = (MazeBox) vertex1;
        MazeBox box2 = (MazeBox) vertex2;
        if((box1.getLabel().equals("W"))||(box2.getLabel().equals("W"))){
            return Integer.MAX_VALUE;
        }
        List<Vertex> voisin1 = box1.getMaze().successors(box1);
        if(voisin1.contains(box2)){
            return 1;
        }
        else{
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Used solely when initializing a maze from a file, returns the dimensions of the maze represented in the file
     * @param fileName name of the file used to initialize the maze
     * @return a dimension object containing the number of lines and columns of the file named fileName
     * @throws MazeReadingException if the file named fileName is empty
     */
    public List<Integer> dimensions(String fileName) throws MazeReadingException{
        List<Integer> dimensions = new ArrayList<Integer>();
        int rows;
        int columns;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String firstLine = reader.readLine();
            if(firstLine != null){
                columns = firstLine.length();
                rows = 1;
            }
            else{
                reader.close();
                throw new MazeReadingException(fileName, 0, "Fichier vide, initialisation impossible");
            }
            while(reader.readLine() != null){
                rows++;
            }
            reader.close();
            dimensions.add(rows);
            dimensions.add(columns);
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        return dimensions;
    } 
    
    /**
     * Initializes a maze from a file
     * @param fileName name of the file used to initialize the maze
     * @throws MazeReadingException if there are more than one characters A or D (representing arrivals and departures) in the file
     */
    public final void initFromTextFile(String fileName) throws MazeReadingException{
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            boolean pasfini = true;
            int i = 0;
            int acounter = 0;
            int dcounter = 0;
            try{
                List<Integer> dimensions = dimensions(fileName);
                rows = dimensions.get(0);
                columns = dimensions.get(1);
                while(pasfini){
                    String line = reader.readLine();
                    if(line != null){
                        int length = line.length();
                        for(int j = 0; j < length; j++){
                            try{
                                char label = line.charAt(j);
                                if(label == 'D'){
                                    dcounter++;
                                }
                                else{
                                    if(label == 'A'){
                                        acounter++;
                                    }
                                }
                                if(dcounter > 1 || acounter > 1){
                                    throw new MazeReadingException(fileName, i,"More than one departure/arrival");
                                }
                                this.setBox(i, j, label, fileName);
                            }
                            catch (MazeReadingException mazeex){
                                mazeex.printMessage();
                                mazeex.printStackTrace();
                            }  
                        }
                    }
                    else{
                        pasfini = false; 
                    }
                    i = i+1;
                }
                reader.close();
            }
            catch(MazeReadingException mre){
                for(int a = 0; a < 10; a++){
                    for(int b = 0; b < 10 ; b++){
                        setBox(a, b, 'E', fileName);
                    }
                }
            }
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * Saves the current maze in a file named fileName
     * @param fileName name of the file where we want to save the maze
     * @throws FileNotFoundException if the file does not exist or is simply not found
     */
    public final void saveToTextFile(String fileName) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(fileName);
        int n = rows;
        int m = columns;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                writer.print(maze.get(j+i*m).getLabel());
            }
            writer.println();
        }
        writer.close();
    }

    /**
     * Prints the maze into the console with the elements of path marked as C except if they are the start or the finish
     * @param path list of vertex constituting the path to mark
     * @throws DepartureArrivalException if there is no departure/arrival in the maze
     */
    public void showPath(List<Vertex> path) throws DepartureArrivalException{
        int line = 0;
        Vertex departure = this.getStart();
        Vertex end = this.getEnd();
        for(Vertex vertex : getAllVertexes()){
            if(line == columns){
                System.out.println();
                line = 0;
            }
            if(path.contains(vertex) && (vertex != departure) && (vertex != end)){
                System.out.print("C");
            }
            else{
                System.out.print(vertex.getLabel());
            }
            line++;
        }
    }
}