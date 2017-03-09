package turtles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import coordinate.Coordinate;
import javafx.scene.image.Image;;

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
			this.flagChange(myTurtleMap.get(k));
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
		flagChange(null);
		return;
	}
	
	public boolean isActive(Double k){
		return myActiveTurtles.contains(k);
	}
	
	public List<Double> getAllTurtleIDs(){
		return new ArrayList<Double>(myTurtleMap.keySet());
	}
	
	private void flagChange(Turtle t){
		this.setChanged();
		this.notifyObservers(t);
	}
	
	public void setPenSize(double d){
		for(Turtle t : getActiveTurtles()){
			t.getPen().setSize(d);
		}
	}
	
	public void setPenState(boolean b, Double id){
		Turtle t = myTurtleMap.get(id);
		t.getPen().setPen(b);
	}
	
	public void setPenColor(double d, Double id){
		Turtle t = myTurtleMap.get(id);
		t.getPen().setColor(d);
	}
	
	public void setImage(Image a){
		for(Turtle t : getActiveTurtles()){
			t.setImage(a);
		}
	}
	
	public void reset(){
		myTurtleMap.clear();
		myActiveTurtles.clear();
	}
}
