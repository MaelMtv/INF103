package model;

import java.awt.Color;

public class Hexagon {

    private final int i;
    private final int j;
    private String label;
	private Color color;

    public Hexagon(int i, int j, String label, Color color){
        this.i = i;
        this.j = j;
        this.label = label;
		this.color = color;
    }

	/**
     * Return the label of this hexagon
     * @return the label of this hexagon
     */
    public String getLabel() {
		return label;
	}

	/**
	 * Sets the label of this hexagon to a new value
	 * @param label the new value
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
     * Return the abscissa of this hexagon
     * @return the abscissa of this hexagon
     */
	public int getI() {
		return i;
	}

	/**
     * Return the ordinate of thishexagon
     * @return the ordinate of this hexagon
     */
	public int getJ() {
		return j;
	}

	/**
     * Return the color of this hexagon
     * @return the color of this hexagon
     */
	public Color getColor(){
		return color;
	}

	/**
	 * Sets the color of this hexagon to a new value
	 * @param color the new value
	 */
	public void setColor(Color color){
		this.color = color;
	}
}
