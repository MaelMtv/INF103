package buttons;

import java.awt.event.*;
import javax.swing.JButton;

import ihm.DrawingApp;

public class ClearButton extends JButton implements ActionListener {
    private final DrawingApp drawingApp;

    public ClearButton(DrawingApp drawingApp){
        super("Clear");
        this.drawingApp = drawingApp;
        addActionListener(this);
    }
    
    public final void actionPerformed(ActionEvent e){
        drawingApp.getDrawingAppModel().clearPath();
    }
}
