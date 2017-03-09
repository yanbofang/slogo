package turtles;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.scene.paint.Color;

public class ColorPalette extends Observable {

	private Map<Double, String> myColorMap;
	private Double myBackgroundColor;
	
	public ColorPalette() {
		this(new HashMap<Double, String>());
	}
	
	public ColorPalette(Map<Double, String> colorsIn) {
		myColorMap = new HashMap<Double,String>(colorsIn);
	}

	public void addColor(Double index, Double r, Double g, Double b) {
		Color temp = Color.rgb(r.intValue(), g.intValue(), b.intValue());
		myColorMap.put(index,  temp.toString());
		this.flagChange(new SingleColor(index, temp.toString()));
	}
	
	public void setBackgroundColor(Double index) {
		myBackgroundColor = index;
		this.flagChange(null);
	}
	
	public double getBackgroundColor(){
		return myBackgroundColor;
	}
	
	public Map<Double, String> getPalette(){
		return myColorMap;
	}
	
	public boolean checkValid(double d){
		return myColorMap.containsKey(d);
	}
	
	private void flagChange(SingleColor sc){
		this.setChanged();
		this.notifyObservers(sc);
	}
}
