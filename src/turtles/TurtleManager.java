package turtles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import coordinate.Coordinate;;

public class TurtleManager implements TurtleManagerAPI, TurtleManagerCommandAPI{
	
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
	public List<TurtleAPI> getActiveTurtles() {
		ArrayList<TurtleAPI> apiList = new ArrayList<TurtleAPI>();
		for (Double k : myActiveTurtles) {
			apiList.add(getAPI(k));
		}
		return Collections.unmodifiableList(apiList);
	}

	@Override
	public List<TurtleAPI> allTurtles() {
		ArrayList<TurtleAPI> returnList = new ArrayList<TurtleAPI>();
		for (Double k : myTurtleMap.keySet()) {
			returnList.add(myTurtleMap.get(k));
		}
		return returnList;
	}

	@Override
	public TurtleAPI getAPI(Double id) {
		return myTurtleMap.get(id);
	}
	
	public Turtle getTurtle(Double id) {
		checkForTurtle(id);
		return myTurtleMap.get(id);
	}
	
	private void checkForTurtle(Double k) {
		if (myTurtleMap.get(k) == null) {
			myTurtleMap.put(k, new Turtle(WIDTH, HEIGHT, XBOUND, YBOUND, k));
		}
		return;
	}
	
	public List<Double> getActiveTurtleIDs() {
		if (myActiveTurtles.size() == 0) {
			myActiveTurtles.addAll(myTurtleMap.keySet());
		}
		return Collections.unmodifiableList(myActiveTurtles);
	}

}
