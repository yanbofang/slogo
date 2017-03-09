package turtles;

import java.util.HashMap;
import java.util.Observable;

import javafx.scene.paint.Color;

public class ColorPalette extends Observable {

	private HashMap<Double, Color> myColorMap;
	private Double myBackgroundColor;
	
	public ColorPalette() {
		myColorMap = new HashMap<Double, Color>();
	}

	public void addColor(Double index, Double r, Double g, Double b) {
		myColorMap.put(index,  Color.rgb(r.intValue(), g.intValue(), b.intValue()));
	}
	
	public void setBackgroundColor(Double index) {
		myBackgroundColor = index;
	}
	
}
