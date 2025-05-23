package menuitems;

import model.DrawingAppModel;
import javax.swing.*;
import ihm.DrawingApp;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveMenuItem extends JMenuItem implements ActionListener {
    private final DrawingApp drawingApp;

    public SaveMenuItem(DrawingApp drawingApp){
        super("Save");
        this.drawingApp = drawingApp;
        addActionListener(this);
    }

    /**
     * Saves the current maze in the file whose path is stored in the drawingAppModel
     */
    public void actionPerformed(ActionEvent e) {
        DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel() ;
        if(drawingAppModel.isModified()){
            int response = JOptionPane.showInternalOptionDialog(this,"Save the maze ?","Save ?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null) ;
         switch (response) {
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
            case JOptionPane.NO_OPTION: 
               break ;
            }
        }
    }
}
    

