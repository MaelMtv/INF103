package menus;
import javax.swing.* ;

import ihm.DrawingApp;

public class DrawingMenuBar extends JMenuBar {

   private final FileMenu fileMenu ;
	
   public DrawingMenuBar(DrawingApp drawingApp) {
      super() ;
      add(fileMenu = new FileMenu(drawingApp)) ;
   }
}