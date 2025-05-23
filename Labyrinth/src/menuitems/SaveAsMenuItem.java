package menuitems;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.DrawingAppModel;
import ihm.DrawingApp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveAsMenuItem extends JMenuItem implements ActionListener {
    private final DrawingApp drawingApp;

    public SaveAsMenuItem(DrawingApp drawingApp){
        super("Save as");
        this.drawingApp = drawingApp;
        addActionListener(this);
    }

    /**
     * Saves the current maze in a file whose name we let the user choose
     */
    public void actionPerformed(ActionEvent e){
        DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel() ;
        int response = JOptionPane.showInternalOptionDialog(this,"Save the maze ?","Save ?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null) ;
        switch (response) {
            case JOptionPane.OK_OPTION:
                try{
                    String fileName = JOptionPane.showInputDialog(this, "Name of the file you want to save your maze as:");
                    drawingAppModel.setFilePath(fileName);
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

