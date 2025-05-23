package menuitems;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import ihm.DrawingApp;
import maze.MazeReadingException;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.*;

public class OpenMenuItem extends JMenuItem implements ActionListener{

    private final DrawingApp drawingApp;

    public OpenMenuItem(DrawingApp drawingApp){
        super("Open");
        this.drawingApp = drawingApp;
        addActionListener(this);
    }

    /**
     * Initializes a maze from a file that the user chooses through a file browser and asking if we want to save the current maze if it is not
     */
    public void actionPerformed(ActionEvent e) {
        DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel();
        if(drawingAppModel.isModified()){
            int response = JOptionPane.showInternalOptionDialog(this,"Current maze not saved. Save it?","Open",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null) ;
            switch(response){
                case JOptionPane.CANCEL_OPTION:
                    return;
                case JOptionPane.OK_OPTION:
                    try{
                        drawingAppModel.saveToFile();
                    }
                    catch(FileNotFoundException fnfe){
                        fnfe.printStackTrace();
                    }
                    catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
            try{
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(this);
                String fileName = chooser.getSelectedFile().getName();
                drawingAppModel.open(fileName);
            }
            catch(MazeReadingException mre){
                mre.printMessage();
            }
        }
        else{
            try{
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(this);
                String fileName = chooser.getSelectedFile().getName();
                drawingAppModel.open(fileName);
            }
            catch(MazeReadingException mre){
                mre.printMessage();
            }
        }
    }
    
}
