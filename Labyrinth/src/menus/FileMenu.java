package menus;
import javax.swing.* ;

import ihm.DrawingApp;
import menuitems.NewMenuItem;
import menuitems.OpenMenuItem;
import menuitems.QuitMenuItem;
import menuitems.SaveAsMenuItem;
import menuitems.SaveMenuItem;

public class FileMenu extends JMenu {

   private final QuitMenuItem quitMenuItem ;
   private final OpenMenuItem openMenuItem ;
   private final NewMenuItem newMenuItem ;
   private final SaveMenuItem saveMenuItem;
   private final SaveAsMenuItem saveAsMenuItem;

   public FileMenu(DrawingApp drawingApp) {
      super("File") ;
      add(newMenuItem = new NewMenuItem(drawingApp));
      add(openMenuItem = new OpenMenuItem(drawingApp));
      add(saveMenuItem = new SaveMenuItem(drawingApp));
      add(saveAsMenuItem = new SaveAsMenuItem(drawingApp));
      add(quitMenuItem = new QuitMenuItem(drawingApp));
   }
}
