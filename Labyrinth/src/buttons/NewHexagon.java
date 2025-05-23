package buttons;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import ihm.DrawingApp;
import model.DrawingAppModel;

public class NewHexagon extends JPanel {
    
    private final DrawingApp drawingApp;
    private String newHexagon;

    public NewHexagon(DrawingApp drawingApp){
        this.drawingApp = drawingApp;
        newHexagon = drawingApp.getDrawingAppModel().getNewHexagon();
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Color color;
        char[] character = {'a'};
        if(newHexagon == "Departure"){
            color = Color.GREEN;
            character[0] = 'D';
        }
        else{ if(newHexagon == "Empty"){
            color = Color.LIGHT_GRAY;
            character[0] = 'E';
        }
        else{ if(newHexagon == "Wall"){
            color = Color.BLUE;
            character[0] = 'W';
        }
        else{ if(newHexagon == "Arrival"){
            color = Color.RED;
            character[0] = 'A';
        }
        else{
            color = Color.BLACK;
            character[0] = 'Z';
        }}}}
        setBackground(color);
        g.drawChars(character,0,1,width/2,2*height/3);
    }

    public void notifyForUpdate(){
        DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel();
        if(drawingAppModel.isModified()){
            newHexagon = drawingAppModel.getNewHexagon();
            repaint();
        }
    }
}
