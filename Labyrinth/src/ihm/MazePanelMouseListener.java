package ihm;

import java.awt.event.*;
import java.util.List;
import model.*;
import java.awt.Color;
import java.awt.Polygon;

public class MazePanelMouseListener extends MouseAdapter{
    private final DrawingApp drawingApp ;
	
	public MazePanelMouseListener(DrawingApp drawingApp) {
		super();
		this.drawingApp = drawingApp ;
	}

	/**
	 * Picks a color based on what type of mazebox newHexagon represents
	 * @param newHexagon String containing the name of a mazebox type
	 * @return the color associated with the type of box represented by newHexagon
	 * @throws NoMatchException if newHexagon is not a type of mazebox
	 */
	private Color colorChooser(String newHexagon) throws NoMatchException{
		if(newHexagon == "Departure"){
			return Color.GREEN;
		}
		else{
			if(newHexagon == "Empty"){
				return Color.LIGHT_GRAY;
			}
			else{
				if(newHexagon == "Wall"){
					return Color.BLUE;
				}
				else{
					if(newHexagon == "Arrival"){
						return Color.RED;
					}
					else{
						throw(new NoMatchException("No matches with expected values of newHexagon"));
					}
				}
			}
		}
	}

	/**
	 * Picks a label based on what type of mazebox newHexagon represents
	 * @param newHexagon String containing the name of a mazebox type
	 * @return the label associated with the type of box represented by newHexagon
	 * @throws NoMatchException if newHexagon is not a type of mazebox
	 */
	private String labelChooser(String newHexagon) throws NoMatchException{
		if(newHexagon == "Departure"){
			return "D";
		}
		else{
			if(newHexagon == "Empty"){
				return "E";
			}
			else{
				if(newHexagon == "Wall"){
					return "W";
				}
				else{
					if(newHexagon == "Arrival"){
						return "A";
					}
					else{
						throw(new NoMatchException("No matches with expected values of newHexagon"));
					}
				}
			}
		}
	}

	@Override
	/**
	 * Modifies the hexagon in which the click occured
	 */
	public void mouseReleased(MouseEvent me){
		DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel();
		String newHexagon = drawingAppModel.getNewHexagon();
		WindowPanel windowPanel = (WindowPanel) drawingApp.getContentPane();
		MazePanel mazePanel = windowPanel.getMazePanel();
		int rows = drawingAppModel.getRows();
		int columns = drawingAppModel.getColumns();
		int x = me.getX();
		int y = me.getY();
		List<Polygon> hexagons = mazePanel.getHexList();
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				Polygon hexagon = hexagons.get(i*columns+j);
				if(hexagon.contains(x,y)){
					try{
						String label = labelChooser(newHexagon);
						Color color = colorChooser(newHexagon);
						drawingAppModel.setHexagon(i, j, label, color);
					}
					catch(NoMatchException nme){
						nme.printStackTrace();
					}
				}
			}
		}
	}
}
