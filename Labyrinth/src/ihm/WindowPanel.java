package ihm;

import javax.swing.* ;
import buttons.ButtonsPanel;
import java.awt.*;

public class WindowPanel extends JPanel {

    private final MazePanel mazePanel ;
    private final ButtonsPanel buttonsPanel ;
    
    public WindowPanel(DrawingApp drawingApp) {
        setLayout(new BorderLayout()) ;
        add(mazePanel = new MazePanel(drawingApp), BorderLayout.CENTER) ;
        add(buttonsPanel = new ButtonsPanel(drawingApp), BorderLayout.NORTH) ;
    }

    public MazePanel getMazePanel(){
        return mazePanel;
    }

    public void notifyForUpdate(){
        mazePanel.notifyForUpdate();
        buttonsPanel.notifyForUpdate();
    }
}
