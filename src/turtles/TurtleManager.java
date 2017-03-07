package turtles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import coordinate.Coordinate;;

public class TurtleManager extends Observable implements TurtleManagerAPI, TurtleManagerCommandAPI{
	
	private HashMap<Double, Turtle> myTurtleMap;
	private List<Double> myActiveTurtles;
	private Double XBOUND;
	private Double YBOUND;
	private static final Double HEIGHT = 25.0;
	private static final Double WIDTH = 25.0;
	
	public TurtleManager(Coordinate bounds) {
		myTurtleMap = new HashMap<Double, Turtle>();
		XBOUND = bounds.getX();
		YBOUND = bounds.getY();
		getTurtle(1.0);
		myActiveTurtles = new ArrayList<Double>();
	}

	@Override
	public List<Turtle> getActiveTurtles() {
		ArrayList<Turtle> apiList = new ArrayList<Turtle>();
		for (Double k : myActiveTurtles) {
			apiList.add(getAPI(k));
		}
		return Collections.unmodifiableList(apiList);
	}

	@Override
	public List<Turtle> allTurtles() {
		ArrayList<Turtle> returnList = new ArrayList<Turtle>();
		for (Double k : myTurtleMap.keySet()) {
			returnList.add(myTurtleMap.get(k));
		}
		return returnList;
	}

	@Override
	public Turtle getAPI(Double id) {
		return myTurtleMap.get(id);
	}
	
	public Turtle getTurtle(Double id) {
		checkForTurtle(id);
		return myTurtleMap.get(id);
	}
	
	private void checkNewInputs() {
		for (Double d: myActiveTurtles) {
			checkForTurtle(d);
		}
	}
	
	private void checkForTurtle(Double k) {
		if (myTurtleMap.get(k) == null) {
			myTurtleMap.put(k, new Turtle(WIDTH, HEIGHT, XBOUND, YBOUND, k));
			this.setChanged();
			this.notifyObservers(myTurtleMap.get(k));
		}
		return;
	}
	
	public List<Double> getActiveTurtleIDs() {
		if (myActiveTurtles.size() == 0) {
			myActiveTurtles.addAll(myTurtleMap.keySet());
		}
		return Collections.unmodifiableList(myActiveTurtles);
	}

	public void addActiveTurtles(Collection<Double> newActives) {
		myActiveTurtles.clear();
		myActiveTurtles.addAll(newActives);
		checkNewInputs();
		return;
	}
}
