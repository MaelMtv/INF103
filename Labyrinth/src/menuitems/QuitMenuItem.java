package menuitems;

import javax.swing.* ;
import ihm.DrawingApp;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.*;

public class QuitMenuItem extends JMenuItem implements ActionListener {

   private final DrawingApp drawingApp ;

   public QuitMenuItem(DrawingApp drawingApp) {
      super("Quit") ;
      this.drawingApp = drawingApp ;
      addActionListener(this);
   }

   /**
    * Quits the application after asking if the user wants to save the current maze if it is not
    */
   public void actionPerformed(ActionEvent evt){
      DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel() ;
      if (drawingAppModel.isModified()) {
         int response = JOptionPane.showInternalOptionDialog(this,"Maze not saved. Save it ?","Quit application",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null) ;
         switch (response) {
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
         System.exit(0);
      }
      else{
         System.exit(0);
      }
   }
}
