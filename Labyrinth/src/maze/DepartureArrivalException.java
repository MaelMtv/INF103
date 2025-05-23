package maze;

public class DepartureArrivalException extends Exception  {
    
    public DepartureArrivalException(String message){
        super("Error, this labyrinth doesn't have a " + message);
    }
    
}
