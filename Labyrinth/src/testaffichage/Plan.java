package testaffichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Plan extends JPanel {
    private Frame frame;
    private GridLayout gridLayout;
    ArrayList<HexagonPane> list;

    public Plan(Frame frame){
        this.frame = frame;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1000,1000));
        gridLayout = new GridLayout(5,5);
        setLayout(gridLayout);
        ArrayList<HexagonPane> list = new ArrayList<HexagonPane>();
        for(int i = 0; i<25; i++){
            HexagonPane hexa = new HexagonPane(frame);
            list.add(hexa);
            add(hexa);
        }

    }
    
}
