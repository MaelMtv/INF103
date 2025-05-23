package ihm;

import javax.swing.*;
import model.DrawingAppModel;
import model.Hexagon;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazePanel extends JPanel {
    private final DrawingApp drawingApp ;
    private int[] xs;
    private int[] ys;
    private int width;
	private int height;
    private List<Polygon> hexList;// used to find which mazebox needs to be changed when a click occurs
	
    public MazePanel(DrawingApp drawingApp) {
        hexList = new ArrayList<Polygon>();
        DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel();
        int rows = drawingAppModel.getRows();
        int columns = drawingAppModel.getColumns();
        width = 700;
        height = 600;
        computeHexagonSize(rows, columns);
        this.drawingApp = drawingApp ;
        setBackground(Color.WHITE) ;
        setPreferredSize(new Dimension(width,height)) ;
        MazePanelMouseListener mazePanelMouseListener = new MazePanelMouseListener(drawingApp);
		addMouseListener(mazePanelMouseListener);
    }

    /**
     * Returns the list of all the hexagons drawn in this panel
     * @return the list of all the hexagons drawn in this panel
     */
    public List<Polygon> getHexList(){
        return hexList;
    }

    /**
     * Computes the new size of hexagons so that they fit in the frame while occupying as much space as possible
     * @param rows the number of rows of the maze
     * @param columns the nomber of columns of the maze
     */
    public void computeHexagonSize(int rows, int columns){
		int small = Math.min(height,width);
        int min = (int) Math.round(0.01*small);
        int c1 =(int) Math.floor(0.98*height*2/(1+3*rows));
        int c2 =(int) Math.floor(0.98*2*width/(1.732*(2*columns+1)));
        int c = Math.min(c1,c2);
        int a = (int) Math.round(min+0.866*c);
        int b = (int) Math.round(min+1.732*c);
        int[] newx = {min,min, a,b,b,a};
        int[] newy = {min+3*c/2,min+c/2,min,min+c/2,min+3*c/2,min+2*c};
		xs = newx;
		ys = newy;
	}

    /**
     * Sets a new height for this panel
     * @param i the new height
     */
    public void setHeight(int i){
        height = i;
    }
    /**
     * Sets a new width for this panel
     * @param i the new width
     */
    public void setWidth(int i){
        width = i;
    }

    /**
     * Computes a new size for hexagons in case the window size/maze dimensions changed and repaints itself
     */
    public void notifyForUpdate(){
        DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel();
        if(drawingAppModel.isModified()){
            int rows = drawingAppModel.getRows();
            int columns = drawingAppModel.getColumns();
            hexList = new ArrayList<Polygon>();
            computeHexagonSize(rows, columns);
            revalidate();
            repaint();
        }
    }

    @Override
    /**
     * Computes the coordinates of each hexagon that needs to be drawn, adds them to a list and draws them
     */
    public void paintComponent(Graphics g){
        DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel();
        int rows = drawingAppModel.getRows();
        int columns = drawingAppModel.getColumns();
        List<Hexagon> hexagons = drawingAppModel.getHexagons();
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
                Hexagon hexagon = hexagons.get(i*columns+j);
                Color color = hexagon.getColor();
                Polygon hexa = new Polygon(xPoints, yPoints, 6);
                hexList.add(hexa);
                g.setColor(color);
                g.fillPolygon(hexa);
                g.setColor(Color.BLACK);
                g.drawPolygon(hexa);
            }
        }
    }       
}

