package testaffichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class HexagonPane extends JPanel {

    private int[] xs;
    private int[] ys;
    int rows;
    int columns;
    int width;
    int height;
    private Frame frame;

    public HexagonPane(Frame frame, int i, int j){
        width = 900;
        height = 900;
        rows = i;
        columns = j;
        this.frame = frame;
        newHexaSize();
        setPreferredSize(new Dimension(950, 950));
        setSize(getPreferredSize());
    }

    public void newHexaSize(){
        int small = Math.min(height,width);
        int min = (int) Math.round(0.01*small);
        int c1 =(int) Math.round(0.98*height*2/(1+3*rows)-0.5);
        int c2 =(int) Math.round(0.98*2*width/(1.732*(2*columns+1))-0.5);
        int c = Math.min(c1,c2);
        int a = (int) Math.round(min+0.866*c);
        int b = (int) Math.round(min+1.732*c);
        int[] newx = {min,min, a,b,b,a};
        int[] newy = {min+3*c/2,min+c/2,min,min+c/2,min+3*c/2,min+2*c};
        xs = newx;
        ys = newy;
    }

    public void setHeight(int i){
        height = i;
    }
    public void setWidth(int i){
        width = i;
    }

    //faire un truc avec panel.getsize() pour recalculer la taille des hexagones, peut être un componentlistener

    @Override
    public void paintComponent(Graphics g){
        int offset;
        int decl = xs[3]-xs[1];
        int decc = ys[0]-ys[2];
        super.paintComponent(g);
        for(int i = 0; i<rows;i++){
            int[] xPoints={0,0,0,0,0,0};
            int[] yPoints={0,0,0,0,0,0};
            for(int a = 0; a<6; a++){
                yPoints[a] = ys[a]+i*decc;
            }
            if(i%2 == 0){
                offset = 0;
            }
            else{
                offset = xs[2]-xs[1];
            }
            for(int j =0;j<columns;j++){
                for(int k = 0; k<6;k++){
                    xPoints[k] = xs[k]+offset+j*decl;
                }
                g.fillPolygon(xPoints, yPoints, 6);
                g.setColor(Color.BLACK);
                g.drawPolygon(xPoints, yPoints,6);
                g.setColor(Color.GRAY);
            }
        }
    }    
}
