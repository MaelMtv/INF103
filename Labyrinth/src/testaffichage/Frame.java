package testaffichage;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Component;

import javax.swing.JFrame;

public class Frame extends JFrame {

    private final Window window;
    
    public Frame(){
        super("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(window = new Window(this));
        setTitle("size" + window.getHexa().getWidth() + window.getHexa().getHeight());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        getContentPane().addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                Component c = (Component) e.getSource();
                int nwidth = c.getWidth();
                int nheight = c.getHeight();
                setTitle("size"+nwidth+nheight);
                HexagonPane hex = window.getHexa();
                hex.setWidth(nwidth);
                hex.setHeight(nheight);
                hex.newHexaSize();
            }
        });
    }
    
}
