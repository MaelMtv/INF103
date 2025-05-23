package buttons;

import ihm.DrawingApp;
import javax.swing.JButton;
import java.awt.event.*;

public class HexagonTypeButton extends JButton implements ActionListener {
    private final DrawingApp drawingApp;
    
    public HexagonTypeButton(DrawingApp drawingApp, String type){
        super(type);
        this.drawingApp = drawingApp;
        addActionListener(this);
    }

    public final void actionPerformed(ActionEvent e){
        String type = this.getText();
        drawingApp.getDrawingAppModel().setNewHexagon(type);;
    }
}
