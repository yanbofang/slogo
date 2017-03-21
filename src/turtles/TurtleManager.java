package turtles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import coordinate.Coordinate;
import javafx.scene.image.Image;;

/**
 * This is the manager that tracks all of the turtles, especially the current active turtles. 
 * @author Henry
 *
 */
public class TurtleManager extends Observable implements TurtleManagerAPI, TurtleManagerCommandAPI {

	private HashMap<Double, Turtle> myTurtleMap;
	private List<Double> myActiveTurtles;
	private Double XBOUND;
	private Double YBOUND;
	private static final Double HEIGHT = 25.0;
	private static final Double WIDTH = 25.0;
	private ColorPalette myColors;

	/**
	 * 
	 * @param bounds - bounds of UI viewer
	 */
	public TurtleManager(Coordinate bounds) {
		myTurtleMap = new HashMap<Double, Turtle>();
		XBOUND = bounds.getX();
		YBOUND = bounds.getY();
		myActiveTurtles = new ArrayList<Double>();
		reset();
	}

	/**
	 * @return - list of all active turtles (returns list of actual Turtles)
	 */
	@Override
	public List<Turtle> getActiveTurtles() {
		ArrayList<Turtle> apiList = new ArrayList<Turtle>();
		for (Double k : myActiveTurtles) {
			apiList.add(getAPI(k));
		}
		return Collections.unmodifiableList(apiList);
	}

	/**
	 * @return List of all turtles, active or not 
	 */
	@Override
	public List<Turtle> allTurtles() {
		ArrayList<Turtle> returnList = new ArrayList<Turtle>();
		for (Double k : myTurtleMap.keySet()) {
			returnList.add(myTurtleMap.get(k));
		}
		return returnList;
	}

	/**
	 * @return - API for a specific turtle of give id
	 */
	@Override
	public Turtle getAPI(Double id) {
		return myTurtleMap.get(id);
	}

	/**
	 * @return - Actual turtle for given ID
	 */
	public Turtle getTurtle(Double id) {
		checkForTurtle(id);
		return myTurtleMap.get(id);
	}
	
	private void checkNewInputs() {
		for (Double d : myActiveTurtles) {
			checkForTurtle(d);
		}
	}

	private void checkForTurtle(Double k) {
		if (myTurtleMap.get(k) == null) {
			myTurtleMap.put(k, new Turtle(WIDTH, HEIGHT, XBOUND, YBOUND, k));
			this.flagChange(myTurtleMap.get(k));
		}
		return;
	}

	/**
	 * @return - List of all active turtles, by ID
	 */
	public List<Double> getActiveTurtleIDs() {
		if (myActiveTurtles.size() == 0) {
			myActiveTurtles.addAll(myTurtleMap.keySet());
		}
		return Collections.unmodifiableList(myActiveTurtles);
	}

	/**
	 * adds all turtles of given ID to active turtles (if turtle doesn't exist, it creates it)
	 */
	public void addActiveTurtles(Collection<Double> newActives) {
		myActiveTurtles.clear();
		myActiveTurtles.addAll(newActives);
		checkNewInputs();
		flagChange(null);
		return;
	}

	/**
	 * Checks to see if turtle of ID, k, is active
	 * @param k - ID of turtle
	 * @return - true if turtle active, false if not
	 */
	public boolean isActive(Double k) {
		return myActiveTurtles.contains(k);
	}

	/**
	 * @return - list of all turtles, by ID
	 */
	public List<Double> getAllTurtleIDs() {
		return new ArrayList<Double>(myTurtleMap.keySet());
	}

	private void flagChange(Turtle t) {
		this.setChanged();
		this.notifyObservers(t);
	}

	/**
	 * Sets Pen Size, d, of turtle with ID of id
	 */
	public void setPenSize(double d, Double id) {
		Turtle t = myTurtleMap.get(id);
		t.getPen().setSize(d);
	}

	/**
	 * Sets Pen State ( showing or hiding ), b, of turtle with ID of id
	 */
	public void setPenState(boolean b, Double id) {
		Turtle t = myTurtleMap.get(id);
		t.getPen().setPen(b);
	}

	/**
	 * Sets Pen Color to index, d, of turtle with ID of id
	 */
	public void setPenColor(double d, Double id) {
		if (myColors.checkValid(d)) {
			Turtle t = myTurtleMap.get(id);
			t.getPen().setColor(d);
		}
	}

	/**
	 * Sets Image to index, a, of turtle with ID of id
	 */
	public void setImage(Image a, Double id) {
		Turtle t = myTurtleMap.get(id);
		t.setImage(a);
	}

	/**
	 * Sets background to index, index
	 */
	public void setBackground(Double index) {
		myColors.setBackgroundColor(index);
	}

	/**
	 * Clears all turtles, both active and inactive
	 */
	public void reset() {
		myTurtleMap.clear();
		myActiveTurtles.clear();
	}
	/**
	 * Adds new color to ColorPalette, with a given index and RGB value
	 */
	public void addColor(Double index, Double r, Double g, Double b) {
		myColors.addColor(index, r, g, b);
		setBackground(index);
	}
	
	/**
	 * @param cp - color palette to use
	 */
	public void setPalette(ColorPalette cp) {
		myColors = cp;
	}

}
