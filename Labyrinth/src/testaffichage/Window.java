package testaffichage;

import javax.swing.JPanel;

import ihm.DrawingApp;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Window extends JPanel {
    
    private HexagonPane hexa;

    public Window(Frame frame){
        setLayout(new BorderLayout());
        add(hexa = new HexagonPane(frame,10,10), BorderLayout.CENTER);
    }

    public HexagonPane getHexa(){
        return hexa;
    }
}
