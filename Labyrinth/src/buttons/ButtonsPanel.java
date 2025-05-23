package buttons;

import javax.swing.*;

import ihm.DrawingApp;

import java.awt.*;

public class ButtonsPanel extends JPanel {

    private final SolveButton solveButton;
    private final ClearButton clearButton;
    private final HexagonTypeButton wallButton;
    private final HexagonTypeButton emptyButton;
    private final HexagonTypeButton departureButton;
    private final HexagonTypeButton arrivalButton;
    private final NewHexagon newHexagon;

    public ButtonsPanel(DrawingApp drawingApp){
        setLayout(new GridLayout(1,3));
        add(solveButton = new SolveButton(drawingApp));
        add(clearButton = new ClearButton(drawingApp));
        add(wallButton = new HexagonTypeButton(drawingApp, "Wall"));
        add(emptyButton = new HexagonTypeButton(drawingApp, "Empty"));
        add(departureButton = new HexagonTypeButton(drawingApp, "Departure"));
        add(arrivalButton = new HexagonTypeButton(drawingApp, "Arrival"));
        add(newHexagon = new NewHexagon(drawingApp));
    }

    public void notifyForUpdate(){
        newHexagon.notifyForUpdate();
    }
}
