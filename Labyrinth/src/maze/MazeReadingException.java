package maze;

public class MazeReadingException extends Exception {

    private String fileName;
    private int lineNumber;
    private String errorMessage;

    public MazeReadingException(String fileName, int lineNumber, String errorMessage){
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the error message of this exception
     * @return the error message of this exception
     */
    public String getErrorMessage(){
        return errorMessage;
    }
    
    /**
     * Prints an error message into the console
     */
    public void printMessage(){
        System.out.print("Erreur dans le fichier" + fileName + "à la ligne" + lineNumber + ":" + errorMessage);
    }
}
