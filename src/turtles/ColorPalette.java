package turtles;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.scene.paint.Color;
/**
 * Keeps track of all of the possible colors made so far. Uses an index to keep track of possible colors.
 * Indexes are mapped to the string form of the color. Just another wrapper for a map class
 * @author Henry and Gordon
 *
 */
public class ColorPalette extends Observable {

	private Map<Double, String> myColorMap;
	private Double myBackgroundColor;
	
	/**
	 * Initiates a new Map
	 */
	public ColorPalette() {
		this(new HashMap<Double, String>());
	}
	
	/**
	 * Assigns map to myColorMap
	 * @param colorsIn - map to adopt
	 */
	public ColorPalette(Map<Double, String> colorsIn) {
		myColorMap = new HashMap<Double,String>(colorsIn);
	}

	/**
	 * Add a color given the RGB color code and the specific index
	 * @param index - index to map the color to
	 * @param r - red value 
	 * @param g - green value
	 * @param b - blue value
	 */
	public void addColor(Double index, Double r, Double g, Double b) {
		Color temp = Color.rgb(r.intValue(), g.intValue(), b.intValue());
		myColorMap.put(index,  temp.toString());
		this.flagChange(new SingleColor(index, temp.toString()));
	}
	
	/**
	 * Sets background color given the index -- flags UI to change to the correct index
	 * @param index - index of color in map
	 */
	public void setBackgroundColor(Double index) {
		myBackgroundColor = index;
		this.flagChange(null);
	}
	
	/**
	 * @return index of current background color
	 */
	public double getBackgroundColor(){
		return myBackgroundColor;
	}
	
	/**
	 * @return map of all made indexes and their corresponding colors in string form
	 */
	public Map<Double, String> getPalette(){
		return myColorMap;
	}
	
	/**
	 * Checks to see if a given color index has been made
	 * @param d - index
	 * @return true/false given whether the index has been assigned a value
	 */
	public boolean checkValid(double d){
		return myColorMap.containsKey(d);
	}
	
	private void flagChange(SingleColor sc){
		this.setChanged();
		this.notifyObservers(sc);
	}
}
