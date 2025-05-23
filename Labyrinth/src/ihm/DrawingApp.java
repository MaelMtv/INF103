package ihm;
import javax.swing.* ;
import javax.swing.event.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Component;
import menus.DrawingMenuBar;
import model.*;

public class DrawingApp extends JFrame implements ChangeListener{

    private final DrawingMenuBar drawingMenuBar;
    private final WindowPanel windowpanel;
    private final DrawingAppModel drawingAppModel = new DrawingAppModel(10,10);

    public DrawingApp() {
        super("Drawing Application") ; 
        setJMenuBar(drawingMenuBar = new DrawingMenuBar(this));
        setContentPane(windowpanel = new WindowPanel(this));
        drawingAppModel.addObserver(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        pack() ;
        setVisible(true) ;

        getContentPane().addComponentListener(new ComponentAdapter(){
          /**
           *  If the frame is resized, compute new values for the hexagons sizes, then stateChanged will be triggered which will repaint them
           */  
          public void componentResized(ComponentEvent e){
              Component c = (Component) e.getSource();
              int nwidth = c.getWidth();
              int nheight = c.getHeight();
              MazePanel mazePanel = windowpanel.getMazePanel();
              DrawingAppModel drawingAppModel = getDrawingAppModel();
              int rows = drawingAppModel.getRows();
              int columns = drawingAppModel.getColumns();
              mazePanel.setWidth(nwidth);
              mazePanel.setHeight(nheight-30);// To compensate for the space occupied by the buttonPanel
              mazePanel.computeHexagonSize(rows, columns);
          }
      });
    }


    public DrawingAppModel getDrawingAppModel(){
        return drawingAppModel;
    }

    public void stateChanged(ChangeEvent evt) {
        windowpanel.notifyForUpdate();
    }
}
