package menuitems;

import javax.swing.*;
import ihm.DrawingApp;
import model.DrawingAppModel;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NewMenuItem extends JMenuItem implements ActionListener {
    private final DrawingApp drawingApp;

    public NewMenuItem(DrawingApp drawingApp){
        super("New");
        this.drawingApp = drawingApp;
        addActionListener(this);
    }

    /**
     * Creates a new maze asking for a name, dimensions and if we want to save the current maze if it is not
     */
    public void actionPerformed(ActionEvent e) {
        DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel();
        int response = JOptionPane.showInternalOptionDialog(this,"Create a new empty maze ?","Create new maze",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null) ;
        switch (response) {
            case JOptionPane.CANCEL_OPTION:
                break ;
            case JOptionPane.OK_OPTION:
                String rows = JOptionPane.showInputDialog(this,"Number of rows:");
                String columns = JOptionPane.showInputDialog(this,"Number of columns:");
                int m = Integer.valueOf(Integer.parseInt(rows));
                int n = Integer.valueOf(Integer.parseInt(columns));
                if(drawingAppModel.isModified()){
                    int response2 = JOptionPane.showInternalOptionDialog(this,"Maze not saved. Save it ?","Save old maze",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null) ;
                    switch (response2) {
                        case JOptionPane.CANCEL_OPTION:
                            return ;
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
                    String fileName = JOptionPane.showInputDialog(this, "Pick a name for your new maze:");
                    drawingAppModel.createNewMaze(m,n,fileName);
                }
                else{
                    String fileName = JOptionPane.showInputDialog(this, "Pick a name for your new maze:");
                    drawingAppModel.createNewMaze(m,n,fileName);
                }
        }
    }
} 
    
