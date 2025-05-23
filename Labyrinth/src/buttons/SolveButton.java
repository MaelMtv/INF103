package buttons;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ihm.DrawingApp;
import maze.DepartureArrivalException;
import maze.MazeReadingException;
import model.NoPathException;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SolveButton extends JButton implements ActionListener {
    private final DrawingApp drawingApp;

    public SolveButton(DrawingApp drawingApp){
        super("Solve");
        this.drawingApp = drawingApp;
        addActionListener(this);
    }
    
    public final void actionPerformed(ActionEvent e){
        try{
            drawingApp.getDrawingAppModel().solveMaze();
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(drawingApp, "File not Found");
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        catch(MazeReadingException mre){
            mre.printStackTrace();
            JOptionPane.showMessageDialog(drawingApp, mre.getErrorMessage());
        }
        catch(DepartureArrivalException dae){
            dae.printStackTrace();
            JOptionPane.showMessageDialog(drawingApp, dae.getMessage());
        }
        catch(NoPathException npe){
            npe.printStackTrace();
            JOptionPane.showMessageDialog(drawingApp, "No possible path from start to finish");
        }
    }
}
