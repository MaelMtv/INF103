package model;

public class NoPathException extends Exception {

    public NoPathException(){
        super("There is no possible path in this maze.");
    }
    
}
